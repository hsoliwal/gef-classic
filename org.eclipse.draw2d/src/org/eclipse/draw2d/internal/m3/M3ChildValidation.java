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

import org.eclipse.draw2d.IFigure;

/**
 * Incremental child validation: Figure.validate ends with
 * {@code children.forEach(IFigure::validate)}, which for a trusted child that
 * is already valid returns at once. This part keeps a bit per slot that is set
 * while the child may be invalid (every child when the index is created, then
 * exactly the setValid(false) calls) and validates, in child-list order, only
 * the flagged children and the children whose classes override validation.
 * Children invalidated during the pass are picked up when they lie ahead of
 * the cursor, as the stock loop would reach them.
 */
public final class M3ChildValidation {

	private final M3FigureChildren index;
	private long[] invalid = new long[1];
	private final M3IntList pending = new M3IntList();
	private final M3IntList visit = new M3IntList();
	private final M3IntList special = new M3IntList();
	private int marks;
	private int validated;

	M3ChildValidation(M3FigureChildren index) {
		this.index = index;
		for (int s = 0; s < index.slots.slotLimit(); s++) {
			if (index.slots.figure(s) != null) {
				mark(s);
			}
		}
	}

	/** Children validated by the last pass (for tests and benchmarks). */
	public int validated() {
		return validated;
	}

	private boolean flagged(int slot) {
		return (invalid[slot >>> 6] & (1L << slot)) != 0;
	}

	private void mark(int slot) {
		if (slot >>> 6 >= invalid.length) {
			invalid = Arrays.copyOf(invalid, Math.max((slot >>> 6) + 1, invalid.length * 2));
		}
		if (!flagged(slot)) {
			invalid[slot >>> 6] |= 1L << slot;
			pending.add(slot);
			marks++;
		}
	}

	private void unmark(int slot) {
		if (flagged(slot)) {
			invalid[slot >>> 6] &= ~(1L << slot);
		}
	}

	void added(int slot) {
		mark(slot);
	}

	void removed(int slot) {
		unmark(slot);
	}

	void validity(int slot, boolean valid) {
		if (valid) {
			unmark(slot);
		} else {
			mark(slot);
		}
	}

	/** Collects the flagged and always-asked children ahead of {@code cursor}, sorted by label. */
	private void collect(long cursor) {
		long[] labels = index.slots.labels();
		visit.clear();
		int kept = 0;
		for (int i = 0; i < pending.size(); i++) {
			int s = pending.get(i);
			if (flagged(s)) {
				pending.set(kept++, s);
				if (labels[s] > cursor && (index.slots.trust(s) & M3FigureTrust.VALIDATE) != 0) {
					visit.add(s);
				}
			}
		}
		while (pending.size() > kept) {
			pending.removeLast();
		}
		special.clear();
		index.addSpecial(special, M3FigureTrust.VALIDATE);
		for (int i = 0; i < special.size(); i++) {
			if (labels[special.get(i)] > cursor) {
				visit.add(special.get(i));
			}
		}
		visit.sortBy(labels);
	}

	/** The children loop of Figure.validate. */
	public void validateChildren() {
		long cursor = Long.MIN_VALUE;
		int expected = index.modCount;
		validated = 0;
		collect(cursor);
		if (visit.size() > 0 && visit.size() >= index.slots.size()) {
			M3DeltaGate.fullValidations++;
		}
		for (int i = 0; i < visit.size(); i++) {
			int slot = visit.get(i);
			IFigure child = index.slots.figure(slot);
			boolean trusted = (index.slots.trust(slot) & M3FigureTrust.VALIDATE) != 0;
			cursor = index.slots.labels()[slot];
			if (trusted && !flagged(slot)) {
				continue;
			}
			unmark(slot);
			int before = marks;
			child.validate();
			validated++;
			M3DeltaGate.validatedChildren++;
			index.checkUnmodified(expected);
			if (marks != before) {
				collect(cursor);
				i = -1;
			}
		}
	}
}
