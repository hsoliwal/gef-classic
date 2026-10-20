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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * Per-step cost of viewport deltas (canvas resize steps and zoom steps):
 * median time of resize + update, repaired pixels, and, on M3 builds, children
 * painted and validated per step. Public API only, so the same class measures
 * stock and the M3 build. Usage: {@code M3ViewportDeltaBench <out.tsv> <label> <nodes>}.
 */
public final class M3ViewportDeltaBench {

	private M3ViewportDeltaBench() {
	}

	private static long counter(String name) {
		try {
			return Class.forName("org.eclipse.draw2d.internal.m3.M3DeltaGate").getField(name).getLong(null); //$NON-NLS-1$
		} catch (ReflectiveOperationException e) {
			return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		String label = args[1];
		int nodes = Integer.parseInt(args[2]);
		M3DiagramFixture d = new M3DiagramFixture(nodes, 900, 600, 21);
		d.manager.nullGraphics = null;
		StringBuilder out = new StringBuilder();
		int steps = 60;
		long[] t = new long[steps];
		long area = 0;
		long painted0 = counter("paintedChildren"); //$NON-NLS-1$
		long validated0 = counter("validatedChildren"); //$NON-NLS-1$
		long area0 = d.manager.repairedArea;
		for (int i = 0; i < steps; i++) {
			int w = 900 + 13 * (i % 30) * (i < 30 ? 1 : -1) + (i < 30 ? 0 : 390);
			int h = 600 + 9 * (i % 30) * (i < 30 ? 1 : -1) + (i < 30 ? 0 : 270);
			long s = System.nanoTime();
			d.resize(w, h);
			d.manager.flush();
			t[i] = System.nanoTime() - s;
		}
		area = d.manager.repairedArea - area0;
		Arrays.sort(t);
		row(out, label, nodes, "resize step (diagonal, 60 steps)", t[steps / 2], area / steps, //$NON-NLS-1$
				per(counter("paintedChildren"), painted0, steps), per(counter("validatedChildren"), validated0, steps)); //$NON-NLS-1$ //$NON-NLS-2$
		double[] scales = { 0.9, 0.8, 0.7, 0.6, 0.5, 0.6, 0.75, 1, 1.25, 1.5, 2, 1.5, 1 };
		long[] z = new long[scales.length];
		painted0 = counter("paintedChildren"); //$NON-NLS-1$
		validated0 = counter("validatedChildren"); //$NON-NLS-1$
		area0 = d.manager.repairedArea;
		for (int i = 0; i < scales.length; i++) {
			long s = System.nanoTime();
			d.pane.setScale(scales[i]);
			d.manager.flush();
			z[i] = System.nanoTime() - s;
		}
		area = d.manager.repairedArea - area0;
		Arrays.sort(z);
		row(out, label, nodes, "zoom step (13 steps)", z[scales.length / 2], area / scales.length, //$NON-NLS-1$
				per(counter("paintedChildren"), painted0, scales.length), //$NON-NLS-1$
				per(counter("validatedChildren"), validated0, scales.length)); //$NON-NLS-1$
		Files.writeString(Path.of(args[0]), out.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	private static String per(long now, long before, int steps) {
		return now < 0 ? "-" : Long.toString((now - before) / steps); //$NON-NLS-1$
	}

	private static void row(StringBuilder out, String label, int nodes, String op, long nanos, long pixels,
			String painted, String validated) {
		out.append(label).append('\t').append(nodes).append('\t').append(op).append('\t').append(nanos).append('\t')
				.append(pixels).append('\t').append(painted).append('\t').append(validated).append('\n');
	}
}
