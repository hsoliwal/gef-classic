/*******************************************************************************
 * Copyright (c) 2004, 2026 IBM Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.ImageData;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import org.eclipse.draw2d.AWTGraphics;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class GraphicsClipping {

	private Graphics graphics;

	@Test
	public void testSimpleClip() {
		Rectangle rect = new Rectangle(14, 21, 30, 40);
		graphics.setClip(rect);
		graphics.drawPoint(0, 0);
		assertEquals(gcClipping(), rect);

		rect.translate(5, 5);
		graphics.clipRect(rect);
		graphics.drawPoint(0, 0);
		rect.resize(-5, -5);
		assertEquals(rect, gcClipping());
	}

	private Rectangle graphicsClip() {
		return graphics.getClip(new Rectangle());
	}

	@Test
	public void testTranslatedClip() {
		Rectangle rect = new Rectangle(14, 21, 300, 400);
		graphics.setClip(rect);
		graphics.translate(9, 7);

		graphics.drawPoint(0, 0);
		assertEquals(rect, gcClipping());

		rect.translate(-9, -7);
		assertEquals(rect, graphicsClip());

		Rectangle intersect = new Rectangle(50, 50, 50, 50);
		graphics.clipRect(intersect);
		rect.intersect(intersect);

		graphics.drawPoint(0, 0);
		assertEquals(graphicsClip(), rect);

		rect.translate(9, 7);
		assertEquals(gcClipping(), rect);
	}

	@Test
	public void testZoomedClip() {
		Rectangle rect = new Rectangle(14, 21, 300, 400);
		graphics.setClip(rect);
		graphics.scale(2.0);
		graphics.translate(90, 0);
		graphics.drawPoint(0, 0);

		rect.scale(0.5);
		rect.translate(-90, 0);
		assertEquals(graphicsClip(), rect);
	}

	@Test
	public void testRestoreClip() {
		Rectangle rect = new Rectangle(50, 50, 300, 400);
		graphics.setClip(rect);

		graphics.pushState();
		graphics.translate(100, 75);
		graphics.clipRect(new Rectangle(0, 0, 250, 375));
		assertEquals(graphicsClip(), new Rectangle(0, 0, 250, 375));

		graphics.restoreState();
		graphics.clipRect(new Rectangle(50, 50, 50, 50));
		assertEquals(graphicsClip(), new Rectangle(50, 50, 50, 50));

		graphics.restoreState();
		assertEquals(graphicsClip(), rect);

		graphics.popState();
		assertEquals(graphicsClip(), rect);
	}

	@Test
	public void testPopClip() {
		Rectangle rect = new Rectangle(50, 50, 300, 400);
		graphics.setClip(rect);

		graphics.pushState();
		graphics.translate(100, 75);
		graphics.clipRect(new Rectangle(0, 0, 250, 375));
		assertEquals(graphicsClip(), new Rectangle(0, 0, 250, 375));
		graphics.popState();

		graphics.pushState();
		graphics.clipRect(new Rectangle(50, 50, 50, 50));
		assertEquals(graphicsClip(), new Rectangle(50, 50, 50, 50));
		graphics.popState();

		assertEquals(graphicsClip(), rect);
	}

	@BeforeEach
	public void setUp() throws Exception {
		graphics = createGraphics();
	}

	@AfterEach
	public void tearDown() throws Exception {
		graphics.dispose();
	}

	protected abstract Graphics createGraphics();

	protected abstract Rectangle gcClipping();

	public static class SWTGraphicsClipping extends GraphicsClipping {
		private Image image;
		private GC gc;

		@Override
		@BeforeEach
		public void setUp() throws Exception {
			image = new Image(Display.getDefault(), 800, 600);
			gc = new GC(image);
			super.setUp();
		}

		@Override
		@AfterEach
		public void tearDown() throws Exception {
			super.tearDown();
			gc.dispose();
			image.dispose();
		}

		@Override
		protected Graphics createGraphics() {
			return new SWTGraphics(gc);
		}

		/**
		 * Exercise the real SWT GC underneath Draw2D: clip, nested clip, state
		 * restoration and pixel delivery remain native-backend responsibilities.
		 * This is an offscreen SWT Image oracle, not a visible compositor proof.
		 */
		@Test
		public void testNativeGcNestedClipPixelsAndRestore() {
			Display display = Display.getDefault();
			Color red = display.getSystemColor(SWT.COLOR_RED);
			Color blue = display.getSystemColor(SWT.COLOR_BLUE);
			Color green = display.getSystemColor(SWT.COLOR_GREEN);
			Color yellow = display.getSystemColor(SWT.COLOR_YELLOW);
			gc.setBackground(red);
			gc.fillRectangle(0, 0, 48, 48);

			SWTGraphics drawing = new SWTGraphics(gc);
			try {
				drawing.setClip(new Rectangle(8, 8, 24, 24));
				drawing.setBackgroundColor(blue);
				drawing.fillRectangle(new Rectangle(0, 0, 48, 48));
				drawing.pushState();
				drawing.clipRect(new Rectangle(16, 16, 8, 8));
				drawing.setBackgroundColor(green);
				drawing.fillRectangle(new Rectangle(0, 0, 48, 48));
				drawing.popState();
				drawing.setBackgroundColor(yellow);
				drawing.fillRectangle(new Rectangle(10, 10, 4, 4));
			} finally {
				drawing.dispose();
			}

			ImageData pixels = image.getImageData();
			assertPixel(pixels, 7, 7, red);
			assertPixel(pixels, 8, 8, blue);
			assertPixel(pixels, 11, 11, yellow);
			assertPixel(pixels, 16, 16, green);
			assertPixel(pixels, 23, 23, green);
			assertPixel(pixels, 24, 24, blue);
			assertPixel(pixels, 32, 32, red);
		}

		private static void assertPixel(ImageData pixels, int x, int y, Color expected) {
			assertEquals(expected.getRGB(), pixels.palette.getRGB(pixels.getPixel(x, y)),
					"SWT GC clipped pixel at " + x + "," + y); //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		protected Rectangle gcClipping() {
			return new Rectangle(gc.getClipping());
		}
	}

	public static class AWTGraphicsClipping extends GraphicsClipping {
		private BufferedImage image;
		private Graphics2D gc;

		@Override
		@BeforeEach
		public void setUp() throws Exception {
			image = new BufferedImage(800, 600, BufferedImage.TYPE_4BYTE_ABGR);
			gc = image.createGraphics();
			gc.setClip(0, 0, image.getWidth(), image.getHeight());
			super.setUp();
		}

		@Override
		@AfterEach
		public void tearDown() throws Exception {
			super.tearDown();
			gc.dispose();
		}

		@Override
		protected Graphics createGraphics() {
			return new AWTGraphics(gc);
		}

		@Override
		protected Rectangle gcClipping() {
			// AWT stores the clip in user coordinates, not device coordinates
			int dx = (int) gc.getTransform().getTranslateX();
			int dy = (int) gc.getTransform().getTranslateY();
			java.awt.Rectangle rect = gc.getClipBounds();
			rect.translate(dx, dy);
			return new Rectangle(rect.x, rect.y, rect.width, rect.height);
		}
	}
}
