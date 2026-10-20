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

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;

import org.eclipse.swt.widgets.Scrollable;

import org.eclipse.draw2d.UpdateManager;

/**
 * Hidden viewports (brief rule 24): while the canvas of an update manager is
 * hidden (disposed, not visible itself or through an ancestor such as an
 * unselected editor tab, or with an empty client area), the deferred updates
 * do nothing: model changes still reach the figures and the child indexes,
 * invalid figures and dirty regions accumulate, but there is no validation and
 * no paint. When the canvas shows, the window system paints all of it once;
 * the first update validates once and drops the regions dirtied while hidden
 * instead of painting them a second time, and the window system's paint drops
 * them too when it comes first. An explicit performUpdate while hidden still
 * validates (the caller asked for current bounds) but does not paint.
 */
public final class M3Visibility {

	/** {@link #repair} answer: paint as stock. */
	public static final int PAINT = 0;
	/** {@link #repair} answer: hidden, keep the dirty regions and do not paint. */
	public static final int KEEP = 1;
	/** {@link #repair} answer: just shown, drop the dirty regions (the window system paints everything). */
	public static final int DROP = 2;

	private static final int VISIBLE = 0;
	private static final int DEFERRED = 1;
	private static final int SHOWN = 2;

	private static final Map<UpdateManager, M3Visibility> MANAGERS = Collections.synchronizedMap(new WeakHashMap<>());

	private final BooleanSupplier hidden;
	private int state = VISIBLE;

	private M3Visibility(BooleanSupplier hidden) {
		this.hidden = hidden;
	}

	/** Registers how to tell whether the canvas of {@code manager} is hidden (viewport mode only). */
	public static void register(UpdateManager manager, BooleanSupplier hidden) {
		if (M3ViewportMode.ENABLED && manager != null) {
			MANAGERS.put(manager, new M3Visibility(hidden));
		}
	}

	/** Hook body of LightweightSystem.setControl and setUpdateManager. */
	public static void register(UpdateManager manager, Scrollable canvas) {
		register(manager, () -> canvas.isDisposed() || !canvas.isVisible() || canvas.getClientArea().isEmpty());
	}

	/**
	 * Hook body of DeferredUpdateManager.UpdateRequest.run: true when the canvas is
	 * hidden and the update is deferred (no validation, no paint, everything
	 * kept for later).
	 */
	public static boolean defer(UpdateManager manager) {
		M3Visibility v = MANAGERS.get(manager);
		if (v == null) {
			return false;
		}
		if (v.hidden.getAsBoolean()) {
			v.state = DEFERRED;
			M3DeltaGate.deferredUpdates++;
			return true;
		}
		if (v.state == DEFERRED) {
			v.state = SHOWN;
		}
		return false;
	}

	/** Hook body at the start of DeferredUpdateManager.repairDamage: {@link #PAINT}, {@link #KEEP} or {@link #DROP}. */
	public static int repair(UpdateManager manager) {
		M3Visibility v = MANAGERS.get(manager);
		if (v == null) {
			return PAINT;
		}
		if (v.hidden.getAsBoolean()) {
			v.state = DEFERRED;
			M3DeltaGate.deferredRepairs++;
			return KEEP;
		}
		if (v.state != VISIBLE) {
			v.state = VISIBLE;
			M3DeltaGate.droppedRepairs++;
			return DROP;
		}
		return PAINT;
	}

	/**
	 * Hook body at the end of DeferredUpdateManager.paint(GC) (the window system's
	 * paint): true when the canvas was hidden since the last update; the hook then
	 * drops the regions dirtied while hidden if the paint covered the whole root.
	 */
	public static boolean painted(UpdateManager manager) {
		M3Visibility v = MANAGERS.get(manager);
		if (v == null || v.state == VISIBLE) {
			return false;
		}
		v.state = VISIBLE;
		return true;
	}
}
