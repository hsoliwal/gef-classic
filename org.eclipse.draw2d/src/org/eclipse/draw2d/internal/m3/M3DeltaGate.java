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

/**
 * Runtime counters of the work an indexed container does over all of its
 * children instead of the changed or visible ones (docs/m3/gef-viewport/
 * DELTA_GATE.tsv). A model delta (add, remove, setConstraint, a move, a
 * visibility or preferred-size change) must leave every counter unchanged;
 * the delta gate tests reset them after the initial build and assert zero.
 * Plain int fields: Draw2d runs on one UI thread, and a counter costs one add
 * on paths that are already O(children).
 */
public final class M3DeltaGate {

	/** XY layout passes that applied every child (first pass, new manager or origin, XYLayout subclass). */
	public static int fullLayouts;
	/** XY layout passes that compared every constraint with its snapshot. */
	public static int constraintSweeps;
	/** Paints that fell back to the stock loop over every child under a clipping strategy. */
	public static int fullPaints;
	/**
	 * Paints left to the stock loop because more than a quarter of the children
	 * meet the clip (at most four times the painted children; not full work).
	 */
	public static int widePaints;
	/** Validation passes that visited every child (new index, invalidateTree). */
	public static int fullValidations;
	/** Z-order label range relabels (label gap used up; amortized O(log n) labels per insert). */
	public static int relabels;
	/** Labels rewritten by those relabels. */
	public static long relabeledLabels;
	/** Child indexes built (O(children) once per container). */
	public static int indexBuilds;
	/** Children painted through an index (per-step paint work; the stock fallback adds every child). */
	public static long paintedChildren;
	/** Children validated through an index (per-step validation work). */
	public static long validatedChildren;
	/** Update requests deferred because the canvas was hidden. */
	public static int deferredUpdates;
	/** Repairs skipped because the canvas was hidden (explicit performUpdate). */
	public static int deferredRepairs;
	/** Repairs dropped after a show (the window system paints the whole canvas). */
	public static int droppedRepairs;

	private M3DeltaGate() {
	}

	/** Sets every counter to zero. */
	public static void reset() {
		fullLayouts = 0;
		constraintSweeps = 0;
		fullPaints = 0;
		widePaints = 0;
		fullValidations = 0;
		relabels = 0;
		relabeledLabels = 0;
		indexBuilds = 0;
		paintedChildren = 0;
		validatedChildren = 0;
		deferredUpdates = 0;
		deferredRepairs = 0;
		droppedRepairs = 0;
	}

	/** The counters as {@code name=value} pairs. */
	public static String summary() {
		return "fullLayouts=" + fullLayouts + " constraintSweeps=" + constraintSweeps + " fullPaints=" + fullPaints //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ " widePaints=" + widePaints //$NON-NLS-1$
				+ " fullValidations=" + fullValidations + " relabels=" + relabels + "/" + relabeledLabels + " indexBuilds=" + indexBuilds //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ " paintedChildren=" + paintedChildren + " validatedChildren=" + validatedChildren; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** Sum of the counters that a model delta must leave at zero. */
	public static int fullWork() {
		return fullLayouts + constraintSweeps + fullPaints + fullValidations;
	}
}
