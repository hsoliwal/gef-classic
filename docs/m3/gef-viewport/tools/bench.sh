#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
#
# Time and allocation benchmark (M3AllocBench), one JVM per size and mode: stock, viewport, viewport+trustConstraints.
# Usage: bench.sh <build-dir> <out.tsv> <sizes, e.g. "10000 100000 1000000">
B=$1; OUT=$2; SIZES=$3
JH="C:/Program Files/Java/jdk-21"
M2=$(cygpath -m "$HOME/.m2/repository")
SWT=$M2/org/eclipse/platform/org.eclipse.swt.win32.win32.x86_64/3.135.0/org.eclipse.swt.win32.win32.x86_64-3.135.0.jar
run() { # label classes-dir extra-flags...
	label=$1; classes=$2; shift 2
	for n in $SIZES; do
		"$JH/bin/java" -Xms4g -Xmx6g -XX:+UseParallelGC -Djava.io.tmpdir=S:/m3-scratch/tmp "$@" \
			-cp "$B/tests;$classes;$SWT" org.eclipse.draw2d.test.m3.M3AllocBench "$OUT" "$label" "$n" \
			|| echo "$label $n failed" >&2
	done
}
run stock "$B/stock"
run viewport "$B/draw2d" -Ddraw2d.viewport.mode=viewport
run viewport+trust "$B/draw2d" -Ddraw2d.viewport.mode=viewport -Ddraw2d.viewport.trustConstraints=true
