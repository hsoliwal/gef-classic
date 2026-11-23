/*******************************************************************************
 * Copyright (c) 2026 Contributors to the Synexia Project.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.draw2d.internal;

/**
 * Bounded, reusable dirty rectangles for an opt-in SWT native invalidation.
 * A native invalidation can retain disjoint regions rather than painting the
 * entire bounding envelope. This class never constructs SWT resources and
 * deliberately does not merge regions: the operating system owns its damage
 * region. If a batch is not provably safe and sparse, the caller uses the
 * original bounding-rectangle path instead.
 *
 * @noreference This class is not intended to be referenced by clients.
 */
public final class NativeDamageRegions {
	private static final int MAX_REGIONS = 32;
	private static final int INTS_PER_REGION = 4;
	private final int[] rectangles = new int[MAX_REGIONS * INTS_PER_REGION];
	private int count;
	private boolean ineligible;
	private long sumArea;
	private long minX;
	private long minY;
	private long maxX;
	private long maxY;

	/** Clears logical contents without allocating or clearing retained storage. */
	public void clear() {
		count = 0;
		ineligible = false;
		sumArea = 0;
	}

	/**
	 * Records a root-coordinate rectangle. Invalid/overflowed values refuse the
	 * optimization, but do not change the original Draw2D damage computation.
	 */
	public void add(int x, int y, int width, int height) {
		if (ineligible) {
			return;
		}
		if (width <= 0 || height <= 0 || count == MAX_REGIONS) {
			ineligible = true;
			return;
		}
		long right = (long) x + width;
		long bottom = (long) y + height;
		if (right > Integer.MAX_VALUE || bottom > Integer.MAX_VALUE) {
			ineligible = true;
			return;
		}
		long area = (long) width * height;
		if (Long.MAX_VALUE - sumArea < area) {
			ineligible = true;
			return;
		}
		int offset = count * INTS_PER_REGION;
		rectangles[offset] = x;
		rectangles[offset + 1] = y;
		rectangles[offset + 2] = width;
		rectangles[offset + 3] = height;
		if (count == 0) {
			minX = x;
			minY = y;
			maxX = right;
			maxY = bottom;
		} else {
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
			maxX = Math.max(maxX, right);
			maxY = Math.max(maxY, bottom);
		}
		count++;
		sumArea += area;
	}

	/**
	 * Only split when the exact current damage envelope matches our snapshot,
	 * there are multiple regions, and at least 75% of bounding pixels can be
	 * avoided. This threshold is a conservative policy, not a speedup claim.
	 */
	public boolean shouldSplit(int x, int y, int width, int height) {
		if (ineligible || count < 2 || width <= 0 || height <= 0) {
			return false;
		}
		long unionWidth = maxX - minX;
		long unionHeight = maxY - minY;
		if (unionWidth != width || unionHeight != height || minX != x || minY != y
				|| maxX > Integer.MAX_VALUE || maxY > Integer.MAX_VALUE) {
			return false;
		}
		long envelopeArea = unionWidth * unionHeight;
		return sumArea <= envelopeArea / 4;
	}

	/** Number of rectangles to invalidate; valid only after shouldSplit. */
	public int size() {
		return count;
	}

	public int x(int index) {
		return rectangles[index * INTS_PER_REGION];
	}

	public int y(int index) {
		return rectangles[index * INTS_PER_REGION + 1];
	}

	public int width(int index) {
		return rectangles[index * INTS_PER_REGION + 2];
	}

	public int height(int index) {
		return rectangles[index * INTS_PER_REGION + 3];
	}
}
