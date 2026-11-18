/*******************************************************************************
 * Copyright (c) 2026 Synexia and contributors.
 *
 * Licensed under the Apache License, Version 2.0 or the Eclipse Public
 * License, Version 2.0, at your option.
 * SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
 *******************************************************************************/
package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Random;

import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Vector;

import org.junit.jupiter.api.Test;

/**
 * Overflow-independent anchor-side oracle for the existing Manhattan router.
 * Reflectively selects its protected method without adding a public API.
 * The current Rectangle.bottom()/right() int edge semantics remain unchanged.
 */
public class ManhattanConnectionExtremeDirectionTest {
    private static final int LEFT = 0, UP = 1, DOWN = 2, RIGHT = 3;
    private static final long SEED = 0x6EF20261118L;

    private static int current(Rectangle bounds, Point point, Method direction,
            ManhattanConnectionRouter router) {
        try {
            Vector answer = (Vector) direction.invoke(router, bounds, point);
            if (answer.x == -1 && answer.y == 0) return LEFT;
            if (answer.x == 0 && answer.y == -1) return UP;
            if (answer.x == 0 && answer.y == 1) return DOWN;
            if (answer.x == 1 && answer.y == 0) return RIGHT;
            throw new AssertionError("not a cardinal direction: " + answer.x + "," + answer.y);
        } catch (IllegalAccessException | InvocationTargetException failure) {
            throw new AssertionError("router invocation failed", failure);
        }
    }

    private static BigInteger distance(int edge, int coordinate) {
        return BigInteger.valueOf(edge).subtract(BigInteger.valueOf(coordinate)).abs();
    }

    /** Independent BigInteger reference with the existing LEFT→UP→DOWN→RIGHT tie order. */
    private static int oracle(Rectangle bounds, Point point) {
        BigInteger distance = distance(bounds.x, point.x);
        int chosen = LEFT;
        BigInteger candidate = distance(bounds.y, point.y);
        if (candidate.compareTo(distance) <= 0) {
            distance = candidate;
            chosen = UP;
        }
        candidate = distance(bounds.bottom(), point.y);
        if (candidate.compareTo(distance) <= 0) {
            distance = candidate;
            chosen = DOWN;
        }
        candidate = distance(bounds.right(), point.x);
        if (candidate.compareTo(distance) < 0) chosen = RIGHT;
        return chosen;
    }

    @Test
    public void largeSignedCoordinatesFollowExactNearestSideAndExistingTies() throws Exception {
        Method direction = ManhattanConnectionRouter.class.getDeclaredMethod(
                "getDirection", Rectangle.class, Point.class);
        direction.setAccessible(true);
        ManhattanConnectionRouter router = new ManhattanConnectionRouter();
        Rectangle edge = new Rectangle(Integer.MIN_VALUE, 100, 10, 20);
        Point far = new Point(Integer.MAX_VALUE, 110);
        assertEquals(DOWN, current(edge, far, direction, router),
                "old int subtraction wraps and chooses LEFT instead of DOWN");
        assertEquals(DOWN, oracle(edge, far));
        Rectangle ties = new Rectangle(10, 10, 20, 20);
        assertEquals(DOWN, current(ties, new Point(20, 20), direction, router),
                "last <= tie wins DOWN; RIGHT only wins on strictly closer distance");

        Random random = new Random(SEED);
        for (int i = 0; i < 20_000; i++) {
            Rectangle bounds = new Rectangle(random.nextInt(), random.nextInt(),
                    random.nextInt(512), random.nextInt(512));
            Point point = new Point(random.nextInt(), random.nextInt());
            assertEquals(oracle(bounds, point), current(bounds, point, direction, router),
                    "anchor direction mismatch at seed " + SEED + ", case " + i
                            + " for " + bounds + " and " + point);
        }
    }
}
