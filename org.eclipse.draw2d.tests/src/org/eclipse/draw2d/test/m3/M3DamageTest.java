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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.internal.m3.M3Damage;
import org.eclipse.draw2d.internal.m3.M3DirtyPieces;
import org.eclipse.draw2d.internal.m3.M3FigureTrust;

import org.junit.jupiter.api.Test;

/**
 * Strip damage ({@link M3Damage}) and piecewise repair ({@link M3DirtyPieces})
 * on their own; the frame differential and {@link M3ViewportDeltaTest} prove
 * them on whole diagrams.
 */
public class M3DamageTest {

	@Test
	public void sizeFreeAndChildKeepingBits() {
		assertTrue((M3FigureTrust.of(new Figure()) & M3FigureTrust.SIZE_FREE) != 0);
		assertTrue((M3FigureTrust.of(new Viewport()) & M3FigureTrust.SIZE_FREE) != 0);
		assertEquals(0, M3FigureTrust.of(new RectangleFigure()) & M3FigureTrust.SIZE_FREE);
		assertTrue((M3FigureTrust.of(new FreeformLayer()) & M3FigureTrust.KEEPS_CHILDREN) != 0);
		assertEquals(0, M3FigureTrust.of(new Figure()) & M3FigureTrust.KEEPS_CHILDREN);
	}

	@Test
	public void stripsOnlyForSizeFreeFiguresWithoutBorderOrClippingStrategy() {
		assumeTrue(M3Damage.STRIPS, "needs -Ddraw2d.viewport.mode=viewport"); //$NON-NLS-1$
		Figure parent = new Figure();
		parent.setBounds(new Rectangle(0, 0, 500, 500));
		Figure plain = new Figure();
		parent.add(plain);
		plain.setBounds(new Rectangle(10, 10, 100, 100));
		Rectangle b = plain.getBounds();
		assertFalse(M3Damage.bounds(plain, b, b.getCopy()));
		assertTrue(M3Damage.bounds(plain, b, new Rectangle(10, 10, 130, 80)));
		assertTrue(M3Damage.bounds(plain, b, new Rectangle(10, 10, 60, 140)));
		// a plain figure moves its children with it: a move repaints as stock
		assertFalse(M3Damage.bounds(plain, b, new Rectangle(12, 10, 100, 100)));
		FreeformLayer free = new FreeformLayer();
		parent.add(free);
		free.setBounds(new Rectangle(0, 0, 200, 200));
		Rectangle f = free.getBounds();
		assertTrue(M3Damage.bounds(free, f, new Rectangle(-20, -30, 260, 250)));
		assertTrue(M3Damage.bounds(free, f, new Rectangle(30, 40, 100, 100)));
		assertTrue(M3Damage.bounds(free, f, new Rectangle(900, 900, 50, 50)));
		assertTrue(M3Damage.bounds(free, new Rectangle(0, 0, 0, 0), new Rectangle(5, 5, 50, 50)));
		RectangleFigure shape = new RectangleFigure();
		parent.add(shape);
		shape.setBounds(new Rectangle(0, 0, 50, 50));
		assertFalse(M3Damage.bounds(shape, shape.getBounds(), new Rectangle(0, 0, 60, 60)));
		plain.setBorder(new LineBorder());
		assertFalse(M3Damage.bounds(plain, plain.getBounds(), new Rectangle(10, 10, 70, 70)));
		plain.setBorder(null);
		plain.setClippingStrategy(new IClippingStrategy() {
			@Override
			public Rectangle[] getClip(IFigure childFigure) {
				return new Rectangle[] { childFigure.getBounds() };
			}
		});
		assertFalse(M3Damage.bounds(plain, plain.getBounds(), new Rectangle(10, 10, 70, 70)));
		plain.setClippingStrategy(null);
		plain.setVisible(false);
		assertTrue(M3Damage.bounds(plain, plain.getBounds(), new Rectangle(10, 10, 90, 90)));
		Figure orphan = new Figure();
		orphan.setBounds(new Rectangle(0, 0, 10, 10));
		assertTrue(M3Damage.bounds(orphan, orphan.getBounds(), new Rectangle(0, 0, 20, 20)));
	}

	@Test
	public void piecesRepairApartAndMergeWhenClose() {
		Figure root = new Figure();
		root.setBounds(new Rectangle(0, 0, 1000, 1000));
		Figure child = new Figure();
		root.add(child);
		child.setBounds(new Rectangle(0, 0, 1000, 1000));
		List<Rectangle> painted = new ArrayList<>();
		M3DirtyPieces pieces = new M3DirtyPieces();
		// two far pieces: repaired apart
		pieces.add(child, 0, 0, 10, 10);
		pieces.add(root, 900, 900, 10, 10);
		pieces.begin();
		pieces.add(child, 500, 500, 5, 5);
		assertTrue(pieces.repair(new Rectangle(0, 0, 910, 910), r -> painted.add(r.getCopy())));
		assertEquals(2, painted.size());
		// the piece added after begin belongs to the next repair; alone it is the stock rectangle
		painted.clear();
		pieces.begin();
		assertFalse(pieces.repair(new Rectangle(500, 500, 5, 5), r -> painted.add(r.getCopy())));
		// overlapping and near pieces merge into one: stock rectangle
		pieces.add(child, 0, 0, 100, 100);
		pieces.add(child, 50, 50, 100, 100);
		pieces.add(child, 0, 160, 150, 10);
		pieces.add(child, 2000, 2000, 10, 10);
		pieces.begin();
		assertFalse(pieces.repair(new Rectangle(0, 0, 150, 170), r -> painted.add(r.getCopy())));
		assertEquals(0, painted.size());
		// pieces outside their ancestors vanish: nothing to paint, although the stock union is not empty
		pieces.add(child, 2000, 0, 10, 10);
		pieces.add(child, 0, 2000, 10, 10);
		pieces.begin();
		assertTrue(pieces.repair(new Rectangle(0, 0, 1000, 1000), r -> painted.add(r.getCopy())));
		assertEquals(0, painted.size());
		// more pieces than kept: stock rectangle
		for (int i = 0; i < 20; i++) {
			pieces.add(child, i * 40, 0, 5, 5);
		}
		pieces.begin();
		assertFalse(pieces.repair(new Rectangle(0, 0, 765, 5), r -> painted.add(r.getCopy())));
		// dropped pieces and an empty damage paint nothing
		pieces.add(child, 0, 0, 10, 10);
		pieces.add(child, 800, 800, 10, 10);
		pieces.drop();
		pieces.begin();
		assertFalse(pieces.repair(null, r -> painted.add(r.getCopy())));
		assertEquals(0, painted.size());
	}
}
