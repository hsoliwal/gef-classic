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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Viewport deltas (brief rule 24): the damage of a bounds change of a container
 * whose painting does not depend on its size. Stock Figure.setBounds damages
 * the whole old bounds (through the parent, erase) and the whole new bounds;
 * for a figure whose class paints only through reviewed size-free code
 * ({@link M3FigureTrust#SIZE_FREE}), without a border or a clipping strategy,
 * the pixels of the area both bounds share stay valid when it does not move its
 * children, so only the strips the change uncovers (old minus new, repainted by
 * the parent) and exposes (new minus old, repainted by the figure) are damaged.
 * Canvas resizes, freeform extent changes and zoom-driven layer resizes then
 * repaint strips instead of the window.
 * <p>
 * {@code -Ddraw2d.viewport.damage=stock} keeps the stock damage (the default in
 * the viewport mode is {@code strips}).
 * </p>
 */
public final class M3Damage {

	/** Whether the viewport mode damages strips and repairs separate pieces. */
	public static final boolean STRIPS = M3ViewportMode.ENABLED
			&& !"stock".equals(System.getProperty("draw2d.viewport.damage")); //$NON-NLS-1$ //$NON-NLS-2$

	private static final Rectangle SCRATCH = new Rectangle();

	private M3Damage() {
	}

	/**
	 * Hook body at the start of Figure.setBounds: when {@code rect} only resizes
	 * {@code figure} (or moves a freeform container, whose children keep their
	 * place) and the figure paints size-free, damages the uncovered and exposed
	 * strips and returns true; the caller then applies the bounds without erase
	 * and repaint. Returns false (stock path) otherwise.
	 */
	public static boolean bounds(Figure figure, Rectangle old, Rectangle rect) {
		boolean translate = rect.x != old.x || rect.y != old.y;
		boolean resize = rect.width != old.width || rect.height != old.height;
		if (!resize && !translate) {
			return false;
		}
		int trust = M3FigureTrust.of(figure);
		if ((trust & M3FigureTrust.SIZE_FREE) == 0 || (translate && (trust & M3FigureTrust.KEEPS_CHILDREN) == 0)
				|| figure.getBorder() != null || figure.getClippingStrategy() != null) {
			return false;
		}
		M3DeltaGate.stripChanges++;
		if (!figure.isVisible()) {
			return true;
		}
		IFigure parent = figure.getParent();
		if (parent != null) {
			subtract(old, rect, parent, true);
		}
		subtract(rect, old, figure, false);
		return true;
	}

	/**
	 * Damages {@code a} minus {@code b} as up to four bands: through the parent
	 * in its coordinates (as Figure.erase) or through the figure itself.
	 */
	private static void subtract(Rectangle a, Rectangle b, IFigure target, boolean viaParent) {
		int ax2 = a.x + a.width;
		int ay2 = a.y + a.height;
		int bx2 = b.x + b.width;
		int by2 = b.y + b.height;
		if (a.isEmpty()) {
			return;
		}
		if (b.isEmpty() || bx2 <= a.x || b.x >= ax2 || by2 <= a.y || b.y >= ay2) {
			damage(target, viaParent, a.x, a.y, a.width, a.height);
			return;
		}
		int top = Math.max(a.y, b.y);
		int bottom = Math.min(ay2, by2);
		if (b.y > a.y) {
			damage(target, viaParent, a.x, a.y, a.width, b.y - a.y);
		}
		if (by2 < ay2) {
			damage(target, viaParent, a.x, by2, a.width, ay2 - by2);
		}
		if (b.x > a.x) {
			damage(target, viaParent, a.x, top, b.x - a.x, bottom - top);
		}
		if (bx2 < ax2) {
			damage(target, viaParent, bx2, top, ax2 - bx2, bottom - top);
		}
	}

	private static void damage(IFigure target, boolean viaParent, int x, int y, int w, int h) {
		if (viaParent) {
			Rectangle r = SCRATCH.setBounds(x, y, w, h);
			target.translateToParent(r);
			target.repaint(r.x, r.y, r.width, r.height);
		} else {
			target.repaint(x, y, w, h);
		}
	}
}
