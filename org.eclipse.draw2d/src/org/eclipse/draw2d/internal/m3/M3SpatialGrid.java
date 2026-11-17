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
 * Uniform-grid spatial hash over integer rectangles held in slot lanes (x, y,
 * width, height as int arrays): rectangle and point queries cost the cells
 * they touch plus the rectangles found, and moving a rectangle costs the cells
 * it spans. No object is created per rectangle or per query.
 * <p>
 * Answers follow Draw2d's {@code Rectangle.intersects(Rectangle)} and
 * {@code Rectangle.contains(int, int)} exactly, including int overflow:
 * rectangles whose right or bottom edge overflows int, and rectangles spanning
 * more than {@link #LARGE_SPAN} cells, are kept in an always-tested list; empty
 * rectangles that cannot match either predicate are kept out of the grid.
 * </p>
 */
public final class M3SpatialGrid {

	/** A rectangle spanning more cells than this is tested on every query instead. */
	public static final int LARGE_SPAN = 64;

	static final byte NONE = 0;
	static final byte CELLS = 1;
	static final byte ALWAYS = 2;
	static final byte NEVER = 3;

	private final int shift;
	private int[] xs = new int[0];
	private int[] ys = new int[0];
	private int[] ws = new int[0];
	private int[] hs = new int[0];
	private byte[] kind = new byte[0];
	private int[] slotEntry = new int[0];
	private int[] stamp = new int[0];
	private int epoch;
	private int count;

	private final M3IntList always = new M3IntList();
	private int[] alwaysAt = new int[0];

	private long[] cellKey = new long[16];
	private int[] cellHead = new int[16];
	private int cellsUsed;

	private int[] entrySlot = new int[16];
	private int[] entryCell = new int[16];
	private int[] entryNext = new int[16];
	private int[] entryPrev = new int[16];
	private int[] entrySlotNext = new int[16];
	private int entryTop;
	private int entryFree = -1;

	private final M3IntList result = new M3IntList();

	/** @param shift log2 of the cell size */
	public M3SpatialGrid(int shift) {
		this.shift = shift;
		Arrays.fill(cellHead, -1);
	}

	/** log2 of the cell size. */
	public int shift() {
		return shift;
	}

	/** Number of rectangles held. */
	public int size() {
		return count;
	}

	/** Number of rectangles tested on every query. */
	public int alwaysCount() {
		return always.size();
	}

	/** Slots found by the last query, in no particular order. */
	public M3IntList result() {
		return result;
	}

	/** Whether {@code slot} holds a rectangle. */
	public boolean holds(int slot) {
		return slot < kind.length && kind[slot] != NONE;
	}

	/** X of the slot's rectangle. */
	public int x(int slot) {
		return xs[slot];
	}

	/** Y of the slot's rectangle. */
	public int y(int slot) {
		return ys[slot];
	}

	/** Width of the slot's rectangle. */
	public int width(int slot) {
		return ws[slot];
	}

	/** Height of the slot's rectangle. */
	public int height(int slot) {
		return hs[slot];
	}

	/** Inserts or moves the rectangle of {@code slot}. */
	public void put(int slot, int x, int y, int w, int h) {
		ensureSlot(slot);
		if (kind[slot] != NONE) {
			if (xs[slot] == x && ys[slot] == y && ws[slot] == w && hs[slot] == h) {
				return;
			}
			unlink(slot);
		} else {
			count++;
		}
		xs[slot] = x;
		ys[slot] = y;
		ws[slot] = w;
		hs[slot] = h;
		link(slot);
	}

	/** Removes the rectangle of {@code slot}, if any. */
	public void remove(int slot) {
		if (!holds(slot)) {
			return;
		}
		unlink(slot);
		kind[slot] = NONE;
		count--;
	}

	private void link(int slot) {
		int x = xs[slot], y = ys[slot], w = ws[slot], h = hs[slot];
		long right = (long) x + w, bottom = (long) y + h;
		if (right != (int) right || bottom != (int) bottom) {
			linkAlways(slot);
		} else if (w <= 0 || h <= 0) {
			kind[slot] = NEVER;
		} else {
			int cx0 = x >> shift, cx1 = (x + w - 1) >> shift, cy0 = y >> shift, cy1 = (y + h - 1) >> shift;
			if ((long) (cx1 - cx0 + 1) * (cy1 - cy0 + 1) > LARGE_SPAN) {
				linkAlways(slot);
				return;
			}
			kind[slot] = CELLS;
			slotEntry[slot] = -1;
			for (int cy = cy0; cy <= cy1; cy++) {
				for (int cx = cx0; cx <= cx1; cx++) {
					addEntry(slot, cell(key(cx, cy), true));
				}
			}
		}
	}

	private void linkAlways(int slot) {
		kind[slot] = ALWAYS;
		alwaysAt[slot] = always.size();
		always.add(slot);
	}

	private void unlink(int slot) {
		if (kind[slot] == ALWAYS) {
			int at = alwaysAt[slot];
			int last = always.removeLast();
			if (last != slot) {
				always.set(at, last);
				alwaysAt[last] = at;
			}
		} else if (kind[slot] == CELLS) {
			for (int e = slotEntry[slot]; e >= 0;) {
				int next = entrySlotNext[e];
				removeEntry(e);
				e = next;
			}
		}
	}

	private void addEntry(int slot, int cell) {
		int e;
		if (entryFree >= 0) {
			e = entryFree;
			entryFree = entryNext[e];
		} else {
			if (entryTop == entrySlot.length) {
				growEntries();
			}
			e = entryTop++;
		}
		entrySlot[e] = slot;
		entryCell[e] = cell;
		entryPrev[e] = -1;
		entryNext[e] = cellHead[cell];
		if (cellHead[cell] >= 0) {
			entryPrev[cellHead[cell]] = e;
		}
		cellHead[cell] = e;
		entrySlotNext[e] = slotEntry[slot];
		slotEntry[slot] = e;
	}

	private void removeEntry(int e) {
		int prev = entryPrev[e], next = entryNext[e];
		if (prev >= 0) {
			entryNext[prev] = next;
		} else {
			cellHead[entryCell[e]] = next;
		}
		if (next >= 0) {
			entryPrev[next] = prev;
		}
		entryNext[e] = entryFree;
		entryFree = e;
	}

	static long key(int cx, int cy) {
		return ((long) cx << 32) | (cy & 0xffffffffL);
	}

	/** Index of the cell with {@code key}; -1 when absent and not {@code create}. */
	private int cell(long key, boolean create) {
		int mask = cellKey.length - 1;
		long mixed = key * 0x9E3779B97F4A7C15L;
		for (int i = (int) (mixed ^ (mixed >>> 29)) & mask;; i = (i + 1) & mask) {
			if (!used(i)) {
				if (!create) {
					return -1;
				}
				if (2 * (cellsUsed + 1) > cellKey.length) {
					growCells();
					return cell(key, true);
				}
				cellKey[i] = key;
				cellHead[i] = -1;
				cellsUsed++;
				markUsed(i);
				return i;
			}
			if (cellKey[i] == key) {
				return i;
			}
		}
	}

	private long[] usedBits = new long[1];

	private boolean used(int i) {
		return (usedBits[i >>> 6] & (1L << i)) != 0;
	}

	private void markUsed(int i) {
		usedBits[i >>> 6] |= 1L << i;
	}

	private void growCells() {
		long[] oldKey = cellKey;
		int[] oldHead = cellHead;
		long[] oldUsed = usedBits;
		int size = oldKey.length * 2;
		cellKey = new long[size];
		cellHead = new int[size];
		Arrays.fill(cellHead, -1);
		usedBits = new long[Math.max(1, size >>> 6)];
		cellsUsed = 0;
		for (int i = 0; i < oldKey.length; i++) {
			if ((oldUsed[i >>> 6] & (1L << i)) != 0 && oldHead[i] >= 0) {
				int c = cell(oldKey[i], true);
				cellHead[c] = oldHead[i];
				for (int e = oldHead[i]; e >= 0; e = entryNext[e]) {
					entryCell[e] = c;
				}
			}
		}
	}

	private void growEntries() {
		int size = entrySlot.length * 2;
		entrySlot = Arrays.copyOf(entrySlot, size);
		entryCell = Arrays.copyOf(entryCell, size);
		entryNext = Arrays.copyOf(entryNext, size);
		entryPrev = Arrays.copyOf(entryPrev, size);
		entrySlotNext = Arrays.copyOf(entrySlotNext, size);
	}

	private void ensureSlot(int slot) {
		if (slot < kind.length) {
			return;
		}
		int size = Math.max(slot + 1, kind.length * 2);
		xs = Arrays.copyOf(xs, size);
		ys = Arrays.copyOf(ys, size);
		ws = Arrays.copyOf(ws, size);
		hs = Arrays.copyOf(hs, size);
		kind = Arrays.copyOf(kind, size);
		slotEntry = Arrays.copyOf(slotEntry, size);
		stamp = Arrays.copyOf(stamp, size);
		alwaysAt = Arrays.copyOf(alwaysAt, size);
	}

	/** Exactly {@code Rectangle.intersects}: both rectangles nonempty and overlapping. */
	static boolean intersects(int x, int y, int w, int h, int qx, int qy, int qw, int qh) {
		return w > 0 && h > 0 && qw > 0 && qh > 0 && x < qx + qw && qx < x + w && y < qy + qh && qy < y + h;
	}

	/** Exactly {@code Rectangle.contains(int, int)}. */
	static boolean contains(int x, int y, int w, int h, int px, int py) {
		return py >= y && py < y + h && px >= x && px < x + w;
	}

	/** Collects into {@link #result()} every slot whose rectangle intersects the query; returns the count. */
	public int query(int qx, int qy, int qw, int qh) {
		result.clear();
		nextEpoch();
		for (int i = 0; i < always.size(); i++) {
			int s = always.get(i);
			if (intersects(xs[s], ys[s], ws[s], hs[s], qx, qy, qw, qh)) {
				result.add(s);
			}
		}
		long right = (long) qx + qw, bottom = (long) qy + qh;
		if (qw <= 0 || qh <= 0 || right != (int) right || bottom != (int) bottom) {
			if (qw > 0 && qh > 0) {
				scanCells(qx, qy, qw, qh);
			}
			return result.size();
		}
		int cx0 = qx >> shift, cx1 = (qx + qw - 1) >> shift, cy0 = qy >> shift, cy1 = (qy + qh - 1) >> shift;
		if ((long) (cx1 - cx0 + 1) * (cy1 - cy0 + 1) > Math.max(16, cellsUsed)) {
			scanCells(qx, qy, qw, qh);
			return result.size();
		}
		for (int cy = cy0; cy <= cy1; cy++) {
			for (int cx = cx0; cx <= cx1; cx++) {
				int c = cell(key(cx, cy), false);
				for (int e = c < 0 ? -1 : cellHead[c]; e >= 0; e = entryNext[e]) {
					int s = entrySlot[e];
					if (stamp[s] != epoch) {
						stamp[s] = epoch;
						if (intersects(xs[s], ys[s], ws[s], hs[s], qx, qy, qw, qh)) {
							result.add(s);
						}
					}
				}
			}
		}
		return result.size();
	}

	/** Linear lane scan of the grid slots, for queries larger than the occupied cells. */
	private void scanCells(int qx, int qy, int qw, int qh) {
		for (int s = 0; s < kind.length; s++) {
			if (kind[s] == CELLS && intersects(xs[s], ys[s], ws[s], hs[s], qx, qy, qw, qh)) {
				result.add(s);
			}
		}
	}

	/** Collects into {@link #result()} every slot whose rectangle contains the point; returns the count. */
	public int queryPoint(int px, int py) {
		result.clear();
		for (int i = 0; i < always.size(); i++) {
			int s = always.get(i);
			if (contains(xs[s], ys[s], ws[s], hs[s], px, py)) {
				result.add(s);
			}
		}
		int c = cell(key(px >> shift, py >> shift), false);
		for (int e = c < 0 ? -1 : cellHead[c]; e >= 0; e = entryNext[e]) {
			int s = entrySlot[e];
			if (contains(xs[s], ys[s], ws[s], hs[s], px, py)) {
				result.add(s);
			}
		}
		return result.size();
	}

	private void nextEpoch() {
		if (++epoch == 0) {
			Arrays.fill(stamp, 0);
			epoch = 1;
		}
	}
}
