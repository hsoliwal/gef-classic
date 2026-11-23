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
