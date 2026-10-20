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
package org.eclipse.draw2d.test.m3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

/**
 * Recorded differential of the Draw2d viewport mode: the same seeded
 * {@link M3Draw2dScript} runs in one class loader over stock Draw2d (the
 * reference) and in another over the M3 build, and the two logs must be
 * identical line for line. Needs the class directories of both builds:
 * {@code -Dm3.draw2d.stock}, {@code -Dm3.draw2d.m3}, {@code -Dm3.draw2d.tests}
 * and {@code -Dm3.swt.jar} (set by docs/m3/gef-viewport/tools/test.sh); skipped
 * without them. {@code -Dm3.differential.seeds} sets the number of seeds.
 */
public class M3ViewportDifferentialTest {

	private static final int SEEDS = Integer.getInteger("m3.differential.seeds", 24).intValue(); //$NON-NLS-1$
	private static final int STEPS = Integer.getInteger("m3.differential.steps", 400).intValue(); //$NON-NLS-1$

	private static URL url(String property) throws MalformedURLException {
		String path = System.getProperty(property);
		assumeTrue(path != null, property + " not set"); //$NON-NLS-1$
		return new File(path).toURI().toURL();
	}

	/** A loader over one Draw2d build and the script, with the M3 mode switches set while it initializes. */
	static ClassLoader loader(String build, String mode, boolean trust) throws Exception {
		return loader(build, mode, trust, true);
	}

	/** Same, with the verify switch chosen. */
	static ClassLoader loader(String build, String mode, boolean trust, boolean verify) throws Exception {
		return loader(build, mode, trust ? "trusted" : "attributed", verify, null); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Same, with the XY constraint policy ({@code exact}, {@code attributed},
	 * {@code trusted}) and the index threshold ({@code null}: default) chosen.
	 */
	static ClassLoader loader(String build, String mode, String constraints, boolean verify, String minChildren)
			throws Exception {
		URLClassLoader swt = new URLClassLoader(new URL[] { url("m3.swt.jar") }, //$NON-NLS-1$
				ClassLoader.getPlatformClassLoader());
		URLClassLoader loader = new URLClassLoader(new URL[] { url(build), url("m3.draw2d.tests") }, swt); //$NON-NLS-1$
		synchronized (M3ViewportDifferentialTest.class) {
			String[] keys = { "draw2d.viewport.mode", "draw2d.viewport.verify", "draw2d.viewport.trustConstraints", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"draw2d.viewport.constraints", "draw2d.viewport.minChildren" }; //$NON-NLS-1$ //$NON-NLS-2$
			String[] saved = new String[keys.length];
			for (int i = 0; i < keys.length; i++) {
				saved[i] = System.getProperty(keys[i]);
			}
			System.setProperty("draw2d.viewport.mode", mode); //$NON-NLS-1$
			System.setProperty("draw2d.viewport.verify", Boolean.toString(verify)); //$NON-NLS-1$
			System.clearProperty("draw2d.viewport.trustConstraints"); //$NON-NLS-1$
			System.setProperty("draw2d.viewport.constraints", constraints); //$NON-NLS-1$
			if (minChildren == null) {
				System.clearProperty("draw2d.viewport.minChildren"); //$NON-NLS-1$
			} else {
				System.setProperty("draw2d.viewport.minChildren", minChildren); //$NON-NLS-1$
			}
			try {
				Class.forName("org.eclipse.draw2d.internal.m3.M3ViewportMode", true, loader); //$NON-NLS-1$
			} catch (ClassNotFoundException e) {
				// the stock build has no M3 classes
			} finally {
				for (int i = 0; i < keys.length; i++) {
					if (saved[i] == null) {
						System.clearProperty(keys[i]);
					} else {
						System.setProperty(keys[i], saved[i]);
					}
				}
			}
		}
		return loader;
	}

	static String run(ClassLoader loader, long seed, int count, boolean mutate) throws Exception {
		Class<?> script = Class.forName("org.eclipse.draw2d.test.m3.M3Draw2dScript", true, loader); //$NON-NLS-1$
		return (String) script.getMethod("run", long.class, int.class, int.class, boolean.class).invoke(null, //$NON-NLS-1$
				Long.valueOf(seed), Integer.valueOf(count), Integer.valueOf(STEPS), Boolean.valueOf(mutate));
	}

	static void compare(String variant, String expected, String actual, long seed) {
		if (expected.equals(actual)) {
			return;
		}
		String[] e = expected.split("\n"); //$NON-NLS-1$
		String[] a = actual.split("\n"); //$NON-NLS-1$
		int i = 0;
		while (i < e.length && i < a.length && e[i].equals(a[i])) {
			i++;
		}
		StringBuilder context = new StringBuilder();
		for (int k = Math.max(0, i - 6); k < i; k++) {
			context.append("  ").append(e[k]).append('\n'); //$NON-NLS-1$
		}
		fail(variant + " differs from stock at seed " + seed + ", line " + i + ":\n" + context + "  stock: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ (i < e.length ? e[i] : "<end>") + "\n  m3:    " + (i < a.length ? a[i] : "<end>")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private static void differential(String variant, String mode, boolean trust, boolean mutate) throws Exception {
		differential(variant, mode, trust, mutate, true);
	}

	private static void differential(String variant, String mode, boolean trust, boolean mutate, boolean verify)
			throws Exception {
		ClassLoader stock = loader("m3.draw2d.stock", "original", false); //$NON-NLS-1$ //$NON-NLS-2$
		ClassLoader m3 = loader("m3.draw2d.m3", mode, trust, verify); //$NON-NLS-1$
		for (long seed = 1; seed <= SEEDS; seed++) {
			int count = 70 + (int) (seed * 37 % 500);
			compare(variant, run(stock, seed, count, mutate), run(m3, seed, count, mutate), seed);
		}
	}

	@Test
	public void viewportModeMatchesStock() throws Exception {
		differential("viewport", "viewport", false, true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void viewportModeWithTrustedConstraintsMatchesStock() throws Exception {
		differential("viewport+trustConstraints", "viewport", true, false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void viewportModeWithoutVerifyMatchesStock() throws Exception {
		differential("viewport, no verify", "viewport", false, true, false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void verifyCatchesInPlaceMutationUnderTrustedConstraints() throws Exception {
		ClassLoader m3 = loader("m3.draw2d.m3", "viewport", true, true); //$NON-NLS-1$ //$NON-NLS-2$
		boolean caught = false;
		for (long seed = 1; seed <= 4 && !caught; seed++) {
			caught = run(m3, seed, 300, true).contains("error java.lang.IllegalStateException"); //$NON-NLS-1$
		}
		assertTrue(caught, "verify mode must reject in-place constraint changes when trustConstraints is set"); //$NON-NLS-1$
	}

	@Test
	public void originalModeLogsItsSelectionAtFine() throws Exception {
		Logger log = Logger.getLogger("org.eclipse.draw2d.internal.m3"); //$NON-NLS-1$
		Level level = log.getLevel();
		log.setLevel(Level.FINE);
		try {
			ClassLoader original = loader("m3.draw2d.m3", "original", false, false); //$NON-NLS-1$ //$NON-NLS-2$
			assertFalse(Class.forName("org.eclipse.draw2d.internal.m3.M3ViewportMode", true, original) //$NON-NLS-1$
					.getField("ENABLED").getBoolean(null)); //$NON-NLS-1$
		} finally {
			log.setLevel(level);
		}
	}

	@Test
	public void originalModeMatchesStock() throws Exception {
		differential("original", "original", false, true); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
