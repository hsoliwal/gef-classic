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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Culled paint and hit tests over a {@link M3FigureChildren} index: the grid
 * names the trusted children that meet the clip or the point, the
 * always-asked children are added, the candidates are sorted into z-order by
 * label, and each candidate gets exactly the calls the stock loop makes. The
 * candidate buffer is reused; a re-entrant call works on a copy.
 */
final class M3ChildQueries {

	/** The indexed paint runs while fewer than one child in this many meets the clip. */
	static final int PAINT_SHARE = 4;

	private final M3FigureChildren index;
	private final M3IntList found = new M3IntList();
	private final Rectangle clip = new Rectangle();
	private int[] buffer = new int[16];
	private boolean busy;

	M3ChildQueries(M3FigureChildren index) {
		this.index = index;
	}

	/** Copies the candidates into the shared buffer, or into a new array when re-entered. */
	private int[] take() {
		int n = found.size();
		int[] visit;
		if (busy) {
			visit = new int[n];
		} else {
			if (buffer.length < n) {
				buffer = new int[Math.max(n, buffer.length * 2)];
			}
			visit = buffer;
			busy = true;
		}
		for (int i = 0; i < n; i++) {
			visit[i] = found.get(i);
		}
		return visit;
	}

	private void release(int[] visit) {
		if (visit == buffer) {
			busy = false;
		}
	}

	/** Grid hits trusted for {@code bit}, plus the always-asked children, in z-order. */
	private void collect(int bit) {
		found.clear();
		M3IntList hits = index.grid.result();
		for (int i = 0; i < hits.size(); i++) {
			int s = hits.get(i);
			if ((index.slots.trust(s) & bit) != 0) {
				found.add(s);
			}
		}
		index.addSpecial(found, bit);
	}

	boolean paintChildren(Graphics graphics, IClippingStrategy strategy) {
		if (strategy != null) {
			fallback();
			return false;
		}
		graphics.getClip(clip);
		index.grid.query(clip.x, clip.y, clip.width, clip.height);
		collect(M3FigureTrust.PAINT);
		if (found.size() * PAINT_SHARE > index.slots.size()) {
			fallback();
			return false;
		}
		found.sortBy(index.slots.labels());
		if (M3ViewportMode.VERIFY) {
			M3Verify.check(index, found, clip, 0, 0, M3FigureTrust.PAINT);
		}
		int n = found.size();
		int expected = index.modCount;
		int[] visit = take();
		try {
			for (int i = 0; i < n; i++) {
				IFigure child = index.slots.figure(visit[i]);
				if (child.isVisible()) {
					Rectangle element = child.getBounds();
					if (element.intersects(graphics.getClip(Rectangle.SINGLETON))) {
						graphics.clipRect(element);
						M3DeltaGate.paintedChildren++;
						child.paint(graphics);
						graphics.restoreState();
					}
					index.checkUnmodified(expected);
				}
			}
		} finally {
			release(visit);
		}
		return true;
	}

	/** The stock loop paints: every child is asked. */
	private void fallback() {
		M3DeltaGate.fullPaints++;
		M3DeltaGate.paintedChildren += index.slots.size();
	}

	private int pointCandidates(int x, int y, int bit) {
		index.grid.queryPoint(x, y);
		collect(bit);
		found.sortBy(index.slots.labels());
		if (M3ViewportMode.VERIFY) {
			M3Verify.check(index, found, null, x, y, bit);
		}
		return found.size();
	}

	IFigure findFigureAt(int x, int y, TreeSearch search) {
		int n = pointCandidates(x, y, M3FigureTrust.HIT);
		int[] visit = take();
		try {
			for (int i = n - 1; i >= 0; i--) {
				IFigure fig = index.slots.figure(visit[i]);
				if (fig.isVisible()) {
					fig = fig.findFigureAt(x, y, search);
					if (fig != null) {
						return fig;
					}
				}
			}
			return null;
		} finally {
			release(visit);
		}
	}

	IFigure findMouseEventTargetAt(int x, int y) {
		int n = pointCandidates(x, y, M3FigureTrust.MOUSE);
		int[] visit = take();
		try {
			for (int i = n - 1; i >= 0; i--) {
				IFigure fig = index.slots.figure(visit[i]);
				if (fig.isVisible() && fig.isEnabled() && fig.containsPoint(x, y)) {
					fig = fig.findMouseEventTargetAt(x, y);
					if (fig != null) {
						return fig;
					}
				}
			}
			return null;
		} finally {
			release(visit);
		}
	}

	boolean containsPoint(int x, int y) {
		int n = pointCandidates(x, y, M3FigureTrust.CONTAINS);
		int[] visit = take();
		try {
			for (int i = 0; i < n; i++) {
				if (index.slots.figure(visit[i]).containsPoint(x, y)) {
					return true;
				}
			}
			return false;
		} finally {
			release(visit);
		}
	}
}
