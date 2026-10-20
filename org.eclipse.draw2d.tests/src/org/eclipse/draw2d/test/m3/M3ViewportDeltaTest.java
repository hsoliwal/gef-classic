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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.logging.Logger;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.internal.m3.M3Damage;
import org.eclipse.draw2d.internal.m3.M3DeltaGate;

import org.junit.jupiter.api.Test;

/**
 * Viewport deltas gate (brief rule 24, DELTA_GATE.tsv): a canvas resize sweep
 * (width only, height only, both growing, both shrinking) and a zoom sweep over
 * a 10^5-node diagram. Per resize step no child is validated and only the
 * children meeting the exposed or uncovered strips are painted; per zoom step
 * no child is validated and the visible children are painted (every visible
 * pixel changes). After every step the canvas raster, repaired step by step,
 * equals a fresh paint of the whole canvas. Runs in the viewport mode with
 * strip damage (the default).
 */
public class M3ViewportDeltaTest {

	private static final int NODES = 100000;

	/** Children of the layer whose bounds meet one of the rectangles (layer and canvas coordinates agree at scale 1, origin 0,0). */
	private static int meeting(M3DiagramFixture d, Rectangle... areas) {
		int n = 0;
		for (IFigure child : d.layer.getChildren()) {
			Rectangle b = child.getBounds();
			for (Rectangle a : areas) {
				if (a != null && !a.isEmpty() && b.intersects(a)) {
					n++;
					break;
				}
			}
		}
		return n;
	}

	private static Rectangle strip(int x, int y, int w, int h) {
		return w > 0 && h > 0 ? new Rectangle(x, y, w, h) : null;
	}

	private static void checkFrame(M3DiagramFixture d, String step) {
		M3FrameRaster fresh = d.freshFrame();
		assertNull(d.frame.firstDifference(fresh), step);
	}

	@Test
	public void resizeStepsPaintOnlyTheStrips() {
		assumeTrue(M3Damage.STRIPS, "needs -Ddraw2d.viewport.mode=viewport"); //$NON-NLS-1$
		M3DiagramFixture d = new M3DiagramFixture(NODES, 900, 600, 11);
		d.trackFrame();
		Logger log = Logger.getLogger(getClass().getName());
		int w = 900;
		int h = 600;
		long painted = 0;
		long stripChildren = 0;
		int[][] phases = { { 17, 0 }, { 0, 13 }, { 19, 23 }, { -11, -7 } };
		for (int[] phase : phases) {
			for (int k = 0; k < 10; k++) {
				int nw = w + phase[0];
				int nh = h + phase[1];
				// each strip is repaired on its own: a child meeting both is painted twice
				int expected = meeting(d, strip(Math.min(w, nw), 0, Math.abs(nw - w), Math.max(h, nh)))
						+ meeting(d, strip(0, Math.min(h, nh), Math.max(w, nw), Math.abs(nh - h)));
				M3DeltaGate.reset();
				d.resize(nw, nh);
				d.manager.flush();
				w = nw;
				h = nh;
				String step = "resize to " + w + 'x' + h; //$NON-NLS-1$
				assertEquals(0, M3DeltaGate.validatedChildren, step);
				assertTrue(M3DeltaGate.paintedChildren <= expected, step + ": painted " + M3DeltaGate.paintedChildren //$NON-NLS-1$
						+ " > " + expected + " meeting the strips"); //$NON-NLS-1$ //$NON-NLS-2$
				painted += M3DeltaGate.paintedChildren;
				stripChildren += expected;
				checkFrame(d, step);
			}
		}
		int visible = meeting(d, new Rectangle(0, 0, w, h));
		log.info("resize sweep: 40 steps, painted children " + painted + " (meeting the strips " + stripChildren //$NON-NLS-1$ //$NON-NLS-2$
				+ "), visible children per step about " + visible); //$NON-NLS-1$
	}

	@Test
	public void zoomStepsValidateNothing() {
		assumeTrue(M3Damage.STRIPS, "needs -Ddraw2d.viewport.mode=viewport"); //$NON-NLS-1$
		M3DiagramFixture d = new M3DiagramFixture(NODES, 900, 600, 12);
		d.trackFrame();
		Logger log = Logger.getLogger(getClass().getName());
		double[] scales = { 0.9, 0.8, 0.7, 0.6, 0.5, 0.6, 0.75, 1, 1.25, 1.5, 2, 1.5, 1 };
		long painted = 0;
		for (double s : scales) {
			M3DeltaGate.reset();
			d.pane.setScale(s);
			d.manager.flush();
			String step = "zoom " + s; //$NON-NLS-1$
			assertEquals(0, M3DeltaGate.validatedChildren, step);
			assertEquals(0, M3DeltaGate.fullWork(), step);
			painted += M3DeltaGate.paintedChildren;
			checkFrame(d, step);
		}
		log.info("zoom sweep: " + scales.length + " steps, painted children " + painted); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
