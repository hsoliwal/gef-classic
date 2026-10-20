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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;
import java.util.logging.Logger;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.UpdateListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.internal.m3.M3DeltaGate;
import org.eclipse.draw2d.internal.m3.M3ViewportMode;
import org.eclipse.draw2d.internal.m3.M3Visibility;

import org.junit.jupiter.api.Test;

/**
 * Hidden viewports gate (brief rule 24, DELTA_GATE.tsv): hide the canvas, apply
 * 10^4 model changes with the display running the posted updates between
 * batches, show. While hidden there is no validation pass and no paint pass;
 * after the show the first update validates once and the window system's
 * paint of the canvas is the one paint pass. Runs in the viewport mode.
 */
public class M3HiddenViewportTest {

	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;

	/** Validation and paint passes seen by an update listener. */
	static final class Passes implements UpdateListener {
		int validations;
		int paints;

		@Override
		public void notifyPainting(Rectangle damage, Map<IFigure, Rectangle> dirtyRegions) {
			paints++;
		}

		@Override
		public void notifyValidating() {
			validations++;
		}
	}

	/** Applies {@code count} model changes to random nodes, running the posted update every 100. */
	private static void changes(M3DiagramFixture d, int count, long seed) {
		SplittableRandom r = new SplittableRandom(seed);
		List<? extends IFigure> nodes = d.layer.getChildren();
		for (int i = 0; i < count; i++) {
			IFigure f = nodes.get(r.nextInt(nodes.size()));
			switch (i % 4) {
			case 0 -> d.layer.setConstraint(f, f.getBounds().getTranslated(r.nextInt(9) - 4, r.nextInt(9) - 4));
			case 1 -> f.setVisible(!f.isVisible());
			case 2 -> d.layer.add(new RectangleFigure(), new Rectangle(r.nextInt(8000), r.nextInt(8000), 30, 20));
			default -> f.setPreferredSize(new Dimension(10 + r.nextInt(30), 10 + r.nextInt(20)));
			}
			if (i % 100 == 99) {
				d.manager.requestUpdate();
			}
		}
	}

	@Test
	public void hiddenCanvasDefersEveryUpdateAndPaintsOnceOnShow() {
		assumeTrue(M3ViewportMode.ENABLED, "needs -Ddraw2d.viewport.mode=viewport"); //$NON-NLS-1$
		M3DiagramFixture d = new M3DiagramFixture(20000, WIDTH, HEIGHT, 5);
		boolean[] hidden = { false };
		M3Visibility.register(d.manager, () -> hidden[0]);
		Passes passes = new Passes();
		d.manager.addUpdateListener(passes);
		hidden[0] = true;
		M3DeltaGate.reset();
		d.manager.repairs = 0;
		changes(d, 10000, 7);
		Logger.getLogger(getClass().getName()).info("hidden: validations " + passes.validations + ", paints " //$NON-NLS-1$ //$NON-NLS-2$
				+ passes.paints + ", repairs " + d.manager.repairs + ", " + M3DeltaGate.summary() //$NON-NLS-1$ //$NON-NLS-2$
				+ ", deferredUpdates " + M3DeltaGate.deferredUpdates); //$NON-NLS-1$
		assertEquals(0, passes.validations);
		assertEquals(0, passes.paints);
		assertEquals(0, d.manager.repairs);
		assertEquals(0, M3DeltaGate.validatedChildren);
		assertEquals(0, M3DeltaGate.paintedChildren);
		assertEquals(100, M3DeltaGate.deferredUpdates);

		hidden[0] = false;
		d.manager.requestUpdate();
		assertEquals(1, passes.validations);
		assertEquals(0, passes.paints);
		assertEquals(1, M3DeltaGate.droppedRepairs);
		// the window system paints the canvas it shows
		d.manager.performUpdate(new Rectangle(0, 0, WIDTH, HEIGHT));
		d.manager.requestUpdate();
		Logger.getLogger(getClass().getName()).info("shown: validations " + passes.validations + ", paints " //$NON-NLS-1$ //$NON-NLS-2$
				+ passes.paints + ", repairs " + d.manager.repairs + ", " + M3DeltaGate.summary()); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(1, passes.validations);
		assertEquals(1, passes.paints);
		assertEquals(1, d.manager.repairs);
		assertEquals(0, M3DeltaGate.fullWork());
		// every change is in: each node sits at its constraint
		for (IFigure f : d.layer.getChildren()) {
			Rectangle c = (Rectangle) d.layer.getLayoutManager().getConstraint(f);
			if (c.width >= 0 && c.height >= 0) {
				assertEquals(c, f.getBounds());
			}
		}
		// visible again: changes update and repaint as usual
		IFigure first = d.layer.getChildren().get(0);
		d.layer.setConstraint(first, first.getBounds().getTranslated(5, 0));
		d.manager.requestUpdate();
		assertEquals(2, passes.validations);
		assertEquals(2, passes.paints);
	}

	@Test
	public void explicitUpdateWhileHiddenValidatesWithoutPainting() {
		assumeTrue(M3ViewportMode.ENABLED, "needs -Ddraw2d.viewport.mode=viewport"); //$NON-NLS-1$
		M3DiagramFixture d = new M3DiagramFixture(2000, WIDTH, HEIGHT, 6);
		boolean[] hidden = { true };
		M3Visibility.register(d.manager, () -> hidden[0]);
		Passes passes = new Passes();
		d.manager.addUpdateListener(passes);
		d.manager.repairs = 0;
		changes(d, 300, 8);
		d.manager.flush();
		assertEquals(1, passes.validations);
		assertEquals(0, passes.paints);
		assertTrue(M3DeltaGate.deferredRepairs > 0);
		assertEquals(0, d.manager.repairs);
		hidden[0] = false;
		d.manager.requestUpdate();
		d.manager.performUpdate(new Rectangle(0, 0, WIDTH, HEIGHT));
		assertEquals(1, passes.validations);
		assertEquals(1, passes.paints);
		assertEquals(1, d.manager.repairs);
	}
}
