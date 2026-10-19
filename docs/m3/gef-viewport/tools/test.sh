#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
#
# Runs the Draw2d M3 tests (org.eclipse.draw2d.test.m3) against a build made by build.sh, under the JaCoCo agent,
# and writes the coverage report of org.eclipse.draw2d.internal.m3 next to the build.
# Usage: test.sh <build-dir> [extra junit console options]
# Headless tests only; tests that need a Display run under ui_lock.sh + hidden_java.sh (ui_test.sh).
set -e
B=$1; shift
JH="C:/Program Files/Java/jdk-21"
M2=$(cygpath -m "$HOME/.m2/repository")
SWT=$M2/org/eclipse/platform/org.eclipse.swt.win32.win32.x86_64/3.135.0/org.eclipse.swt.win32.win32.x86_64-3.135.0.jar
JUNIT=$M2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
AGENT=$M2/org/jacoco/org.jacoco.agent/0.8.15/org.jacoco.agent-0.8.15-runtime.jar
CLI=$M2/org/jacoco/org.jacoco.cli/0.8.15/org.jacoco.cli-0.8.15-nodeps.jar
rm -f "$B/jacoco.exec"
"$JH/bin/java" -Xmx3g -Djava.io.tmpdir=S:/m3-scratch/tmp \
	-javaagent:"$AGENT"=destfile="$B/jacoco.exec",includes=org.eclipse.draw2d.internal.m3.* \
	-Ddraw2d.viewport.mode=viewport -Ddraw2d.viewport.verify=true \
	-Dm3.draw2d.stock="$B/stock" -Dm3.draw2d.m3="$B/draw2d" -Dm3.draw2d.tests="$B/tests" -Dm3.swt.jar="$SWT" \
	-jar "$JUNIT" execute -cp "$B/tests;$B/draw2d;$SWT" --select-package org.eclipse.draw2d.test.m3 \
	--exclude-classname '.*UiTest' --disable-banner --details=tree "$@" || STATUS=$?
rm -rf "$B/coverage"; mkdir -p "$B/coverage"
"$JH/bin/java" -jar "$CLI" report "$B/jacoco.exec" --classfiles "$B/draw2d/org/eclipse/draw2d/internal/m3" \
	--sourcefiles "$(dirname "$0")/../../../../org.eclipse.draw2d/src" --csv "$B/coverage/m3.csv" --html "$B/coverage/html" > /dev/null
awk -F, 'NR>1 {ml+=$8; cl+=$9; mb+=$6; cb+=$7} END {printf "coverage org.eclipse.draw2d.internal.m3: lines %.2f%% (%d missed), branches %.2f%% (%d missed)\n", 100*cl/(ml+cl), ml, 100*cb/(mb+cb), mb}' "$B/coverage/m3.csv"
exit ${STATUS:-0}
