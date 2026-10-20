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
import org.eclipse.draw2d.Graphics;
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

	/**
	 * Painting does not depend on the figure's size: neither the class nor a
	 * superclass below Figure declares paint, paintFigure, paintBorder,
	 * paintClientArea or paintChildren, except the reviewed size-free ones
	 * ({@link #SIZE_FREE_PAINTERS}). A resize then leaves the shared area's pixels valid.
	 */
	public static final int SIZE_FREE = 1 << 8;

	/** primTranslate keeps the children where they are (freeform containers): a move does not move the content. */
	public static final int KEEPS_CHILDREN = 1 << 9;

	/**
	 * Classes whose paint overrides were reviewed as size-free: Viewport and the
	 * scalable panes translate and scale by their view location and scale only,
	 * ConnectionLayer sets the antialias.
	 */
	static final java.util.Set<String> SIZE_FREE_PAINTERS = java.util.Set.of("org.eclipse.draw2d.Viewport", //$NON-NLS-1$
			"org.eclipse.draw2d.ScalableLayeredPane", "org.eclipse.draw2d.ScalableFreeformLayeredPane", //$NON-NLS-1$ //$NON-NLS-2$
			"org.eclipse.draw2d.ConnectionLayer"); //$NON-NLS-1$

	/** Classes whose primTranslate moves only their own bounds. */
	static final java.util.Set<String> CHILD_KEEPERS = java.util.Set.of("org.eclipse.draw2d.FreeformLayer", //$NON-NLS-1$
			"org.eclipse.draw2d.FreeformLayeredPane"); //$NON-NLS-1$

	/**
	 * A container whose invalidations run Figure.revalidate and Figure.invalidate
	 * unchanged, so the index can tell an add, remove, setConstraint, resize or
	 * child revalidate from a direct revalidate.
	 */
	public static final int INVALIDATION = 1 << 7;

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
		if (!declares(type, "invalidate") && !declares(type, "revalidate")) { //$NON-NLS-1$ //$NON-NLS-2$
			bits |= INVALIDATION;
		}
		if (paintsSizeFree(type)) {
			bits |= SIZE_FREE;
		}
		Class<?> translator = declarer(type, "primTranslate", int.class, int.class); //$NON-NLS-1$
		if (translator != null && CHILD_KEEPERS.contains(translator.getName())) {
			bits |= KEEPS_CHILDREN;
		}
		return bits;
	}

	private static final String[] PAINT_METHODS = { "paint", "paintFigure", "paintBorder", "paintClientArea", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"paintChildren" }; //$NON-NLS-1$

	static boolean paintsSizeFree(Class<?> type) {
		for (Class<?> c = type; c != Figure.class; c = c.getSuperclass()) {
			for (String name : PAINT_METHODS) {
				if (declaresHere(c, name, Graphics.class) && !SIZE_FREE_PAINTERS.contains(c.getName())) {
					return false;
				}
			}
		}
		return true;
	}

	/** The class nearest to {@code type}, below Figure, that declares the method, or null. */
	static Class<?> declarer(Class<?> type, String name, Class<?>... parameters) {
		for (Class<?> c = type; c != Figure.class; c = c.getSuperclass()) {
			if (declaresHere(c, name, parameters)) {
				return c;
			}
		}
		return null;
	}

	private static boolean declaresHere(Class<?> c, String name, Class<?>... parameters) {
		try {
			c.getDeclaredMethod(name, parameters);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
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
