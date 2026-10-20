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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.internal.m3.M3FigureChildren;
import org.eclipse.draw2d.internal.m3.M3FigureTrust;
import org.eclipse.draw2d.internal.m3.M3ViewportMode;

import org.junit.jupiter.api.Test;

/**
 * Edges of the child index that the recorded differential does not reach on
 * its own: trust bits per overridden method, the verify mode catching bounds
 * changed behind the index's back, the paint fallbacks, re-entrant hit tests,
 * concurrent modification during paint, an emptied freeform layer, and hooks
 * for figures the index does not know. Runs with
 * {@code -Ddraw2d.viewport.mode=viewport -Ddraw2d.viewport.verify=true}.
 */
public class M3ChildIndexTest {

	static final class BoundsOverride extends Figure {
		@Override
		public Rectangle getBounds() {
			return super.getBounds();
		}
	}

	static final class VisibleOverride extends Figure {
		@Override
		public boolean isVisible() {
			return super.isVisible();
		}
	}

	static final class FindOverride extends Figure {
		@Override
		public IFigure findFigureAt(int x, int y, TreeSearch search) {
			return super.findFigureAt(x, y, search);
		}
	}

	static final class EnabledOverride extends Figure {
		@Override
		public boolean isEnabled() {
			return super.isEnabled();
		}
	}

	static final class ValidOverride extends Figure {
		@Override
		protected boolean isValid() {
			return super.isValid();
		}
	}

	static final class ChildrenOverride extends Figure {
		@Override
		public List<? extends IFigure> getChildren() {
			return super.getChildren();
		}
	}

	/** Re-enters its parent's hit test from its own. */
	static final class Reentrant extends Figure {
		@Override
		public IFigure findFigureAt(int x, int y, TreeSearch search) {
			if (getParent() instanceof Layer layer) {
				layer.containsPoint(x, y);
			}
			return super.findFigureAt(x, y, search);
		}
	}

	/** Removes a sibling while painting. */
	static final class SiblingRemover extends Figure {
		IFigure victim;

		@Override
		public void paint(Graphics graphics) {
			if (victim != null && victim.getParent() != null) {
				getParent().remove(victim);
			}
		}
	}

	private static void assumeViewport() {
		assumeTrue(M3ViewportMode.ENABLED && M3ViewportMode.VERIFY, "needs -Ddraw2d.viewport.mode=viewport and verify"); //$NON-NLS-1$
	}

	private static Layer layer(int nodes) {
		Layer layer = new Layer();
		layer.setLayoutManager(new XYLayout());
		layer.setBounds(new Rectangle(0, 0, 4000, 4000));
		for (int i = 0; i < nodes; i++) {
			Figure f = new Figure();
			f.setOpaque(true);
			layer.add(f, new Rectangle((i % 20) * 50, (i / 20) * 40, 40, 30));
		}
		layer.validate();
		return layer;
	}

	@Test
	public void trustBitsFollowOverrides() {
		int all = M3FigureTrust.ALL | M3FigureTrust.CONTAINER | M3FigureTrust.INVALIDATION;
		assertEquals(all, M3FigureTrust.of(new Figure()));
		assertEquals(0, M3FigureTrust.of(new BoundsOverride()) & M3FigureTrust.BOUNDS);
		assertEquals(0, M3FigureTrust.of(new VisibleOverride()) & M3FigureTrust.PAINT);
		assertEquals(0, M3FigureTrust.of(new FindOverride()) & M3FigureTrust.HIT);
		assertEquals(M3FigureTrust.CONTAINS, M3FigureTrust.of(new FindOverride()) & M3FigureTrust.CONTAINS);
		assertEquals(0, M3FigureTrust.of(new EnabledOverride()) & M3FigureTrust.MOUSE);
		assertEquals(0, M3FigureTrust.of(new ValidOverride()) & M3FigureTrust.VALIDATE);
		assertEquals(0, M3FigureTrust.of(new ChildrenOverride()) & M3FigureTrust.CONTAINER);
		IFigure proxy = (IFigure) Proxy.newProxyInstance(IFigure.class.getClassLoader(), new Class<?>[] { IFigure.class },
				(p, m, a) -> null);
		assertEquals(0, M3FigureTrust.of(proxy));
	}

	@Test
	public void verifyCatchesBoundsChangedWithoutFigureMoved() {
		assumeViewport();
		Layer layer = layer(100);
		IFigure child = layer.getChildren().get(5);
		int x = child.getBounds().x + 1, y = child.getBounds().y + 1;
		assertSame(child, layer.findFigureAt(x, y));
		child.getBounds().x += 2000;
		assertThrows(IllegalStateException.class, () -> layer.findFigureAt(x, y));
		assertThrows(IllegalStateException.class,
				() -> layer.paint(new M3NullGraphics().reset(new Rectangle(240, 0, 60, 40))));
		child.getBounds().x -= 2000;
		IFigure other = layer.getChildren().get(6);
		other.getBounds().x += 2;
		assertThrows(IllegalStateException.class, () -> layer.findFigureAt(other.getBounds().x - 1, 5));
		other.getBounds().x -= 2;
		// a child moved onto the point behind the index's back: the index misses it
		IFigure far = layer.getChildren().get(99);
		far.getBounds().setLocation(3000, 3000);
		assertThrows(IllegalStateException.class, () -> layer.findFigureAt(3001, 3001));
		IFigure last = layer.getChildren().get(98);
		last.getBounds().setLocation(3500, 3500);
		assertThrows(IllegalStateException.class, () -> layer.findFigureAt(3501, 3501));
	}

	static final class PrimTranslateOverride extends Figure {
		@Override
		protected void primTranslate(int dx, int dy) {
			super.primTranslate(dx, dy);
		}
	}

	static final class FireMovedOverride extends Figure {
		@Override
		protected void fireFigureMoved() {
			super.fireFigureMoved();
		}
	}

	static final class FlagOverride extends Figure {
		@Override
		protected boolean getFlag(int flag) {
			return super.getFlag(flag);
		}
	}

	static final class SetValidOverride extends Figure {
		@Override
		public void setValid(boolean value) {
			super.setValid(value);
		}
	}

	static final class ValidateOverride extends Figure {
		@Override
		public void validate() {
			super.validate();
		}
	}

	static final class InvalidateOverride extends Figure {
		@Override
		public void invalidate() {
			super.invalidate();
		}
	}

	static final class RevalidateOverride extends Figure {
		@Override
		public void revalidate() {
			super.revalidate();
		}
	}

	static final class RevIterableOverride extends Figure {
		@Override
		public Iterable<IFigure> getChildrenRevIterable() {
			return super.getChildrenRevIterable();
		}
	}

	@Test
	public void trustBitsForTheRemainingOverrides() {
		assertEquals(0, M3FigureTrust.of(new PrimTranslateOverride()) & M3FigureTrust.BOUNDS);
		assertEquals(0, M3FigureTrust.of(new FireMovedOverride()) & M3FigureTrust.BOUNDS);
		int flag = M3FigureTrust.of(new FlagOverride());
		assertEquals(M3FigureTrust.BOUNDS | M3FigureTrust.CONTAINS | M3FigureTrust.CONTAINER | M3FigureTrust.INVALIDATION, flag);
		assertEquals(0, M3FigureTrust.of(new SetValidOverride()) & M3FigureTrust.VALIDATE);
		assertEquals(0, M3FigureTrust.of(new ValidateOverride()) & M3FigureTrust.VALIDATE);
		assertEquals(0, M3FigureTrust.of(new RevIterableOverride()) & M3FigureTrust.CONTAINER);
		assertEquals(0, M3FigureTrust.of(new InvalidateOverride()) & M3FigureTrust.INVALIDATION);
		assertEquals(0, M3FigureTrust.of(new RevalidateOverride()) & M3FigureTrust.INVALIDATION);
	}

	@Test
	public void xyLayoutPassesFollowManagerAndOrigin() {
		assumeViewport();
		Layer layer = layer(80);
		IFigure first = layer.getChildren().get(0);
		layer.setBorder(new org.eclipse.draw2d.MarginBorder(7));
		layer.validate();
		assertEquals(new Rectangle(7, 7, 40, 30), first.getBounds());
		XYLayout fresh = new XYLayout();
		for (IFigure child : layer.getChildren()) {
			fresh.setConstraint(child, new Rectangle(child.getBounds().x + 1, child.getBounds().y, 40, 30));
		}
		layer.setLayoutManager(fresh);
		layer.validate();
		assertEquals(new Rectangle(15, 14, 40, 30), first.getBounds());
		XYLayout subclass = new XYLayout() {
			// a subclass: the stock loop lays it out
		};
		subclass.setConstraint(first, new Rectangle(1, 2, 3, 4));
		layer.setLayoutManager(subclass);
		layer.validate();
		assertEquals(new Rectangle(8, 9, 3, 4), first.getBounds());
	}

	@Test
	public void partsCreatedAfterRemovalsSkipFreeSlots() {
		Figure host = new Figure();
		List<IFigure> children = new ArrayList<>();
		for (int i = 0; i < 70; i++) {
			Figure f = new Figure();
			f.setBounds(new Rectangle(i, i, i % 3 == 0 ? 5 : 0, i % 2 == 0 ? 4 : -1));
			children.add(f);
		}
		M3FigureChildren index = new M3FigureChildren(host, children);
		IFigure gone = children.remove(10);
		index.removed(gone);
		index.moved(children.get(3));
		assertEquals(0, index.validation().validated());
		assertNotNull(index.freeform().extent(host));
		assertNotNull(index.layout());
		index.removed(children.remove(0));
		index.moved(children.get(0));
	}

	@Test
	public void paintFallsBackUnderClippingStrategyAndWideClips() {
		assumeViewport();
		Layer layer = layer(100);
		M3NullGraphics g = new M3NullGraphics();
		layer.paint(g.reset(new Rectangle(0, 0, 4000, 4000)));
		int wide = g.draws;
		assertEquals(100, wide);
		layer.setClippingStrategy(new IClippingStrategy() {
			@Override
			public Rectangle[] getClip(IFigure childFigure) {
				return new Rectangle[] { childFigure.getBounds() };
			}
		});
		layer.paint(g.reset(new Rectangle(0, 0, 4000, 4000)));
		assertEquals(wide, g.draws);
	}

	@Test
	public void reentrantHitTestWorksOnACopy() {
		assumeViewport();
		Layer layer = layer(80);
		Reentrant r = new Reentrant();
		layer.add(r, new Rectangle(2, 2, 10, 10));
		layer.validate();
		assertSame(r, layer.findFigureAt(5, 5));
		assertNull(layer.findMouseEventTargetAt(5, 5));
	}

	@Test
	public void removingASiblingDuringPaintThrows() {
		assumeViewport();
		Layer layer = layer(80);
		SiblingRemover remover = new SiblingRemover();
		remover.victim = layer.getChildren().get(3);
		layer.add(remover, new Rectangle(0, 0, 30, 30), 0);
		layer.validate();
		assertThrows(ConcurrentModificationException.class,
				() -> layer.paint(new M3NullGraphics().reset(new Rectangle(0, 0, 100, 100))));
	}

	@Test
	public void emptiedFreeformLayerKeepsItsIndex() {
		assumeViewport();
		FreeformLayer free = new FreeformLayer();
		free.setLayoutManager(new FreeformLayout());
		for (int i = 0; i < 70; i++) {
			free.add(new Figure(), new Rectangle(i * 3, i * 2, 10, 10));
		}
		free.validate();
		assertEquals(new Rectangle(0, 0, 217, 148), free.getFreeformExtent());
		new ArrayList<>(free.getChildren()).forEach(free::remove);
		assertEquals(new Rectangle(0, 0, 0, 0), free.getFreeformExtent());
		free.add(new Figure(), new Rectangle(4, 4, 6, 6));
		free.validate();
		assertEquals(new Rectangle(4, 4, 6, 6), free.getFreeformExtent());
	}

	@Test
	public void indexOverEmptyChildrenAndForeignFigures() {
		Logger log = Logger.getLogger("org.eclipse.draw2d.internal.m3"); //$NON-NLS-1$
		Level level = log.getLevel();
		log.setLevel(Level.FINE);
		try {
			Figure host = new Figure();
			List<IFigure> children = new ArrayList<>();
			for (int i = 0; i < 40; i++) {
				children.add(i % 2 == 0 ? new Figure() : new VisibleOverride());
			}
			M3FigureChildren index = new M3FigureChildren(host, children);
			Figure stranger = new Figure();
			index.removed(stranger);
			index.moved(stranger);
			index.validity(stranger, false);
			assertNotNull(index.validation());
			index.validity(stranger, true);
			assertEquals(0, index.validation().validated());
			assertEquals(0, index.layout().laidOut());
			index.layout().constraintChanged(stranger);
			assertNull(index.findFigureAt(1, 1, new TreeSearch() {
				@Override
				public boolean accept(IFigure figure) {
					return true;
				}

				@Override
				public boolean prune(IFigure figure) {
					return false;
				}
			}));
		} finally {
			log.setLevel(level);
		}
	}
}
