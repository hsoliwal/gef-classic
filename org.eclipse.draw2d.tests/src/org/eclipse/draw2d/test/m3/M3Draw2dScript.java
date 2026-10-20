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

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Seeded random public-API script over a GEF figure stack, for the recorded
 * differential: every step appends what it observed (answers as figure ids,
 * repaint regions and graphics hashes, figure-moved and validate events, the
 * freeform extents, and periodically every node's bounds) to one log. Run in
 * separate class loaders against stock Draw2d and the M3 build, the logs must
 * be identical; the first differing line is the reproduction.
 */
public final class M3Draw2dScript {

	/** A figure whose class overrides setBounds: never trusted by the index. */
	static final class OverridingFigure extends Figure {
		@Override
		public void setBounds(Rectangle rect) {
			super.setBounds(rect);
		}
	}

	/** A figure whose class overrides validate, logs each call and now and then invalidates a partner. */
	final class ValidatingFigure extends Figure {
		IFigure partner;
		int calls;

		@Override
		public void validate() {
			log("validate " + id(this)); //$NON-NLS-1$
			if (partner != null && ++calls % 3 == 0) {
				partner.invalidate();
			}
			super.validate();
		}
	}

	/** A figure whose hit area is smaller than its bounds. */
	static final class InsetHitFigure extends Figure {
		@Override
		public boolean containsPoint(int x, int y) {
			return getBounds().getShrinked(2, 2).contains(x, y);
		}
	}

	private final StringBuilder out = new StringBuilder();
	private final Map<IFigure, Integer> ids = new IdentityHashMap<>();
	private final SplittableRandom random;
	private final boolean mutateInPlace;
	private final boolean deltaOnly;
	/** {@code M3DeltaGate.summary()} after the last run (M3 build only, else "-"). */
	static String gateSummary = "-"; //$NON-NLS-1$
	/** {@code M3DeltaGate.fullWork()} after the last run (M3 build only, else 0). */
	static int gateFullWork;
	/** Constraint sweeps after the last run (M3 build only, else 0). */
	static int gateSweeps;
	/** Whole-container dirty regions of the last run, counted from the reset on. */
	static int wholeRepaints;
	private final M3DiagramFixture d;
	private final Layer second = new Layer();
	private final Layer third = new Layer();
	private final FreeformLayer inner = new FreeformLayer();
	private boolean cascading;
	private int cascades;
	private final List<IFigure> nodes = new ArrayList<>();
	private int nextId;

	private M3Draw2dScript(long seed, int count, boolean mutateInPlace, boolean deltaOnly) {
		this.random = new SplittableRandom(seed);
		this.mutateInPlace = mutateInPlace;
		this.deltaOnly = deltaOnly;
		this.d = new M3DiagramFixture(0, 900, 600, seed);
		second.setLayoutManager(new XYLayout());
		d.pane.add(second, "second"); //$NON-NLS-1$
		third.setLayoutManager(new XYLayout() {
			// a subclass: the index leaves its layout to the stock loop
		});
		d.pane.add(third, "third"); //$NON-NLS-1$
		inner.setLayoutManager(new FreeformLayout());
		// no XY constraint: FreeformHelper.setFreeformBounds alone sizes a nested freeform layer
		d.layer.add(inner);
		ids.put(inner, Integer.valueOf(-10));
		if (deltaOnly) {
			frame();
		}
		for (int i = 0; i < count; i++) {
			addNode(-1);
		}
		d.layer.addFigureListener(f -> log("moved layer " + f.getBounds())); //$NON-NLS-1$
		flush();
	}

	/**
	 * Runs one script and returns its log.
	 *
	 * @param seed          random seed
	 * @param count         nodes at the start
	 * @param steps         random steps
	 * @param mutateInPlace whether steps may change XY constraints in place
	 */
	public static String run(long seed, int count, int steps, boolean mutateInPlace) {
		return run(seed, count, steps, mutateInPlace, false);
	}

	/**
	 * Runs one script and returns its log; with {@code deltaOnly} the steps are
	 * model deltas only (add, remove, setConstraint, setBounds, visibility,
	 * enablement, z-order, preferred size, front bursts, emptying a container)
	 * inside a fixed frame, so no step changes a freeform extent, scrolls or
	 * zooms, and the delta gate counters ({@link #gateFullWork},
	 * {@link #wholeRepaints}) must stay at zero from the first flush on.
	 */
	public static String run(long seed, int count, int steps, boolean mutateInPlace, boolean deltaOnly) {
		M3Draw2dScript s = new M3Draw2dScript(seed, count, mutateInPlace, deltaOnly);
		gate("reset"); //$NON-NLS-1$
		s.d.manager.wholeRepaints = 0;
		for (int i = 0; i < steps; i++) {
			s.log("step " + i); //$NON-NLS-1$
			try {
				s.step();
			} catch (RuntimeException e) {
				s.log("error " + e.getClass().getName()); //$NON-NLS-1$
			}
		}
		s.digest();
		wholeRepaints = s.d.manager.wholeRepaints;
		Object summary = gate("summary"); //$NON-NLS-1$
		gateSummary = summary == null ? "-" : summary.toString(); //$NON-NLS-1$
		Object work = gate("fullWork"); //$NON-NLS-1$
		gateFullWork = work == null ? 0 : ((Integer) work).intValue();
		gateSweeps = summary == null ? 0 : field("constraintSweeps"); //$NON-NLS-1$
		return s.out.toString();
	}

	/** Calls a static no-argument method of M3DeltaGate when the build has it (the stock build has not). */
	private static Object gate(String method) {
		try {
			return Class.forName("org.eclipse.draw2d.internal.m3.M3DeltaGate").getMethod(method).invoke(null); //$NON-NLS-1$
		} catch (ReflectiveOperationException e) {
			return null;
		}
	}

	private static int field(String name) {
		try {
			return Class.forName("org.eclipse.draw2d.internal.m3.M3DeltaGate").getField(name).getInt(null); //$NON-NLS-1$
		} catch (ReflectiveOperationException e) {
			return 0;
		}
	}

	/**
	 * Fixed nodes at the corners of the area the delta steps use, outside the
	 * node list, so the freeform extents never change; and enough children in
	 * the second layer and the nested freeform layer that they keep a child
	 * index from the first flush on (with a small draw2d.viewport.minChildren).
	 */
	private void frame() {
		IFigure[] parents = { d.layer, second, inner };
		for (int p = 0; p < parents.length; p++) {
			for (int k = 0; k < 4; k++) {
				RectangleFigure corner = new RectangleFigure();
				ids.put(corner, Integer.valueOf(-20 - 4 * p - k));
				parents[p].add(corner, new Rectangle(k % 2 == 0 ? -20 : 2100, k < 2 ? -20 : 1600, 4, 4));
			}
		}
	}

	void log(String line) {
		out.append(line).append('\n');
	}

	int id(IFigure f) {
		if (f == null) {
			return -1;
		}
		Integer id = ids.get(f);
		return id == null ? -2 : id.intValue();
	}

	private IFigure create() {
		IFigure f = switch (random.nextInt(9)) {
		case 0 -> new Figure();
		case 1 -> new Ellipse();
		case 2 -> new RoundedRectangle();
		case 3 -> new OverridingFigure();
		case 4 -> {
			ValidatingFigure v = new ValidatingFigure();
			v.partner = anyNode();
			yield v;
		}
		case 5 -> new InsetHitFigure();
		case 6 -> {
			Figure box = new Figure();
			box.setLayoutManager(new XYLayout());
			for (int k = 0; k < 3; k++) {
				RectangleFigure cell = new RectangleFigure();
				ids.put(cell, Integer.valueOf(nextId++));
				box.add(cell, new Rectangle(2 + 6 * k, 2, 5, 5));
			}
			yield box;
		}
		case 7 -> {
			if (deltaOnly) {
				// a polyline's bounds follow its points and may leave the frame
				yield new RectangleFigure();
			}
			Polyline line = new Polyline();
			line.setPoints(new PointList(new int[] { random.nextInt(2000), random.nextInt(1500),
					random.nextInt(2000), random.nextInt(1500) }));
			yield line;
		}
		default -> new RectangleFigure();
		};
		ids.put(f, Integer.valueOf(nextId++));
		if (f instanceof Figure plain && random.nextInt(4) == 0) {
			plain.setOpaque(true);
		}
		if (random.nextInt(5) == 0) {
			f.addMouseListener(new MouseListener.Stub());
		}
		if (random.nextInt(7) == 0) {
			f.addFigureListener(src -> log("moved " + id(src) + ' ' + src.getBounds())); //$NON-NLS-1$
		}
		IFigure partner = anyNode();
		if (partner != null && random.nextInt(25) == 0) {
			f.addFigureListener(src -> cascade(partner));
		}
		return f;
	}

	private Rectangle constraint() {
		int kind = random.nextInt(40);
		if (deltaOnly && (kind == 1 || kind == 3)) {
			// negative and far-off constraints leave the frame
			kind = 40;
		}
		if (kind == 0) {
			return new Rectangle(random.nextInt(2000), random.nextInt(1500), -1, -1);
		}
		if (kind == 1) {
			return new Rectangle(-random.nextInt(300), -random.nextInt(300), random.nextInt(900), random.nextInt(700));
		}
		if (kind == 2) {
			return new Rectangle(random.nextInt(2000), random.nextInt(1500), 0, random.nextInt(30));
		}
		if (kind == 3) {
			return new Rectangle(Integer.MAX_VALUE - random.nextInt(50), random.nextInt(1500), 100, 20);
		}
		if (kind == 4) {
			return new Rectangle(random.nextInt(2000), random.nextInt(1500), -1, 5 + random.nextInt(20));
		}
		if (kind == 5) {
			return new Rectangle(random.nextInt(2000), random.nextInt(1500), 5 + random.nextInt(20), -1);
		}
		return new Rectangle(random.nextInt(2000), random.nextInt(1500), 4 + random.nextInt(60), 4 + random.nextInt(40));
	}

	private IFigure parentFor() {
		return switch (random.nextInt(10)) {
		case 0, 1 -> second;
		case 2 -> deltaOnly ? second : third;
		case 3 -> inner;
		default -> d.layer;
		};
	}

	/**
	 * A figure listener that moves another figure (not recursively, and at most
	 * 48 times per script: unbounded cascades livelock stock Draw2d, since every
	 * layout pass moves a partner, which revalidates the layer again): changes
	 * during layout.
	 */
	private void cascade(IFigure partner) {
		if (!cascading && cascades < 48 && partner.getParent() != null) {
			cascades++;
			cascading = true;
			try {
				partner.setBounds(partner.getBounds().getTranslated(1, 1));
			} finally {
				cascading = false;
			}
		}
	}

	private void addNode(int index) {
		IFigure f = create();
		IFigure parent = parentFor();
		int at = index < 0 || index > parent.getChildren().size() ? -1 : index;
		if (random.nextInt(20) == 0) {
			parent.add(f, at);
		} else {
			parent.add(f, constraint(), at);
		}
		nodes.add(f);
	}

	private IFigure anyNode() {
		return nodes.isEmpty() ? null : nodes.get(random.nextInt(nodes.size()));
	}

	private void flush() {
		d.manager.flush();
		M3RecordingGraphics g = d.manager.last;
		log("flush " + (g == null ? "none" : g.operations() + " " + Long.toHexString(g.hash()))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private void step() {
		IFigure f = anyNode();
		int op = random.nextInt(19);
		if (f == null && op < 8) {
			op = 0;
		}
		if (deltaOnly && (op == 3 || op == 9 || op == 10)) {
			// in-place constraint changes revalidate the container directly; scroll and zoom are viewport deltas
			op = op == 3 ? 2 : 16;
		}
		switch (op) {
		case 0 -> addNode(random.nextInt(3) == 0 ? random.nextInt(nodes.size() + 1) : -1);
		case 1 -> {
			f.getParent().remove(f);
			nodes.remove(f);
			log("removed " + id(f)); //$NON-NLS-1$
		}
		case 2 -> f.getParent().setConstraint(f, constraint());
		case 3 -> {
			Object c = f.getParent().getLayoutManager().getConstraint(f);
			if (mutateInPlace && c instanceof Rectangle r) {
				if (random.nextBoolean()) {
					r.translate(random.nextInt(21) - 10, random.nextInt(21) - 10);
				} else {
					r.resize(random.nextInt(9) - 4, random.nextInt(9) - 4);
				}
				f.getParent().revalidate();
			}
		}
		case 4 -> f.setBounds(new Rectangle(random.nextInt(2000), random.nextInt(1500), 1 + random.nextInt(50), 1
				+ random.nextInt(50)));
		case 5 -> f.setVisible(!f.isVisible());
		case 6 -> {
			if (f.getClass() == Figure.class || f instanceof InsetHitFigure) {
				f.setEnabled(!f.isEnabled());
			}
		}
		case 7 -> {
			IFigure parent = f.getParent();
			Object c = parent.getLayoutManager().getConstraint(f);
			parent.remove(f);
			parent.add(f, c, random.nextInt(parent.getChildren().size() + 1));
		}
		case 8 -> f.setPreferredSize(new Dimension(random.nextInt(80), random.nextInt(60)));
		case 9 -> d.viewport.setViewLocation(random.nextInt(1500), random.nextInt(1200));
		case 10 -> d.pane.setScale(new double[] { 0.25, 0.5, 1, 1, 2 }[random.nextInt(5)]);
		case 11 -> paint();
		case 12, 13 -> hits();
		case 14 -> log("extent " + d.layer.getFreeformExtent() + ' ' + d.pane.getFreeformExtent()); //$NON-NLS-1$
		case 15 -> refill(new IFigure[] { second, deltaOnly ? inner : third, inner }[random.nextInt(3)]);
		case 18 -> burst();
		default -> flush();
		}
	}

	/** Inserts a run of new nodes at the front of the primary layer (z-order labels run out of gaps). */
	private void burst() {
		for (int k = 0; k < 14; k++) {
			IFigure f = create();
			d.layer.add(f, constraint(), 1);
			nodes.add(f);
		}
	}

	/** Empties a container (stock Figure then replaces its child list on the next add) and adds a few nodes. */
	private void refill(IFigure parent) {
		if (random.nextInt(8) != 0) {
			return;
		}
		for (IFigure child : new ArrayList<>(parent.getChildren())) {
			parent.remove(child);
			nodes.remove(child);
		}
		log("emptied " + id(parent)); //$NON-NLS-1$
		for (int k = 0; k < 3; k++) {
			IFigure f = create();
			parent.add(f, constraint());
			nodes.add(f);
		}
	}

	private void paint() {
		Rectangle clip = new Rectangle(random.nextInt(900), random.nextInt(600), random.nextInt(400),
				random.nextInt(300));
		M3RecordingGraphics g = d.paint(clip, false);
		log("paint " + clip + ' ' + g.operations() + ' ' + Long.toHexString(g.hash())); //$NON-NLS-1$
	}

	private void hits() {
		for (int k = 0; k < 8; k++) {
			int x = random.nextInt(900), y = random.nextInt(600);
			IFigure a = d.root.findFigureAt(x, y);
			IFigure b = d.root.findMouseEventTargetAt(x, y);
			List<IFigure> exclude = new ArrayList<>();
			if (a != null && a != d.root) {
				exclude.add(a);
			}
			IFigure c = d.root.findFigureAtExcluding(x, y, exclude);
			Point local = new Point(x, y);
			d.layer.translateToRelative(local);
			boolean in = d.layer.containsPoint(local.x, local.y);
			boolean in2 = second.containsPoint(local.x, local.y);
			log("hit " + x + ',' + y + ' ' + id(a) + ' ' + id(b) + ' ' + id(c) + ' ' + in + ' ' + in2); //$NON-NLS-1$
		}
	}

	private void digest() {
		flush();
		StringBuilder b = new StringBuilder("bounds"); //$NON-NLS-1$
		for (IFigure f : nodes) {
			b.append(' ').append(id(f)).append(f.getBounds());
		}
		log(b.toString());
	}
}
