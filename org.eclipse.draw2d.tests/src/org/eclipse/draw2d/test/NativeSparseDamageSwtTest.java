/*******************************************************************************
 * Copyright (c) 2026 Contributors to the Synexia Project.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.draw2d.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.NativeGraphicsSource;
import org.eclipse.draw2d.geometry.Rectangle;

import org.junit.jupiter.api.Test;

/** Actual SWT Control redraw dispatch; pixels and JNI performance are separate gates. */
public class NativeSparseDamageSwtTest {

    private static final class RecordingCanvas extends Canvas {
        final List<Rectangle> requested = new ArrayList<>();

        RecordingCanvas(Composite parent) {
            super(parent, SWT.NONE);
        }

        @Override
        public void redraw(int x, int y, int width, int height, boolean all) {
            requested.add(new Rectangle(x, y, width, height));
            super.redraw(x, y, width, height, all);
        }
    }

    @Test
    void exactManagerPostsSparseOrLegacyNativeInvalidation() {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        try {
            RecordingCanvas canvas = new RecordingCanvas(shell);
            canvas.setBounds(0, 0, 400, 300);
            shell.setSize(450, 350);
            shell.open();

            Figure root = new Figure();
            root.setBounds(new Rectangle(0, 0, 400, 300));
            Figure first = new Figure();
            first.setBounds(new Rectangle(5, 7, 10, 10));
            Figure last = new Figure();
            last.setBounds(new Rectangle(300, 250, 10, 10));
            root.add(first);
            root.add(last);

            DeferredUpdateManager manager = new DeferredUpdateManager(new NativeGraphicsSource(canvas));
            manager.setRoot(root);
            canvas.requested.clear();
            manager.addDirtyRegion(first, 5, 7, 10, 10);
            manager.addDirtyRegion(last, 300, 250, 10, 10);
            manager.performUpdate();

            boolean sparse = Boolean.getBoolean("draw2d.native.sparseDamage");
            // Independent environment marker from CI prevents a false opt-in PASS
            // if Tycho accidentally omits the JVM -D option from the test fork.
            if ("true".equals(System.getenv("DRAW2D_SPARSE_NATIVE_REQUIRED"))) {
                assertEquals(true, sparse);
            }
            if (sparse) {
                assertEquals(2, canvas.requested.size());
                // HashMap iteration order is unspecified: compare sets, not order.
                List<Rectangle> expected = List.of(new Rectangle(5, 7, 10, 10),
                        new Rectangle(300, 250, 10, 10));
                assertEquals(2, canvas.requested.stream().distinct().count());
                for (Rectangle request : canvas.requested) {
                    assertEquals(true, expected.contains(request));
                }
            } else {
                assertEquals(List.of(new Rectangle(5, 7, 305, 253)), canvas.requested);
            }
        } finally {
            shell.dispose();
        }
    }
}
