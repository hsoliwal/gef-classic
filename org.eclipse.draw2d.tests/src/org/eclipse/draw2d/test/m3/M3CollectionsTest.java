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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.internal.m3.M3ChildSlots;
import org.eclipse.draw2d.internal.m3.M3ExtentTree;
import org.eclipse.draw2d.internal.m3.M3IntList;
import org.eclipse.draw2d.internal.m3.M3SpatialGrid;

import org.junit.jupiter.api.Test;

/**
 * The lane collections of the Draw2d child index, each against a brute-force
 * model: the grid against the Rectangle predicates (including int overflow),
 * the slot registry against a list, the int list sort against Arrays.sort.
 */
public class M3CollectionsTest {

	private static boolean intersects(int[] r, int qx, int qy, int qw, int qh) {
		return r[2] > 0 && r[3] > 0 && qw > 0 && qh > 0 && r[0] < qx + qw && qx < r[0] + r[2] && r[1] < qy + qh
				&& qy < r[1] + r[3];
	}

	private static boolean contains(int[] r, int px, int py) {
		return py >= r[1] && py < r[1] + r[3] && px >= r[0] && px < r[0] + r[2];
	}

	private static int[] sorted(M3IntList list) {
		int[] a = new int[list.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = list.get(i);
		}
		Arrays.sort(a);
		return a;
	}

	private static int[] rect(SplittableRandom r) {
		return switch (r.nextInt(12)) {
		case 0 -> new int[] { Integer.MAX_VALUE - r.nextInt(40), r.nextInt(500), 20 + r.nextInt(80), 10 };
		case 1 -> new int[] { r.nextInt(500), r.nextInt(500), 0, 10 };
		case 2 -> new int[] { r.nextInt(500), r.nextInt(500), 5, -3 };
		case 3 -> new int[] { -r.nextInt(4000), -r.nextInt(4000), 3000 + r.nextInt(3000), 2000 };
		case 4 -> new int[] { Integer.MIN_VALUE + r.nextInt(20), 5, -30, 10 };
		default -> new int[] { r.nextInt(3000) - 500, r.nextInt(3000) - 500, 1 + r.nextInt(90), 1 + r.nextInt(70) };
		};
	}

	@Test
	public void gridMatchesBruteForce() {
		SplittableRandom r = new SplittableRandom(11);
		M3SpatialGrid grid = new M3SpatialGrid(5);
		assertEquals(5, grid.shift());
		int[][] model = new int[600][];
		for (int step = 0; step < 6000; step++) {
			int slot = r.nextInt(model.length);
			if (r.nextInt(5) == 0) {
				grid.remove(slot);
				model[slot] = null;
			} else {
				int[] b = rect(r);
				grid.put(slot, b[0], b[1], b[2], b[3]);
				grid.put(slot, b[0], b[1], b[2], b[3]);
				model[slot] = b;
			}
			int qx = r.nextInt(3200) - 600, qy = r.nextInt(3200) - 600;
			int qw = r.nextInt(9) == 0 ? -1 : r.nextInt(r.nextInt(7) == 0 ? 6000 : 400);
			int qh = r.nextInt(400);
			if (r.nextInt(25) == 0) {
				qx = Integer.MAX_VALUE - 30;
				qw = 100;
			}
			grid.query(qx, qy, qw, qh);
			M3IntList want = new M3IntList();
			M3IntList at = new M3IntList();
			int held = 0;
			for (int s = 0; s < model.length; s++) {
				if (model[s] != null) {
					held++;
					if (intersects(model[s], qx, qy, qw, qh)) {
						want.add(s);
					}
					if (contains(model[s], qx + 7, qy + 5)) {
						at.add(s);
					}
					assertTrue(grid.holds(s));
					assertEquals(model[s][0], grid.x(s));
					assertEquals(model[s][1], grid.y(s));
					assertEquals(model[s][2], grid.width(s));
					assertEquals(model[s][3], grid.height(s));
				} else {
					assertFalse(grid.holds(s));
				}
			}
			assertEquals(held, grid.size());
			assertTrue(Arrays.equals(sorted(want), sorted(grid.result())), "query at step " + step); //$NON-NLS-1$
			grid.queryPoint(qx + 7, qy + 5);
			assertTrue(Arrays.equals(sorted(at), sorted(grid.result())), "point at step " + step); //$NON-NLS-1$
		}
		assertTrue(grid.alwaysCount() > 0);
		assertFalse(grid.holds(100000));
	}

	@Test
	public void gridEpochWraps() throws Exception {
		M3SpatialGrid grid = new M3SpatialGrid(6);
		grid.put(0, 10, 10, 20, 20);
		Field epoch = M3SpatialGrid.class.getDeclaredField("epoch"); //$NON-NLS-1$
		epoch.setAccessible(true);
		epoch.setInt(grid, -1);
		assertEquals(1, grid.query(0, 0, 100, 100));
		assertEquals(1, epoch.getInt(grid));
		assertEquals(1, grid.query(0, 0, 100, 100));
	}

	@Test
	public void slotsMatchModel() {
		SplittableRandom r = new SplittableRandom(5);
		M3ChildSlots slots = new M3ChildSlots();
		List<IFigure> present = new ArrayList<>();
		for (int step = 0; step < 20000; step++) {
			if (present.isEmpty() || r.nextInt(3) != 0) {
				IFigure f = new Figure();
				int slot = slots.add(f, step);
				assertSame(f, slots.figure(slot));
				assertEquals(step, slots.labels()[slot]);
				present.add(f);
			} else {
				IFigure f = present.remove(r.nextInt(present.size()));
				int slot = slots.slotOf(f);
				assertEquals(slot, slots.remove(f));
				assertNull(slots.figure(slot));
				assertEquals(-1, slots.remove(f));
			}
			if (step % 997 == 0) {
				for (IFigure f : present) {
					assertSame(f, slots.figure(slots.slotOf(f)));
				}
			}
		}
		assertEquals(present.size(), slots.size());
		assertTrue(slots.slotLimit() >= present.size());
		assertEquals(-1, slots.slotOf(new Figure()));
	}

	@Test
	public void labelsRelabelWhenGapsRunOut() throws Exception {
		M3ChildSlots slots = new M3ChildSlots();
		List<IFigure> children = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			children.add(new Figure());
		}
		for (IFigure f : children) {
			slots.add(f, 0);
		}
		slots.relabel(children);
		assertEquals(1, slots.relabels());
		int inserted = 0;
		long label;
		do {
			IFigure f = new Figure();
			children.add(1, f);
			label = slots.labelAt(children, 1);
			slots.add(f, Math.max(label, 0));
			inserted++;
		} while (label >= 0);
		assertTrue(inserted > 5);
		slots.relabel(children);
		long[] labels = slots.labels();
		for (int i = 1; i < children.size(); i++) {
			assertTrue(labels[slots.slotOf(children.get(i - 1))] < labels[slots.slotOf(children.get(i))]);
		}
		IFigure last = new Figure();
		children.add(last);
		labels[slots.slotOf(children.get(children.size() - 2))] = (1L << 38) - 5;
		assertEquals(-1, slots.labelAt(children, children.size() - 1));
		children.add(0, new Figure());
		assertTrue(slots.labelAt(children, 0) > 0);
	}

	@Test
	public void intListSortsSmallAndLargeKeys() {
		M3IntList list = new M3IntList();
		list.sortBy(new long[0]);
		list.add(3);
		list.sortBy(new long[] { 0, 0, 0, 7 });
		assertEquals(3, list.get(0));
		long[] keys = new long[64];
		SplittableRandom r = new SplittableRandom(2);
		for (int i = 0; i < keys.length; i++) {
			keys[i] = r.nextLong(1L << 30);
			list.add(i);
		}
		list.set(0, 63);
		list.sortBy(keys);
		for (int i = 1; i < list.size(); i++) {
			assertTrue(keys[list.get(i - 1)] <= keys[list.get(i)]);
		}
		keys[5] = Long.MAX_VALUE;
		keys[6] = -4;
		list.sortBy(keys);
		assertEquals(6, list.get(0));
		assertEquals(5, list.removeLast());
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	public void extentTreeTracksBoundingBox() {
		M3ExtentTree tree = new M3ExtentTree();
		assertEquals(0, tree.size());
		tree.set(40, 5, 6, 10, 12);
		tree.set(3, -2, 8, 4, 30);
		assertTrue(tree.contains(3));
		assertFalse(tree.contains(4));
		assertFalse(tree.contains(400));
		assertEquals(-2, tree.minX());
		assertEquals(6, tree.minY());
		assertEquals(10, tree.maxRight());
		assertEquals(30, tree.maxBottom());
		tree.set(3, 1, 9, 2, 10);
		tree.clear(3);
		tree.clear(3);
		assertEquals(1, tree.size());
		assertEquals(5, tree.minX());
		assertEquals(12, tree.maxBottom());
	}
}
