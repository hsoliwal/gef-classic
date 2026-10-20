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
	private M3FrameRaster raster;
	private long lastOp;

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

	/**
	 * Same, stamping every drawing operation into {@code raster} (see
	 * {@link M3FrameRaster}); the clip region of the raster is cleared first, as a
	 * repair paints its region from scratch.
	 */
	public M3RecordingGraphics(Rectangle clip, boolean keepLog, M3FrameRaster raster) {
		this(clip, keepLog);
		this.raster = raster;
		if (raster != null) {
			raster.clear(clip.x, clip.y, clip.width, clip.height);
		}
	}

	/**
	 * Model pixel value of the operation just recorded: its own arguments and the
	 * colours and line width, not its place in the order; never 0 or
	 * {@link M3FrameRaster#EXPOSED}.
	 */
	private int pixel() {
		long h = lastOp ^ (state.fg == null ? 7 : state.fg.hashCode()) * 31L ^ (state.bg == null ? 11 : state.bg.hashCode())
				^ Float.floatToIntBits(state.lineWidth);
		int v = (int) (h ^ (h >>> 32)) | 1;
		return v == M3FrameRaster.EXPOSED ? v + 2 : v;
	}

	/** Stamps the device rectangle, cut by the current clip. */
	private void stamp(int x, int y, int w, int h) {
		if (raster != null) {
			stampValue(x, y, w, h, pixel());
		}
	}

	private void stampValue(int x, int y, int w, int h, int value) {
		int cx = state.cx + state.tx;
		int cy = state.cy + state.ty;
		int x1 = Math.max(x, cx);
		int y1 = Math.max(y, cy);
		long x2 = Math.min((long) x + w, (long) cx + state.cw);
		long y2 = Math.min((long) y + h, (long) cy + state.ch);
		if (x2 > x1 && y2 > y1) {
			raster.fill(x1, y1, (int) (x2 - x1), (int) (y2 - y1), value);
		}
	}

	/** Stamps a line of the current width between two device points, cut to the clip first (Liang-Barsky). */
	private void stampLine(int x1, int y1, int x2, int y2) {
		if (raster == null) {
			return;
		}
		int lw = Math.max(1, (int) Math.ceil(state.lineWidth));
		double minX = (double) state.cx + state.tx - lw;
		double minY = (double) state.cy + state.ty - lw;
		double maxX = minX + state.cw + 2.0 * lw;
		double maxY = minY + state.ch + 2.0 * lw;
		double dx = (double) x2 - x1;
		double dy = (double) y2 - y1;
		double[] p = { -dx, dx, -dy, dy };
		double[] q = { x1 - minX, maxX - x1, y1 - minY, maxY - y1 };
		double t0 = 0;
		double t1 = 1;
		for (int i = 0; i < 4; i++) {
			if (p[i] == 0) {
				if (q[i] < 0) {
					return;
				}
			} else {
				double t = q[i] / p[i];
				if (p[i] < 0) {
					t0 = Math.max(t0, t);
				} else {
					t1 = Math.min(t1, t);
				}
			}
		}
		if (t0 > t1) {
			return;
		}
		// the points follow the whole segment's parameter, so a clipped repaint stamps the same pixels
		long steps = (long) Math.max(Math.abs(dx), Math.abs(dy));
		long from = (long) Math.floor(t0 * steps);
		long to = (long) Math.ceil(t1 * steps);
		int value = pixel();
		for (long i = from; i <= to; i++) {
			long x = steps == 0 ? x1 : x1 + Math.round(dx * i / steps);
			long y = steps == 0 ? y1 : y1 + Math.round(dy * i / steps);
			stampValue((int) x - lw / 2, (int) y - lw / 2, lw, lw, value);
		}
	}

	/** Stamps the segments of a polyline, closed for a polygon. */
	private void stampSegments(PointList points, boolean closed) {
		int n = points.size();
		for (int i = 0; i + 1 < n; i++) {
			stampLine(points.getPoint(i).x + state.tx, points.getPoint(i).y + state.ty, points.getPoint(i + 1).x + state.tx,
					points.getPoint(i + 1).y + state.ty);
		}
		if (closed && n > 2) {
			stampLine(points.getPoint(n - 1).x + state.tx, points.getPoint(n - 1).y + state.ty,
					points.getPoint(0).x + state.tx, points.getPoint(0).y + state.ty);
		}
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
		long op = 0xcbf29ce484222325L;
		op = (op ^ code) * 0x100000001b3L;
		op = (op ^ a) * 0x100000001b3L;
		op = (op ^ b) * 0x100000001b3L;
		op = (op ^ c) * 0x100000001b3L;
		lastOp = (op ^ d) * 0x100000001b3L;
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
		int dx = x + state.tx;
		int dy = y + state.ty;
		int lw = Math.max(1, (int) Math.ceil(state.lineWidth));
		switch (code) {
		case 10, 12, 18 -> stamp(dx, dy, w, h);
		case 11, 13, 19 -> stamp(dx - lw, dy - lw, w + 2 * lw + 1, h + 2 * lw + 1);
		case 20, 21 -> stamp(dx, dy, 1, 1);
		default -> {
			// polylines and polygons stamp their segments
		}
		}
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
		stampLine(x1 + state.tx, y1 + state.ty, x2 + state.tx, y2 + state.ty);
	}

	@Override
	public void drawPolyline(PointList points) {
		Rectangle b = points.getBounds();
		draw(15, b.x, b.y, b.width, points.size());
		stampSegments(points, false);
	}

	@Override
	public void drawPolygon(PointList points) {
		Rectangle b = points.getBounds();
		draw(16, b.x, b.y, b.width, points.size());
		stampSegments(points, true);
	}

	@Override
	public void fillPolygon(PointList points) {
		Rectangle b = points.getBounds();
		draw(17, b.x, b.y, b.width, points.size());
		stamp(b.x + state.tx, b.y + state.ty, b.width + 1, b.height + 1);
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
