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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.internal.m3.M3ChildSlots;
import org.eclipse.draw2d.internal.m3.M3FigureChildren;
import org.eclipse.draw2d.internal.m3.M3IntList;
import org.eclipse.draw2d.internal.m3.M3ViewportMode;
import org.eclipse.draw2d.internal.m3.M3XYLayoutPlan;

import org.junit.jupiter.api.Test;

/**
 * Retained layout work stays bounded by the slot high-water mark, including
 * slots removed and reused before the next collection. Run in separate VMs
 * with {@code -Ddraw2d.viewport.mode=viewport}, both with and without
 * {@code -Ddraw2d.viewport.trustConstraints=true}.
 */
public class M3PreferredLayoutRetentionTest {

	private static final int CHILDREN = 64;
	private static final int PASSES = 1000;

	private static final class Fixture {
		final Figure parent = new Figure();
		final XYLayout layout = new XYLayout();
		final M3FigureChildren index;
		final M3XYLayoutPlan plan;
		final M3ChildSlots slots;

		Fixture(int count) throws Exception {
			assumeTrue(M3ViewportMode.ENABLED, "needs -Ddraw2d.viewport.mode=viewport"); //$NON-NLS-1$
			parent.setBounds(new Rectangle(11, 13, 2000, 2000));
			parent.setLayoutManager(layout);
			for (int i = 0; i < count; i++) {
				parent.add(preferred(20 + i, 30 + i), new Rectangle(3 * i, 2 * i, -1, -1));
			}
			layout.layout(parent);
			index = (M3FigureChildren) field(parent, Figure.class, "m3Children"); //$NON-NLS-1$
			assertNotNull(index, "the fixture must exercise the viewport layout plan"); //$NON-NLS-1$
			plan = index.layout();
			slots = (M3ChildSlots) field(index, M3FigureChildren.class, "slots"); //$NON-NLS-1$
		}

		M3IntList list(String name) throws Exception {
			return (M3IntList) field(plan, M3XYLayoutPlan.class, name);
		}

		void assertTrackingBounded() {
			assertAll(() -> assertBounded(list("prefList"), slots.slotLimit(), "preferred"), //$NON-NLS-1$ //$NON-NLS-2$
					() -> assertBounded(list("dirtyList"), slots.slotLimit(), "dirty")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private static Object field(Object owner, Class<?> declaringClass, String name) throws Exception {
		Field field = declaringClass.getDeclaredField(name);
		field.setAccessible(true);
		return field.get(owner);
	}

	private static Figure preferred(int width, int height) {
		Figure child = new Figure();
		child.setPreferredSize(new Dimension(width, height));
		return child;
	}

	private static void assertBounded(M3IntList list, int slotLimit, String name) {
		assertTrue(list.size() <= slotLimit,
				name + " list retained " + list.size() + " entries for " + slotLimit + " allocated slots"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		boolean[] seen = new boolean[slotLimit];
		for (int i = 0; i < list.size(); i++) {
			int slot = list.get(i);
			assertTrue(slot >= 0 && slot < slotLimit, name + " contains an unallocated slot"); //$NON-NLS-1$
			assertFalse(seen[slot], name + " retained slot " + slot + " more than once"); //$NON-NLS-1$ //$NON-NLS-2$
			seen[slot] = true;
		}
	}

	@Test
	public void repeatedPreferredLayoutKeepsBothTrackingListsBounded() throws Exception {
		Fixture f = new Fixture(CHILDREN);
		IFigure child = f.parent.getChildren().get(0);
		int preferredBefore = f.list("prefList").size(); //$NON-NLS-1$
		int dirtyBefore = f.list("dirtyList").size(); //$NON-NLS-1$
		for (int pass = 1; pass <= PASSES; pass++) {
			f.layout.setConstraint(child, new Rectangle(pass, 7, -1, -1));
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(11 + pass, 20, 20, 30), child.getBounds());
			assertEquals(CHILDREN, f.plan.laidOut());
		}
		System.out.println("layout retention trustConstraints=" + M3ViewportMode.TRUST_CONSTRAINTS //$NON-NLS-1$
				+ " children=" + CHILDREN + " passes=" + PASSES + " preferred=" + preferredBefore + "->" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ f.list("prefList").size() + " dirty=" + dirtyBefore + "->" + f.list("dirtyList").size()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		f.assertTrackingBounded();
	}

	@Test
	public void removedSlotsCanBeReusedBeforeCompaction() throws Exception {
		Fixture f = new Fixture(2 * CHILDREN);
		IFigure child = f.parent.getChildren().get(0);
		for (IFigure other : new ArrayList<>(f.parent.getChildren())) {
			if (other != child) {
				f.parent.remove(other);
			}
		}
		assertEquals(1, f.parent.getChildren().size());
		assertEquals(2 * CHILDREN, f.slots.slotLimit());
		f.assertTrackingBounded();
		for (int pass = 1; pass <= PASSES; pass++) {
			int slot = f.slots.slotOf(child);
			f.parent.remove(child);
			child = preferred(40 + pass, 50 + pass);
			f.parent.add(child, new Rectangle(5, 7, -1, -1));
			assertEquals(slot, f.slots.slotOf(child));
			f.assertTrackingBounded();
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 40 + pass, 50 + pass), child.getBounds());
			f.assertTrackingBounded();
		}
	}

	@Test
	public void removedSlotsCanBeReusedAfterCompaction() throws Exception {
		Fixture f = new Fixture(CHILDREN);
		IFigure child = f.parent.getChildren().get(CHILDREN - 1);
		for (int pass = 1; pass <= 100; pass++) {
			int slot = f.slots.slotOf(child);
			f.parent.remove(child);
			f.layout.layout(f.parent);
			child = preferred(40 + pass, 50 + pass);
			f.parent.add(child, new Rectangle(5, 7, -1, 23));
			assertEquals(slot, f.slots.slotOf(child));
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 40 + pass, 23), child.getBounds());
			child.setPreferredSize(new Dimension(60 + pass, 70 + pass));
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 60 + pass, 23), child.getBounds());
			f.assertTrackingBounded();
		}
	}

	@Test
	public void fixedAndPreferredTransitionsKeepGeometryAndTracking() throws Exception {
		Fixture f = new Fixture(CHILDREN);
		IFigure child = f.parent.getChildren().get(CHILDREN - 1);
		for (int pass = 1; pass <= 100; pass++) {
			f.layout.setConstraint(child, new Rectangle(5, 7, 42, 43));
			f.layout.layout(f.parent);
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 42, 43), child.getBounds());
			child.setPreferredSize(new Dimension(40 + pass, 50 + pass));
			f.layout.setConstraint(child, new Rectangle(5, 7, 42, -1));
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 42, 50 + pass), child.getBounds());
			f.layout.setConstraint(child, new Rectangle(5, 7, -1, -1));
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 40 + pass, 50 + pass), child.getBounds());
			child.setPreferredSize(new Dimension(60 + pass, 70 + pass));
			f.layout.layout(f.parent);
			assertEquals(new Rectangle(16, 20, 60 + pass, 70 + pass), child.getBounds());
			f.assertTrackingBounded();
		}
	}
}
