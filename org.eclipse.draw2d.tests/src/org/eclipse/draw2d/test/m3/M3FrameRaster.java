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

/**
 * A model framebuffer for damage proofs: every drawing operation of an
 * {@link M3RecordingGraphics} stamps its device bounding box, cut by the clip,
 * with a value derived from the operation, its arguments and the colours and
 * line state. Repairs paint into one persistent raster (the canvas, which keeps
 * its pixels between paints); a fresh full paint into a new raster is the
 * reference. The two are equal exactly when every repair covered every model
 * pixel its changes touched: a stale pixel keeps the value of an operation the
 * fresh paint no longer makes. Pixels a resize exposes start as
 * {@link #EXPOSED}, which no operation writes, so an exposed pixel no repair
 * covered is found as well.
 */
public final class M3FrameRaster {

	/** Value of a pixel exposed by a resize and not painted since. */
	public static final int EXPOSED = 0x5EED_DEAD;

	private int width;
	private int height;
	private int[] pixels;

	/** A raster of the given size, all pixels 0 (nothing painted). */
	public M3FrameRaster(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}

	/** Width in pixels. */
	public int width() {
		return width;
	}

	/** Height in pixels. */
	public int height() {
		return height;
	}

	/**
	 * Resizes like a canvas that keeps its pixels: the overlap keeps its values,
	 * the exposed area becomes {@link #EXPOSED}.
	 */
	public void resize(int w, int h) {
		int[] next = new int[w * h];
		Arrays.fill(next, EXPOSED);
		for (int y = 0; y < Math.min(h, height); y++) {
			System.arraycopy(pixels, y * width, next, y * w, Math.min(w, width));
		}
		width = w;
		height = h;
		pixels = next;
	}

	/** Sets the pixels of a device rectangle to 0 (a repair starts from the cleared region). */
	public void clear(int x, int y, int w, int h) {
		fill(x, y, w, h, 0);
	}

	/** Stamps {@code value} on the device rectangle, cut to the raster. */
	public void fill(int x, int y, int w, int h, int value) {
		int x1 = Math.max(0, x);
		int y1 = Math.max(0, y);
		int x2 = (int) Math.min(width, (long) x + Math.max(0, w));
		int y2 = (int) Math.min(height, (long) y + Math.max(0, h));
		for (int row = y1; row < y2; row++) {
			int at = row * width;
			Arrays.fill(pixels, at + x1, Math.max(at + x1, at + x2), value);
		}
	}

	/** Order-sensitive hash of every pixel and the size. */
	public long hash() {
		long h = 0xcbf29ce484222325L ^ width ^ ((long) height << 32);
		for (int p : pixels) {
			h = (h ^ p) * 0x100000001b3L;
		}
		return h;
	}

	/**
	 * First pixel where this raster differs from {@code other} as "x,y", or null
	 * when they are equal.
	 */
	public String firstDifference(M3FrameRaster other) {
		if (width != other.width || height != other.height) {
			return "size " + width + 'x' + height + " vs " + other.width + 'x' + other.height; //$NON-NLS-1$ //$NON-NLS-2$
		}
		int i = Arrays.mismatch(pixels, other.pixels);
		return i < 0 ? null : (i % width) + "," + (i / width) + (pixels[i] == EXPOSED ? " exposed" : ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
