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
import java.util.List;

import org.eclipse.draw2d.IFigure;

/**
 * Slot registry of a container's children: an int slot per child (reused after
 * removal), an identity-keyed open-addressing table from figure to slot, and
 * an order label per slot that sorts like the child list (list labelling with
 * gaps: an append or insert takes a free label between its neighbours; when a
 * gap is used up, a range of neighbours is relabelled, {@link #relabelAround}). Paint and hit-test
 * candidates found by the spatial grid are put back in z-order by sorting
 * their labels.
 */
public final class M3ChildSlots {

	/** Label distance between neighbours after a relabel. */
	static final long GAP = 1L << 10;
	/** Bits of a label. */
	static final int BITS = 38;
	/** Labels stay below this bound, so a label and a slot pack into one long. */
	static final long LIMIT = 1L << BITS;
	/** Labels an aligned range of size 2^i may hold before it is relabelled: floor(1.5^i). */
	private static final long[] CAPACITY = new long[BITS + 1];

	static {
		double c = 1;
		for (int i = 0; i < BITS; i++) {
			CAPACITY[i] = (long) c;
			c *= 1.5;
		}
		// the whole label space takes any count (more than 1.5^38 children only lose the amortized bound)
		CAPACITY[BITS] = Long.MAX_VALUE;
	}

	private Object[] keys = new Object[16];
	private int[] values = new int[16];
	private int keyCount;

	private IFigure[] figures = new IFigure[0];
	private long[] labels = new long[0];
	private int[] trust = new int[0];
	private final M3IntList free = new M3IntList();
	private int top;
	private int relabels;

	/** Number of registered children. */
	public int size() {
		return keyCount;
	}

	/** One past the highest slot ever used. */
	public int slotLimit() {
		return top;
	}

	/** How often the labels were rebuilt. */
	public int relabels() {
		return relabels;
	}

	/** Slot of {@code figure}, or -1. */
	public int slotOf(IFigure figure) {
		int mask = keys.length - 1;
		for (int i = hash(figure, mask);; i = (i + 1) & mask) {
			Object k = keys[i];
			if (k == null) {
				return -1;
			}
			if (k == figure) {
				return values[i];
			}
		}
	}

	/** Figure in {@code slot}, or null for a free slot. */
	public IFigure figure(int slot) {
		return figures[slot];
	}

	/** The label lane, indexed by slot. */
	public long[] labels() {
		return labels;
	}

	/** Trust bits ({@link M3FigureTrust}) of the child in {@code slot}. */
	public int trust(int slot) {
		return trust[slot];
	}

	/** Registers {@code figure} with the given label and returns its new slot. */
	public int add(IFigure figure, long label) {
		int slot;
		if (free.size() > 0) {
			slot = free.removeLast();
		} else {
			slot = top++;
			if (slot == figures.length) {
				int size = Math.max(16, slot * 2);
				figures = Arrays.copyOf(figures, size);
				labels = Arrays.copyOf(labels, size);
				trust = Arrays.copyOf(trust, size);
			}
		}
		figures[slot] = figure;
		labels[slot] = label;
		trust[slot] = M3FigureTrust.of(figure);
		put(figure, slot);
		return slot;
	}

	/** Unregisters {@code figure}; returns its former slot or -1. */
	public int remove(IFigure figure) {
		int mask = keys.length - 1;
		int i = hash(figure, mask);
		while (keys[i] != figure) {
			if (keys[i] == null) {
				return -1;
			}
			i = (i + 1) & mask;
		}
		int slot = values[i];
		deleteAt(i);
		figures[slot] = null;
		free.add(slot);
		return slot;
	}

	/**
	 * Label for the child just placed at {@code index} of {@code children}: the
	 * middle of the gap between its neighbours' labels, or -1 when the gap is
	 * used up ({@link #relabelAround}). Every other child must be registered.
	 */
	public long labelAt(List<? extends IFigure> children, int index) {
		long before = index == 0 ? 0 : labels[slotOf(children.get(index - 1))];
		long after = index == children.size() - 1 ? before + 2 * GAP : labels[slotOf(children.get(index + 1))];
		if (after - before >= 2 && after < LIMIT) {
			return before + (after - before) / 2;
		}
		return -1;
	}

	/**
	 * Relabels the children around the child just placed at {@code index}
	 * (registered with any label) when {@link #labelAt} found no free label:
	 * the smallest aligned label range of size 2^i around its left neighbour's
	 * label that holds at most T^i labels with the new child counted is
	 * relabelled evenly (order maintenance by list labelling, Bender, Cole,
	 * Demaine, Farach-Colton and Zito 2002, T = 1.5): amortized O(log n) labels
	 * rewritten per insert instead of the whole list. Returns how many labels
	 * were rewritten.
	 */
	public int relabelAround(List<? extends IFigure> children, int index) {
		relabels++;
		M3DeltaGate.relabels++;
		int n = children.size();
		long anchor = index > 0 ? labels[slotOf(children.get(index - 1))] : 0;
		int lo = index;
		int hi = index;
		for (int level = 1;; level++) {
			long size = 1L << level;
			long base = anchor & -size;
			long end = base + size;
			while (lo > 0 && labels[slotOf(children.get(lo - 1))] >= base) {
				lo--;
			}
			while (hi < n - 1 && labels[slotOf(children.get(hi + 1))] < end) {
				hi++;
			}
			int count = hi - lo + 1;
			if (count <= CAPACITY[level]) {
				for (int j = 0; j < count; j++) {
					labels[slotOf(children.get(lo + j))] = base + (j + 1) * size / (count + 1);
				}
				M3DeltaGate.relabeledLabels += count;
				return count;
			}
		}
	}

	private static int hash(Object key, int mask) {
		return (System.identityHashCode(key) * 0x9E3779B9 >>> 7) & mask;
	}

	private void put(IFigure figure, int slot) {
		if (2 * (keyCount + 1) > keys.length) {
			Object[] oldKeys = keys;
			int[] oldValues = values;
			keys = new Object[oldKeys.length * 2];
			values = new int[oldKeys.length * 2];
			for (int i = 0; i < oldKeys.length; i++) {
				if (oldKeys[i] != null) {
					insert(oldKeys[i], oldValues[i]);
				}
			}
		}
		insert(figure, slot);
		keyCount++;
	}

	private void insert(Object key, int value) {
		int mask = keys.length - 1;
		int i = hash(key, mask);
		while (keys[i] != null) {
			i = (i + 1) & mask;
		}
		keys[i] = key;
		values[i] = value;
	}

	/** Linear-probing deletion with backward shift: no tombstones. */
	private void deleteAt(int hole) {
		int mask = keys.length - 1;
		keys[hole] = null;
		keyCount--;
		for (int i = (hole + 1) & mask; keys[i] != null; i = (i + 1) & mask) {
			int home = hash(keys[i], mask);
			boolean movable = hole <= i ? home <= hole || home > i : home <= hole && home > i;
			if (movable) {
				keys[hole] = keys[i];
				values[hole] = values[i];
				keys[i] = null;
				hole = i;
			}
		}
	}
}
