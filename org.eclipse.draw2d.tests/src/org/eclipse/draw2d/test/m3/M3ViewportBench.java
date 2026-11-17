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
import java.util.List;
import java.util.SplittableRandom;
import java.util.function.IntConsumer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Headless Draw2d benchmark over the GEF figure stack ({@link M3DiagramFixture}):
 * paint, scroll, zoom, hit tests, moves, adds, removes and damage repair at
 * several diagram sizes. It uses public API only, so the same class measures the
 * stock build and the M3 build (mode chosen with
 * {@code -Ddraw2d.viewport.mode}).
 *
 * <p>
 * Usage: {@code M3ViewportBench <out.tsv> <label> <sizes, e.g. 1000,10000,100000>}.
 * Each row: label, nodes, operation, median nanoseconds, a work counter (graphics
 * operations or answers) and a checksum, appended as TSV.
 * </p>
 */
public final class M3ViewportBench {

	private static final int VIEW_W = 1200;
	private static final int VIEW_H = 800;

	private final StringBuilder out = new StringBuilder();
	private final String label;
	private long sink;

	private M3ViewportBench(String label) {
		this.label = label;
	}

	public static void main(String[] args) throws IOException {
		M3ViewportBench bench = new M3ViewportBench(args[1]);
		for (String size : args[2].split(",")) { //$NON-NLS-1$
			bench.run(Integer.parseInt(size.trim()));
		}
		Files.writeString(Path.of(args[0]), bench.out.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	private void row(int nodes, String op, long nanos, long work) {
		out.append(label).append('\t').append(nodes).append('\t').append(op).append('\t').append(nanos).append('\t')
				.append(work).append('\t').append(sink).append('\n');
	}

	/** Median of {@code reps} timed runs of {@code body} after {@code warm} untimed runs. */
	private static long median(int warm, int reps, IntConsumer body) {
		for (int i = 0; i < warm; i++) {
			body.accept(i);
		}
		long[] t = new long[reps];
		for (int i = 0; i < reps; i++) {
			long s = System.nanoTime();
			body.accept(i);
			t[i] = System.nanoTime() - s;
		}
		Arrays.sort(t);
		return t[reps / 2];
	}

	private void run(int nodes) {
		long s = System.nanoTime();
		M3DiagramFixture d = new M3DiagramFixture(nodes, VIEW_W, VIEW_H, 42);
		row(nodes, "build+validate", System.nanoTime() - s, d.layer.getChildren().size()); //$NON-NLS-1$
		int reps = nodes >= 100000 ? 15 : 41;
		Rectangle view = new Rectangle(0, 0, VIEW_W, VIEW_H);
		int[] work = new int[1];
		row(nodes, "paint-viewport", median(5, reps, i -> work[0] = d.paint(view, false).operations()), work[0]); //$NON-NLS-1$
		Rectangle damage = new Rectangle(300, 200, 50, 50);
		row(nodes, "paint-damage-50x50", median(5, reps, i -> work[0] = d.paint(damage, false).operations()), //$NON-NLS-1$
				work[0]);
		int maxX = Math.max(1, d.diagramWidth() - VIEW_W);
		int maxY = Math.max(1, d.diagramHeight() - VIEW_H);
		row(nodes, "scroll+paint", median(5, reps, i -> { //$NON-NLS-1$
			d.viewport.setViewLocation((i * 7919) % maxX, (i * 104729) % maxY);
			d.manager.flush();
			work[0] = d.paint(view, false).operations();
		}), work[0]);
		d.viewport.setViewLocation(0, 0);
		d.pane.setScale(0.25);
		d.manager.flush();
		row(nodes, "zoom0.25+paint", median(3, reps, i -> work[0] = d.paint(view, false).operations()), work[0]); //$NON-NLS-1$
		d.pane.setScale(1);
		d.manager.flush();
		SplittableRandom r = new SplittableRandom(7);
		int[] px = new int[1024];
		int[] py = new int[1024];
		for (int i = 0; i < px.length; i++) {
			px[i] = r.nextInt(VIEW_W);
			py[i] = r.nextInt(VIEW_H);
		}
		row(nodes, "findFigureAt", median(3, reps, i -> { //$NON-NLS-1$
			int hits = 0;
			for (int k = 0; k < px.length; k++) {
				hits += d.root.findFigureAt(px[k], py[k]) != d.root ? 1 : 0;
			}
			work[0] = hits;
		}) / px.length, work[0]);
		List<? extends IFigure> nodesList = d.layer.getChildren();
		for (int k = 0; k < nodesList.size(); k += 10) {
			nodesList.get(k).addMouseListener(new MouseListener.Stub());
		}
		row(nodes, "findMouseEventTargetAt", median(3, reps, i -> { //$NON-NLS-1$
			int hits = 0;
			for (int k = 0; k < px.length; k++) {
				hits += d.root.findMouseEventTargetAt(px[k], py[k]) != null ? 1 : 0;
			}
			work[0] = hits;
		}) / px.length, work[0]);
		IFigure mover = nodesList.get(nodesList.size() / 2);
		Rectangle home = mover.getBounds().getCopy();
		row(nodes, "move-node+update", median(5, reps, i -> { //$NON-NLS-1$
			d.layer.setConstraint(mover, home.getTranslated((i & 1) * 9, 3));
			d.manager.flush();
			work[0] = d.manager.last == null ? 0 : d.manager.last.operations();
		}), work[0]);
		IFigure first = nodesList.get(0);
		IFigure last = nodesList.get(nodesList.size() - 1);
		Rectangle firstHome = first.getBounds().getCopy();
		Rectangle lastHome = last.getBounds().getCopy();
		row(nodes, "move-2-far-nodes+update", median(3, reps, i -> { //$NON-NLS-1$
			d.layer.setConstraint(first, firstHome.getTranslated((i & 1) * 5, 0));
			d.layer.setConstraint(last, lastHome.getTranslated((i & 1) * 5, 0));
			d.manager.flush();
			work[0] = d.manager.last == null ? 0 : d.manager.last.operations();
		}), work[0]);
		Point p = new Point();
		row(nodes, "freeform-extent-after-move", median(5, reps, i -> { //$NON-NLS-1$
			mover.setBounds(home.getTranslated((i & 1) * 11, 0));
			p.setLocation(d.layer.getFreeformExtent().getBottomRight());
			sink += p.x;
		}), 1);
		row(nodes, "add-node+update", median(3, reps, i -> { //$NON-NLS-1$
			d.addNode(nodes + i);
			d.manager.flush();
		}), d.layer.getChildren().size());
		row(nodes, "remove-middle-node+update", median(3, reps, i -> { //$NON-NLS-1$
			d.layer.remove(d.layer.getChildren().get(d.layer.getChildren().size() / 2));
			d.manager.flush();
		}), d.layer.getChildren().size());
	}
}
