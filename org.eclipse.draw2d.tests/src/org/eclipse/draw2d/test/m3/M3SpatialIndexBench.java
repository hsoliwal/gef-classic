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
import java.util.Arrays;
import java.util.SplittableRandom;

import org.eclipse.draw2d.internal.m3.M3SpatialGrid;

/**
 * Rule-21 candidate measurement for the child index (problem category:
 * orthogonal range / stabbing queries over a dynamic set of rectangles):
 * linear lane scan, uniform-grid spatial hash ({@link M3SpatialGrid}), and
 * sort-and-sweep (rectangles sorted by x, binary search bounded by the widest
 * rectangle). Each answers a viewport query, a point query and a one-rectangle
 * move on the same data. Usage: {@code M3SpatialIndexBench <out.tsv> <n>}.
 */
public final class M3SpatialIndexBench {

	private final int n;
	private final int[] x;
	private final int[] y;
	private final int[] w;
	private final int[] h;
	private long sink;

	private M3SpatialIndexBench(int n) {
		this.n = n;
		SplittableRandom r = new SplittableRandom(3);
		int columns = (int) Math.ceil(Math.sqrt(n * 1.5));
		x = new int[n];
		y = new int[n];
		w = new int[n];
		h = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = (i % columns) * 60 + r.nextInt(8);
			y[i] = (i / columns) * 40 + r.nextInt(6);
			w[i] = 40 + r.nextInt(8);
			h[i] = 24 + r.nextInt(4);
		}
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(args[1]);
		M3SpatialIndexBench b = new M3SpatialIndexBench(n);
		StringBuilder out = new StringBuilder();
		b.linear(out);
		b.grid(out);
		b.sweep(out);
		Files.writeString(Path.of(args[0]), out.toString(), StandardCharsets.UTF_8,
				java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
	}

	private static long time(Runnable body, int reps) {
		for (int i = 0; i < 3000; i++) {
			body.run();
		}
		long[] t = new long[reps];
		for (int i = 0; i < reps; i++) {
			long s = System.nanoTime();
			body.run();
			t[i] = System.nanoTime() - s;
		}
		Arrays.sort(t);
		return t[reps / 2];
	}

	private void row(StringBuilder out, String candidate, long rect, long point, long move) {
		out.append(n).append('\t').append(candidate).append('\t').append(rect).append('\t').append(point).append('\t')
				.append(move).append('\t').append(sink).append('\n');
	}

	private void linear(StringBuilder out) {
		long rect = time(() -> {
			int c = 0;
			for (int i = 0; i < n; i++) {
				c += x[i] < 3000 + 1200 && 3000 < x[i] + w[i] && y[i] < 2000 + 800 && 2000 < y[i] + h[i] ? 1 : 0;
			}
			sink += c;
		}, 301);
		long point = time(() -> {
			int c = 0;
			for (int i = 0; i < n; i++) {
				c += 3010 >= x[i] && 3010 < x[i] + w[i] && 2010 >= y[i] && 2010 < y[i] + h[i] ? 1 : 0;
			}
			sink += c;
		}, 301);
		row(out, "linear-lanes", rect, point, 0); //$NON-NLS-1$
	}

	private void grid(StringBuilder out) {
		M3SpatialGrid g = new M3SpatialGrid(7);
		for (int i = 0; i < n; i++) {
			g.put(i, x[i], y[i], w[i], h[i]);
		}
		long rect = time(() -> sink += g.query(3000, 2000, 1200, 800), 301);
		long point = time(() -> sink += g.queryPoint(3010, 2010), 301);
		int[] k = new int[1];
		long move = time(() -> {
			int i = n / 2;
			g.put(i, x[i] + (k[0]++ & 1) * 9, y[i], w[i], h[i]);
		}, 301);
		row(out, "uniform-grid", rect, point, move); //$NON-NLS-1$
	}

	private void sweep(StringBuilder out) {
		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++) {
			order[i] = Integer.valueOf(i);
		}
		Arrays.sort(order, (a, b) -> Integer.compare(x[a.intValue()], x[b.intValue()]));
		int[] sx = new int[n];
		int[] id = new int[n];
		int maxW = 0;
		for (int i = 0; i < n; i++) {
			id[i] = order[i].intValue();
			sx[i] = x[id[i]];
			maxW = Math.max(maxW, w[id[i]]);
		}
		int widest = maxW;
		long rect = time(() -> {
			int from = lowerBound(sx, 3000 - widest), c = 0;
			for (int i = from; i < n && sx[i] < 3000 + 1200; i++) {
				int s = id[i];
				c += 3000 < x[s] + w[s] && y[s] < 2000 + 800 && 2000 < y[s] + h[s] ? 1 : 0;
			}
			sink += c;
		}, 301);
		long point = time(() -> {
			int from = lowerBound(sx, 3010 - widest), c = 0;
			for (int i = from; i < n && sx[i] <= 3010; i++) {
				int s = id[i];
				c += 3010 < x[s] + w[s] && 2010 >= y[s] && 2010 < y[s] + h[s] ? 1 : 0;
			}
			sink += c;
		}, 301);
		long move = time(() -> {
			// a move re-sorts one entry: shift the run between its old and new position
			int at = n / 2;
			System.arraycopy(sx, at + 1, sx, at, n - at - 1);
			System.arraycopy(id, at + 1, id, at, n - at - 1);
			System.arraycopy(sx, at, sx, at + 1, n - at - 1);
			System.arraycopy(id, at, id, at + 1, n - at - 1);
		}, 301);
		row(out, "sort-and-sweep", rect, point, move); //$NON-NLS-1$
	}

	private static int lowerBound(int[] a, int key) {
		int lo = 0, hi = a.length;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (a[mid] < key) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}
}
