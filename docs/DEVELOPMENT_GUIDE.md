# GEF Classic: source tour and development cheat sheet

This guide is for readers of the experimental `hsoliwal/gef-classic` fork. It complements the [README](../README.md), [contribution guide](../CONTRIBUTING.md) and existing [GEF description](GEF_Description.md). Upstream authorship, licensing and project identity remain unchanged. Fork research directions do not establish compatibility or performance results.

## Choose the layer first

GEF Classic provides related libraries, rather than one monolithic editor:

| Layer | Purpose | Source and examples |
| --- | --- | --- |
| Draw2d | Figures, geometry, layout and painting on SWT | [Implementation](../org.eclipse.draw2d), [examples](../org.eclipse.draw2d.examples) |
| GEF | Graphical editing controllers, policies, requests and commands | [Implementation](../org.eclipse.gef), [Shapes example](../org.eclipse.gef.examples.shapes) |
| Zest | Graph visualization and graph layout | [Core](../org.eclipse.zest.core), [layouts](../org.eclipse.zest.layouts), [examples](../org.eclipse.zest.examples) |

Use Draw2d when exploring a figure or layout problem, GEF when exploring interactive editing and undo/redo, and Zest when exploring graph visualization. Start with the smallest matching example instead of importing editor assumptions into every drawing problem.

Other important owners:

- [Draw2d tests](../org.eclipse.draw2d.tests), [GEF tests](../org.eclipse.gef.tests) and [Zest tests](../org.eclipse.zest.tests) keep component verification separate.
- [Draw2d developer docs](../org.eclipse.draw2d.doc.isv), [GEF developer docs](../org.eclipse.gef.doc.isv) and [Zest developer docs](../org.eclipse.zest.doc.isv) contain the fuller reference material.
- [Target platform](../target-platform) resolves Eclipse dependencies; [API baselines](../org.eclipse.gef.baseline) support compatibility checks.
- [Repository project](../org.eclipse.gef.repository) packages the update site. Feature projects group bundles for installation; they are not the Java implementation owners.

## Build prerequisites: current fork versus older prose

The checked-in [Maven workflow](../.github/workflows/maven.yml) uses **Temurin 25 and Maven 3.9.12**. The [root POM](../pom.xml) selects `JavaSE-25`, and [.mvn/maven.config](../.mvn/maven.config) sets Tycho 5.0.4. Use those checked-in inputs to reproduce this fork's current verification lane.

The existing contribution guide's Java 17 headless-build text describes an older configuration. Do not interpret it as the current fork's build-JDK selection. Build JDK, target platform and an individual bundle's declared execution environment are different settings; inspect the bundle manifest when evaluating application compatibility.

You also need Git, network access to resolve Maven/p2 dependencies, and a usable SWT display environment for UI tests. Linux CI wraps the build with Xvfb. The contribution guide describes Eclipse/PDE setup, setting the target platform and defining the API baseline.

## Command cheat sheet

Run from a local checkout's root. These commands were checked against source configuration but **were not executed for this documentation change**.

```sh
git clone https://github.com/hsoliwal/gef-classic.git
cd gef-classic
git rev-parse HEAD
java -version
mvn -version

# Current-target lane from the workflow
mvn -V -B -fae -ntp -Pmaster clean verify

# Separate compatibility-target lane from the workflow
mvn -V -B -fae -ntp -P2024-09 clean verify
```

On Linux with Xvfb installed, a local display wrapper is:

```sh
xvfb-run -a mvn -V -B -fae -ntp -Pmaster clean verify
```

The `master` profile is intentionally empty: the root POM's normal target selection applies. The `2024-09` profile selects the checked-in GEF 3.21.0 baseline target and sets `tycho.p2.baseline.skip=true`. A pass in that lane therefore must not be reported as passing the skipped baseline check.

A completed build produces update-site output under `org.eclipse.gef.repository/target/`; the workflow collects `**/target/surefire-reports/*.xml`. Check reports for failures, skipped tests and actual counts. Do not call a compile-only or selectively scoped run a full matrix pass.

## Worked exploration: move and undo a shape

Open the [Shapes example](../org.eclipse.gef.examples.shapes) in the configured PDE workspace. Read these two classes together:

1. [ShapeSetConstraintCommand](../org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ShapeSetConstraintCommand.java) receives a shape, change-bounds request and new rectangle. Its constructor copies the requested bounds.
2. `canExecute()` admits the move/resize request kinds. `execute()` snapshots the old bounds and delegates to `redo()`.
3. `redo()` updates the model's size and location; `undo()` restores the saved values. This is the state-change owner to inspect when undo differs from the original move.
4. [ShapeEditPart](../org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/ShapeEditPart.java) listens for model property changes. Size/location changes call `refreshVisuals()`, which sets the parent layout constraint using the model bounds.
5. Trace the flow as request → command → model notification → edit part → figure layout. If the model is right but the drawing is wrong, inspect the refresh/layout path before changing command state.

A useful regression exercise is to move a shape, resize it, undo, redo and compare both model bounds and displayed bounds. This describes what to verify; it is not evidence that the example was launched or the regression passed here.

## Troubleshooting and review boundaries

- **Missing target bundles:** resolve the selected target and record failing repositories/units before changing dependencies. A local cached artifact can hide a clean-machine failure.
- **PDE API-baseline errors:** follow the contribution guide's baseline setup instead of disabling the tooling.
- **UI tests cannot open a display:** check OS/backend and display availability; a Maven invocation alone does not create a desktop.
- **One target passes:** report that target and OS explicitly. The workflow has separate OS and target matrix entries.
- **Research claims:** keep source inspection, compilation, tests, interactive behavior and performance measurements separate. A documentation PR establishes none of the latter by itself.

For a review handover record base/head revision, component, JDK/Maven, target profile, OS/display, exact commands and report locations. Preserve license headers and existing notices. Submit fork-local documentation for review in this fork; upstream contributions follow the existing contribution process.
