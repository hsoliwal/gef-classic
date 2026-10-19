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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The {@code -Ddraw2d.viewport.verify=true} cross-check: recomputes, by the
 * stock linear scan over the live child list, which children the index must
 * offer for a clip or a point, and throws {@link IllegalStateException} when
 * the index offers anything else or in another order. It catches a child whose
 * bounds changed without figureMoved, which the index cannot see.
 */
final class M3Verify {

	private M3Verify() {
	}

	/**
	 * @param clip the paint clip, or null for a point query at (x, y)
	 * @param bit  the trust bit the query relies on
	 */
	static void check(M3FigureChildren index, M3IntList found, Rectangle clip, int x, int y, int bit) {
		List<? extends IFigure> children = index.children;
		int k = 0;
		for (IFigure child : children) {
			int slot = index.slots.slotOf(child);
			boolean trusted = (index.slots.trust(slot) & bit) != 0;
			Rectangle b = child.getBounds();
			boolean meets = clip != null ? b.intersects(clip) : b.contains(x, y);
			if (!trusted || meets) {
				if (k >= found.size() || found.get(k) != slot) {
					throw new IllegalStateException(message(index, child, clip, x, y));
				}
				k++;
			}
		}
		if (k != found.size()) {
			throw new IllegalStateException(message(index, index.slots.figure(found.get(k)), clip, x, y));
		}
	}

	private static String message(M3FigureChildren index, IFigure child, Rectangle clip, int x, int y) {
		return "draw2d viewport index differs from the stock scan at child " + child + " of " + index.host //$NON-NLS-1$ //$NON-NLS-2$
				+ (clip != null ? " for clip " + clip : " for point " + x + ',' + y) //$NON-NLS-1$ //$NON-NLS-2$
				+ "; were its bounds changed without figureMoved?"; //$NON-NLS-1$
	}
}
