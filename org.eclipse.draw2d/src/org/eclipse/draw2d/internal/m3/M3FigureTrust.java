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
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Which stock Draw2d answers a figure class leaves to {@link Figure}: one bit
 * per question the child index may answer from its lanes instead of asking the
 * figure. A class earns a bit only when neither it nor any superclass below
 * {@link Figure} declares one of the methods that answer depends on, so the
 * stock answer is a pure function of the bounds the index mirrors. Classes
 * that do not extend Figure earn no bits. Results are cached per class.
 */
public final class M3FigureTrust {

	/** Bounds change only through Figure.setBounds/translate, which fire figureMoved. */
	public static final int BOUNDS = 1;
	/** paintChildren may skip the child when its bounds miss the clip. */
	public static final int PAINT = 1 << 1;
	/** findDescendantAtExcluding may skip the child when its bounds miss the point. */
	public static final int HIT = 1 << 2;
	/** findMouseEventTargetInDescendantsAt may skip the child when its bounds miss the point. */
	public static final int MOUSE = 1 << 3;
	/** Layer.containsPoint may answer the child's containsPoint from its bounds. */
	public static final int CONTAINS = 1 << 4;
	/** validate() of a valid child returns at once. */
	public static final int VALIDATE = 1 << 5;
	/** Every bit. */
	public static final int ALL = BOUNDS | PAINT | HIT | MOUSE | CONTAINS | VALIDATE;

	/** A container whose child list the index may mirror. */
	public static final int CONTAINER = 1 << 6;

	private static final ClassValue<Integer> CACHE = new ClassValue<>() {
		@Override
		protected Integer computeValue(Class<?> type) {
			return Integer.valueOf(compute(type));
		}
	};

	private M3FigureTrust() {
	}

	/** Trust bits of the figure's class. */
	public static int of(IFigure figure) {
		return CACHE.get(figure.getClass()).intValue();
	}

	static int compute(Class<?> type) {
		if (!Figure.class.isAssignableFrom(type)) {
			return 0;
		}
		boolean bounds = !declares(type, "getBounds") && !declares(type, "setBounds", Rectangle.class) //$NON-NLS-1$ //$NON-NLS-2$
				&& !declares(type, "primTranslate", int.class, int.class) && !declares(type, "fireFigureMoved"); //$NON-NLS-1$ //$NON-NLS-2$
		boolean flags = !declares(type, "getFlag", int.class); //$NON-NLS-1$
		boolean visible = flags && !declares(type, "isVisible"); //$NON-NLS-1$
		boolean contains = !declares(type, "containsPoint", int.class, int.class); //$NON-NLS-1$
		boolean find = !declares(type, "findFigureAt", int.class, int.class, TreeSearch.class); //$NON-NLS-1$
		boolean enabled = flags && !declares(type, "isEnabled"); //$NON-NLS-1$
		int bits = 0;
		if (bounds) {
			bits |= BOUNDS;
			bits |= visible ? PAINT : 0;
			bits |= visible && contains && find ? HIT : 0;
			bits |= visible && contains && enabled ? MOUSE : 0;
			bits |= contains ? CONTAINS : 0;
		}
		if (flags && !declares(type, "validate") && !declares(type, "isValid") && !declares(type, "setValid", boolean.class)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			bits |= VALIDATE;
		}
		if (!declares(type, "getChildren") && !declares(type, "getChildrenRevIterable")) { //$NON-NLS-1$ //$NON-NLS-2$
			bits |= CONTAINER;
		}
		return bits;
	}

	/** Whether {@code type} or a superclass below Figure declares the method. */
	static boolean declares(Class<?> type, String name, Class<?>... parameters) {
		for (Class<?> c = type; c != Figure.class; c = c.getSuperclass()) {
			try {
				c.getDeclaredMethod(name, parameters);
				return true;
			} catch (NoSuchMethodException e) {
				// not declared at this level
			}
		}
		return false;
	}
}
