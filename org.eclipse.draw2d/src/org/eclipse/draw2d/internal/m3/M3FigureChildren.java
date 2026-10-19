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

import java.util.ConcurrentModificationException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The child index of one Draw2d container in the viewport mode: the children's
 * bounds as int lanes in a {@link M3SpatialGrid}, their z-order as labels
 * ({@link M3ChildSlots}), and the children whose classes override geometry
 * methods ({@link M3FigureTrust}) in an always-asked list. Figure keeps it up
 * to date through one-line hooks (child added, removed, moved, validity
 * changed) and asks it to paint the children that meet the clip, to find the
 * child under a point, to validate the invalid children, to lay out the
 * changed XY children and to answer the freeform extent.
 * <p>
 * Every answer is the stock answer: the index only skips children for which
 * stock Draw2d would do nothing observable (a trusted child whose bounds miss
 * the clip or the point, a trusted child that is already valid or already laid
 * out), and visits the others in stock order with the stock calls.
 * </p>
 */
public final class M3FigureChildren {

	final IFigure host;
	final List<? extends IFigure> children;
	final M3ChildSlots slots = new M3ChildSlots();
	final M3SpatialGrid grid;
	private final M3IntList special = new M3IntList();
	private int[] specialAt = new int[16];
	private final M3ChildQueries queries = new M3ChildQueries(this);
	int modCount;
	private M3ChildValidation validation;
	private M3XYLayoutPlan layout;
	private M3FreeformExtent freeform;

	/** Indexes the current children of {@code host}; {@code children} is its live child list. */
	public M3FigureChildren(IFigure host, List<? extends IFigure> children) {
		this.host = host;
		this.children = children;
		this.grid = new M3SpatialGrid(cellShift(children));
		for (int i = 0; i < children.size(); i++) {
			register(children.get(i), (i + 1) * M3ChildSlots.GAP);
		}
		if (M3ViewportMode.LOG.isLoggable(Level.FINE)) {
			M3ViewportMode.LOG.fine("child index for " + host.getClass().getName() + ": " + children.size() //$NON-NLS-1$ //$NON-NLS-2$
					+ " children, cell " + (1 << grid.shift()) + ", always-asked " + special.size()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/** Grid cell size: the power of two at least twice the mean child extent, 32..8192. */
	static int cellShift(List<? extends IFigure> children) {
		long sum = 0;
		int n = 0;
		for (IFigure child : children) {
			Rectangle b = child.getBounds();
			if (b.width > 0 && b.height > 0) {
				sum += Math.max(b.width, b.height);
				n++;
			}
		}
		long size = n == 0 ? 64 : Math.max(32, Math.min(8192, 2 * sum / n));
		return 64 - Long.numberOfLeadingZeros(size - 1);
	}

	private void register(IFigure child, long label) {
		int slot = slots.add(child, label);
		if (slot >= specialAt.length) {
			specialAt = Arrays.copyOf(specialAt, Math.max(slot + 1, specialAt.length * 2));
		}
		if ((slots.trust(slot) & M3FigureTrust.ALL) != M3FigureTrust.ALL) {
			specialAt[slot] = special.size();
			special.add(slot);
		}
		Rectangle b = child.getBounds();
		grid.put(slot, b.x, b.y, b.width, b.height);
		if (validation != null) {
			validation.added(slot);
		}
		if (layout != null) {
			layout.added(slot);
		}
		if (freeform != null) {
			freeform.added(child, slot);
		}
	}

	/** Hook: {@code child} was inserted into the child list at {@code index} (-1: appended). */
	public void added(IFigure child, int index) {
		modCount++;
		int at = index < 0 ? children.size() - 1 : index;
		long label = at > 0 || children.size() > 1 ? slots.labelAt(children, at) : M3ChildSlots.GAP;
		register(child, Math.max(label, 0));
		if (label < 0) {
			slots.relabel(children);
		}
	}

	/** Hook: {@code child} was removed from the child list. */
	public void removed(IFigure child) {
		int slot = slots.remove(child);
		if (slot < 0) {
			return;
		}
		modCount++;
		grid.remove(slot);
		if ((slots.trust(slot) & M3FigureTrust.ALL) != M3FigureTrust.ALL) {
			int at = specialAt[slot];
			int last = special.removeLast();
			if (last != slot) {
				special.set(at, last);
				specialAt[last] = at;
			}
		}
		if (validation != null) {
			validation.removed(slot);
		}
		if (layout != null) {
			layout.removed(slot);
		}
		if (freeform != null) {
			freeform.removed(child, slot);
		}
	}

	/** Hook: the bounds of {@code child} changed (figureMoved). */
	public void moved(IFigure child) {
		int slot = slots.slotOf(child);
		if (slot < 0) {
			return;
		}
		Rectangle b = child.getBounds();
		grid.put(slot, b.x, b.y, b.width, b.height);
		if (layout != null) {
			layout.moved(slot);
		}
		if (freeform != null) {
			freeform.moved(slot, b);
		}
	}

	/** Hook: the validity flag of {@code child} was set. */
	public void validity(IFigure child, boolean valid) {
		if (validation != null) {
			int slot = slots.slotOf(child);
			if (slot >= 0) {
				validation.validity(slot, valid);
			}
		}
	}

	/** The validation part, created on first use. */
	public M3ChildValidation validation() {
		if (validation == null) {
			validation = new M3ChildValidation(this);
		}
		return validation;
	}

	/** The XY layout part, created on first use. */
	public M3XYLayoutPlan layout() {
		if (layout == null) {
			layout = new M3XYLayoutPlan(this);
		}
		return layout;
	}

	/** The freeform extent part, created on first use. */
	public M3FreeformExtent freeform() {
		if (freeform == null) {
			freeform = new M3FreeformExtent(this);
		}
		return freeform;
	}

	/** Collects the always-asked slots lacking {@code bit} into {@code out}. */
	void addSpecial(M3IntList out, int bit) {
		for (int i = 0; i < special.size(); i++) {
			int s = special.get(i);
			if ((slots.trust(s) & bit) == 0) {
				out.add(s);
			}
		}
	}

	/** Throws like ArrayList's iterator when the child list changed since {@code expected}. */
	void checkUnmodified(int expected) {
		if (modCount != expected) {
			throw new ConcurrentModificationException();
		}
	}

	/** Hook body of Figure.paintChildren; false lets the stock loop run. */
	public boolean paintChildren(Graphics graphics, IClippingStrategy strategy) {
		return queries.paintChildren(graphics, strategy);
	}

	/** Hook body of Figure.findDescendantAtExcluding (the loop over the children). */
	public IFigure findFigureAt(int x, int y, TreeSearch search) {
		return queries.findFigureAt(x, y, search);
	}

	/** Hook body of Figure.findMouseEventTargetInDescendantsAt (the loop over the children). */
	public IFigure findMouseEventTargetAt(int x, int y) {
		return queries.findMouseEventTargetAt(x, y);
	}

	/** Hook body of Layer.containsPoint (the loop over the children). */
	public boolean containsPoint(int x, int y) {
		return queries.containsPoint(x, y);
	}
}
