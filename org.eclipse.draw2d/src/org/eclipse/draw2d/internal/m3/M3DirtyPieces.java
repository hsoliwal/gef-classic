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
import java.util.function.Consumer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The dirty regions of one DeferredUpdateManager as separate pieces. Stock
 * Draw2d unions the dirty regions per figure and then into one damage
 * rectangle, so two small changes far apart (a node moved across the window,
 * the right and bottom strips of a resize) repaint everything between them.
 * Every addDirtyRegion call is also kept here as a piece; at repair time the
 * pieces are clipped up the parent chain exactly like the stock contributions,
 * pieces that overlap or nearly fill their union are merged, and when the
 * merged pieces cover at most 3/4 of the stock damage rectangle each is
 * repaired with its own clip. Listeners still get the stock damage and map.
 * Allocation-free after construction.
 */
public final class M3DirtyPieces {

	/** Pieces per repair beyond which the stock damage rectangle is painted. */
	static final int MAX_PIECES = 16;

	private final IFigure[] figures = new IFigure[MAX_PIECES];
	private final int[] boxes = new int[4 * MAX_PIECES];
	private int count;
	private int frozen;
	private boolean overflow;
	private boolean frozenOverflow;
	private final int[] merged = new int[4 * MAX_PIECES];
	private final Rectangle piece = new Rectangle();

	/** Hook body of DeferredUpdateManager.addDirtyRegion, after its own checks. */
	public void add(IFigure figure, int x, int y, int w, int h) {
		if (count == MAX_PIECES) {
			overflow = true;
			return;
		}
		figures[count] = figure;
		int at = 4 * count++;
		boxes[at] = x;
		boxes[at + 1] = y;
		boxes[at + 2] = w;
		boxes[at + 3] = h;
	}

	/**
	 * Hook body where DeferredUpdateManager.repairDamage takes the dirty regions:
	 * the pieces added so far belong to this repair, later ones to the next.
	 */
	public void begin() {
		frozen = count;
		frozenOverflow = overflow;
		overflow = false;
	}

	/** Drops every piece (the regions were dropped: the window system paints the shown canvas). */
	public void drop() {
		Arrays.fill(figures, 0, count, null);
		count = 0;
		frozen = 0;
		overflow = false;
		frozenOverflow = false;
	}

	/**
	 * Hook body of DeferredUpdateManager.repairDamage before it paints {@code damage}
	 * (the stock union, may be null): when the pieces of this repair, clipped and
	 * merged, cover at most 3/4 of it, paints each with {@code paint} and returns
	 * true; returns false for the stock paint. Consumes the pieces of this repair.
	 */
	public boolean repair(Rectangle damage, Consumer<Rectangle> paint) {
		if (frozenOverflow || damage == null || damage.isEmpty()) {
			consume();
			return false;
		}
		// the stock damage unions a figure's regions before clipping them; pieces clip first, so regions that
		// lie outside their ancestors (strips a shrinking child uncovers beyond its shrinking parent) vanish
		int n = merge(clip());
		consume();
		long area = 0;
		for (int i = 0; i < n; i++) {
			area += (long) merged[4 * i + 2] * merged[4 * i + 3];
		}
		if (4 * area > 3L * damage.width * damage.height) {
			return false;
		}
		M3DeltaGate.pieceRepairs++;
		for (int i = 0; i < n; i++) {
			int at = 4 * i;
			paint.accept(piece.setBounds(merged[at], merged[at + 1], merged[at + 2], merged[at + 3]));
		}
		return true;
	}

	/** Moves the pieces added after {@link #begin()} to the front. */
	private void consume() {
		int rest = count - frozen;
		System.arraycopy(figures, frozen, figures, 0, rest);
		System.arraycopy(boxes, 4 * frozen, boxes, 0, 4 * rest);
		Arrays.fill(figures, rest, count, null);
		count = rest;
		frozen = 0;
		frozenOverflow = false;
	}

	/** Clips the pieces of this repair up their parent chains into {@link #merged}; returns how many remain. */
	private int clip() {
		int n = 0;
		for (int i = 0; i < frozen; i++) {
			int at = 4 * i;
			Rectangle r = piece.setBounds(boxes[at], boxes[at + 1], boxes[at + 2], boxes[at + 3]);
			IFigure figure = figures[i];
			r.intersect(figure.getBounds());
			for (IFigure walker = figure.getParent(); !r.isEmpty() && walker != null; walker = walker.getParent()) {
				walker.translateToParent(r);
				r.intersect(walker.getBounds());
			}
			if (!r.isEmpty()) {
				int to = 4 * n++;
				merged[to] = r.x;
				merged[to + 1] = r.y;
				merged[to + 2] = r.width;
				merged[to + 3] = r.height;
			}
		}
		return n;
	}

	/** Merges pieces that intersect or whose union is at most 1.25 times their areas; returns the new count. */
	private int merge(int n) {
		int i = 0;
		while (i < n) {
			int j = i + 1;
			while (j < n && !joins(i, j)) {
				j++;
			}
			if (j == n) {
				i++;
				continue;
			}
			int a = 4 * i;
			int b = 4 * j;
			int x1 = Math.min(merged[a], merged[b]);
			int y1 = Math.min(merged[a + 1], merged[b + 1]);
			int x2 = Math.max(merged[a] + merged[a + 2], merged[b] + merged[b + 2]);
			int y2 = Math.max(merged[a + 1] + merged[a + 3], merged[b + 1] + merged[b + 3]);
			merged[a] = x1;
			merged[a + 1] = y1;
			merged[a + 2] = x2 - x1;
			merged[a + 3] = y2 - y1;
			n--;
			System.arraycopy(merged, 4 * n, merged, b, 4);
			// the grown piece may now join pieces it was tested against: start over from the first
			i = 0;
		}
		return n;
	}

	private boolean joins(int i, int j) {
		int a = 4 * i;
		int b = 4 * j;
		long ax2 = (long) merged[a] + merged[a + 2];
		long ay2 = (long) merged[a + 1] + merged[a + 3];
		long bx2 = (long) merged[b] + merged[b + 2];
		long by2 = (long) merged[b + 1] + merged[b + 3];
		if (merged[a] < bx2 && merged[b] < ax2 && merged[a + 1] < by2 && merged[b + 1] < ay2) {
			return true;
		}
		long union = (Math.max(ax2, bx2) - Math.min(merged[a], merged[b]))
				* (Math.max(ay2, by2) - Math.min(merged[a + 1], merged[b + 1]));
		long areas = (long) merged[a + 2] * merged[a + 3] + (long) merged[b + 2] * merged[b + 3];
		return 4 * union <= 5 * areas;
	}
}
