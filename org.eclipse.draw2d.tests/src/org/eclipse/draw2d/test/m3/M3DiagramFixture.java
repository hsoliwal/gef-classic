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

import java.util.SplittableRandom;

import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GraphicsSource;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The figure stack GEF builds for a diagram (FreeformViewport over a
 * ScalableFreeformLayeredPane over a FreeformLayer with a FreeformLayout),
 * filled with nodes on a jittered grid, headless: the root hands out a
 * DeferredUpdateManager whose update requests are run by the caller and whose
 * graphics are {@link M3RecordingGraphics}.
 */
public class M3DiagramFixture {

	/** Grid pitch of the nodes. */
	public static final int PITCH_X = 60;
	/** Grid pitch of the nodes. */
	public static final int PITCH_Y = 40;

	/** Headless update manager: requests are not posted, {@link #flush()} runs them. */
	public static final class HeadlessUpdateManager extends DeferredUpdateManager {
		M3RecordingGraphics last;
		int repairs;
		/** When set, repairs paint into this graphics instead of a new recording one. */
		public M3NullGraphics nullGraphics;
		/**
		 * Dirty regions covering the whole bounds of the root, a viewport, a layered
		 * pane or a layer: repaints of the whole diagram view rather than of the
		 * figures a delta touched.
		 */
		public int wholeRepaints;
		/** Pixels repaired (sum of the repair regions). */
		public long repairedArea;

		@Override
		public synchronized void addDirtyRegion(IFigure figure, int x, int y, int w, int h) {
			if (w > 0 && h > 0 && (figure.getParent() == null || figure instanceof Viewport
					|| figure instanceof LayeredPane || figure.getParent() instanceof LayeredPane)) {
				Rectangle b = figure.getBounds();
				if (x <= b.x && y <= b.y && x + w >= b.right() && y + h >= b.bottom()) {
					wholeRepaints++;
				}
			}
			super.addDirtyRegion(figure, x, y, w, h);
		}

		@Override
		protected void sendUpdateRequest() {
			// the caller runs performUpdate()
		}

		/** Runs one deferred update request, as the display would run the posted one. */
		public void requestUpdate() {
			new UpdateRequest().run();
		}

		/** Runs the pending validation and repair; {@link #last} is the repair's graphics or null. */
		public void flush() {
			last = null;
			performUpdate();
		}
	}

	/** Root whose update manager is the headless one. */
	static final class Root extends Figure {
		final HeadlessUpdateManager manager;

		Root(HeadlessUpdateManager manager) {
			this.manager = manager;
			setLayoutManager(new StackLayout());
		}

		@Override
		public UpdateManager getUpdateManager() {
			return manager;
		}
	}

	public final HeadlessUpdateManager manager = new HeadlessUpdateManager();
	public final Figure root = new Root(manager);
	public final FreeformViewport viewport = new FreeformViewport();
	public final ScalableFreeformLayeredPane pane = new ScalableFreeformLayeredPane();
	public final FreeformLayer layer = new FreeformLayer();
	public final int columns;
	public final int width;
	public final int height;
	private final SplittableRandom random;

	/**
	 * @param nodes  number of nodes
	 * @param width  viewport width
	 * @param height viewport height
	 * @param seed   layout seed
	 */
	public M3DiagramFixture(int nodes, int width, int height, long seed) {
		this.width = width;
		this.height = height;
		this.random = new SplittableRandom(seed);
		this.columns = Math.max(1, (int) Math.ceil(Math.sqrt(nodes * 1.5)));
		manager.setRoot(root);
		manager.setGraphicsSource(new GraphicsSource() {
			@Override
			public Graphics getGraphics(Rectangle region) {
				if (manager.nullGraphics != null) {
					return manager.nullGraphics.reset(region);
				}
				manager.last = new M3RecordingGraphics(region, false, frame);
				manager.repairs++;
				manager.repairedArea += (long) region.width * region.height;
				return manager.last;
			}

			@Override
			public void flushGraphics(Rectangle region) {
				// nothing to flush headless
			}
		});
		layer.setLayoutManager(new FreeformLayout());
		pane.add(layer, "primary"); //$NON-NLS-1$
		viewport.setContents(pane);
		root.add(viewport);
		root.setBounds(new Rectangle(0, 0, width, height));
		for (int i = 0; i < nodes; i++) {
			addNode(i);
		}
		manager.flush();
	}

	/** Bounds of the i-th grid node, jittered by the seed. */
	public Rectangle nodeBounds(int i) {
		int x = (i % columns) * PITCH_X + random.nextInt(8);
		int y = (i / columns) * PITCH_Y + random.nextInt(6);
		return new Rectangle(x, y, 40 + random.nextInt(8), 24 + random.nextInt(4));
	}

	/** Adds the i-th grid node at the end of the layer. */
	public IFigure addNode(int i) {
		RectangleFigure node = new RectangleFigure();
		layer.add(node, nodeBounds(i));
		return node;
	}

	/** Width of the whole diagram. */
	public int diagramWidth() {
		return columns * PITCH_X + 48;
	}

	/** Height of the whole diagram. */
	public int diagramHeight() {
		return ((layer.getChildren().size() + columns - 1) / columns) * PITCH_Y + 30;
	}

	/** The canvas raster the repairs paint into (frame proofs), or null. */
	public M3FrameRaster frame;

	/** Starts frame proofs: a raster of the root's size, painted once by a repair of the whole root. */
	public void trackFrame() {
		Rectangle b = root.getBounds();
		frame = new M3FrameRaster(b.width, b.height);
		root.repaint();
		manager.flush();
	}

	/** Resizes the canvas: the root's bounds, a revalidate, and the raster (which keeps its pixels). */
	public void resize(int w, int h) {
		root.setBounds(new Rectangle(0, 0, w, h));
		root.revalidate();
		if (frame != null) {
			frame.resize(w, h);
		}
	}

	/** A fresh paint of the whole root into a new raster: what the canvas must show. */
	public M3FrameRaster freshFrame() {
		Rectangle b = root.getBounds();
		M3FrameRaster fresh = new M3FrameRaster(b.width, b.height);
		root.paint(new M3RecordingGraphics(new Rectangle(0, 0, b.width, b.height), false, fresh));
		return fresh;
	}

	/** Paints the viewport area {@code clip} (viewport coordinates) and returns the record. */
	public M3RecordingGraphics paint(Rectangle clip, boolean keepLog) {
		M3RecordingGraphics g = new M3RecordingGraphics(clip, keepLog);
		root.paint(g);
		return g;
	}
}
