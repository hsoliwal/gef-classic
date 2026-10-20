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
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.function.IntConsumer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Time and bytes allocated per operation (the measuring thread's allocation
 * counter) for the Draw2d hot paths, painting into {@link M3NullGraphics} so
 * the bytes are Draw2d's own. Public API only: the same class measures stock
 * and the M3 build. Usage:
 * {@code M3AllocBench <out.tsv> <label> <nodes>}; one JVM per size.
 */
public final class M3AllocBench {

	private static final com.sun.management.ThreadMXBean THREADS = (com.sun.management.ThreadMXBean) ManagementFactory
			.getThreadMXBean();

	private final StringBuilder out = new StringBuilder();
	private final String label;
	private final int nodes;

	private M3AllocBench(String label, int nodes) {
		this.label = label;
		this.nodes = nodes;
	}

	public static void main(String[] args) throws IOException {
		M3AllocBench bench = new M3AllocBench(args[1], Integer.parseInt(args[2]));
		bench.run();
		Files.writeString(Path.of(args[0]), bench.out.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	/** Median nanoseconds and mean bytes per call of {@code body} over {@code reps} calls after warm-up. */
	private void measure(String op, int warm, int reps, IntConsumer body) {
		// warm up for at least 2 s (C2 and escape analysis), at most 200000 calls
		long until = System.nanoTime() + 2_000_000_000L;
		for (int i = 0; i < warm || (i < 200000 && System.nanoTime() < until); i++) {
			body.accept(i);
		}
		long[] t = new long[reps];
		long tid = Thread.currentThread().getId();
		long bytes = THREADS.getThreadAllocatedBytes(tid);
		for (int i = 0; i < reps; i++) {
			long s = System.nanoTime();
			body.accept(i);
			t[i] = System.nanoTime() - s;
		}
		bytes = THREADS.getThreadAllocatedBytes(tid) - bytes;
		Arrays.sort(t);
		out.append(label).append('\t').append(nodes).append('\t').append(op).append('\t').append(t[reps / 2])
				.append('\t').append(bytes / reps).append('\n');
	}

	private void run() {
		long s = System.nanoTime();
		M3DiagramFixture d = new M3DiagramFixture(nodes, 1200, 800, 42);
		out.append(label).append('\t').append(nodes).append("\tbuild+validate\t").append(System.nanoTime() - s) //$NON-NLS-1$
				.append("\t-\n"); //$NON-NLS-1$
		M3NullGraphics g = new M3NullGraphics();
		d.manager.nullGraphics = g;
		int reps = nodes >= 1000000 ? 21 : 201;
		Rectangle view = new Rectangle(0, 0, 1200, 800);
		Rectangle damage = new Rectangle(300, 200, 50, 50);
		measure("paint-viewport", 50, reps, i -> d.root.paint(g.reset(view))); //$NON-NLS-1$
		measure("paint-damage-50x50", 200, reps, i -> d.root.paint(g.reset(damage))); //$NON-NLS-1$
		SplittableRandom r = new SplittableRandom(7);
		int[] px = new int[1024];
		int[] py = new int[1024];
		for (int i = 0; i < px.length; i++) {
			px[i] = r.nextInt(1200);
			py[i] = r.nextInt(800);
		}
		measure("findFigureAt", 2000, 20 * reps, i -> d.root.findFigureAt(px[i & 1023], py[i & 1023])); //$NON-NLS-1$
		List<? extends IFigure> list = d.layer.getChildren();
		for (int k = 0; k < list.size(); k += 10) {
			list.get(k).addMouseListener(new MouseListener.Stub());
		}
		measure("findMouseEventTargetAt", 2000, 20 * reps, //$NON-NLS-1$
				i -> d.root.findMouseEventTargetAt(px[i & 1023], py[i & 1023]));
		IFigure mover = list.get(list.size() / 2);
		Rectangle home = mover.getBounds().getCopy();
		Rectangle[] spots = { home.getTranslated(9, 3), home.getTranslated(0, 3) };
		measure("move-node+update", 20, reps, i -> { //$NON-NLS-1$
			d.layer.setConstraint(mover, spots[i & 1]);
			d.manager.flush();
		});
		IFigure top = list.get(0);
		Rectangle topHome = top.getBounds().getCopy();
		Rectangle[] topSpots = { topHome.getTranslated(7, 0), topHome };
		measure("move-visible-node+update", 20, reps, i -> { //$NON-NLS-1$
			d.layer.setConstraint(top, topSpots[i & 1]);
			d.manager.flush();
		});
		int[] scroll = new int[1];
		measure("scroll+update", 20, reps, i -> { //$NON-NLS-1$
			d.viewport.setViewLocation((scroll[0] += 37) % 900, (scroll[0] * 3) % 700);
			d.manager.flush();
		});
		// z-order inserts at one spot (send to back, behind the first node): label gaps run out there
		Rectangle spot = new Rectangle(5000, 5000, 10, 10);
		int inserts = 4000;
		long[] t = new long[inserts];
		long tid = Thread.currentThread().getId();
		long bytes = THREADS.getThreadAllocatedBytes(tid);
		for (int i = 0; i < inserts; i++) {
			long s0 = System.nanoTime();
			d.layer.add(new org.eclipse.draw2d.RectangleFigure(), spot, 1);
			d.manager.flush();
			t[i] = System.nanoTime() - s0;
		}
		bytes = THREADS.getThreadAllocatedBytes(tid) - bytes;
		long sum = 0;
		for (long v : t) {
			sum += v;
		}
		Arrays.sort(t);
		out.append(label).append('\t').append(nodes).append("\tinsert-at-1+update (mean of 4000)\t") //$NON-NLS-1$
				.append(sum / inserts).append('\t').append(bytes / inserts).append('\n');
		out.append(label).append('\t').append(nodes).append("\tinsert-at-1+update (p99 of 4000)\t") //$NON-NLS-1$
				.append(t[inserts * 99 / 100]).append("\t-\n"); //$NON-NLS-1$
	}
}
