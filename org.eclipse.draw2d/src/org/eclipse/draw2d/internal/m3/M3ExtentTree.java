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
package org.eclipse.draw2d.internal.m3;

import java.util.Arrays;

/**
 * Bounding box of a changing set of rectangles: an iterative min/max segment
 * tree over slots (four int lanes: least x, least y, greatest right, greatest
 * bottom). Including, moving or excluding one rectangle costs O(log n); the
 * union of all included rectangles is read at the root in O(1). It replaces
 * the O(n) union that FreeformHelper recomputes after every child move.
 */
public final class M3ExtentTree {

	private int leaves = 1;
	private int[] minX = neutral(2, Integer.MAX_VALUE);
	private int[] minY = neutral(2, Integer.MAX_VALUE);
	private int[] maxR = neutral(2, Integer.MIN_VALUE);
	private int[] maxB = neutral(2, Integer.MIN_VALUE);
	private int included;
	private boolean[] in = new boolean[1];

	private static int[] neutral(int size, int value) {
		int[] a = new int[size];
		Arrays.fill(a, value);
		return a;
	}

	/** Number of included rectangles. */
	public int size() {
		return included;
	}

	/** Whether {@code slot} is included. */
	public boolean contains(int slot) {
		return slot < leaves && in[slot];
	}

	/** Includes or moves the rectangle of {@code slot}, given by its edges. */
	public void set(int slot, int x, int y, int right, int bottom) {
		while (slot >= leaves) {
			grow();
		}
		if (!in[slot]) {
			in[slot] = true;
			included++;
		}
		store(slot, x, y, right, bottom);
	}

	/** Excludes the rectangle of {@code slot}, if included. */
	public void clear(int slot) {
		if (!contains(slot)) {
			return;
		}
		in[slot] = false;
		included--;
		store(slot, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/** Least x of the included rectangles (meaningless when none is included). */
	public int minX() {
		return minX[1];
	}

	/** Least y of the included rectangles. */
	public int minY() {
		return minY[1];
	}

	/** Greatest right edge of the included rectangles. */
	public int maxRight() {
		return maxR[1];
	}

	/** Greatest bottom edge of the included rectangles. */
	public int maxBottom() {
		return maxB[1];
	}

	private void store(int slot, int x, int y, int right, int bottom) {
		int i = slot + leaves;
		minX[i] = x;
		minY[i] = y;
		maxR[i] = right;
		maxB[i] = bottom;
		for (i >>>= 1; i >= 1; i >>>= 1) {
			int l = 2 * i, r = l + 1;
			minX[i] = Math.min(minX[l], minX[r]);
			minY[i] = Math.min(minY[l], minY[r]);
			maxR[i] = Math.max(maxR[l], maxR[r]);
			maxB[i] = Math.max(maxB[l], maxB[r]);
		}
	}

	private void grow() {
		int old = leaves;
		int[] ox = minX, oy = minY, or = maxR, ob = maxB;
		leaves = old * 2;
		minX = neutral(2 * leaves, Integer.MAX_VALUE);
		minY = neutral(2 * leaves, Integer.MAX_VALUE);
		maxR = neutral(2 * leaves, Integer.MIN_VALUE);
		maxB = neutral(2 * leaves, Integer.MIN_VALUE);
		in = Arrays.copyOf(in, leaves);
		System.arraycopy(ox, old, minX, leaves, old);
		System.arraycopy(oy, old, minY, leaves, old);
		System.arraycopy(or, old, maxR, leaves, old);
		System.arraycopy(ob, old, maxB, leaves, old);
		for (int i = leaves - 1; i >= 1; i--) {
			minX[i] = Math.min(minX[2 * i], minX[2 * i + 1]);
			minY[i] = Math.min(minY[2 * i], minY[2 * i + 1]);
			maxR[i] = Math.max(maxR[2 * i], maxR[2 * i + 1]);
			maxB[i] = Math.max(maxB[2 * i], maxB[2 * i + 1]);
		}
	}
}
