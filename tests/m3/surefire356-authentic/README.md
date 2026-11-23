# GEF review-only Apache Surefire 3.5.6 original reporter RED/GREEN proof

**Not a GEF product change.** This draft contains only a GitHub Actions experiment and two mechanically generated patches from the **already source-sealed Synexia Maven/OpenRewrite recipe**. No runtime source under `org.eclipse.draw2d`, `org.eclipse.gef`, `org.eclipse.zest`, native SWT/JNI, Maven POM, or the strict GEF test verifier is changed.

Canonical: [Synexia #18841](https://github.com/hsoliwal/com.synexia/pull/18841) and the [reviewable derived patch custody #19016](https://github.com/hsoliwal/com.synexia/pull/19016), Apache-2.0 donor [apache/maven-surefire@25ea054860a5c1e5932b360d8aa0a31944c2b841](https://github.com/apache/maven-surefire/tree/25ea054860a5c1e5932b360d8aa0a31944c2b841).

| Upstream Java owner | Preimage Git blob | Reviewed postimage Git blob | SHA-256 postimage |
|---|---|---|---|
| `maven-surefire-common/src/main/java/org/apache/maven/plugin/surefire/report/StatelessXmlReporter.java` | `17547db9bcbd964bcf8729eff12b0624f2ddf067` | `c5ef6fc2a009f8c416eacd949dc4543e865d0525` | `06235aefe3e27f0c4b5a1d8e1ebff471c4f445d48bb54e487b6c0fd6d004fdfd` |
| `maven-surefire-common/src/test/java/org/apache/maven/plugin/surefire/report/StatelessXmlReporterTest.java` | `59f1a9b38d34c5530178ec3f74072509644a0608` | `fe12f8f3773ad73c43a133022e588f1d93dbc02e` | `7f6583143f9931a3f6bc17c41db703599c03ea21aa974d4bb032e836ee7b10a7` |

The CI uses the **genuine original source**, first applies only the sealed JUnit3 regression and demands a specific RED XML report from Maven with one failure for the wrong `tests` count. Next it applies the reporter patch, checks both complete canonical hashes and allowed source paths, and requires the *same* test GREEN in the original Apache Surefire Maven reactor. It uploads exact logs and source/test artifact custody. It does not substitute Java model tests for the real reporter.

A successful independent public canary **does not** prove the private Synexia OpenRewrite recipe actually executed, does not install this patched reporter in GEF/Tycho, does not repair the original GEF XML header, and does not qualify full Windows/GTK/Cocoa native release. The private recipe's Actions remain unavailable at this time. Maintain fail-closed release gates and additive history; never merge this experiment into product `master` merely to obtain a green check.
