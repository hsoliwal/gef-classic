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

import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Freeform extent of a FreeformLayer-like host, kept incrementally. Stock
 * FreeformHelper unions, after every child move, the bounds of all children
 * (the freeform extent for FreeformFigure children): a copy of the first
 * child's rectangle, then every nonempty one. Here the nonempty bounds of the
 * trusted plain children live in a {@link M3ExtentTree}; the first child and
 * the children asked live (FreeformFigure children and children whose classes
 * override geometry) are folded in at query time, so a query costs O(live) and
 * a move O(log n). FreeformHelper.setFreeformBounds visits only the
 * FreeformFigure children, in child-list order.
 */
public final class M3FreeformExtent {

	private final M3FigureChildren index;
	private final M3ExtentTree tree = new M3ExtentTree();
	private final M3IntList live = new M3IntList();
	private int[] liveAt = new int[16];

	M3FreeformExtent(M3FigureChildren index) {
		this.index = index;
		for (int s = 0; s < index.slots.slotLimit(); s++) {
			IFigure child = index.slots.figure(s);
			if (child != null) {
				added(child, s);
			}
		}
	}

	private boolean isLive(IFigure child, int slot) {
		return child instanceof FreeformFigure || (index.slots.trust(slot) & M3FigureTrust.BOUNDS) == 0;
	}

	void added(IFigure child, int slot) {
		if (isLive(child, slot)) {
			if (slot >= liveAt.length) {
				liveAt = java.util.Arrays.copyOf(liveAt, Math.max(slot + 1, liveAt.length * 2));
			}
			liveAt[slot] = live.size();
			live.add(slot);
		} else {
			moved(slot, child.getBounds());
		}
	}

	void removed(IFigure child, int slot) {
		if (isLive(child, slot)) {
			int at = liveAt[slot];
			int last = live.removeLast();
			if (last != slot) {
				live.set(at, last);
				liveAt[last] = at;
			}
		} else {
			tree.clear(slot);
		}
	}

	void moved(int slot, Rectangle b) {
		IFigure child = index.slots.figure(slot);
		if (isLive(child, slot)) {
			return;
		}
		if (b.isEmpty()) {
			tree.clear(slot);
		} else {
			tree.set(slot, b.x, b.y, b.x + b.width, b.y + b.height);
		}
	}

	private static Rectangle extentOf(IFigure child) {
		return child instanceof FreeformFigure free ? free.getFreeformExtent() : child.getBounds();
	}

	/** Hook body of FreeformHelper.getFreeformExtent once its cache is empty. */
	public Rectangle extent(IFigure host) {
		Rectangle extent = null;
		if (!index.children.isEmpty()) {
			IFigure first = index.children.get(0);
			extent = extentOf(first).getCopy();
			if (tree.size() > 0) {
				extent.union(tree.minX(), tree.minY(), tree.maxRight() - tree.minX(), tree.maxBottom() - tree.minY());
			}
			live.sortBy(index.slots.labels());
			for (int i = 0; i < live.size(); i++) {
				int slot = live.get(i);
				liveAt[slot] = i;
				IFigure child = index.slots.figure(slot);
				if (child != first) {
					extent.union(extentOf(child));
				}
			}
		}
		Insets insets = host.getInsets();
		if (extent == null) {
			return new Rectangle(0, 0, insets.getWidth(), insets.getHeight());
		}
		host.translateToParent(extent);
		extent.expand(insets);
		return extent;
	}

	/** The children loop of FreeformHelper.setFreeformBounds. */
	public void setFreeformBounds(Rectangle bounds) {
		live.sortBy(index.slots.labels());
		int expected = index.modCount;
		int n = live.size();
		int[] visit = new int[n];
		for (int i = 0; i < n; i++) {
			visit[i] = live.get(i);
			liveAt[visit[i]] = i;
		}
		for (int slot : visit) {
			if (index.slots.figure(slot) instanceof FreeformFigure free) {
				free.setFreeformBounds(bounds);
				index.checkUnmodified(expected);
			}
		}
	}
}
