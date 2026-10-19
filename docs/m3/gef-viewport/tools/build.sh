#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
#
# Compiles Draw2d and the Draw2d M3 tests with javac (no Tycho), for the viewport differential and benchmarks.
# Usage: build.sh <worktree> <out-dir> [stock-revision]
#   <out-dir>/draw2d   Draw2d of the worktree (the M3 build; hooks inert unless -Ddraw2d.viewport.mode=viewport)
#   <out-dir>/stock    Draw2d of <stock-revision> (default origin/master): the differential reference
#   <out-dir>/tests    GraphicsStub + org.eclipse.draw2d.test.m3 (compiled against the M3 build, public API only)
# Run it under C:/m3-superset/tools/build_lock.sh. Output must stay on S:.
set -e
W=$1; OUT=$2; REV=${3:-origin/master}
JH="C:/Program Files/Java/jdk-21"
M2=$(cygpath -m "$HOME/.m2/repository")
SWT=$M2/org/eclipse/platform/org.eclipse.swt.win32.win32.x86_64/3.135.0/org.eclipse.swt.win32.win32.x86_64-3.135.0.jar
JUNIT=$M2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
LIB=$OUT/lib; mkdir -p "$LIB"
ANN=$LIB/api-annotations.jar
[ -f "$ANN" ] || curl -sfL -o "$ANN" https://repo1.maven.org/maven2/org/eclipse/pde/org.eclipse.pde.api.tools.annotations/1.3.0/org.eclipse.pde.api.tools.annotations-1.3.0.jar
compile() { # <source-root> <dest> <classpath>
	rm -rf "$2"; mkdir -p "$2"
	find "$1" -name '*.java' > "$2.list"
	"$JH/bin/javac" -nowarn -encoding UTF-8 --release 21 -proc:none -d "$2" -cp "$3" @"$2.list"
	(cd "$1" && find . -type f ! -name '*.java' ! -name '*.html' -exec cp --parents {} "$2/" \;)
}
compile "$W/org.eclipse.draw2d/src" "$OUT/draw2d" "$SWT;$ANN"
if [ "$REV" != none ]; then
	rm -rf "$OUT/stock-src"; mkdir -p "$OUT/stock-src"
	git -C "$W" archive "$REV" org.eclipse.draw2d/src | tar -x -C "$OUT/stock-src"
	compile "$OUT/stock-src/org.eclipse.draw2d/src" "$OUT/stock" "$SWT;$ANN"
fi
T=$OUT/tests-src; rm -rf "$T"; mkdir -p "$T/org/eclipse/draw2d/test"
cp -r "$W/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/m3" "$T/org/eclipse/draw2d/test/"
mkdir -p "$T/org/eclipse/draw2d/test/utils"
cp "$W/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/utils/GraphicsStub.java" "$T/org/eclipse/draw2d/test/utils/"
compile "$T" "$OUT/tests" "$OUT/draw2d;$SWT;$JUNIT"
echo "built: $OUT/draw2d $OUT/stock $OUT/tests"
