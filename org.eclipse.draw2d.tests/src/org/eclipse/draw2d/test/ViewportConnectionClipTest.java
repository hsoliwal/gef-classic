/*******************************************************************************
 * Copyright (c) 2026 Synexia, Hitesh Soliwal and contributors.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License 2.0 or the Eclipse Public License 2.0.
 *
 * SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
 *******************************************************************************/
package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.ViewportAwareConnectionLayerClippingStrategy;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

import org.junit.jupiter.api.Test;

/**
 * Whole-connection visibility checks with real Draw2D figure ancestry and an
 * independent four-edge geometry oracle. A retained edge still receives the
 * original common-viewport rectangle; these tests do not specify fragment masks.
 */
public class ViewportConnectionClipTest {
    private static ConnectionAnchor anchor(IFigure owner) {
        return (ConnectionAnchor) Proxy.newProxyInstance(ConnectionAnchor.class.getClassLoader(),
                new Class<?>[] { ConnectionAnchor.class }, (proxy, method, args) -> {
                    if ("getOwner".equals(method.getName())) return owner;
                    throw new AssertionError("Unexpected anchor method " + method.getName());
                });
    }

    private static Connection connection(ConnectionAnchor from, ConnectionAnchor to) {
        return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(),
                new Class<?>[] { Connection.class }, (proxy, method, args) -> switch (method.getName()) {
                    case "getSourceAnchor" -> from;
                    case "getTargetAnchor" -> to;
                    default -> throw new AssertionError("Unexpected connection method " + method.getName());
                });
    }

    private static final class Fixture {
        final Viewport root = new Viewport();
        final Viewport sourceViewport = new Viewport();
        final Viewport targetViewport = new Viewport();
        final Figure body = new Figure();
        final Figure source = new Figure();
        final Figure target = new Figure();
        final Connection connection;
        final Clip strategy;

        Fixture() {
            root.setContents(body);
            body.add(sourceViewport);
            body.add(targetViewport);
            Figure sourceBody = new Figure(), targetBody = new Figure();
            sourceViewport.setContents(sourceBody);
            targetViewport.setContents(targetBody);
            sourceBody.add(source);
            targetBody.add(target);
            ConnectionAnchor from = anchor(source), to = anchor(target);
            connection = connection(from, to);
            strategy = new Clip(root, sourceViewport, targetViewport);
        }

        Rectangle[] clips(int... coordinates) {
            strategy.path = new PointList(coordinates);
            return strategy.clips(connection);
        }
    }

    private static final class Clip extends ViewportAwareConnectionLayerClippingStrategy {
        final Viewport root, sourceViewport, targetViewport;
        final Map<Viewport, Rectangle> areas = new IdentityHashMap<>();
        PointList path;

        Clip(Viewport root, Viewport sourceViewport, Viewport targetViewport) {
            super(new ConnectionLayer());
            this.root = root;
            this.sourceViewport = sourceViewport;
            this.targetViewport = targetViewport;
            areas.put(sourceViewport, new Rectangle(0, 0, 20, 20));
            areas.put(targetViewport, new Rectangle(80, 0, 20, 20));
        }

        Rectangle[] clips(Connection connection) {
            return getEdgeClippingRectangle(connection);
        }

        @Override
        protected Viewport getRootViewport() {
            return root;
        }

        @Override
        protected PointList getAbsolutePointsAsCopy(Connection connection) {
            return path.getCopy();
        }

        @Override
        protected Rectangle getAbsoluteBoundsAsCopy(IFigure figure) {
            return figure instanceof Connection ? path.getBounds().getCopy()
                    : super.getAbsoluteBoundsAsCopy(figure);
        }

        @Override
        protected Rectangle getAbsoluteViewportAreaAsCopy(Viewport viewport) {
            Rectangle area = areas.get(viewport);
            if (area != null) return area.getCopy();
            return new Rectangle(-1000, -1000, 2000, 2000);
        }
    }

    @Test
    public void outsideAnchorsStillExposeSegmentsCrossingBothViewports() {
        Rectangle[] clip = new Fixture().clips(-10, 10, 110, 10);
        assertEquals(1, clip.length);
        assertEquals(new Rectangle(-10, 10, 121, 1), clip[0]);
    }

    @Test
    public void oneOutsideAnchorCrossingItsViewportIsRetained() {
        assertEquals(1, new Fixture().clips(-10, 10, 90, 10).length);
    }

    @Test
    public void boundingBoxOverlapWithoutSegmentCrossingRemainsCulled() {
        assertEquals(0, new Fixture().clips(-10, 30, 90, 10).length);
    }

    @Test
    public void pathOutsideBothNestedViewportsRemainsCulled() {
        assertEquals(0, new Fixture().clips(-10, -5, 110, -5).length);
    }

    @Test
    public void visibleAnchorsKeepOriginalCommonClip() {
        Rectangle[] clip = new Fixture().clips(10, 10, 90, 10);
        assertEquals(1, clip.length);
        assertTrue(clip[0].contains(10, 10));
        assertTrue(clip[0].contains(90, 10));
    }

    @Test
    public void outsideTargetCrossingItsViewportIsRetained() {
        assertEquals(1, new Fixture().clips(10, 10, 110, 10).length);
    }

    @Test
    public void crossingOneViewportCannotExcuseMissingTheOther() {
        assertEquals(0, new Fixture().clips(-10, 10, 10, 10, 50, 30).length);
        assertEquals(0, new Fixture().clips(50, 30, 90, 10, 110, 10).length);
    }

    @Test
    public void routedBendsAreExaminedInsteadOfTheEndpointChord() {
        assertEquals(1, new Fixture().clips(-10, -10, 10, 10, 90, 10, 110, -10).length);
    }

    @Test
    public void repeatedVerticesAndRouteReversalPreserveVisibility() {
        int[] forward = {-10, 10, -10, 10, 10, 10, 90, 10, 110, 10};
        int[] reverse = {110, 10, 90, 10, 10, 10, -10, 10, -10, 10};
        assertArrayEquals(new Fixture().clips(forward), new Fixture().clips(reverse));
    }

    @Test
    public void cornerTouchAndOnePixelViewportUseExistingPointListBoundaryRules() {
        Fixture fixture = new Fixture();
        fixture.strategy.areas.put(fixture.sourceViewport, new Rectangle(0, 0, 1, 1));
        fixture.strategy.areas.put(fixture.targetViewport, new Rectangle(80, 0, 1, 1));
        assertEquals(1, fixture.clips(-10, 0, 90, 0).length);
        assertEquals(0, fixture.clips(-10, -1, 90, -1).length);
    }

    @Test
    public void rightAndBottomAnchorBoundaryCompatibilityIsPreserved() {
        // A 2010 compatibility correction deliberately includes these endpoints.
        assertEquals(1, new Fixture().clips(20, 20, 100, 20).length);
    }

    @Test
    public void anchorsOutsideOwnerBoundsRemainVisibleInsideTheirViewports() {
        Fixture fixture = new Fixture();
        fixture.source.setBounds(new Rectangle(-200, -200, 1, 1));
        fixture.target.setBounds(new Rectangle(200, 200, 1, 1));
        assertEquals(1, fixture.clips(10, 10, 90, 10).length);
    }

    @Test
    public void sourceAndTargetInSameViewportKeepTheOriginalFastPath() {
        Fixture fixture = new Fixture();
        fixture.target.getParent().remove(fixture.target);
        fixture.source.getParent().add(fixture.target);
        assertEquals(1, fixture.clips(-10, -5, 110, -5).length);
    }

    @Test
    public void noCommonViewportKeepsOriginalConnectionBounds() {
        Fixture fixture = new Fixture();
        fixture.source.getParent().remove(fixture.source);
        assertArrayEquals(new Rectangle[] {new Rectangle(-10, -5, 121, 1)},
                fixture.clips(-10, -5, 110, -5));
    }

    @Test
    public void missingAnchorAndUnownedAnchorKeepOriginalFallback() {
        Fixture fixture = new Fixture();
        fixture.strategy.path = new PointList(-10, -5, 110, -5);
        Rectangle[] expected = {new Rectangle(-10, -5, 121, 1)};
        assertArrayEquals(expected, fixture.strategy.clips(connection(null, anchor(fixture.target))));
        assertArrayEquals(expected, fixture.strategy.clips(connection(anchor(fixture.source), null)));
        assertArrayEquals(expected, fixture.strategy.clips(connection(anchor(null), anchor(fixture.target))));
        assertArrayEquals(expected, fixture.strategy.clips(connection(anchor(fixture.source), anchor(null))));
    }

    @Test
    public void crossingChildViewportOutsideAnAncestorRemainsCulled() {
        Fixture fixture = new Fixture();
        Viewport enclosing = new Viewport();
        Figure contents = new Figure();
        fixture.body.remove(fixture.sourceViewport);
        enclosing.setContents(contents);
        contents.add(fixture.sourceViewport);
        fixture.body.add(enclosing);
        fixture.strategy.areas.put(enclosing, new Rectangle(0, 0, 15, 5));
        assertEquals(0, fixture.clips(-10, 10, 90, 10).length);
        fixture.strategy.areas.put(enclosing, new Rectangle(0, 0, 15, 20));
        assertEquals(1, fixture.clips(-10, 10, 90, 10).length);
    }

    @Test
    public void retainedCrossingStillUsesNestedCommonViewportClip() {
        Fixture fixture = new Fixture();
        Viewport common = new Viewport();
        common.setBounds(new Rectangle(0, 0, 100, 20));
        Figure contents = new Figure();
        fixture.body.remove(fixture.sourceViewport);
        fixture.body.remove(fixture.targetViewport);
        common.setContents(contents);
        contents.add(fixture.sourceViewport);
        contents.add(fixture.targetViewport);
        fixture.body.add(common);
        fixture.strategy.areas.put(common, new Rectangle(0, 0, 100, 20));
        assertArrayEquals(new Rectangle[] {new Rectangle(0, 10, 100, 1)},
                fixture.clips(-10, 10, 110, 10));
    }

    @Test
    public void repeatedQueriesDoNotMutateConnectionPointsOrViewportAreas() {
        Fixture fixture = new Fixture();
        fixture.strategy.path = new PointList(-10, 10, 10, 10, 90, 10, 110, 10);
        int[] before = fixture.strategy.path.toIntArray().clone();
        Rectangle sourceArea = fixture.strategy.areas.get(fixture.sourceViewport).getCopy();
        Rectangle targetArea = fixture.strategy.areas.get(fixture.targetViewport).getCopy();
        Rectangle[] first = fixture.strategy.clips(fixture.connection);
        assertEquals(1, first.length);
        first[0].setBounds(-999, -999, 1, 1);
        assertArrayEquals(new Rectangle[] {new Rectangle(-10, 10, 121, 1)},
                fixture.strategy.clips(fixture.connection));
        assertArrayEquals(before, fixture.strategy.path.toIntArray());
        assertEquals(sourceArea, fixture.strategy.areas.get(fixture.sourceViewport));
        assertEquals(targetArea, fixture.strategy.areas.get(fixture.targetViewport));
    }

    private static final class LocalFigure extends Figure {
        @Override
        protected boolean useLocalCoordinates() {
            return true;
        }
    }

    @Test
    public void publicClipUsesRealScrolledViewportsAndConnectionCoordinates() {
        Viewport root = new Viewport(true);
        root.setBounds(new Rectangle(0, 0, 300, 100));
        LocalFigure body = new LocalFigure();
        body.setBounds(new Rectangle(30, 40, 200, 100));
        root.setContents(body);
        root.getHorizontalRangeModel().setAll(0, 100, 1000);
        root.getVerticalRangeModel().setAll(0, 100, 1000);
        root.setViewLocation(7, 11);

        Viewport from = new Viewport(true), to = new Viewport(true);
        from.setBounds(new Rectangle(0, 0, 20, 20));
        to.setBounds(new Rectangle(80, 0, 20, 20));
        Figure source = new Figure(), target = new Figure();
        from.setContents(source);
        to.setContents(target);
        body.add(from);
        body.add(to);
        from.getHorizontalRangeModel().setAll(0, 20, 200);
        from.getVerticalRangeModel().setAll(0, 20, 200);
        from.setViewLocation(5, 3);

        ConnectionLayer layer = new ConnectionLayer();
        body.add(layer);
        PolylineConnection connection = new PolylineConnection();
        connection.setSourceAnchor(new ChopboxAnchor(source));
        connection.setTargetAnchor(new ChopboxAnchor(target));
        layer.add(connection);
        PointList points = new PointList(-10, 10, 110, 10);
        connection.setPoints(points);
        Point absolute = points.getFirstPoint().getCopy();
        connection.translateToAbsolute(absolute);
        assertEquals(new Point(13, 39), absolute);

        ViewportAwareConnectionLayerClippingStrategy strategy =
                new ViewportAwareConnectionLayerClippingStrategy(layer);
        assertArrayEquals(new Rectangle[] {new Rectangle(-10, 10, 121, 1)}, strategy.getClip(connection));
        assertArrayEquals(new int[] {-10, 10, 110, 10}, points.toIntArray());
        assertEquals(new Point(7, 11), root.getViewLocation());
        assertEquals(new Point(5, 3), from.getViewLocation());
    }

    private static int direction(int ax, int ay, int bx, int by, int cx, int cy) {
        BigInteger ux = BigInteger.valueOf((long) bx - ax);
        BigInteger uy = BigInteger.valueOf((long) by - ay);
        BigInteger vx = BigInteger.valueOf((long) cx - ax);
        BigInteger vy = BigInteger.valueOf((long) cy - ay);
        return ux.multiply(vy).subtract(uy.multiply(vx)).signum();
    }

    private static boolean exactSegments(int ax, int ay, int bx, int by,
            int cx, int cy, int dx, int dy) {
        if (Math.max(ax, bx) < Math.min(cx, dx) || Math.max(cx, dx) < Math.min(ax, bx)
                || Math.max(ay, by) < Math.min(cy, dy) || Math.max(cy, dy) < Math.min(ay, by)) {
            return false;
        }
        return direction(ax, ay, bx, by, cx, cy) * direction(ax, ay, bx, by, dx, dy) <= 0
                && direction(cx, cy, dx, dy, ax, ay) * direction(cx, cy, dx, dy, bx, by) <= 0;
    }

    private static boolean exactCrossing(int[] path, int left, int top, int right, int bottom) {
        for (int i = 0; i < path.length; i += 2) {
            if (path[i] >= left && path[i] <= right && path[i + 1] >= top && path[i + 1] <= bottom) {
                return true;
            }
            if (i + 3 < path.length) {
                int ax = path[i], ay = path[i + 1], bx = path[i + 2], by = path[i + 3];
                if (exactSegments(ax, ay, bx, by, left, top, right, top)
                        || exactSegments(ax, ay, bx, by, right, top, right, bottom)
                        || exactSegments(ax, ay, bx, by, right, bottom, left, bottom)
                        || exactSegments(ax, ay, bx, by, left, bottom, left, top)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void hiddenEndpointDecisionsMatchIndependentFourEdgeOracle() {
        Random random = new Random(20261122L);
        Fixture fixture = new Fixture();
        for (int sample = 0; sample < 2000; sample++) {
            int[] path = new int[(2 + random.nextInt(5)) * 2];
            // Both endpoints are unambiguously outside their own viewports.
            path[0] = -40 - random.nextInt(60);
            path[1] = random.nextInt(101) - 40;
            path[path.length - 2] = 120 + random.nextInt(60);
            path[path.length - 1] = random.nextInt(101) - 40;
            for (int i = 2; i < path.length - 2; i++) {
                path[i] = random.nextInt(161) - 30;
            }
            boolean expected = exactCrossing(path, 0, 0, 19, 19)
                    && exactCrossing(path, 80, 0, 99, 19);
            Rectangle[] actual = fixture.clips(path);
            assertEquals(expected, actual.length == 1,
                    "sample " + sample + " " + java.util.Arrays.toString(path));
            if (expected) {
                assertEquals(fixture.strategy.path.getBounds(), actual[0]);
            } else {
                assertEquals(0, actual.length);
            }
        }
    }

    @Test
    public void returnedCommonRectangleIsNotClaimedToBeAnExactFragmentMask() {
        Rectangle[] clip = new Fixture().clips(-10, 10, 110, 10);
        assertEquals(1, clip.length);
        assertTrue(clip[0].contains(-10, 10));
        assertTrue(clip[0].contains(110, 10));
        assertFalse(new Rectangle(0, 0, 20, 20).contains(-10, 10));
    }
}
