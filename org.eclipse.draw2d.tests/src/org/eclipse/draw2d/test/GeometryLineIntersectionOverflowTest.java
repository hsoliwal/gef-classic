/*******************************************************************************
 * Copyright (c) 2026 Hitesh Soliwal and Contributors to the Synexia Project.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigInteger;
import java.util.Random;

import org.eclipse.draw2d.geometry.Geometry;
import org.junit.jupiter.api.Test;

/** Exact-integer oracle for the existing Draw2D segment/viewport geometry owner. */
public class GeometryLineIntersectionOverflowTest {
    private static final int[] EXTREMES = {
        Integer.MIN_VALUE, Integer.MIN_VALUE + 1, -1, 0, 1,
        Integer.MAX_VALUE - 1, Integer.MAX_VALUE
    };

    private static int direction(int ax, int ay, int bx, int by, int cx, int cy) {
        BigInteger ux = BigInteger.valueOf((long) bx - ax);
        BigInteger uy = BigInteger.valueOf((long) by - ay);
        BigInteger vx = BigInteger.valueOf((long) cx - ax);
        BigInteger vy = BigInteger.valueOf((long) cy - ay);
        return ux.multiply(vy).subtract(uy.multiply(vx)).signum();
    }

    private static boolean exact(int[] p) {
        int x1 = p[0], y1 = p[1], x2 = p[2], y2 = p[3];
        int x3 = p[4], y3 = p[5], x4 = p[6], y4 = p[7];
        if (Math.max(x1, x2) < Math.min(x3, x4)
                || Math.max(x3, x4) < Math.min(x1, x2)
                || Math.max(y1, y2) < Math.min(y3, y4)
                || Math.max(y3, y4) < Math.min(y1, y2)) return false;
        int a = direction(x1, y1, x2, y2, x3, y3);
        int b = direction(x1, y1, x2, y2, x4, y4);
        int c = direction(x3, y3, x4, y4, x1, y1);
        int d = direction(x3, y3, x4, y4, x2, y2);
        return a * b <= 0 && c * d <= 0;
    }

    private static void assertMatchesOracle(int[] p) {
        assertEquals(exact(p), Geometry.linesIntersect(
                p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]),
                () -> "line segment coordinates " + java.util.Arrays.toString(p));
    }

    @Test
    public void testSignedLongOverflowFalseIntersection() {
        int[] counterexample = {
            1, -1, Integer.MAX_VALUE, -Integer.MAX_VALUE,
            Integer.MIN_VALUE, -1, Integer.MAX_VALUE, Integer.MAX_VALUE
        };
        assertFalse(exact(counterexample));
        assertMatchesOracle(counterexample);
    }

    @Test
    public void testExactFullIntRangeAndBoundaryConfigurations() {
        Random random = new Random(0x425617L);
        int[] p = new int[8];
        for (int caseNo = 0; caseNo < 60_000; caseNo++) {
            for (int j = 0; j < p.length; j++) {
                p[j] = caseNo % 8 == 0 ? EXTREMES[random.nextInt(EXTREMES.length)] : random.nextInt();
            }
            assertMatchesOracle(p);
        }
    }

    @Test
    public void testDegenerateSegmentsAndEndpointTouching() {
        int[] p = new int[8];
        for (int x = -3; x <= 3; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int u = -3; u <= 3; u++) {
                    for (int v = -3; v <= 3; v++) {
                        p[0] = x; p[1] = y; p[2] = u; p[3] = v;
                        p[4] = 0; p[5] = 0; p[6] = 0; p[7] = 0;
                        assertMatchesOracle(p);
                        p[6] = 1; p[7] = 1;
                        assertMatchesOracle(p);
                    }
                }
            }
        }
    }
}
