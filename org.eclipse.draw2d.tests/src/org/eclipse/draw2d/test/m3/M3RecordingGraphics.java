/*******************************************************************************
 * Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0, or (at your option) the Eclipse
 * Public License 2.0, which are available at
 * https://www.apache.org/licenses/LICENSE-2.0 and
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
 *
 * Contributors:
 *     Hitesh Soliwal (Synexia) - initial API and implementation
 *******************************************************************************/
package org.eclipse.draw2d.test.m3;

import java.util.ArrayDeque;
import java.util.Deque;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.LineAttributes;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.test.utils.GraphicsStub;

/**
 * A headless Graphics that keeps the translation and clip state like
 * SWTGraphics and records every drawing and state operation. The record is an
 * order-sensitive 64-bit hash plus an operation count, and optionally a
 * readable log, so two Draw2d builds painting the same figures can be compared
 * operation by operation without a display. Queries (getClip and friends) are
 * not recorded: they produce no pixels.
 */
public class M3RecordingGraphics extends GraphicsStub {

	private static final class State {
		int tx;
		int ty;
		int cx;
		int cy;
		int cw;
		int ch;
		float lineWidth = 1;
		int lineStyle;
		Color bg;
		Color fg;
		Font font;

		State copy() {
			State s = new State();
			s.tx = tx;
			s.ty = ty;
			s.cx = cx;
			s.cy = cy;
			s.cw = cw;
			s.ch = ch;
			s.lineWidth = lineWidth;
			s.lineStyle = lineStyle;
			s.bg = bg;
			s.fg = fg;
			s.font = font;
			return s;
		}
	}

	private final Deque<State> stack = new ArrayDeque<>();
	private State state = new State();
	private final StringBuilder log;
	private long hash = 0xcbf29ce484222325L;
	private int ops;

	/**
	 * @param clip the device clip, in device coordinates
	 * @param keepLog whether to keep a readable log as well as the hash
	 */
	public M3RecordingGraphics(Rectangle clip, boolean keepLog) {
		state.cx = clip.x;
		state.cy = clip.y;
		state.cw = clip.width;
		state.ch = clip.height;
		log = keepLog ? new StringBuilder() : null;
	}

	/** Order-sensitive hash of every recorded operation. */
	public long hash() {
		return hash;
	}

	/** Number of recorded operations. */
	public int operations() {
		return ops;
	}

	/** The readable log, or an empty string when the log is not kept. */
	public String log() {
		return log == null ? "" : log.toString(); //$NON-NLS-1$
	}

	private void record(int code, int a, int b, int c, int d) {
		ops++;
		long h = hash;
		h = (h ^ code) * 0x100000001b3L;
		h = (h ^ a) * 0x100000001b3L;
		h = (h ^ b) * 0x100000001b3L;
		h = (h ^ c) * 0x100000001b3L;
		h = (h ^ d) * 0x100000001b3L;
		hash = h;
		if (log != null) {
			log.append(code).append(' ').append(a).append(' ').append(b).append(' ').append(c).append(' ').append(d)
					.append('\n');
		}
	}

	private void draw(int code, int x, int y, int w, int h) {
		record(code, x + state.tx, y + state.ty, w, h);
	}

	@Override
	public void clipRect(Rectangle r) {
		int x1 = Math.max(state.cx, r.x);
		int y1 = Math.max(state.cy, r.y);
		int x2 = Math.min(state.cx + state.cw, r.x + r.width);
		int y2 = Math.min(state.cy + state.ch, r.y + r.height);
		state.cx = x1;
		state.cy = y1;
		state.cw = Math.max(0, x2 - x1);
		state.ch = Math.max(0, y2 - y1);
		record(1, state.cx + state.tx, state.cy + state.ty, state.cw, state.ch);
	}

	@Override
	public void setClip(Rectangle r) {
		state.cx = r.x;
		state.cy = r.y;
		state.cw = r.width;
		state.ch = r.height;
		record(2, r.x + state.tx, r.y + state.ty, r.width, r.height);
	}

	@Override
	public Rectangle getClip(Rectangle rect) {
		rect.setBounds(state.cx, state.cy, state.cw, state.ch);
		return rect;
	}

	@Override
	public void translate(int dx, int dy) {
		state.tx += dx;
		state.ty += dy;
		state.cx -= dx;
		state.cy -= dy;
		record(3, dx, dy, 0, 0);
	}

	@Override
	public void pushState() {
		stack.push(state.copy());
		record(4, 0, 0, 0, 0);
	}

	@Override
	public void popState() {
		state = stack.pop();
		record(5, 0, 0, 0, 0);
	}

	@Override
	public void restoreState() {
		state = stack.peek().copy();
		record(6, 0, 0, 0, 0);
	}

	@Override
	public void dispose() {
		record(7, 0, 0, 0, 0);
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		draw(10, x, y, width, height);
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		draw(11, x, y, width, height);
	}

	@Override
	public void fillOval(int x, int y, int w, int h) {
		draw(12, x, y, w, h);
	}

	@Override
	public void drawOval(int x, int y, int w, int h) {
		draw(13, x, y, w, h);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		record(14, x1 + state.tx, y1 + state.ty, x2 + state.tx, y2 + state.ty);
	}

	@Override
	public void drawPolyline(PointList points) {
		Rectangle b = points.getBounds();
		draw(15, b.x, b.y, b.width, points.size());
	}

	@Override
	public void drawPolygon(PointList points) {
		Rectangle b = points.getBounds();
		draw(16, b.x, b.y, b.width, points.size());
	}

	@Override
	public void fillPolygon(PointList points) {
		Rectangle b = points.getBounds();
		draw(17, b.x, b.y, b.width, points.size());
	}

	@Override
	public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		draw(18, r.x, r.y, r.width, r.height);
	}

	@Override
	public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		draw(19, r.x, r.y, r.width, r.height);
	}

	@Override
	public void drawString(String s, int x, int y) {
		draw(20, x, y, s.hashCode(), 0);
	}

	@Override
	public void drawText(String s, int x, int y) {
		draw(21, x, y, s.hashCode(), 0);
	}

	@Override
	public void setBackgroundColor(Color rgb) {
		state.bg = rgb;
		record(30, rgb == null ? -1 : rgb.hashCode(), 0, 0, 0);
	}

	@Override
	public void setForegroundColor(Color rgb) {
		state.fg = rgb;
		record(31, rgb == null ? -1 : rgb.hashCode(), 0, 0, 0);
	}

	@Override
	public Color getBackgroundColor() {
		return state.bg;
	}

	@Override
	public Color getForegroundColor() {
		return state.fg;
	}

	@Override
	public void setFont(Font f) {
		state.font = f;
		record(32, f == null ? -1 : 1, 0, 0, 0);
	}

	@Override
	public Font getFont() {
		return state.font;
	}

	@Override
	public void setLineWidth(int width) {
		state.lineWidth = width;
		record(33, width, 0, 0, 0);
	}

	@Override
	public void setLineWidthFloat(float width) {
		state.lineWidth = width;
		record(34, Float.floatToIntBits(width), 0, 0, 0);
	}

	@Override
	public int getLineWidth() {
		return (int) state.lineWidth;
	}

	@Override
	public float getLineWidthFloat() {
		return state.lineWidth;
	}

	@Override
	public void setLineStyle(int style) {
		state.lineStyle = style;
		record(35, style, 0, 0, 0);
	}

	@Override
	public int getLineStyle() {
		return state.lineStyle;
	}

	@Override
	public void setLineCap(int cap) {
		record(36, cap, 0, 0, 0);
	}

	@Override
	public void setLineJoin(int join) {
		record(37, join, 0, 0, 0);
	}

	@Override
	public void setLineMiterLimit(float miterLimit) {
		record(38, Float.floatToIntBits(miterLimit), 0, 0, 0);
	}

	@Override
	public void setLineDash(int[] dash) {
		record(39, dash == null ? -1 : dash.length, 0, 0, 0);
	}

	@Override
	public void setLineDash(float[] dash) {
		record(40, dash == null ? -1 : dash.length, 0, 0, 0);
	}

	@Override
	public void setLineDashOffset(float value) {
		record(41, Float.floatToIntBits(value), 0, 0, 0);
	}

	@Override
	public void setLineAttributes(LineAttributes attributes) {
		state.lineWidth = attributes.width;
		state.lineStyle = attributes.style;
		record(46, Float.floatToIntBits(attributes.width), attributes.style, attributes.cap, attributes.join);
	}

	@Override
	public void setAntialias(int value) {
		record(42, value, 0, 0, 0);
	}

	@Override
	public void setAlpha(int alpha) {
		record(43, alpha, 0, 0, 0);
	}

	@Override
	public void scale(double amount) {
		record(44, (int) Double.doubleToLongBits(amount), (int) (Double.doubleToLongBits(amount) >>> 32), 0, 0);
	}

	@Override
	public void setXORMode(boolean b) {
		record(45, b ? 1 : 0, 0, 0, 0);
	}

	@Override
	public boolean getXORMode() {
		return false;
	}
}
