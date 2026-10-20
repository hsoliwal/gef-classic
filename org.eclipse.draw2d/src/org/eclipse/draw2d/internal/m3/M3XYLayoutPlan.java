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

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Incremental XYLayout/FreeformLayout: the stock layout sets every child's
 * bounds to its constraint (moved by the origin), and Figure.setBounds does
 * nothing when the bounds are unchanged. This plan remembers, per slot, the
 * constraint object it applied and that object's fields, and lays out only
 * the children for which the stock call could change something: children
 * moved or added since, children whose constraint was replaced or changed in
 * place, children sized by their preferred size, and children whose classes
 * override setBounds. The origin, or the layout manager, changing makes the
 * next pass a full stock pass. Changed children are laid out in child-list
 * order with the stock calls.
 */
public final class M3XYLayoutPlan {

	private final M3FigureChildren index;
	private Object[] ref = new Object[16];
	private int[] cx = new int[16];
	private int[] cy = new int[16];
	private int[] cw = new int[16];
	private int[] ch = new int[16];
	private long[] dirty = new long[1];
	private int marks;
	private Object manager;
	private int ox;
	private int oy;
	private boolean full = true;
	private boolean sweepPending;
	private boolean sweepPass;
	private final M3IntList visit = new M3IntList();
	private final M3IntList dirtyList = new M3IntList();
	private final M3IntList prefList = new M3IntList();
	private final M3IntList special = new M3IntList();
	private final M3IntList check = new M3IntList();
	private long[] seen = new long[16];
	private long epoch;
	private int laidOut;
	private int applying = -1;

	M3XYLayoutPlan(M3FigureChildren index) {
		this.index = index;
		ensure(Math.max(0, index.slots.slotLimit() - 1));
	}

	/** Children laid out by the last pass (for tests and benchmarks). */
	public int laidOut() {
		return laidOut;
	}

	private void ensure(int slot) {
		if (slot >= ref.length) {
			int size = Math.max(slot + 1, ref.length * 2);
			ref = Arrays.copyOf(ref, size);
			cx = Arrays.copyOf(cx, size);
			cy = Arrays.copyOf(cy, size);
			cw = Arrays.copyOf(cw, size);
			ch = Arrays.copyOf(ch, size);
		}
		if (slot >>> 6 >= dirty.length) {
			dirty = Arrays.copyOf(dirty, Math.max((slot >>> 6) + 1, dirty.length * 2));
		}
	}

	private boolean isDirty(int slot) {
		return (dirty[slot >>> 6] & (1L << slot)) != 0;
	}

	/** Marks the slot for the next pass. */
	void mark(int slot) {
		ensure(slot);
		if (!isDirty(slot)) {
			dirty[slot >>> 6] |= 1L << slot;
			dirtyList.add(slot);
			if (slot != applying) {
				marks++;
			}
		}
	}

	void added(int slot) {
		ensure(slot);
		ref[slot] = null;
		mark(slot);
	}

	void removed(int slot) {
		ensure(slot);
		ref[slot] = null;
		dirty[slot >>> 6] &= ~(1L << slot);
	}

	void moved(int slot) {
		mark(slot);
	}

	/**
	 * The host was invalidated directly ({@link M3FigureChildren#invalidated}):
	 * under the attributed policy the next pass, or the rest of the current one,
	 * compares every constraint, since the stock loop would pick up constraints
	 * changed in place.
	 */
	void unexplained() {
		if (M3ViewportMode.CONSTRAINTS != M3ViewportMode.TRUSTED) {
			sweepPending = true;
			marks++;
		}
	}

	/** Hook body of AbstractConstraintLayout.setConstraint for a child of the host. */
	public void constraintChanged(IFigure child) {
		int slot = index.slots.slotOf(child);
		if (slot >= 0) {
			mark(slot);
		}
	}

	/**
	 * Hook body of XYLayout.layout; returns false (stock loop) for subclasses of
	 * XYLayout other than FreeformLayout.
	 */
	public boolean layout(XYLayout layout) {
		if (layout.getClass() != XYLayout.class && layout.getClass() != FreeformLayout.class) {
			M3DeltaGate.fullLayouts++;
			return false;
		}
		Point offset = layout.getOrigin(index.host);
		laidOut = 0;
		if (full || manager != layout || offset.x != ox || offset.y != oy) {
			manager = layout;
			ox = offset.x;
			oy = offset.y;
			full = false;
			sweepPending = false;
			M3DeltaGate.fullLayouts++;
			for (IFigure f : index.children) {
				apply(layout, f, index.slots.slotOf(f), offset);
			}
			return true;
		}
		sweepPass = sweepPending;
		sweepPending = false;
		long cursor = Long.MIN_VALUE;
		collect(cursor);
		for (int i = 0; i < visit.size(); i++) {
			int slot = visit.get(i);
			cursor = index.slots.labels()[slot];
			int before = marks;
			apply(layout, index.slots.figure(slot), slot, offset);
			if (marks != before) {
				collect(cursor);
				i = -1;
			}
		}
		return true;
	}

	private void collect(long cursor) {
		if (M3ViewportMode.CONSTRAINTS == M3ViewportMode.EXACT || sweepPass || sweepPending) {
			M3DeltaGate.constraintSweeps++;
			sweep(cursor, visit);
			return;
		}
		collectChanged(cursor);
	}

	/** Every child that needs a stock call, found by comparing every snapshot. */
	private void sweep(long cursor, M3IntList out) {
		out.clear();
		long[] labels = index.slots.labels();
		for (int s = 0; s < index.slots.slotLimit(); s++) {
			if (index.slots.figure(s) != null && labels[s] > cursor && needs(s)) {
				out.add(s);
			}
		}
		out.sortBy(labels);
	}

	/**
	 * Under the trusted policy, and the attributed one while the host was not
	 * invalidated directly (no constraint changed in place): only the marked
	 * children, the children sized by their preferred size and the children whose
	 * classes override setBounds; O(changed) instead of a sweep over all slots.
	 */
	private void collectChanged(long cursor) {
		visit.clear();
		epoch++;
		long[] labels = index.slots.labels();
		int kept = 0;
		for (int i = 0; i < dirtyList.size(); i++) {
			int s = dirtyList.get(i);
			if (isDirty(s) && index.slots.figure(s) != null) {
				dirtyList.set(kept++, s);
				offer(s, cursor, labels);
			}
		}
		truncate(dirtyList, kept);
		kept = 0;
		for (int i = 0; i < prefList.size(); i++) {
			int s = prefList.get(i);
			if (index.slots.figure(s) != null && ref[s] != null && (cw[s] == -1 || ch[s] == -1)) {
				prefList.set(kept++, s);
				offer(s, cursor, labels);
			}
		}
		truncate(prefList, kept);
		special.clear();
		index.addSpecial(special, M3FigureTrust.BOUNDS);
		for (int i = 0; i < special.size(); i++) {
			offer(special.get(i), cursor, labels);
		}
		visit.sortBy(labels);
		if (M3ViewportMode.VERIFY) {
			sweep(cursor, check);
			if (check.size() != visit.size()) {
				throw new IllegalStateException("draw2d viewport: an XY constraint of " + index.host //$NON-NLS-1$
						+ " was changed in place without a direct revalidate of the container (constraint policy " //$NON-NLS-1$
						+ M3ViewportMode.POLICY_NAMES[M3ViewportMode.CONSTRAINTS] + ")"); //$NON-NLS-1$
			}
		}
	}

	private void offer(int s, long cursor, long[] labels) {
		if (s >= seen.length) {
			seen = Arrays.copyOf(seen, Math.max(s + 1, seen.length * 2));
		}
		if (seen[s] != epoch && labels[s] > cursor) {
			seen[s] = epoch;
			visit.add(s);
		}
	}

	private static void truncate(M3IntList list, int size) {
		while (list.size() > size) {
			list.removeLast();
		}
	}

	private boolean needs(int s) {
		if (isDirty(s) || (index.slots.trust(s) & M3FigureTrust.BOUNDS) == 0) {
			return true;
		}
		Object r = ref[s];
		if (r instanceof Rectangle c) {
			return c.x != cx[s] || c.y != cy[s] || c.width != cw[s] || c.height != ch[s] || cw[s] == -1
					|| ch[s] == -1;
		}
		return false;
	}

	/** The body of the stock loop for one child, recording what it applied. */
	private void apply(XYLayout layout, IFigure f, int slot, Point offset) {
		Rectangle bounds = (Rectangle) layout.getConstraint(f);
		ref[slot] = bounds;
		if (bounds != null) {
			cx[slot] = bounds.x;
			cy[slot] = bounds.y;
			cw[slot] = bounds.width;
			ch[slot] = bounds.height;
			if (bounds.width == -1 || bounds.height == -1) {
				prefList.add(slot);
			}
		}
		if (bounds == null) {
			clear(slot);
			return;
		}
		if (bounds.width == -1 || bounds.height == -1) {
			Dimension preferredSize = f.getPreferredSize(bounds.width, bounds.height);
			bounds = bounds.getCopy();
			if (bounds.width == -1) {
				bounds.width = preferredSize.width;
			}
			if (bounds.height == -1) {
				bounds.height = preferredSize.height;
			}
		}
		bounds = bounds.getTranslated(offset);
		applying = slot;
		try {
			f.setBounds(bounds);
		} finally {
			applying = -1;
		}
		laidOut++;
		clear(slot);
	}

	private void clear(int slot) {
		dirty[slot >>> 6] &= ~(1L << slot);
	}
}
