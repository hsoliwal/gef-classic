/*******************************************************************************
 * Copyright (c) 2026 Contributors to the Synexia Project.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.eclipse.draw2d.internal.NativeDamageRegions;

import org.junit.jupiter.api.Test;

/** The sparse damage hint cannot omit or reorder any original dirty fragment. */
public class NativeDamageRegionsTest {
	@Test
	void sparseRectanglesPreserveIndividualCoordinates() {
		NativeDamageRegions batch = new NativeDamageRegions();
		batch.add(-200, 40, 10, 10);
		batch.add(500, 400, 12, 11);
		assertTrue(batch.shouldSplit(-200, 40, 712, 371));
		assertEquals(2, batch.size());
		assertEquals(-200, batch.x(0));
		assertEquals(40, batch.y(0));
		assertEquals(12, batch.width(1));
		assertEquals(11, batch.height(1));
		assertFalse(batch.shouldSplit(-200, 40, 713, 371));
	}

	@Test
	void overlapAndNearnessFallBackToOriginalEnvelope() {
		NativeDamageRegions batch = new NativeDamageRegions();
		batch.add(0, 0, 100, 100);
		batch.add(5, 5, 100, 100);
		assertFalse(batch.shouldSplit(0, 0, 105, 105));
		batch.clear();
		batch.add(0, 0, 10, 10);
		assertFalse(batch.shouldSplit(0, 0, 10, 10));
	}

	@Test
	void malformedOverflowAndExcessiveRegionsRefuseSplitting() {
		NativeDamageRegions batch = new NativeDamageRegions();
		batch.add(Integer.MAX_VALUE - 1, 0, 10, 20);
		batch.add(0, 100, 10, 10);
		assertFalse(batch.shouldSplit(0, 0, Integer.MAX_VALUE, 110));
		batch.clear();
		for (int i = 0; i < 33; i++) batch.add(i * 100, i * 100, 1, 1);
		assertFalse(batch.shouldSplit(0, 0, 3201, 3201));
		assertEquals(32, batch.size());
		batch.clear();
		batch.add(0, 0, 0, 50);
		batch.add(100, 100, 10, 10);
		assertFalse(batch.shouldSplit(0, 0, 110, 110));
	}

	@Test
	void signedBoundaryRectanglesNeverOverflowTheAreaPolicy() {
		NativeDamageRegions batch = new NativeDamageRegions();
		batch.add(Integer.MIN_VALUE, Integer.MIN_VALUE, 10, 10);
		batch.add(-1000, -1000, 10, 10);
		assertFalse(batch.shouldSplit(Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MAX_VALUE, Integer.MAX_VALUE));
		batch.clear();
		batch.add(Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 3, 3, 3);
		batch.add(0, 0, 1, 1);
		assertTrue(batch.shouldSplit(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE));
		batch.clear();
		batch.add(Integer.MIN_VALUE, 0, Integer.MAX_VALUE, 10);
		batch.add(0, 10, 1, Integer.MAX_VALUE - 10);
		assertFalse(batch.shouldSplit(Integer.MIN_VALUE, 0, Integer.MAX_VALUE,
				Integer.MAX_VALUE));
	}

	@Test
	void seededEnvelopePolicyMatchesIndependentLongArithmetic() {
		NativeDamageRegions batch = new NativeDamageRegions();
		Random random = new Random(0xD2D20261121L);
		for (int sample = 0; sample < 10_000; sample++) {
			batch.clear();
			int n = 1 + random.nextInt(36);
			long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
			long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;
			long totalArea = 0;
			for (int i = 0; i < n; i++) {
				int x = random.nextInt(2001) - 1000;
				int y = random.nextInt(2001) - 1000;
				int width = 1 + random.nextInt(60);
				int height = 1 + random.nextInt(60);
				batch.add(x, y, width, height);
				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, (long) x + width);
				maxY = Math.max(maxY, (long) y + height);
				totalArea += (long) width * height;
			}
			int envelopeWidth = (int) (maxX - minX);
			int envelopeHeight = (int) (maxY - minY);
			boolean expected = n >= 2 && n <= 32
					&& totalArea <= ((long) envelopeWidth * envelopeHeight) / 4;
			assertEquals(expected, batch.shouldSplit((int) minX, (int) minY,
					envelopeWidth, envelopeHeight));
			assertFalse(batch.shouldSplit((int) minX, (int) minY,
					envelopeWidth + 1, envelopeHeight));
		}
	}
}
