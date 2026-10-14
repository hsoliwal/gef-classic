# Project and module records: fork-tooling-and-provenance

[Module index](../MODULES.md) · [Reactor edges](../REACTOR.md) · [Packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md)

This navigation group is not a new module. The U-identifiers and discovery manifests below identify actual units. All behavior-level review is partial as stated in the coverage ledger.

### U002: `.m3/openrewrite-recipes`

- Classification: fork tooling/proof; outside parsed default reactor; retained, not excluded
- Discovery: [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/.m3/openrewrite-recipes/pom.xml)
- Maven artifact: `com.synexia.m3.bootstrap:m3-recipe-first-bootstrap:1.0.0-SNAPSHOT`; packaging: `jar (Maven default)`
- Direct POM dependencies: `org.openrewrite:rewrite-core`, `org.openrewrite:rewrite-java`, `org.openrewrite:rewrite-java-21`, `org.junit.jupiter:junit-jupiter`, `org.slf4j:slf4j-api` (inherited/profile/plugin dependencies are additional)
- Owned declaration namespaces: [`com.synexia.m3.bootstrap`](../packages/fork-tooling-and-provenance.md#comsynexiam3bootstrap)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U003: `m3/source-owned-qualification`

- Classification: fork tooling/proof; outside parsed default reactor; retained, not excluded
- Discovery: [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/m3/source-owned-qualification/pom.xml)
- Maven artifact: `com.synexia.recipe:gef-owned-source-qualification:0.1.0-SNAPSHOT`; packaging: `pom`
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

### U004: `m3/source-owned-qualification/bootstrap-runtime`

- Classification: fork tooling/proof; outside parsed default reactor; retained, not excluded
- Discovery: [pom.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/m3/source-owned-qualification/bootstrap-runtime/pom.xml)
- Maven artifact: `com.synexia.m3.proof:gef-bootstrap-runtime-recipe-proof:1.0.0`; packaging: `jar (Maven default)`
- Direct POM dependencies: `org.openrewrite:rewrite-core`, `org.slf4j:slf4j-api`, `org.jetbrains:annotations` (inherited/profile/plugin dependencies are additional)
- Owned declaration namespaces: [`org.openrewrite.text`](../packages/fork-tooling-and-provenance.md#orgopenrewritetext)
- Responsibility boundary: Produces/identifies the bundle, feature, or project declared above; use its owned package entries for source navigation.
- Semantic coverage: manifest-accounted, partial; package/API behavior is reviewed only where the lifecycle guide explicitly names it

