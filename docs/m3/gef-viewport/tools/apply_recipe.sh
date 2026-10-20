#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
#
# Applies the Draw2d viewport hook recipe (recipe/META-INF/rewrite/m3-draw2d-viewport.yml) twice with the
# Synexia per-file applier; the second pass must report UNCHANGED for every file (fixed point).
# Usage: apply_recipe.sh <worktree>
set -e
W=$1
C=K:/m3-work/syn-recipes/synexia-openrewrite-recipes/crates/m3-viewport-catalogue-recipes
JH="C:/Program Files/Java/jdk-21"
CPR="$W/docs/m3/gef-viewport/recipe;$C/target/classes;$(cat $C/target/cp.txt)"
D=$W/org.eclipse.draw2d/src/org/eclipse/draw2d
FILES="$D/Figure.java $D/Layer.java $D/XYLayout.java $D/AbstractConstraintLayout.java $D/FreeformHelper.java
	$D/DeferredUpdateManager.java $D/LightweightSystem.java"
mkdir -p S:/m3-scratch/tmp
for pass in 1 2; do
	echo "recipe pass $pass:"
	"$JH/bin/java" -Xmx1g -Djava.io.tmpdir=S:/m3-scratch/tmp -cp "$CPR" com.synexia.openrewrite.viewport.M3PerFileApply \
		com.synexia.openrewrite.viewport.Draw2dViewport $FILES 2>&1 | grep -E '^(CHANGED|UNCHANGED|ERROR)' | sed "s#$D/##"
done
# GEF palette children deltas (correction sites PaletteEditPart.java:390, PaletteStackEditPart.java:270)
G=$W/org.eclipse.gef/src/org/eclipse/gef
GFILES="$G/ui/palette/editparts/PaletteEditPart.java $G/internal/ui/palette/editparts/PaletteStackEditPart.java
	$G/internal/ui/palette/editparts/PinnablePaletteStackEditPart.java"
for pass in 1 2; do
	echo "palette recipe pass $pass:"
	"$JH/bin/java" -Xmx1g -Djava.io.tmpdir=S:/m3-scratch/tmp -cp "$CPR" com.synexia.openrewrite.viewport.M3PerFileApply \
		com.synexia.openrewrite.viewport.GefPaletteDeltas $GFILES 2>&1 | grep -E '^(CHANGED|UNCHANGED|ERROR)' | sed "s#$G/##"
done
