# Project and module records: build-and-releng

[Module index](../MODULES.md) · [Reactor edges](../REACTOR.md) · [Packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md)

This navigation group is not a new module. The U-identifiers and discovery manifests below identify actual units. All behavior-level review is partial as stated in the coverage ledger.

### U001: `.`

- Classification: production/build; default-reactor
- Discovery: [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/pom.xml)
- Maven artifact: `org.eclipse.gef_root:org.eclipse.gef.releng:3.30.0-SNAPSHOT`; packaging: `pom`
- Child build inputs: `org.eclipse.draw2d` (default), `org.eclipse.draw2d.svg` (default), `org.eclipse.gef` (default), `org.eclipse.zest.cloudio` (default), `org.eclipse.zest.core` (default), `org.eclipse.zest.layouts` (default), `org.eclipse.draw2d.doc.isv` (default), `org.eclipse.zest.doc.isv` (default), `org.eclipse.gef.doc.isv` (default), `org.eclipse.draw2d.tests` (default), `org.eclipse.zest.tests` (default), `org.eclipse.gef.tests` (default), `org.eclipse.draw2d.examples` (default), `org.eclipse.gef.examples` (default), `org.eclipse.gef.examples.digraph1` (default), `org.eclipse.gef.examples.digraph2` (default), `org.eclipse.gef.examples.flow` (default), `org.eclipse.gef.examples.logic` (default), `org.eclipse.gef.examples.shapes` (default), `org.eclipse.gef.examples.text` (default), `org.eclipse.gef.examples.ui.capabilities` (default), `org.eclipse.gef.examples.ui.pde` (default), `org.eclipse.zest.examples` (default), `org.eclipse.zest.examples.cloudio` (default), `org.eclipse.zest.examples.layouts` (default), `org.eclipse.gef-feature` (default), `org.eclipse.draw2d-feature` (default), `org.eclipse.gef.examples-feature` (default), `org.eclipse.gef.sdk-feature` (default), `org.eclipse.zest-feature` (default), `org.eclipse.zest.sdk-feature` (default), `org.eclipse.gef.repository` (default), `org.eclipse.draw2d.sdk-feature` (default), `promotion` (profile promote)
- Responsibility boundary: Aggregates the child build units listed above; the aggregator itself is not a widget/runtime API.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U039: `promotion`

- Classification: production/build; outside parsed default reactor; retained, not excluded
- Discovery: [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/promotion/pom.xml)
- Maven artifact: `org.eclipse.gef_root:org.eclipse.gef.promotion:3.30.0-SNAPSHOT`; packaging: `pom`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U040: `target-platform`

- Classification: production/build; outside parsed default reactor; retained, not excluded
- Discovery: [.project](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/target-platform/.project)
- Eclipse project: `target-platform`; natures: `org.eclipse.m2e.core.maven2Nature`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

