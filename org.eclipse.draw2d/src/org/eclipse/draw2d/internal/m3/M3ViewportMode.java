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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Startup switch of the Draw2d viewport mode.
 * <p>
 * {@code -Ddraw2d.viewport.mode=viewport} makes containers with many children
 * keep a child index ({@link M3FigureChildren}) so that painting, hit testing,
 * validation, XY layout and freeform extents cost work proportional to the
 * visible or changed children instead of all children. Any other value, or no
 * value, keeps stock Draw2d ({@code original}); the hooks then reduce to one
 * constant test.
 * </p>
 * <p>
 * {@code -Ddraw2d.viewport.minChildren=<n>} (default 64) is the child count at
 * which a container gets its index. {@code -Ddraw2d.viewport.verify=true}
 * re-checks every indexed answer against the stock scan and throws
 * {@link IllegalStateException} on a difference (test runs only).
 * </p>
 */
public final class M3ViewportMode {

	/** Logger of the Draw2d viewport mode. */
	static final Logger LOG = Logger.getLogger("org.eclipse.draw2d.internal.m3"); //$NON-NLS-1$

	/** Whether the viewport mode is on. */
	public static final boolean ENABLED = "viewport".equals(System.getProperty("draw2d.viewport.mode")); //$NON-NLS-1$ //$NON-NLS-2$

	/** Child count at which a container gets its child index. */
	public static final int MIN_CHILDREN = Math.max(1, Integer.getInteger("draw2d.viewport.minChildren", 64)); //$NON-NLS-1$

	/**
	 * Whether XY layout constraints are only ever replaced through setConstraint,
	 * never changed in place; lets the XY layout skip its snapshot sweep.
	 */
	public static final boolean TRUST_CONSTRAINTS = Boolean.getBoolean("draw2d.viewport.trustConstraints"); //$NON-NLS-1$

	/** Whether every indexed answer is re-checked against the stock scan. */
	public static final boolean VERIFY = Boolean.getBoolean("draw2d.viewport.verify"); //$NON-NLS-1$

	static {
		Level level = ENABLED ? Level.INFO : Level.FINE;
		if (LOG.isLoggable(level)) {
			LOG.log(level, "draw2d viewport mode " + (ENABLED ? "viewport" : "original") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ ", minChildren " + MIN_CHILDREN + (TRUST_CONSTRAINTS ? ", trustConstraints" : "") + (VERIFY ? ", verify" : "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
	}

	private M3ViewportMode() {
	}
}
