# Project and module records: draw2d

[Module index](../MODULES.md) · [Reactor edges](../REACTOR.md) · [Packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md)

This navigation group is not a new module. The U-identifiers and discovery manifests below identify actual units. All behavior-level review is partial as stated in the coverage ledger.

### U005: `org.eclipse.draw2d`

- Classification: production/build; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/.project)
- Eclipse project: `org.eclipse.draw2d`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.draw2d`; execution environment: `JavaSE-17`
- Exported package clauses: `org.eclipse.draw2d`, `org.eclipse.draw2d.backgrounds`, `org.eclipse.draw2d.backgrounds.shadows`, `org.eclipse.draw2d.colors`, `org.eclipse.draw2d.geometry`, `org.eclipse.draw2d.graph`, `org.eclipse.draw2d.images`, `org.eclipse.draw2d.internal; x-friends:="org.eclipse.gef,  org.eclipse.zest.core,  org.eclipse.draw2d.examples,  org.eclipse.draw2d.tests,  org.eclipse.gef.examples.flow,  org.eclipse.draw2d.svg"`, `org.eclipse.draw2d.parts`, `org.eclipse.draw2d.text`, `org.eclipse.draw2d.widgets`, `org.eclipse.draw2d.zoom`
- Required bundles: `org.eclipse.swt;bundle-version="[3.126.0,4.0.0)";visibility:=reexport`
- Owned declaration namespaces: [`org.eclipse.draw2d`](../packages/draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.backgrounds`](../packages/draw2d.md#orgeclipsedraw2dbackgrounds), [`org.eclipse.draw2d.backgrounds.shadows`](../packages/draw2d.md#orgeclipsedraw2dbackgroundsshadows), [`org.eclipse.draw2d.colors`](../packages/draw2d.md#orgeclipsedraw2dcolors), [`org.eclipse.draw2d.geometry`](../packages/draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](../packages/draw2d.md#orgeclipsedraw2dgraph), [`org.eclipse.draw2d.internal`](../packages/draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.parts`](../packages/draw2d.md#orgeclipsedraw2dparts), [`org.eclipse.draw2d.text`](../packages/draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.draw2d.widgets`](../packages/draw2d.md#orgeclipsedraw2dwidgets), [`org.eclipse.draw2d.zoom`](../packages/draw2d.md#orgeclipsedraw2dzoom)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U006: `org.eclipse.draw2d-feature`

- Classification: production/build; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d-feature/.project)
- Eclipse project: `org.eclipse.draw2d-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.draw2d`; packages plug-ins `org.eclipse.draw2d`, `org.eclipse.draw2d.svg`; included features none declared
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U007: `org.eclipse.draw2d.doc.isv`

- Classification: documentation; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.doc.isv/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.doc.isv/pom.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.doc.isv/.project)
- Eclipse project: `org.eclipse.draw2d.doc.isv`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.m2e.core.maven2Nature`
- Maven artifact: `org.eclipse.draw2d.plugins:org.eclipse.draw2d.doc.isv:3.13.1100-SNAPSHOT`; packaging: `eclipse-plugin`
- OSGi bundle: `org.eclipse.draw2d.doc.isv`; execution environment: `not declared`
- Required bundles: `org.eclipse.swt;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.platform.doc.isv;bundle-version="[3.2.0,5.0.0)";resolution:=optional`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U008: `org.eclipse.draw2d.examples`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/.project)
- Eclipse project: `org.eclipse.draw2d.examples`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- OSGi bundle: `org.eclipse.draw2d.examples`; execution environment: `JavaSE-21`
- Exported package clauses: `gef.bugs;x-friends:="org.eclipse.draw2d.tests"`
- Required bundles: `org.eclipse.draw2d;bundle-version="[3.16.0,4.0.0)"`, `org.eclipse.draw2d.svg;bundle-version="[1.0.0,2.0.0)"`
- Owned declaration namespaces: [`awt`](../packages/draw2d.md#awt), [`bidi`](../packages/draw2d.md#bidi), [`gef.bugs`](../packages/draw2d.md#gefbugs), [`org.eclipse.draw2d.examples`](../packages/draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.examples.awt`](../packages/draw2d.md#orgeclipsedraw2dexamplesawt), [`org.eclipse.draw2d.examples.cg`](../packages/draw2d.md#orgeclipsedraw2dexamplescg), [`org.eclipse.draw2d.examples.connections`](../packages/draw2d.md#orgeclipsedraw2dexamplesconnections), [`org.eclipse.draw2d.examples.graph`](../packages/draw2d.md#orgeclipsedraw2dexamplesgraph), [`org.eclipse.draw2d.examples.hittest`](../packages/draw2d.md#orgeclipsedraw2dexampleshittest), [`org.eclipse.draw2d.examples.image`](../packages/draw2d.md#orgeclipsedraw2dexamplesimage), [`org.eclipse.draw2d.examples.layouts`](../packages/draw2d.md#orgeclipsedraw2dexampleslayouts), [`org.eclipse.draw2d.examples.path`](../packages/draw2d.md#orgeclipsedraw2dexamplespath), [`org.eclipse.draw2d.examples.printing`](../packages/draw2d.md#orgeclipsedraw2dexamplesprinting), [`org.eclipse.draw2d.examples.scrollpane`](../packages/draw2d.md#orgeclipsedraw2dexamplesscrollpane), [`org.eclipse.draw2d.examples.shadows`](../packages/draw2d.md#orgeclipsedraw2dexamplesshadows), [`org.eclipse.draw2d.examples.svg`](../packages/draw2d.md#orgeclipsedraw2dexamplessvg), [`org.eclipse.draw2d.examples.swt`](../packages/draw2d.md#orgeclipsedraw2dexamplesswt), [`org.eclipse.draw2d.examples.text`](../packages/draw2d.md#orgeclipsedraw2dexamplestext), [`org.eclipse.draw2d.examples.thumbnail`](../packages/draw2d.md#orgeclipsedraw2dexamplesthumbnail), [`org.eclipse.draw2d.examples.tree`](../packages/draw2d.md#orgeclipsedraw2dexamplestree), [`org.eclipse.draw2d.examples.uml`](../packages/draw2d.md#orgeclipsedraw2dexamplesuml), [`org.eclipse.draw2d.examples.zoom`](../packages/draw2d.md#orgeclipsedraw2dexampleszoom), [`swt.bugs`](../packages/draw2d.md#swtbugs), [`swt.transforms`](../packages/draw2d.md#swttransforms)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U009: `org.eclipse.draw2d.sdk-feature`

- Classification: production/build; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.sdk-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.sdk-feature/.project)
- Eclipse project: `org.eclipse.draw2d.sdk-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.draw2d.sdk`; packages plug-ins `org.eclipse.draw2d.doc.isv`; included features `org.eclipse.draw2d`
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U010: `org.eclipse.draw2d.svg`

- Classification: production/build; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.svg/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.svg/.project)
- Eclipse project: `org.eclipse.draw2d.svg`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.draw2d.svg`; execution environment: `JavaSE-17`
- Exported package clauses: `org.eclipse.draw2d.svg`
- Required bundles: `org.eclipse.draw2d;bundle-version="[3.24.0,4.0.0)"`, `org.apache.batik.awt.util;bundle-version="[1.17.0,2.0.0)"`, `org.apache.batik.codec;bundle-version="[1.17.0,2.0.0)"`, `org.apache.batik.svggen;bundle-version="[1.17.0,2.0.0)"`
- Owned declaration namespaces: [`org.eclipse.draw2d.svg`](../packages/draw2d.md#orgeclipsedraw2dsvg)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U011: `org.eclipse.draw2d.tests`

- Classification: test; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/pom.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/.project)
- Eclipse project: `org.eclipse.draw2d.tests`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Maven artifact: `org.eclipse.draw2d.plugins:org.eclipse.draw2d.tests:3.15.0-SNAPSHOT`; packaging: `eclipse-test-plugin`
- OSGi bundle: `org.eclipse.draw2d.tests`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.core.runtime;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.draw2d;bundle-version="[3.16.0,4.0.0)"`, `org.eclipse.draw2d.examples`, `org.eclipse.draw2d.svg;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.swtbot.swt.finder;bundle-version="[4.2.0,5.0.0)"`, `junit-jupiter-api;bundle-version="[5.10.2,6.0.0)"`, `junit-jupiter-params;bundle-version="[5.10.2,6.0.0)"`, `junit-platform-suite-api;bundle-version="[1.10.2,2.0.0)"`, `org.opentest4j;bundle-version="[1.3.0,2.0.0)"`
- Imported package clauses: `org.slf4j`
- Owned declaration namespaces: [`org.eclipse.draw2d.graph.test`](../packages/draw2d.md#orgeclipsedraw2dgraphtest), [`org.eclipse.draw2d.test`](../packages/draw2d.md#orgeclipsedraw2dtest), [`org.eclipse.draw2d.test.colors`](../packages/draw2d.md#orgeclipsedraw2dtestcolors), [`org.eclipse.draw2d.test.swtbot`](../packages/draw2d.md#orgeclipsedraw2dtestswtbot), [`org.eclipse.draw2d.test.utils`](../packages/draw2d.md#orgeclipsedraw2dtestutils)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

