/*******************************************************************************
 * Copyright (c) 2026 Hitesh Soliwal and Contributors to the Synexia Project.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Hitesh Soliwal and Contributors - bounded damage regression tests
 *******************************************************************************/
package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.UpdateListener;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;

import org.junit.jupiter.api.Test;

/** Real hierarchy/geometry qualification without creating a native Display. */
public class DeferredUpdateManagerDamageTest {

	@Test
	void emptyFirstContributionDoesNotEnlargeVisibleDamage() {
		for (boolean emptyFirst : new boolean[] { true, false }) {
			Probe manager = new Probe();
			Figure root = root(manager, new Rectangle(0, 0, 640, 480));
			OrderedFigure outside = child(root, emptyFirst ? 0 : 1, new Rectangle(700, 600, 10, 10));
			OrderedFigure visible = child(root, emptyFirst ? 1 : 0, new Rectangle(320, 200, 10, 10));
			manager.addDirtyRegion(outside, outside.getBounds());
			manager.addDirtyRegion(visible, visible.getBounds());
			manager.performUpdate();
			assertEquals(new Rectangle(320, 200, 10, 10), manager.damage());
			assertEquals(List.of(new Rectangle(320, 200, 10, 10)), manager.requests);
			assertEquals(2, manager.events.getFirst().regions.size());
			assertTrue(manager.events.getFirst().regions.get(outside).isEmpty());
			assertSame(emptyFirst ? outside : visible, manager.events.getFirst().order.getFirst());
		}
	}

	@Test
	void boundaryContactCannotWidenNonemptyDamage() {
		for (Rectangle edge : List.of(new Rectangle(640, 100, 20, 20),
				new Rectangle(100, 480, 20, 20), new Rectangle(-20, 100, 20, 20))) {
			Probe manager = new Probe();
			Figure root = root(manager, new Rectangle(0, 0, 640, 480));
			OrderedFigure touching = child(root, 0, edge);
			OrderedFigure visible = child(root, 1, new Rectangle(320, 200, 10, 10));
			manager.addDirtyRegion(touching, touching.getBounds());
			manager.addDirtyRegion(visible, visible.getBounds());
			manager.performUpdate();
			assertEquals(new Rectangle(320, 200, 10, 10), manager.damage());
			assertTrue(manager.events.getFirst().regions.get(touching).isEmpty());
		}
	}

	@Test
	void nestedScrolledViewportPreservesItsTranslatedDamage() {
		Probe manager = new Probe();
		Figure root = root(manager, new Rectangle(0, 0, 640, 480));
		Viewport viewport = new Viewport(true);
		viewport.setBounds(new Rectangle(100, 80, 200, 160));
		viewport.getHorizontalRangeModel().setAll(0, 200, 1_000_000);
		viewport.getVerticalRangeModel().setAll(0, 160, 1_000_000);
		viewport.setViewLocation(400_000, 300_000);
		root.add(viewport);
		OrderedFigure outside = child(viewport, 0, new Rectangle(900_000, 900_000, 10, 10));
		OrderedFigure visible = child(viewport, 1, new Rectangle(400_130, 300_100, 10, 10));
		manager.addDirtyRegion(outside, outside.getBounds());
		manager.addDirtyRegion(visible, visible.getBounds());
		manager.performUpdate();
		assertEquals(new Rectangle(130, 100, 10, 10), manager.damage());
		assertEquals(2, manager.events.getFirst().regions.size());
	}

	@Test
	void emptyOnlyKeepsItsEventAndFirstAnchorWithoutGraphics() {
		Probe manager = new Probe();
		Figure root = root(manager, new Rectangle(0, 0, 640, 480));
		OrderedFigure boundary = child(root, 0, new Rectangle(640, 100, 20, 20));
		OrderedFigure outside = child(root, 1, new Rectangle(800, 800, 10, 10));
		manager.addDirtyRegion(boundary, boundary.getBounds());
		manager.addDirtyRegion(outside, outside.getBounds());
		manager.performUpdate();
		assertEquals(1, manager.events.size());
		assertEquals(new Rectangle(640, 100, 0, 20), manager.damage());
		assertEquals(2, manager.events.getFirst().regions.size());
		assertTrue(manager.requests.isEmpty());
		manager.performUpdate();
		assertEquals(1, manager.events.size());
	}

	@Test
	void nonemptyEnvelopesAndSameFigureCoalescingRemainUnchanged() {
		Probe manager = new Probe();
		Figure root = root(manager, new Rectangle(-500, -400, 1_000, 800));
		OrderedFigure first = child(root, 0, new Rectangle(-450, -350, 30, 40));
		OrderedFigure second = child(root, 1, new Rectangle(200, 100, 20, 20));
		manager.addDirtyRegion(first, -445, -345, 5, 5);
		manager.addDirtyRegion(first, -425, -315, 5, 5);
		manager.addDirtyRegion(second, second.getBounds());
		manager.performUpdate();
		assertEquals(new Rectangle(-445, -345, 665, 465), manager.damage());
		assertEquals(new Rectangle(-445, -345, 25, 35), manager.events.getFirst().regions.get(first));
		assertEquals(2, manager.events.getFirst().regions.size());
		manager.addDirtyRegion(second, 205, 105, 3, 4);
		manager.performUpdate();
		assertEquals(new Rectangle(205, 105, 3, 4), manager.damage());
	}

	@Test
	void hiddenAndZeroSizeInputsDoNotQueueDamage() {
		Probe manager = new Probe();
		Figure root = root(manager, new Rectangle(0, 0, 640, 480));
		OrderedFigure hidden = child(root, 0, new Rectangle(10, 10, 10, 10));
		hidden.setVisible(false);
		manager.addDirtyRegion(hidden, hidden.getBounds());
		manager.addDirtyRegion(root, 10, 10, 0, 10);
		manager.addDirtyRegion(root, 10, 10, 10, 0);
		manager.performUpdate();
		assertEquals(0, manager.queued);
		assertTrue(manager.requests.isEmpty());
		assertTrue(manager.events.isEmpty());
	}

	@Test
	void damageQueuedDuringBoundsQueryIsRetainedForNextCycle() {
		Probe manager = new Probe();
		Figure root = root(manager, new Rectangle(0, 0, 640, 480));
		OrderedFigure later = child(root, 1, new Rectangle(500, 400, 10, 10));
		OrderedFigure first = child(root, 0, new Rectangle(100, 100, 10, 10));
		manager.addDirtyRegion(first, 100, 100, 10, 10);
		first.boundsHook = () -> manager.addDirtyRegion(later, 500, 400, 10, 10);
		manager.performUpdate();
		assertEquals(new Rectangle(100, 100, 10, 10), manager.damage());
		assertEquals(1, manager.events.getFirst().regions.size());
		manager.performUpdate();
		assertEquals(new Rectangle(500, 400, 10, 10), manager.damage());
		assertEquals(2, manager.events.size());
		assertTrue(manager.events.getLast().regions.containsKey(later));
	}

	@Test
	void validationAndAfterUpdateOrderSurvive() {
		Probe manager = new Probe();
		Figure root = root(manager, new Rectangle(0, 0, 640, 480));
		List<String> order = new ArrayList<>();
		Figure invalid = new Figure() {
			@Override public void validate() { order.add("validate"); super.validate(); }
		};
		manager.addUpdateListener(new UpdateListener() {
			@Override public void notifyValidating() { order.add("validating"); }
			@Override public void notifyPainting(Rectangle damage, Map<IFigure, Rectangle> regions) { order.add("paint"); }
		});
		manager.addInvalidFigure(invalid);
		manager.addInvalidFigure(invalid);
		manager.addDirtyRegion(root, 200, 100, 10, 10);
		manager.runWithUpdate(() -> {
			order.add("after-a");
			manager.runWithUpdate(() -> order.add("after-c"));
		});
		manager.runWithUpdate(() -> order.add("after-b"));
		manager.performUpdate();
		assertEquals(List.of("validating", "validate", "paint", "after-a", "after-b"), order);
		manager.performUpdate();
		assertEquals(List.of("validating", "validate", "paint", "after-a", "after-b", "after-c"), order);
	}

	@Test
	void permutationCorpusMatchesIndependentNonemptyEnvelope() {
		Random random = new Random(0x31D2D2026L);
		int[][] permutations = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 },
				{ 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };
		int mismatches = 0;
		String firstMismatch = null;
		for (int sample = 0; sample < 512; sample++) {
			int x = random.nextInt(20_001) - 10_000;
			int y = random.nextInt(20_001) - 10_000;
			Rectangle viewport = new Rectangle(x, y, 640, 480);
			Rectangle[] input = {
					new Rectangle(x + 700 + random.nextInt(500), y + 600, 20, 20),
					new Rectangle(x + 30 + random.nextInt(200), y + 20 + random.nextInt(160), 10, 10),
					new Rectangle(x + 600, y + 450, 80, 80) };
			Rectangle expected = envelope(viewport, input);
			for (int[] permutation : permutations) {
				Probe manager = new Probe();
				Figure root = root(manager, viewport);
				for (int bucket = 0; bucket < permutation.length; bucket++) {
					Rectangle rectangle = input[permutation[bucket]];
					OrderedFigure figure = child(root, bucket, rectangle);
					manager.addDirtyRegion(figure, rectangle);
				}
				manager.performUpdate();
				if (!expected.equals(manager.damage())) {
					mismatches++;
					if (firstMismatch == null) firstMismatch = expected + " != " + manager.damage();
				}
				assertEquals(3, manager.events.getFirst().regions.size());
			}
		}
		assertEquals(0, mismatches, "3072 complete samples; first mismatch: " + firstMismatch);
	}

	/** Independent coordinate oracle; no Draw2D union/intersection operations. */
	private static Rectangle envelope(Rectangle viewport, Rectangle[] input) {
		long left = Long.MAX_VALUE, top = Long.MAX_VALUE;
		long right = Long.MIN_VALUE, bottom = Long.MIN_VALUE;
		for (Rectangle rectangle : input) {
			long x1 = Math.max((long) viewport.x, rectangle.x);
			long y1 = Math.max((long) viewport.y, rectangle.y);
			long x2 = Math.min((long) viewport.x + viewport.width, (long) rectangle.x + rectangle.width);
			long y2 = Math.min((long) viewport.y + viewport.height, (long) rectangle.y + rectangle.height);
			if (x1 < x2 && y1 < y2) {
				left = Math.min(left, x1); top = Math.min(top, y1);
				right = Math.max(right, x2); bottom = Math.max(bottom, y2);
			}
		}
		return new Rectangle(Math.toIntExact(left), Math.toIntExact(top),
				Math.toIntExact(right - left), Math.toIntExact(bottom - top));
	}

	private static Figure root(Probe manager, Rectangle bounds) {
		Figure root = new Figure();
		root.setBounds(bounds);
		manager.setRoot(root);
		return root;
	}

	private static OrderedFigure child(Figure root, int hash, Rectangle bounds) {
		OrderedFigure child = new OrderedFigure(hash);
		child.setBounds(bounds);
		root.add(child);
		return child;
	}

	private static final class OrderedFigure extends Figure {
		private final int hash;
		private Runnable boundsHook;
		OrderedFigure(int hash) { this.hash = hash; }
		@Override public int hashCode() { return hash; }
		@Override public Rectangle getBounds() {
			Runnable hook = boundsHook;
			boundsHook = null;
			if (hook != null) hook.run();
			return super.getBounds();
		}
	}

	private record Event(Rectangle damage, Map<IFigure, Rectangle> regions, List<IFigure> order) { }

	private static final class Probe extends DeferredUpdateManager {
		private final List<Event> events = new ArrayList<>();
		private final List<Rectangle> requests = new ArrayList<>();
		private int queued;
		Probe() {
			addUpdateListener(new UpdateListener() {
				@Override public void notifyValidating() { }
				@Override public void notifyPainting(Rectangle damage, Map<IFigure, Rectangle> regions) {
					Map<IFigure, Rectangle> snapshot = new LinkedHashMap<>();
					regions.forEach((figure, rectangle) -> snapshot.put(figure, rectangle.getCopy()));
					events.add(new Event(damage.getCopy(), snapshot, new ArrayList<>(regions.keySet())));
				}
			});
		}
		@Override protected void sendUpdateRequest() { queued++; }
		@Override protected Graphics getGraphics(Rectangle region) {
			requests.add(region.getCopy());
			return null;
		}
		Rectangle damage() { return events.getLast().damage; }
	}
}
