# GEF review-only Apache Surefire 3.5.6 original reporter RED/GREEN proof

**Not a GEF product change.** This draft contains only a GitHub Actions experiment and two mechanically generated patches from the **already source-sealed Synexia Maven/OpenRewrite recipe**. No runtime source under `org.eclipse.draw2d`, `org.eclipse.gef`, `org.eclipse.zest`, native SWT/JNI, Maven POM, or the strict GEF test verifier is changed.

Canonical: [Synexia #18841](https://github.com/hsoliwal/com.synexia/pull/18841) and the [reviewable derived patch custody #19016](https://github.com/hsoliwal/com.synexia/pull/19016), Apache-2.0 donor [apache/maven-surefire@25ea054860a5c1e5932b360d8aa0a31944c2b841](https://github.com/apache/maven-surefire/tree/25ea054860a5c1e5932b360d8aa0a31944c2b841).

| Upstream Java owner | Preimage Git blob | Reviewed postimage Git blob | SHA-256 postimage |
|---|---|---|---|
| `maven-surefire-common/src/main/java/org/apache/maven/plugin/surefire/report/StatelessXmlReporter.java` | `17547db9bcbd964bcf8729eff12b0624f2ddf067` | `c5ef6fc2a009f8c416eacd949dc4543e865d0525` | `06235aefe3e27f0c4b5a1d8e1ebff471c4f445d48bb54e487b6c0fd6d004fdfd` |
| `maven-surefire-common/src/test/java/org/apache/maven/plugin/surefire/report/StatelessXmlReporterTest.java` | `59f1a9b38d34c5530178ec3f74072509644a0608` | `31bcd15a411282807ba948955d40753f55b43636` | `df96bf5d72924e0a472f5ea96b404469b90fd66f6f12e51c11edd4236a73a053` |

The CI uses the **genuine original source**, first applies only the sealed JUnit3 regression and demands a specific RED XML report from Maven with one failure for the wrong `tests` count. Next it applies the reporter patch, checks both complete canonical hashes and allowed source paths, and requires the *same* test GREEN in the original Apache Surefire Maven reactor. It uploads exact logs and source/test artifact custody. It does not substitute Java model tests for the real reporter.

A successful independent public canary **does not** prove the private Synexia OpenRewrite recipe actually executed, does not install this patched reporter in GEF/Tycho, does not repair the original GEF XML header, and does not qualify full Windows/GTK/Cocoa native release. The private recipe's Actions remain unavailable at this time. Maintain fail-closed release gates and additive history; never merge this experiment into product `master` merely to obtain a green check.

## 23 November 2026: executed local source qualification

The existing canonical Java recipe executed against real OpenRewrite 8.17.1 on
JDK 21.0.13.1+2. All nine SDK tests passed, including exact emission, source-drift
refusal and a no-change second application. Its actual emitted reporter remains
`06235aefe3e27f0c4b5a1d8e1ebff471c4f445d48bb54e487b6c0fd6d004fdfd`.
The test postimage in the table incorporates only the four constructor-line
layouts produced by the authentic upstream Spotless formatter; its assertions
and Java tokens are unchanged. This test patch is a deterministic diff from the
original preimage to that actual SDK output. Applying it to the frozen original
reproduces the complete emitted file exactly.

| Executed local gate | Result |
|---|---|
| Existing canonical Java recipe | 9 tests; 0 failures, errors or skips |
| Original Apache reporter plus emitted regression test | 1 executed test; the required `expected 2 / actual 1` assertion failure; 0 errors or skips |
| Emitted reporter plus the same emitted regression test | 1 executed test passed; 0 failures, errors or skips |
| Complete original reporter test class plus the added regression | 12 tests passed; 0 failures, errors or skips |

The two authentic source runs used Maven 3.9.11, `CI=true`, and the reactor
`package` lifecycle. The package phase is required because
`surefire-shared-utils` creates its relocated classes in its Shade execution;
ending the reactor at `test` leaves those classes unavailable to downstream
modules. `CI=true` selects the upstream formatter's check mode. Complete source
hashes were verified again after each qualified run, so no build-time source
formatting is hidden in these results.

```sh
CI=true mvn -B -ntp -f "$original/pom.xml" -pl maven-surefire-common -am \
  '-Dtest=StatelessXmlReporterTest#testCountsRepeatedMethodNameInvocationsInXml' \
  -Dsurefire.failIfNoSpecifiedTests=false package
```

The original failure before JUnit, the first formatter-mutated local attempt,
and all original XML and logs are retained separately from the passing results.
Their durable evidence packet is prepared for the existing
[canonical continuation](https://github.com/hsoliwal/com.synexia/pull/18969).
The final RED XML SHA-256 is
`019eda1b804bb9f37e4ecd4379a9a323ecf2e9d90be420d49339019f08c53ae3`;
the final selected GREEN XML is
`8e0bc16ce8f0e75fe25ce0aefe92ca784042ccfc1230a1a11028e9cfa034c61c`;
the complete reporter regression XML is
`3fa502a543c82e0831e472727579588759c3d731aa6e881c61746800b25773ee`.
The source-built common JAR SHA-256 is
`8eca11359c2d71c5114bbefad8ea8057076721e3aed212b734f8f50d1ddf60c6`.

These receipts establish the local canonical producer and genuine upstream
reporter behavior. The corrected hosted canary, actual Tycho OSGi consumption,
strict complete GEF report acceptance and the Linux/Windows/macOS release
matrix remain separate gates. This PR remains draft and release status remains
HOLD until their matching evidence is available.

## Independently verified hosted receipt — 23 November 2026

The existing workflow completed successfully at exact GEF source
`d519581355cf81fc4e4e06d54f8e4515a1a43083` in both the
[pull-request run 45117238935](https://github.com/hsoliwal/gef-classic/actions/runs/45117238935)
and [push run 45117228242](https://github.com/hsoliwal/gef-classic/actions/runs/45117228242).
The downloaded pull-request artifact was independently opened and its original
XML inspected. This supersedes the earlier pending status of that hosted canary.

| Original hosted artifact | Verified result / SHA-256 |
| --- | --- |
| Artifact ID `14803096611`, 26,129 bytes | `354c101f6293506384f2ab5cbefd1304b242d26223ba9ad4f3f906f9c4b51e0d` |
| `baseline-red.xml` | Exactly one executed case; one assertion failure, zero errors/skips; `expected:<[2]> but was:<[1]>`; SHA-256 `e52197f4df3079d457d7f290c3e3a8fd7f403a1b48ad01babdceb044be024ccc` |
| `candidate-green.xml` | Same one case passed, zero failures/errors/skips; SHA-256 `c70a1934e60c9e5601e0b0e37a70957184aaf5d0062c579a96058c308e30d185` |

Both XML files name
`StatelessXmlReporterTest#testCountsRepeatedMethodNameInvocationsInXml`.
The artifact binds the original Apache revision, the two complete corrected
source hashes above, Temurin 21.0.13+9 and Maven 3.9.12. The earlier failed run
`45108977800` produced no qualifying baseline XML and remains a build failure
before the regression, never an expected-red result.

The successful upstream job already uses the runner's `CI=true` environment.
No redundant environment override is needed to qualify this completed run.

## Executed formatting-only source custody

Canonical successor
`com.synexia.rewrite.M3Surefire356SpotlessTest20261123` at Synexia
`6fb64b697ddea8facee558082ef7bcd9cbf6935a` was executed with the real
OpenRewrite 8.17.1 SDK on Java 21. Its unchanged focused POM passed a clean
offline Maven 3.9.9 run: **five tests, zero failures/errors/skips**.
The tests cover full-file emission, unchanged second application, YAML recipe
discovery, missing input, source drift and rejection of plain text as Java.

This is a formatting successor to the original behavioral recipe. It accepts
only source SHA-256
`7f6583143f9931a3f6bc17c41db703599c03ea21aa974d4bb032e836ee7b10a7`
and emits the complete formatted test
`df96bf5d72924e0a472f5ea96b404469b90fd66f6f12e51c11edd4236a73a053`.
The historical pre-format snapshot is preserved. The untouched five-test JUnit
receipt SHA-256 is
`27fd1de1e3fdc86a3a7dcb0d3c0fbf5185b45539a1c1c7352464dec5b40e3c4d`.
Original dependency-cache and JVM-DNS failures are retained alongside the
passing offline proof in [Synexia #19016](https://github.com/hsoliwal/com.synexia/pull/19016).

## Bounded downstream Tycho handoff

The successor workflow adds one Java 21 / GTK / `2024-09` differential proof
after the authentic upstream red/green gate. Its source-sealed owner is
`com.synexia.rewrite.M3Surefire356TychoHandoff20261123`, using Synexia's
existing text snapshot recipe. The PR handoff binds the exact private recipe
commit and executed output hashes; recipe sources remain private.

The experiment builds the corrected Apache common JAR in a job-local Maven
repository and rebuilds the unchanged original Tycho OSGi booter module at
`c4349ee27dfde2817dfc7121b5f84bfb9eeb0195`. Tycho embeds the reporter at build
time, so installing a common JAR alone is insufficient. The experiment checks
the embedded reporter bytes and actual forked JVM class-loading origin.

Baseline and candidate execute the original full GEF Maven command at the
same exact source. Their raw reports remain untouched. The workflow compares
testcase multisets, preserving duplicate names and outcomes, and checks every
original Draw2D, GEF and Zest report with the unchanged readiness parser from
SWT `1202ab304c3b81b73b628d201b987941d16c94d2`.

The previously recorded 17,569-case local crossing result and its 9,404 declared
baseline describe a different test source from the supplied sparse replay
(17,554 actual / 9,389 declared). The underlying new Tycho XML and class-origin
packet referenced by that earlier PR narrative was not recovered from the
inspected published or local locations on 23 November. That historical
observation is not substituted for this exact-source hosted experiment.

**Status at publication: downstream execution pending.** A workflow definition,
recipe pass or JAR-byte match is not a native test result. The readiness parser
permits some assumptions; parser acceptance therefore establishes report
integrity, not a separate zero-skip or full-release claim. Full nine-axis hosted
verification, Windows/Cocoa corrected-runtime consumption, pixels, resources
and performance retain their own required evidence. This proof PR remains
draft and the release remains HOLD.

### Receiving branch freshness

GEF #44 was merged by the owner on 23 November 2026 at 09:22:42 UTC.
This successor is prepared as a separate draft from the refreshed master
`a649d062a183effcbaae7e0fcdfe9825a5037893`; both workflow and README
preimages remain identical to the verified upstream proof. The merged
product test sources are retained, and both downstream executions use one
exact receiving head. The earlier merge does not certify the new Tycho
experiment or the outstanding release gates.

### Newly reproduced producer and original Tycho build

The authentic producer and original Tycho booter were independently rebuilt
on 23 November using Maven 3.9.9 and Adoptium Java 21.0.13.1. The entire
original reporter regression class executed: **12 tests passed, zero
failures/errors/skips**. Its untouched XML SHA-256 is
`425c8c8f40c2967606fd212c4ce6133813f4cab002a5f7995d1d03cb40b05716`.

The resulting common JAR is
`8eca11359c2d71c5114bbefad8ea8057076721e3aed212b734f8f50d1ddf60c6`;
the genuinely rebuilt booter is
`73c9822161a262d9f8fa3169219f15be316a73b6f9df8ea94210eb22939a42cc`;
and their identical reporter class is
`cdd12416d7e9a0e18795f95747f28b945b7ee0f542f6f8c1d86f1ab86d97152a`.
These newly produced bytes reproduce the previously recorded artifact
identities independently of the missing older downstream packet.

Complete original Git trees were authenticated before the build. Afterwards,
all 2,127 original Surefire files matched except the two admitted Java paths,
and all 6,583 original Tycho files remained unchanged. The build used the
original POMs and installed only the qualified common JAR before building
the original standalone booter module. No archive was edited. This establishes
producer-to-booter byte transfer; GEF runtime loading and native XML remain
the hosted experiment's separate acceptance gates.

Both GTK executions enable the existing `draw2d.native.sparseDamage=true`
option with the independent `DRAW2D_SPARSE_NATIVE_REQUIRED=true` marker.
The original native test therefore verifies delivery of the JVM option to
the real fork, and the comparison requires all six exact native/geometry
methods to pass once. This adds bounded opt-in coverage without changing
the product defaults, test sources or original Maven POMs.
