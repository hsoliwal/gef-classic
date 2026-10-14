# Project and module records: gef

[Module index](../MODULES.md) · [Reactor edges](../REACTOR.md) · [Packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md)

This navigation group is not a new module. The U-identifiers and discovery manifests below identify actual units. All behavior-level review is partial as stated in the coverage ledger.

### U012: `org.eclipse.gef`

- Classification: production/build; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/.project)
- Eclipse project: `org.eclipse.gef`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.gef`; execution environment: `JavaSE-17`
- Exported package clauses: `org.eclipse.gef`, `org.eclipse.gef.commands`, `org.eclipse.gef.dnd`, `org.eclipse.gef.editparts`, `org.eclipse.gef.editpolicies`, `org.eclipse.gef.handles`, `org.eclipse.gef.internal;x-friends:="org.eclipse.gef.examples.text,org.eclipse.gef.examples.logic"`, `org.eclipse.gef.internal.icons;x-internal:=true`, `org.eclipse.gef.internal.ui.palette;x-friends:="org.eclipse.gef.tests"`, `org.eclipse.gef.internal.ui.palette.editparts;x-friends:="org.eclipse.gef.tests"`, `org.eclipse.gef.internal.ui.rulers;x-friends:="org.eclipse.gef.tests"`, `org.eclipse.gef.palette`, `org.eclipse.gef.print`, `org.eclipse.gef.requests`, `org.eclipse.gef.rulers`, `org.eclipse.gef.tools`, `org.eclipse.gef.ui.actions`, `org.eclipse.gef.ui.palette`, `org.eclipse.gef.ui.palette.customize`, `org.eclipse.gef.ui.palette.editparts`, `org.eclipse.gef.ui.parts`, `org.eclipse.gef.ui.properties`, `org.eclipse.gef.ui.rulers`, `org.eclipse.gef.ui.views.palette`, `org.eclipse.gef.util`
- Required bundles: `org.eclipse.draw2d;visibility:=reexport;bundle-version="[3.21.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.ui.views;resolution:=optional;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.workbench;bundle-version="[3.133.0,4.0.0)"`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef`](../packages/gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](../packages/gef.md#orgeclipsegefcommands), [`org.eclipse.gef.dnd`](../packages/gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](../packages/gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](../packages/gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.handles`](../packages/gef.md#orgeclipsegefhandles), [`org.eclipse.gef.internal`](../packages/gef.md#orgeclipsegefinternal), [`org.eclipse.gef.internal.ui.palette`](../packages/gef.md#orgeclipsegefinternaluipalette), [`org.eclipse.gef.internal.ui.palette.editparts`](../packages/gef.md#orgeclipsegefinternaluipaletteeditparts), [`org.eclipse.gef.internal.ui.rulers`](../packages/gef.md#orgeclipsegefinternaluirulers), [`org.eclipse.gef.palette`](../packages/gef.md#orgeclipsegefpalette), [`org.eclipse.gef.print`](../packages/gef.md#orgeclipsegefprint), [`org.eclipse.gef.requests`](../packages/gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](../packages/gef.md#orgeclipsegefrulers), [`org.eclipse.gef.tools`](../packages/gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.actions`](../packages/gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](../packages/gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.customize`](../packages/gef.md#orgeclipsegefuipalettecustomize), [`org.eclipse.gef.ui.palette.editparts`](../packages/gef.md#orgeclipsegefuipaletteeditparts), [`org.eclipse.gef.ui.parts`](../packages/gef.md#orgeclipsegefuiparts), [`org.eclipse.gef.ui.properties`](../packages/gef.md#orgeclipsegefuiproperties), [`org.eclipse.gef.ui.rulers`](../packages/gef.md#orgeclipsegefuirulers), [`org.eclipse.gef.ui.views.palette`](../packages/gef.md#orgeclipsegefuiviewspalette), [`org.eclipse.gef.util`](../packages/gef.md#orgeclipsegefutil)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U013: `org.eclipse.gef-feature`

- Classification: production/build; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef-feature/.project)
- Eclipse project: `org.eclipse.gef-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.gef`; packages plug-ins `org.eclipse.gef`; included features `org.eclipse.draw2d`
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U014: `org.eclipse.gef.baseline`

- Classification: production/build; outside parsed default reactor; retained, not excluded
- Discovery: [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.baseline/.project)
- Eclipse project: `org.eclipse.gef.baseline`; natures: 
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U015: `org.eclipse.gef.doc.isv`

- Classification: documentation; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.doc.isv/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.doc.isv/pom.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.doc.isv/.project)
- Eclipse project: `org.eclipse.gef.doc.isv`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.m2e.core.maven2Nature`
- Maven artifact: `org.eclipse.gef.plugins:org.eclipse.gef.doc.isv:3.13.1500-SNAPSHOT`; packaging: `eclipse-plugin`
- OSGi bundle: `org.eclipse.gef.doc.isv`; execution environment: `not declared`
- Required bundles: `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.workbench;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.draw2d;bundle-version="[3.16.0,4.0.0)";resolution:=optional`, `org.eclipse.platform.doc.isv;bundle-version="[3.2.0,5.0.0)";resolution:=optional`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U016: `org.eclipse.gef.examples`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples/.project)
- Eclipse project: `org.eclipse.gef.examples`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.gef.examples`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.gef.examples.palette;x-friends:="org.eclipse.gef.tests"`
- Required bundles: `org.eclipse.gef;bundle-version="[3.21.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.31.100,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.206.0,4.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.examples`](../packages/gef.md#orgeclipsegefexamples), [`org.eclipse.gef.examples.palette`](../packages/gef.md#orgeclipsegefexamplespalette)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U017: `org.eclipse.gef.examples-feature`

- Classification: example; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples-feature/.project)
- Eclipse project: `org.eclipse.gef.examples-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.gef.examples`; packages plug-ins `org.eclipse.gef.examples.logic`, `org.eclipse.gef.examples.flow`, `org.eclipse.gef.examples.text`, `org.eclipse.gef.examples.shapes`, `org.eclipse.gef.examples.digraph1`, `org.eclipse.gef.examples.digraph2`, `org.eclipse.gef.examples.ui.capabilities`, `org.eclipse.gef.examples.ui.pde`, `org.eclipse.gef.examples`; included features none declared
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U018: `org.eclipse.gef.examples.digraph1`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/.project)
- Eclipse project: `org.eclipse.gef.examples.digraph1`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.gef.examples.digraph1`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.gef.examples.digraph1.editor;x-friends:="org.eclipse.gef.examples.digraph2"`, `org.eclipse.gef.examples.digraph1.editpart;x-friends:="org.eclipse.gef.examples.digraph2"`, `org.eclipse.gef.examples.digraph1.figure;x-friends:="org.eclipse.gef.examples.digraph2"`, `org.eclipse.gef.examples.digraph1.model;x-friends:="org.eclipse.gef.examples.digraph2"`, `org.eclipse.gef.examples.digraph1.policy;x-friends:="org.eclipse.gef.examples.digraph2"`
- Required bundles: `org.eclipse.gef;bundle-version="[3.18.0,4.0.0)";visibility:=reexport`, `org.eclipse.core.runtime;bundle-version="[3.3.0,4.0.0)";visibility:=reexport`, `org.eclipse.ui;bundle-version="[3.3.0,4.0.0)";visibility:=reexport`, `org.eclipse.ui.ide;bundle-version="[3.3.0,4.0.0)";visibility:=reexport`, `org.eclipse.core.resources;bundle-version="[3.3.0,4.0.0)";visibility:=reexport`
- Owned declaration namespaces: [`org.eclipse.gef.examples.digraph1.editor`](../packages/gef.md#orgeclipsegefexamplesdigraph1editor), [`org.eclipse.gef.examples.digraph1.editpart`](../packages/gef.md#orgeclipsegefexamplesdigraph1editpart), [`org.eclipse.gef.examples.digraph1.factory`](../packages/gef.md#orgeclipsegefexamplesdigraph1factory), [`org.eclipse.gef.examples.digraph1.figure`](../packages/gef.md#orgeclipsegefexamplesdigraph1figure), [`org.eclipse.gef.examples.digraph1.model`](../packages/gef.md#orgeclipsegefexamplesdigraph1model), [`org.eclipse.gef.examples.digraph1.policy`](../packages/gef.md#orgeclipsegefexamplesdigraph1policy), [`org.eclipse.gef.examples.digraph1.rcp`](../packages/gef.md#orgeclipsegefexamplesdigraph1rcp), [`org.eclipse.gef.examples.digraph1.view`](../packages/gef.md#orgeclipsegefexamplesdigraph1view)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U019: `org.eclipse.gef.examples.digraph2`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/.project)
- Eclipse project: `org.eclipse.gef.examples.digraph2`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.gef.examples.digraph2`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.gef.examples.digraph1;bundle-version="[1.0.0,2.0.0)";visibility:=reexport`
- Owned declaration namespaces: [`org.eclipse.gef.examples.digraph2.editor`](../packages/gef.md#orgeclipsegefexamplesdigraph2editor), [`org.eclipse.gef.examples.digraph2.editpart`](../packages/gef.md#orgeclipsegefexamplesdigraph2editpart), [`org.eclipse.gef.examples.digraph2.factory`](../packages/gef.md#orgeclipsegefexamplesdigraph2factory), [`org.eclipse.gef.examples.digraph2.figure`](../packages/gef.md#orgeclipsegefexamplesdigraph2figure), [`org.eclipse.gef.examples.digraph2.model`](../packages/gef.md#orgeclipsegefexamplesdigraph2model), [`org.eclipse.gef.examples.digraph2.policy`](../packages/gef.md#orgeclipsegefexamplesdigraph2policy)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U020: `org.eclipse.gef.examples.flow`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/.project)
- Eclipse project: `org.eclipse.gef.examples.flow`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- OSGi bundle: `org.eclipse.gef.examples.flow`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.workbench;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.gef;bundle-version="[3.18.0,4.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.examples.flow`](../packages/gef.md#orgeclipsegefexamplesflow), [`org.eclipse.gef.examples.flow.actions`](../packages/gef.md#orgeclipsegefexamplesflowactions), [`org.eclipse.gef.examples.flow.figures`](../packages/gef.md#orgeclipsegefexamplesflowfigures), [`org.eclipse.gef.examples.flow.model`](../packages/gef.md#orgeclipsegefexamplesflowmodel), [`org.eclipse.gef.examples.flow.model.commands`](../packages/gef.md#orgeclipsegefexamplesflowmodelcommands), [`org.eclipse.gef.examples.flow.parts`](../packages/gef.md#orgeclipsegefexamplesflowparts), [`org.eclipse.gef.examples.flow.policies`](../packages/gef.md#orgeclipsegefexamplesflowpolicies), [`org.eclipse.gef.examples.flow.ui`](../packages/gef.md#orgeclipsegefexamplesflowui)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U021: `org.eclipse.gef.examples.logic`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/.project)
- Eclipse project: `org.eclipse.gef.examples.logic`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- OSGi bundle: `org.eclipse.gef.examples.logic`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.gef.examples.logicdesigner`, `org.eclipse.gef.examples.logicdesigner.actions`, `org.eclipse.gef.examples.logicdesigner.dnd`, `org.eclipse.gef.examples.logicdesigner.edit`, `org.eclipse.gef.examples.logicdesigner.figures`, `org.eclipse.gef.examples.logicdesigner.model`, `org.eclipse.gef.examples.logicdesigner.model.commands`, `org.eclipse.gef.examples.logicdesigner.palette`, `org.eclipse.gef.examples.logicdesigner.rulers`
- Required bundles: `org.eclipse.gef;bundle-version="[3.18.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.206.0,4.0.0)"`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.workbench;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.examples.logicdesigner`](../packages/gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.examples.logicdesigner.actions`](../packages/gef.md#orgeclipsegefexampleslogicdesigneractions), [`org.eclipse.gef.examples.logicdesigner.dnd`](../packages/gef.md#orgeclipsegefexampleslogicdesignerdnd), [`org.eclipse.gef.examples.logicdesigner.edit`](../packages/gef.md#orgeclipsegefexampleslogicdesigneredit), [`org.eclipse.gef.examples.logicdesigner.figures`](../packages/gef.md#orgeclipsegefexampleslogicdesignerfigures), [`org.eclipse.gef.examples.logicdesigner.handlers`](../packages/gef.md#orgeclipsegefexampleslogicdesignerhandlers), [`org.eclipse.gef.examples.logicdesigner.model`](../packages/gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.examples.logicdesigner.model.commands`](../packages/gef.md#orgeclipsegefexampleslogicdesignermodelcommands), [`org.eclipse.gef.examples.logicdesigner.palette`](../packages/gef.md#orgeclipsegefexampleslogicdesignerpalette), [`org.eclipse.gef.examples.logicdesigner.rulers`](../packages/gef.md#orgeclipsegefexampleslogicdesignerrulers), [`org.eclipse.gef.examples.logicdesigner.tools`](../packages/gef.md#orgeclipsegefexampleslogicdesignertools)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U022: `org.eclipse.gef.examples.shapes`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/.project)
- Eclipse project: `org.eclipse.gef.examples.shapes`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.gef.examples.shapes`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.gef;bundle-version="[3.18.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.workbench;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.e4.core.contexts;bundle-version="[1.12.600,2.0.0)"`, `org.eclipse.e4.core.di.annotations;bundle-version="[1.8.400,2.0.0)"`, `org.eclipse.e4.ui.model.workbench;bundle-version="[2.4.300,3.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.examples.shapes`](../packages/gef.md#orgeclipsegefexamplesshapes), [`org.eclipse.gef.examples.shapes.handler`](../packages/gef.md#orgeclipsegefexamplesshapeshandler), [`org.eclipse.gef.examples.shapes.model`](../packages/gef.md#orgeclipsegefexamplesshapesmodel), [`org.eclipse.gef.examples.shapes.model.commands`](../packages/gef.md#orgeclipsegefexamplesshapesmodelcommands), [`org.eclipse.gef.examples.shapes.parts`](../packages/gef.md#orgeclipsegefexamplesshapesparts)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U023: `org.eclipse.gef.examples.text`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/.project)
- Eclipse project: `org.eclipse.gef.examples.text`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.gef.examples.text`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.gef.examples.text`, `org.eclipse.gef.examples.text.actions`, `org.eclipse.gef.examples.text.edit`, `org.eclipse.gef.examples.text.figures`, `org.eclipse.gef.examples.text.model`, `org.eclipse.gef.examples.text.model.commands`, `org.eclipse.gef.examples.text.requests`, `org.eclipse.gef.examples.text.tools`
- Required bundles: `org.eclipse.gef;bundle-version="[3.18.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.workbench;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)"`, `org.junit`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.examples.text`](../packages/gef.md#orgeclipsegefexamplestext), [`org.eclipse.gef.examples.text.actions`](../packages/gef.md#orgeclipsegefexamplestextactions), [`org.eclipse.gef.examples.text.edit`](../packages/gef.md#orgeclipsegefexamplestextedit), [`org.eclipse.gef.examples.text.figures`](../packages/gef.md#orgeclipsegefexamplestextfigures), [`org.eclipse.gef.examples.text.model`](../packages/gef.md#orgeclipsegefexamplestextmodel), [`org.eclipse.gef.examples.text.model.commands`](../packages/gef.md#orgeclipsegefexamplestextmodelcommands), [`org.eclipse.gef.examples.text.requests`](../packages/gef.md#orgeclipsegefexamplestextrequests), [`org.eclipse.gef.examples.text.tools`](../packages/gef.md#orgeclipsegefexamplestexttools), [`org.eclipse.gef.examples.text.wizard`](../packages/gef.md#orgeclipsegefexamplestextwizard)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U024: `org.eclipse.gef.examples.ui.capabilities`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.capabilities/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.capabilities/.project)
- Eclipse project: `org.eclipse.gef.examples.ui.capabilities`; natures: `org.eclipse.pde.PluginNature`
- OSGi bundle: `org.eclipse.gef.examples.ui.capabilities`; execution environment: `not declared`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U025: `org.eclipse.gef.examples.ui.pde`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/.project)
- Eclipse project: `org.eclipse.gef.examples.ui.pde`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.gef.examples.ui.pde`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.gef.examples.ui.pde.internal;x-internal:=true`, `org.eclipse.gef.examples.ui.pde.internal.l10n;x-internal:=true`, `org.eclipse.gef.examples.ui.pde.internal.wizards;x-internal:=true`
- Required bundles: `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.examples.ui.pde.internal`](../packages/gef.md#orgeclipsegefexamplesuipdeinternal), [`org.eclipse.gef.examples.ui.pde.internal.l10n`](../packages/gef.md#orgeclipsegefexamplesuipdeinternall10n), [`org.eclipse.gef.examples.ui.pde.internal.wizards`](../packages/gef.md#orgeclipsegefexamplesuipdeinternalwizards)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U026: `org.eclipse.gef.repository`

- Classification: production/build; default-reactor
- Discovery: [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.repository/.project)
- Eclipse project: `org.eclipse.gef.repository`; natures: 
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U027: `org.eclipse.gef.sdk-feature`

- Classification: production/build; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.sdk-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.sdk-feature/.project)
- Eclipse project: `org.eclipse.gef.sdk-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.gef.sdk`; packages plug-ins `org.eclipse.gef.doc.isv`; included features `org.eclipse.gef`, `org.eclipse.draw2d`
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U028: `org.eclipse.gef.tests`

- Classification: test; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/pom.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/.project)
- Eclipse project: `org.eclipse.gef.tests`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Maven artifact: `org.eclipse.gef.plugins:org.eclipse.gef.tests:3.14.200-SNAPSHOT`; packaging: `eclipse-test-plugin`
- OSGi bundle: `org.eclipse.gef.tests`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.22.200,4.0.0)"`, `org.eclipse.draw2d;bundle-version="[3.16.0,4.0.0)"`, `org.eclipse.gef;bundle-version="[3.18.0,4.0.0)"`, `org.eclipse.gef.examples;bundle-version="[1.2.0,2.0.0)"`, `org.eclipse.gef.examples.logic;bundle-version="[3.14.400,4.0.0)"`, `org.eclipse.gef.examples.shapes;bundle-version="[3.15.300,4.0.0)"`, `org.eclipse.swtbot.eclipse.gef.finder;bundle-version="[4.2.0,5.0.0)"`, `junit-jupiter-api;bundle-version="[5.10.2,6.0.0)"`, `junit-platform-suite-api;bundle-version="[1.10.2,2.0.0)"`, `org.opentest4j;bundle-version="[1.3.0,2.0.0)"`
- Owned declaration namespaces: [`org.eclipse.gef.test`](../packages/gef.md#orgeclipsegeftest), [`org.eclipse.gef.test.swtbot`](../packages/gef.md#orgeclipsegeftestswtbot), [`org.eclipse.gef.test.swtbot.utils`](../packages/gef.md#orgeclipsegeftestswtbotutils), [`org.eclipse.gef.test.utils`](../packages/gef.md#orgeclipsegeftestutils)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

