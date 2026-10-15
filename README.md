<!-- fork-homepage-detail:v1:start -->
# GEF Classic: diagrams, graphical editing and graph visualization

GEF Classic is a family of SWT-based frameworks: **Draw2d** for figures and
painting, **GEF MVC** for interactive graphical editors, and **Zest** for graph
visualization and layout. Choose the smallest layer that owns your problem.
The JavaFX-based GEF components live in the separate upstream GEF repository.

[Layer map](#gef-layer-map) · [Concepts](#gef-key-concepts) ·
[Quickstart](#gef-quickstart) · [Example](#gef-worked-example) ·
[Development guide](docs/DEVELOPMENT_GUIDE.md)

## GEF layer map

| Need | Implementation | Small starting example |
| --- | --- | --- |
| Draw and lay out figures | [org.eclipse.draw2d](org.eclipse.draw2d/) | [Draw2d examples](org.eclipse.draw2d.examples/) |
| Edit diagrams with undo/redo | [org.eclipse.gef](org.eclipse.gef/) | [Shapes editor](org.eclipse.gef.examples.shapes/) |
| Visualize a graph | [Zest core](org.eclipse.zest.core/) and [layouts](org.eclipse.zest.layouts/) | [Zest examples](org.eclipse.zest.examples/) |
| Check behavior | [Draw2d tests](org.eclipse.draw2d.tests/), [GEF tests](org.eclipse.gef.tests/) and [Zest tests](org.eclipse.zest.tests/) | Matching layer's regression fixtures |
| Resolve/install components | [Target platform](target-platform/) and [repository project](org.eclipse.gef.repository/) | PDE target and p2 update-site output |

## GEF key concepts

- **Figure:** Draw2d presentation, geometry and painting.
- **Model:** application state; changing a figure directly does not automatically
  update the model or editing history.
- **Edit part:** controller connecting a model to a figure and its interactions.
- **Edit policy / request:** rules for admitting operations and the description of
  an operation such as move, resize, creation or connection.
- **Command / command stack:** state change and its undo/redo history.
- **Layout algorithm:** positions graph nodes or figures; correctness includes
  geometry, model relationships and interaction, not only rendering speed.

## GEF fork status

This is a downstream fork of [Eclipse GEF Classic](https://github.com/eclipse-gef/gef-classic).
The checkout contains the established frameworks/examples plus an isolated
[M3 recipe sidecar](.m3/README.md) and source-owned qualification work.
The [development guide](docs/DEVELOPMENT_GUIDE.md) documents the actual layer,
test and lifecycle boundaries. The M3 direction below is a research programme;
the existence of tooling or tests does not certify performance, undo/redo
equivalence or all native platforms.

## GEF quickstart

For **this checkout's build**, the [workflow](.github/workflows/maven.yml) uses
**Temurin 25 and Maven 3.9.12**, and [the POM](pom.xml) selects `JavaSE-25`.
The retained upstream adoption requirements below describe a different context;
inspect each bundle's manifest and selected target when evaluating application
compatibility. Prepare Eclipse/p2 network access and an SWT display for UI tests.

```sh
git clone https://github.com/hsoliwal/gef-classic.git
cd gef-classic
git rev-parse HEAD
java -version
mvn -version
mvn -V -B -fae -ntp -Pmaster clean verify
```

For interactive exploration, use the PDE target setup and example-launch steps
in the original README below. These build commands are source-checked examples;
no compilation, native interaction or regression test was run for this update.

## GEF command cheat sheet

```sh
# Linux display wrapper, after installing Xvfb/native prerequisites.
xvfb-run -a mvn -V -B -fae -ntp -Pmaster clean verify
# Separate compatibility-target lane, not the default target.
mvn -V -B -fae -ntp -P2024-09 clean verify
# Locate the move/resize command and its visual refresh path.
git grep -n -e 'ShapeSetConstraintCommand' -e 'refreshVisuals' -- org.eclipse.gef.examples.shapes
```

The `2024-09` profile selects a baseline target and skips the p2 baseline check;
do not describe that skipped check as passed. Collect the actual
`target/surefire-reports` and distinguish component tests from the full matrix.

## GEF worked example

**Move a shape, then undo and redo it.** In the configured Shapes example, read
[ShapeSetConstraintCommand](org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ShapeSetConstraintCommand.java)
alongside [ShapeEditPart](org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/ShapeEditPart.java).

1. The command receives a model, move/resize request and new bounds; it copies
   those requested bounds instead of borrowing a mutable rectangle.
2. `canExecute()` restricts accepted request kinds. `execute()` saves the old
   model bounds and applies the new bounds through `redo()`.
3. Model property notifications make the edit part refresh its layout constraint.
4. `undo()` restores the saved model state; `redo()` reapplies the requested state.
5. Compare model bounds and displayed bounds after each step. If only the view is
   wrong, trace notification/layout before altering command history.

This is a source walkthrough and suggested regression, not a recorded execution.
Direct figure mutation bypasses the model/command path and is unsuitable for an
operation that must participate in undo. Continue with
[the development guide](docs/DEVELOPMENT_GUIDE.md),
[GEF description](docs/GEF_Description.md) and [contributor guide](CONTRIBUTING.md).
Existing project, adoption and licensing material is preserved below.

> Source tour checked at [`3f5b817adaec`](https://github.com/hsoliwal/gef-classic/tree/3f5b817adaece849209961ed70e29e31d984c376). Commands describe that revision; recheck build configuration when changing revisions. Build/UI/performance verification was not run for this README update.

---

<!-- fork-homepage-detail:v1:end -->

<div align="center">

## M³ × GEF Classic

**Graphical editors, Draw2D and graph workloads make performance questions concrete.**

[![M3 research target](https://img.shields.io/badge/M%C2%B3-desktop%20research%20target-4b62b7?style=flat-square)](https://github.com/hsoliwal/M3jdk21)
[![Evidence first](https://img.shields.io/badge/acceptance-tests%20%2B%20measurements-287b57?style=flat-square)](https://github.com/hsoliwal/M3jdk21/blob/master/m3/release/BENCHMARK_AND_COMPATIBILITY_SPEC.md)

**[M3 runtime programme](https://github.com/hsoliwal/M3jdk21)** · **[Technical paper](https://github.com/hsoliwal/M3jdk21/blob/master/m3/papers/M3_SHARED_STRUCTURE_AND_REUSABLE_COMPUTATION.md)** · **[Source invitation](#come-inspect-the-code)**

</div>

> [!NOTE]
> **This is an experimental downstream fork of [the upstream GEF Classic project](https://github.com/eclipse-gef/gef-classic).** M3 String, regex, precompute and collection integration are **engineering directions**, not verified deployment, performance, compatibility or upstream-endorsement claims. Preserve original licensing, copyright, API behavior and native/UI contracts.

### Engineering questions for this fork

| Surface | What to investigate | Non-negotiable contract |
| --- | --- | --- |
| **Draw2D and geometry** | Characterize repeated layout and repaint work | Drawing correctness and UI-resource lifecycle |
| **Diagram models** | Evaluate reuse for editing, graph and selection workloads | Undo/redo, listener and model contracts |
| **Interactive editors** | Measure latency and retained state across edits and redraws | UI behavior, event ordering and no regressions |

**Approach:** inventory first; copy/derive only with license and provenance; qualify reusable recipes in Synexia; port into the appropriate runtime/product owner; then test with representative desktop projects and publish CPU, allocation, retained-memory, startup and responsiveness numbers, including regressions. No Synexia runtime dependency.

### Come inspect the code

> **Java, JNI, SWT, workbenches, widgets, real code: open the repositories.** Bring a failing case, a performance counterexample, a profiler trace or a better algorithm. Challenge the design on the merits of source, tests and evidence—not slogans or a résumé.

**Explore the family:** [M3JDK21](https://github.com/hsoliwal/M3jdk21) · [SWT](https://github.com/hsoliwal/eclipse.platform.swt) · [Eclipse Platform](https://github.com/hsoliwal/eclipse.platform) · [Platform UI](https://github.com/hsoliwal/eclipse.platform.ui) · [Nebula](https://github.com/hsoliwal/nebula) · [GEF Classic](https://github.com/hsoliwal/gef-classic).

**Contribute here:** [issues](https://github.com/hsoliwal/gef-classic/issues) · [pull requests](https://github.com/hsoliwal/gef-classic/pulls) · [M3 compatibility and benchmark contract](https://github.com/hsoliwal/M3jdk21/blob/master/m3/release/BENCHMARK_AND_COMPATIBILITY_SPEC.md).

The remainder of this README retains the existing M3 fork notes and the **original upstream GEF Classic documentation**. Nothing in this introduction rebrands upstream work as M3-authored code.

---

## M3 direction in this fork

M3 is based on the idea that shared immutable structure and indexed metadata can
provide a basis for reusable computation. The programme starts with M3 String,
regex, and precompute in M3JDK21, then collections and SWT/Eclipse integration.

This GEF Classic fork is a target for evaluating qualified runtime and collection changes in graphical editing and graph-oriented desktop workloads.

**Explore the idea:** [M3 technical design paper](https://github.com/hsoliwal/M3jdk21/blob/master/m3/papers/M3_SHARED_STRUCTURE_AND_REUSABLE_COMPUTATION.md) ·
[Programme goals and roadmap](https://github.com/hsoliwal/M3jdk21#project-goals-and-plan).
The paper explains the proposed architecture, cost tradeoffs, and evaluation plan.
Readers are invited to examine the work and contribute representative workloads.

This section describes the direction of the hsoliwal fork. Upstream documentation
follows below; upstream authorship, licenses, and project identity remain intact.

---

# Eclipse Graphical Editing Framework (GEF) Classic

This repository contains the code base of the [Eclipse Graphical Editing Framework (GEF)](https://projects.eclipse.org/projects/tools.gef/) project's classic components: 
 - [GEF (MVC) 3.x](https://github.com/eclipse-gef/gef-classic/wiki/GEF-(MCV))
 - [Draw2d 3.x](https://github.com/eclipse-gef/gef-classic/wiki/Draw2D) 
 - [Zest 2.x](https://github.com/eclipse-gef/gef-classic/wiki/Zest)
 
The code base of the GEF components implemented in JavaFX is located in the [eclipse-gef/gef](https://github.com/eclipse-gef/gef) repository instead.

# Requirements:

- Eclipse 2024-06 (Draw2D/Zest), Eclipse 2024-09 (GEF) or later
- Java 17 or later

# Update sites:

Update Sites (p2 repositories) are available at:

 - Latest release of GEF Classic: [https://download.eclipse.org/tools/gef/classic/release/latest]( https://download.eclipse.org/tools/gef/classic/release/latest) 
 - Latest version build from master: [https://download.eclipse.org/tools/gef/classic/nightly/latest](https://download.eclipse.org/tools/gef/classic/nightly/latest)

Other update sites are:

- 3.x.y release: `https://download.eclipse.org/tools/gef/classic/release/3.x.y`

## Getting started with the framework components ([adopters](https://www.eclipse.org/projects/dev_process/#2_3_3_Adopters))
In order to develop graphical applications with GEF Classic, you should first set up a proper development environment. The following sections shortly lay out how to set up an Eclipse IDE for this purpose. They conclude with running our deployed and undeployed examples to confirm everything is set up properly. 

Having accomplished that, you might want to browse our [developer documentation](https://github.com/eclipse-gef/gef-classic/wiki#developer-documentation) to learn about the framework components in detail. At any time, if you get stuck, feel free to [contact us](https://projects.eclipse.org/projects/tools.gef/contact).

### Set up an Eclipse IDE
1. Install a recent **Java SE Development Kit 21** as a prerequisite.
2. Download an '[Eclipse IDE for Eclipse Committers ](http://www.eclipse.org/downloads/packages)' package and start it, pointing to an empty workspace folder. 

### Set up a Target Definition containing GEF Classic (development snapshot)
1. Go to *File -> New -> Project...* and select to create a *General/Project*. Name it `gef-classic-integration.target` or as you like, the project is to contain only a target definition.
2. Go to *File -> New -> Other...* then choose *Plug-in Development/Target Definition* and create a new empty (*Nothing: Start with an empty target definition*) target definition file named `gef-classic-integration.target` within the newly created project.
3. Close the *Target Editor* that has automatically opened, open the target file with the *Text Editor* using the *Open With* context menu, then paste the following contents:
	
	```
	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	<?pde version="3.8"?><target name="gef-integration" sequenceNumber="1">
	<locations>
	<location includeAllPlatforms="false" includeConfigurePhase="true" includeMode="planner" includeSource="true" type="InstallableUnit">
	<unit id="org.eclipse.sdk.ide" version="0.0.0"/>
	<repository location="https://download.eclipse.org/releases/latest"/>
	</location>
	<location includeAllPlatforms="false" includeConfigurePhase="true" includeMode="planner" includeSource="true" type="InstallableUnit">
	<unit id="org.eclipse.zest.sdk.feature.group" version="0.0.0"/>
	<unit id="org.eclipse.draw2d.sdk.feature.group" version="0.0.0"/>
	<unit id="org.eclipse.gef.sdk.feature.group" version="0.0.0"/>
	<unit id="org.eclipse.gef.examples.feature.group" version="0.0.0"/>
	<repository location="https://download.eclipse.org/tools/gef/classic/nightly/latest"/>
	</location>
	</locations>
	</target>
	```
4. Now open the `gef-classic-integration.target` file with the *Target Editor* again, using the *Open With* context menu, let if fully resolve, then click *Set as Target Platform* (link in the upper right corner of the editor).

### Run the examples
As the deployed 'Flow', 'Logic', 'Shapes', and 'WYSIWYG Document' GEF (MVC) examples are contained in the target definition, we only need to start a new Eclipse Runtime to run them: 

1. Go to *Run -> Run Configurations...* then create a new *Eclipse Application* launch configuration and *Run* it.
2. Go to *File -> New -> Project...* and select to create a new *General/Project*. Name it `gef-logic-example` or as you like.
3. Go to *File -> New -> Example...* and select to create a new *GEF (Graphical Editing Framework)/Logic Diagram*, choosing the *Four-bit Adder Model* from the *Logic Model Samples* section.

The undeployed Draw2d and Zest examples have to be checked out in source before. Using EGit this can easily be achieved as follows:

1. Go to *File -> Import...*, then select *Git/Projects from Git*, press *Next >*.
2. Select *Clone URI*, press *Next >*.
3. Paste `https://github.com/eclipse-gef/gef-classic.git` to the *URI* field , press *Next >*.
3. Select *master* branch, press *Next >*.
4. Confirm the local directory or change it as needed, press *Next >*.
5. Ensure *Import existing Eclipse projects* is checked, then select *Working Tree* and press *Next >*.
5. Select `org.eclipse.draw2d.examples` and `org.eclipse.zest.examples`, press *Finish*.
6. Select an arbitrary example class, e.g. `org.eclipse.zest.examples.jface.GraphJFaceSnippet1`, in the *Package Explorer* view and select *Run As -> Java Application* from the context menu.

## How to proceed from here?
The first thing you will probably want to consult is the developer documentation, which explains the different framework components in detail. It is bundled by the individual SDK features that are available for the framework components and can be accessed via *Help -> Help Contents* if these features are installed into the Eclipse IDE (its not sufficient to include them in a target definition for this purpose). It is further contributed to [help.eclipse.org](http://help.eclipse.org/) for each release, where it can be accessed online.<sup>6)</sup>

All further project information (forum, mailing list, issue tracker, update-site locations, release plans) can be retrieved from the project meta-data at [projects.eclipse.org](https://projects.eclipse.org/projects/tools.gef).

If you want to contribute, please consult the [contributor guide](https://github.com/eclipse-gef/gef-classic/blob/master/CONTRIBUTING.md#contributing-to-the-eclipse-graphical-editing-framework-gef).

## Fork development guide

For a source map, current build prerequisites, command cheat sheet and a worked Shapes example, see the [GEF Classic development guide](docs/DEVELOPMENT_GUIDE.md).
