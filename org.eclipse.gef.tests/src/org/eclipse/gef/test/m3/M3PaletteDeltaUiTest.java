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
package org.eclipse.gef.test.m3;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.preference.PreferenceStore;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.palette.DefaultPaletteViewerPreferences;
import org.eclipse.gef.ui.palette.PaletteViewer;

/**
 * Palette children deltas (correction sites PaletteEditPart.java:390 and
 * PaletteStackEditPart.java:270): random adds, removes, moves, visibility
 * toggles and setChildren on a drawer and a stack; after each change the child
 * edit parts must mirror getModelChildren() exactly, every edit part of an
 * entry that stayed must be the same object, every child entry must carry the
 * child listener exactly once (none on removed entries), and in the stack
 * only the active entry's figure may be visible. Then the time of single adds
 * and removes on a drawer of n entries. Needs a Display: runs on the hidden
 * desktop (ui_lock.sh + hidden_java.sh). Usage:
 * {@code M3PaletteDeltaUiTest <out.tsv> <label> <steps> <n>}; exit 1 on a failure.
 */
public final class M3PaletteDeltaUiTest {

	private M3PaletteDeltaUiTest() {
	}

	static final class Tool extends ToolEntry {
		Tool(String label) {
			super(label, null, null, null, SelectionTool.class);
		}
	}

	private static int listenerCount(PaletteEntry entry) throws ReflectiveOperationException {
		Field f = PaletteEntry.class.getDeclaredField("listeners"); //$NON-NLS-1$
		f.setAccessible(true);
		return ((PropertyChangeSupport) f.get(entry)).getPropertyChangeListeners().length;
	}

	private static EditPart part(PaletteViewer viewer, Object model) {
		return viewer.getEditPartRegistry().get(model);
	}

	/** Checks the container's edit part against the model; returns null or the first problem. */
	@SuppressWarnings("unchecked")
	private static String check(PaletteViewer viewer, PaletteContainer container, Map<Object, EditPart> before,
			List<PaletteEntry> removed) throws ReflectiveOperationException {
		EditPart cp = part(viewer, container);
		List<PaletteEntry> model = (List<PaletteEntry>) invokeModelChildren(cp);
		List<? extends EditPart> parts = cp.getChildren();
		if (parts.size() != model.size()) {
			return "size " + parts.size() + " vs model " + model.size(); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (int i = 0; i < parts.size(); i++) {
			if (parts.get(i).getModel() != model.get(i)) {
				return "edit part " + i + " shows another entry"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			EditPart old = before.get(model.get(i));
			if (old != null && old != parts.get(i)) {
				return "edit part of a kept entry was recreated at " + i; //$NON-NLS-1$
			}
		}
		for (PaletteEntry e : container.getChildren()) {
			// the container's own listener (its edit part) plus the child listener
			int n = listenerCount(e);
			EditPart ep = part(viewer, e);
			int expected = 1 + (ep != null && ep.isActive() ? 1 : 0);
			if (n != expected) {
				return "entry " + e.getLabel() + " has " + n + " listeners, expected " + expected; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
		for (PaletteEntry e : removed) {
			if (e.getParent() == null && listenerCount(e) != 0) {
				return "removed entry " + e.getLabel() + " still has listeners"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		if (container instanceof PaletteStack stack) {
			for (EditPart ep : parts) {
				boolean active = ep.getModel() == stack.getActiveEntry();
				if (!active && ((GraphicalEditPart) ep).getFigure().isVisible()) {
					return "stack shows the inactive entry " + ((PaletteEntry) ep.getModel()).getLabel(); //$NON-NLS-1$
				}
			}
		}
		return null;
	}

	private static Object invokeModelChildren(EditPart cp) throws ReflectiveOperationException {
		return cp.getClass().getMethod("getModelChildren").invoke(cp); //$NON-NLS-1$
	}

	private static Map<Object, EditPart> snapshot(PaletteViewer viewer, PaletteContainer container) {
		Map<Object, EditPart> m = new IdentityHashMap<>();
		for (EditPart ep : part(viewer, container).getChildren()) {
			m.put(ep.getModel(), ep);
		}
		return m;
	}

	private static int nextLabel;

	private static PaletteEntry entry(SplittableRandom r, boolean separators) {
		return separators && r.nextInt(6) == 0 ? new PaletteSeparator() : new Tool("t" + nextLabel++); //$NON-NLS-1$
	}

	/** One random change of {@code c}; the entries it removed go to {@code removed}. */
	private static void change(SplittableRandom r, PaletteContainer c, boolean separators, List<PaletteEntry> removed) {
		List<? extends PaletteEntry> kids = c.getChildren();
		int op = kids.isEmpty() ? 0 : r.nextInt(6);
		switch (op) {
		case 0 -> c.add(r.nextInt(kids.size() + 1), entry(r, separators));
		case 1 -> {
			PaletteEntry e = kids.get(r.nextInt(kids.size()));
			c.remove(e);
			removed.add(e);
		}
		case 2 -> c.moveUp(kids.get(r.nextInt(kids.size())));
		case 3 -> c.moveDown(kids.get(r.nextInt(kids.size())));
		case 4 -> {
			PaletteEntry e = kids.get(r.nextInt(kids.size()));
			e.setVisible(!e.isVisible());
		}
		default -> {
			List<PaletteEntry> next = new ArrayList<>(kids);
			if (!next.isEmpty()) {
				removed.add(next.remove(r.nextInt(next.size())));
			}
			next.add(r.nextInt(next.size() + 1), entry(r, separators));
			if (next.size() > 2) {
				next.add(next.remove(0));
			}
			c.setChildren(next);
		}
		}
	}

	public static void main(String[] args) throws Exception {
		String label = args[1];
		int steps = Integer.parseInt(args[2]);
		int n = Integer.parseInt(args[3]);
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(400, 600);
		PaletteViewer viewer = new PaletteViewer();
		viewer.setPaletteViewerPreferences(new DefaultPaletteViewerPreferences(new PreferenceStore()));
		viewer.createControl(shell);
		PaletteRoot root = new PaletteRoot();
		PaletteDrawer drawer = new PaletteDrawer("drawer"); //$NON-NLS-1$
		PaletteStack stack = new PaletteStack("stack", null, null); //$NON-NLS-1$
		SplittableRandom r = new SplittableRandom(17);
		for (int i = 0; i < 20; i++) {
			drawer.add(entry(r, true));
		}
		for (int i = 0; i < 5; i++) {
			stack.add(new Tool("s" + i)); //$NON-NLS-1$
		}
		drawer.add(stack);
		root.add(drawer);
		viewer.setPaletteRoot(root);
		shell.open();
		StringBuilder out = new StringBuilder();
		String failure = null;
		for (int i = 0; i < steps && failure == null; i++) {
			boolean onStack = r.nextInt(4) == 0;
			PaletteContainer c = onStack ? stack : drawer;
			Map<Object, EditPart> before = snapshot(viewer, c);
			List<PaletteEntry> removed = new ArrayList<>();
			change(r, c, !onStack, removed);
			if (onStack && stack.getChildren().isEmpty()) {
				stack.add(new Tool("s" + nextLabel++)); //$NON-NLS-1$
			}
			String problem = check(viewer, c, before, removed);
			if (problem != null) {
				failure = "step " + i + (onStack ? " stack: " : " drawer: ") + problem; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
		out.append(label).append("\tcorrectness\t").append(steps).append(" steps\t").append(failure == null ? "PASS" : failure) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.append('\n');
		// cost of single changes on a drawer of n entries
		PaletteDrawer big = new PaletteDrawer("big"); //$NON-NLS-1$
		for (int i = 0; i < n; i++) {
			big.add(new Tool("b" + i)); //$NON-NLS-1$
		}
		root.add(big);
		int reps = 400;
		long[] t = new long[reps];
		for (int i = 0; i < reps; i++) {
			long s = System.nanoTime();
			Tool added = new Tool("x" + i); //$NON-NLS-1$
			big.add(r.nextInt(big.getChildren().size()), added);
			big.remove(added);
			t[i] = System.nanoTime() - s;
		}
		java.util.Arrays.sort(t);
		out.append(label).append("\tadd + remove one entry\t").append(n).append(" entries\t").append(t[reps / 2]) //$NON-NLS-1$ //$NON-NLS-2$
				.append(" ns median\n"); //$NON-NLS-1$
		Files.writeString(Path.of(args[0]), out.toString(), StandardCharsets.UTF_8,
				java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
		shell.dispose();
		display.dispose();
		System.exit(failure == null ? 0 : 1);
	}
}
