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

import java.util.Arrays;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.LineAttributes;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.test.utils.GraphicsStub;

/**
 * A Graphics that allocates nothing once warmed up: translation and clip state
 * on int stacks, drawing operations only counted. Used to measure the bytes
 * Draw2d itself allocates per paint, without the allocations of a real
 * Graphics implementation.
 */
public final class M3NullGraphics extends GraphicsStub {

	private int[] stack = new int[6 * 64];
	private int depth;
	private int tx;
	private int ty;
	private int cx;
	private int cy;
	private int cw;
	private int ch;
	private float lineWidth = 1;
	private int lineStyle;
	private Color bg;
	private Color fg;
	private Font font;
	/** Drawing operations since the last {@link #reset(Rectangle)}. */
	public int draws;

	/** Starts a paint with the given device clip. */
	public M3NullGraphics reset(Rectangle clip) {
		depth = 0;
		tx = ty = 0;
		cx = clip.x;
		cy = clip.y;
		cw = clip.width;
		ch = clip.height;
		draws = 0;
		return this;
	}

	@Override
	public void pushState() {
		if (depth + 6 > stack.length) {
			stack = Arrays.copyOf(stack, stack.length * 2);
		}
		stack[depth++] = tx;
		stack[depth++] = ty;
		stack[depth++] = cx;
		stack[depth++] = cy;
		stack[depth++] = cw;
		stack[depth++] = ch;
	}

	@Override
	public void restoreState() {
		tx = stack[depth - 6];
		ty = stack[depth - 5];
		cx = stack[depth - 4];
		cy = stack[depth - 3];
		cw = stack[depth - 2];
		ch = stack[depth - 1];
	}

	@Override
	public void popState() {
		restoreState();
		depth -= 6;
	}

	@Override
	public void translate(int dx, int dy) {
		tx += dx;
		ty += dy;
		cx -= dx;
		cy -= dy;
	}

	@Override
	public void clipRect(Rectangle r) {
		int x1 = Math.max(cx, r.x), y1 = Math.max(cy, r.y);
		int x2 = Math.min(cx + cw, r.x + r.width), y2 = Math.min(cy + ch, r.y + r.height);
		cx = x1;
		cy = y1;
		cw = Math.max(0, x2 - x1);
		ch = Math.max(0, y2 - y1);
	}

	@Override
	public void setClip(Rectangle r) {
		cx = r.x;
		cy = r.y;
		cw = r.width;
		ch = r.height;
	}

	@Override
	public Rectangle getClip(Rectangle rect) {
		rect.setBounds(cx, cy, cw, ch);
		return rect;
	}

	@Override
	public void dispose() {
		// nothing to release
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		draws++;
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		draws++;
	}

	@Override
	public void fillOval(int x, int y, int w, int h) {
		draws++;
	}

	@Override
	public void drawOval(int x, int y, int w, int h) {
		draws++;
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		draws++;
	}

	@Override
	public void drawPolyline(PointList points) {
		draws++;
	}

	@Override
	public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		draws++;
	}

	@Override
	public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		draws++;
	}

	@Override
	public void setBackgroundColor(Color rgb) {
		bg = rgb;
	}

	@Override
	public void setForegroundColor(Color rgb) {
		fg = rgb;
	}

	@Override
	public Color getBackgroundColor() {
		return bg;
	}

	@Override
	public Color getForegroundColor() {
		return fg;
	}

	@Override
	public void setFont(Font f) {
		font = f;
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setLineWidth(int width) {
		lineWidth = width;
	}

	@Override
	public void setLineWidthFloat(float width) {
		lineWidth = width;
	}

	@Override
	public int getLineWidth() {
		return (int) lineWidth;
	}

	@Override
	public float getLineWidthFloat() {
		return lineWidth;
	}

	@Override
	public void setLineStyle(int style) {
		lineStyle = style;
	}

	@Override
	public int getLineStyle() {
		return lineStyle;
	}

	@Override
	public void setLineAttributes(LineAttributes attributes) {
		lineWidth = attributes.width;
		lineStyle = attributes.style;
	}

	@Override
	public void setLineCap(int cap) {
		// state not needed
	}

	@Override
	public void setLineJoin(int join) {
		// state not needed
	}

	@Override
	public void setLineMiterLimit(float miterLimit) {
		// state not needed
	}

	@Override
	public void setAntialias(int value) {
		// state not needed
	}

	@Override
	public void setAlpha(int alpha) {
		// state not needed
	}

	@Override
	public boolean getXORMode() {
		return false;
	}
}
