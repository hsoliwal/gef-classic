#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
#
# Compiles org.eclipse.gef (and, with "zest", org.eclipse.zest.layouts and org.eclipse.zest.core) with javac against
# a Draw2d build and the newest Eclipse platform jars in ~/.m2, to check source changes outside Draw2d (no Tycho).
# Usage: build_gef.sh <worktree> <draw2d-classes> <out-dir> [zest]. Run it under C:/m3-superset/tools/build_lock.sh.
set -e
W=$1; D2=$2; OUT=$3; ZEST=$4
JH="C:/Program Files/Java/jdk-21"
M2=$(cygpath -m "$HOME/.m2/repository")
CP="$D2"
for d in "$M2"/org/eclipse/platform/*/ "$M2"/org/osgi/*/; do
	a=$(basename "$d")
	case $a in *gtk*|*cocoa*|*.tests*|*win32.win32.aarch64*) continue ;; esac
	v=$(ls -d "$d"*/ 2>/dev/null | xargs -n1 basename | grep -E '^[0-9]' | sort -V | tail -1)
	j=$(ls "$d$v"/*.jar 2>/dev/null | grep -v sources | head -1)
	[ -n "$j" ] && CP="$CP;$j"
done
ANN=$(ls "$OUT/../lib/api-annotations.jar" 2>/dev/null || true)
[ -n "$ANN" ] && CP="$CP;$ANN"
compile() { # <source-root> <dest> <classpath>
	rm -rf "$2"; mkdir -p "$2"
	find "$1" -name '*.java' > "$2.list"
	"$JH/bin/javac" -nowarn -encoding UTF-8 --release 21 -proc:none -d "$2" -cp "$3" @"$2.list"
}
compile "$W/org.eclipse.gef/src" "$OUT/gef" "$CP"
if [ "$ZEST" = zest ]; then
	compile "$W/org.eclipse.zest.layouts/src" "$OUT/zest.layouts" "$CP"
	compile "$W/org.eclipse.zest.core/src" "$OUT/zest.core" "$CP;$OUT/zest.layouts"
fi
echo "built: $OUT/gef${ZEST:+ $OUT/zest.layouts $OUT/zest.core}"
