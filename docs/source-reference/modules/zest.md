# Project and module records: zest

[Module index](../MODULES.md) · [Reactor edges](../REACTOR.md) · [Packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md)

This navigation group is not a new module. The U-identifiers and discovery manifests below identify actual units. All behavior-level review is partial as stated in the coverage ledger.

### U029: `org.eclipse.zest-feature`

- Classification: production/build; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest-feature/.project)
- Eclipse project: `org.eclipse.zest-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.zest`; packages plug-ins `org.eclipse.zest.core`, `org.eclipse.zest.layouts`, `org.eclipse.zest.cloudio`; included features `org.eclipse.draw2d`
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U030: `org.eclipse.zest.cloudio`

- Classification: production/build; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/.project)
- Eclipse project: `org.eclipse.zest.cloudio`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.zest.cloudio`; execution environment: `JavaSE-17`
- Exported package clauses: `org.eclipse.zest.cloudio`, `org.eclipse.zest.cloudio.layout`, `org.eclipse.zest.cloudio.util`
- Required bundles: `org.eclipse.ui`, `org.eclipse.core.runtime`
- Owned declaration namespaces: [`org.eclipse.zest.cloudio`](../packages/zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.cloudio.layout`](../packages/zest.md#orgeclipsezestcloudiolayout), [`org.eclipse.zest.cloudio.util`](../packages/zest.md#orgeclipsezestcloudioutil)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U031: `org.eclipse.zest.core`

- Classification: production/build; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/.project)
- Eclipse project: `org.eclipse.zest.core`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.zest.core`; execution environment: `JavaSE-17`
- Exported package clauses: `org.eclipse.zest.core.viewers`, `org.eclipse.zest.core.viewers.decorators`, `org.eclipse.zest.core.viewers.internal;x-internal:=true`, `org.eclipse.zest.core.widgets`, `org.eclipse.zest.core.widgets.custom`, `org.eclipse.zest.core.widgets.decorators`, `org.eclipse.zest.core.widgets.gestures`, `org.eclipse.zest.core.widgets.internal;x-friends:="org.eclipse.zest.tests"`
- Required bundles: `org.eclipse.zest.layouts`, `org.eclipse.jface;bundle-version="[3.34.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.draw2d;bundle-version="[3.21.0,4.0.0)";visibility:=reexport`
- Owned declaration namespaces: [`org.eclipse.zest.core.viewers`](../packages/zest.md#orgeclipsezestcoreviewers), [`org.eclipse.zest.core.viewers.decorators`](../packages/zest.md#orgeclipsezestcoreviewersdecorators), [`org.eclipse.zest.core.viewers.internal`](../packages/zest.md#orgeclipsezestcoreviewersinternal), [`org.eclipse.zest.core.widgets`](../packages/zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.custom`](../packages/zest.md#orgeclipsezestcorewidgetscustom), [`org.eclipse.zest.core.widgets.decorators`](../packages/zest.md#orgeclipsezestcorewidgetsdecorators), [`org.eclipse.zest.core.widgets.gestures`](../packages/zest.md#orgeclipsezestcorewidgetsgestures), [`org.eclipse.zest.core.widgets.internal`](../packages/zest.md#orgeclipsezestcorewidgetsinternal)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U032: `org.eclipse.zest.doc.isv`

- Classification: documentation; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.doc.isv/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.doc.isv/pom.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.doc.isv/.project)
- Eclipse project: `org.eclipse.zest.doc.isv`; natures: `org.eclipse.m2e.core.maven2Nature`, `org.eclipse.pde.PluginNature`
- Maven artifact: `org.eclipse.zest.plugins:org.eclipse.zest.doc.isv:1.8.1700-SNAPSHOT`; packaging: `eclipse-plugin`
- OSGi bundle: `org.eclipse.zest.doc.isv`; execution environment: `not declared`
- Required bundles: `org.eclipse.swt;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.jface;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.draw2d;bundle-version="[3.16.0,4.0.0)";resolution:=optional`, `org.eclipse.platform.doc.isv;bundle-version="[3.2.0,5.0.0)";resolution:=optional`, `org.eclipse.ui.workbench;bundle-version="[3.131.0,4.0.0)";resolution:=optional`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U033: `org.eclipse.zest.examples`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/.project)
- Eclipse project: `org.eclipse.zest.examples`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.zest.examples`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.zest.examples.jface;x-friends:="org.eclipse.zest.tests"`, `org.eclipse.zest.examples.swt;x-friends:="org.eclipse.zest.tests"`, `org.eclipse.zest.examples.uml;x-friends:="org.eclipse.zest.tests"`
- Required bundles: `org.eclipse.ui`, `org.eclipse.core.runtime`, `org.eclipse.pde`, `org.eclipse.pde.ui`, `org.eclipse.jdt`, `org.eclipse.jdt.ui`, `org.eclipse.core.resources`, `org.eclipse.jdt.core`, `org.eclipse.zest.core`, `org.eclipse.zest.layouts`, `org.junit`
- Owned declaration namespaces: [`org.eclipse.zest.examples`](../packages/zest.md#orgeclipsezestexamples), [`org.eclipse.zest.examples.jface`](../packages/zest.md#orgeclipsezestexamplesjface), [`org.eclipse.zest.examples.layouts`](../packages/zest.md#orgeclipsezestexampleslayouts), [`org.eclipse.zest.examples.swt`](../packages/zest.md#orgeclipsezestexamplesswt), [`org.eclipse.zest.examples.uml`](../packages/zest.md#orgeclipsezestexamplesuml)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U034: `org.eclipse.zest.examples.cloudio`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/.project)
- Eclipse project: `org.eclipse.zest.examples.cloudio`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.zest.examples.cloudio`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.ui`, `org.eclipse.ui.views`, `org.eclipse.core.runtime;bundle-version="3.6.0"`, `org.eclipse.zest.cloudio;bundle-version="2.0.0"`
- Owned declaration namespaces: [`org.eclipse.zest.examples.cloudio.application`](../packages/zest.md#orgeclipsezestexamplescloudioapplication), [`org.eclipse.zest.examples.cloudio.application.about`](../packages/zest.md#orgeclipsezestexamplescloudioapplicationabout), [`org.eclipse.zest.examples.cloudio.application.actions`](../packages/zest.md#orgeclipsezestexamplescloudioapplicationactions), [`org.eclipse.zest.examples.cloudio.application.data`](../packages/zest.md#orgeclipsezestexamplescloudioapplicationdata), [`org.eclipse.zest.examples.cloudio.application.ui`](../packages/zest.md#orgeclipsezestexamplescloudioapplicationui), [`org.eclipse.zest.examples.cloudio.application.ui.customization`](../packages/zest.md#orgeclipsezestexamplescloudioapplicationuicustomization), [`org.eclipse.zest.examples.cloudio.snippets`](../packages/zest.md#orgeclipsezestexamplescloudiosnippets)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U035: `org.eclipse.zest.examples.layouts`

- Classification: example; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.layouts/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.layouts/.project)
- Eclipse project: `org.eclipse.zest.examples.layouts`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- OSGi bundle: `org.eclipse.zest.examples.layouts`; execution environment: `JavaSE-21`
- Exported package clauses: `org.eclipse.zest.layouts.exampleStructures;x-internal:=true`, `org.eclipse.zest.layouts.exampleUses;x-internal:=true`
- Required bundles: `org.eclipse.zest.core;bundle-version="1.14.0"`, `org.eclipse.zest.layouts;bundle-version="2.0.0"`, `org.eclipse.jface;bundle-version="[3.34.0,5.0.0)"`, `org.eclipse.core.runtime;bundle-version="3.31.100"`
- Owned declaration namespaces: [`org.eclipse.zest.layouts.exampleStructures`](../packages/zest.md#orgeclipsezestlayoutsexamplestructures), [`org.eclipse.zest.layouts.exampleUses`](../packages/zest.md#orgeclipsezestlayoutsexampleuses)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U036: `org.eclipse.zest.layouts`

- Classification: production/build; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/META-INF/MANIFEST.MF), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/.project)
- Eclipse project: `org.eclipse.zest.layouts`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- OSGi bundle: `org.eclipse.zest.layouts`; execution environment: `JavaSE-17`
- Exported package clauses: `org.eclipse.zest.layouts`, `org.eclipse.zest.layouts.algorithms`, `org.eclipse.zest.layouts.algorithms.internal`, `org.eclipse.zest.layouts.constraints`, `org.eclipse.zest.layouts.dataStructures`, `org.eclipse.zest.layouts.interfaces`, `org.eclipse.zest.layouts.progress`
- Required bundles: `org.eclipse.swt;bundle-version="[3.4.0,4.0.0)";visibility:=reexport`, `org.eclipse.draw2d`
- Owned declaration namespaces: [`org.eclipse.zest.layouts`](../packages/zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](../packages/zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.algorithms.internal`](../packages/zest.md#orgeclipsezestlayoutsalgorithmsinternal), [`org.eclipse.zest.layouts.constraints`](../packages/zest.md#orgeclipsezestlayoutsconstraints), [`org.eclipse.zest.layouts.dataStructures`](../packages/zest.md#orgeclipsezestlayoutsdatastructures), [`org.eclipse.zest.layouts.interfaces`](../packages/zest.md#orgeclipsezestlayoutsinterfaces), [`org.eclipse.zest.layouts.progress`](../packages/zest.md#orgeclipsezestlayoutsprogress)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U037: `org.eclipse.zest.sdk-feature`

- Classification: production/build; default-reactor
- Discovery: [feature.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.sdk-feature/feature.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.sdk-feature/.project)
- Eclipse project: `org.eclipse.zest.sdk-feature`; natures: `org.eclipse.pde.FeatureNature`
- Feature identity: `org.eclipse.zest.sdk`; packages plug-ins `org.eclipse.zest.doc.isv`; included features `org.eclipse.zest`, `org.eclipse.draw2d`
- Responsibility boundary: Assembles the declared plug-in and feature membership for installation/distribution; it does not define a Java namespace by itself.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U038: `org.eclipse.zest.tests`

- Classification: test; default-reactor
- Discovery: [MANIFEST.MF](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/pom.xml), [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/.project)
- Eclipse project: `org.eclipse.zest.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Maven artifact: `org.eclipse.zest.plugins:org.eclipse.zest.tests:1.10.100-SNAPSHOT`; packaging: `eclipse-test-plugin`
- OSGi bundle: `org.eclipse.zest.tests`; execution environment: `JavaSE-21`
- Required bundles: `org.eclipse.ui`, `org.eclipse.core.runtime`, `org.eclipse.pde`, `org.eclipse.pde.ui`, `org.eclipse.jdt`, `org.eclipse.jdt.ui`, `org.eclipse.core.resources`, `org.eclipse.jdt.core`, `org.eclipse.zest.cloudio`, `org.eclipse.zest.core;bundle-version="[1.19.0,2.0.0)"`, `org.eclipse.zest.examples;bundle-version="[3.20.0,4.0.0)"`, `org.eclipse.zest.layouts`, `junit-jupiter-api;bundle-version="[5.10.2,6.0.0)"`, `junit-platform-suite-api;bundle-version="[1.10.2,2.0.0)"`, `org.eclipse.swtbot.swt.finder;bundle-version="[4.2.0,5.0.0)"`
- Imported package clauses: `org.slf4j;version="1.7.30"`
- Owned declaration namespaces: [`org.eclipse.zest.tests`](../packages/zest.md#orgeclipsezesttests), [`org.eclipse.zest.tests.cloudio`](../packages/zest.md#orgeclipsezesttestscloudio), [`org.eclipse.zest.tests.examples`](../packages/zest.md#orgeclipsezesttestsexamples), [`org.eclipse.zest.tests.utils`](../packages/zest.md#orgeclipsezesttestsutils)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

