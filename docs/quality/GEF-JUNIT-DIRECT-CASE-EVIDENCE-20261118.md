# GEF Tycho JUnit test-count inconsistency — retained artifact evidence (2026-11-18)

## Provenance and strict status
- Original GEF pull request: [GEF #35](https://github.com/hsoliwal/gef-classic/pull/35), head `731a79e4124610ad648950fab0ef1da60ba4b21b`.
- Actual [GitHub Actions matrix run #44275363429](https://github.com/hsoliwal/gef-classic/actions/runs/44275363429) performed Maven/Tycho on Java 21 and 25 on Windows and Linux. The examined jobs reached `BUILD SUCCESS`, but the downstream **report-consistency gate failed**, so release qualification is **NOT GREEN**.
- Existing verifier is [SWT readiness.py](https://github.com/hsoliwal/eclipse.platform.swt/blob/1202ab304c3b81b73b628d201b987941d16c94d2/tools/release/readiness.py), pinned by `.github/workflows/maven.yml`. Its `junit_report` requires declared XML counters to match actually recorded testcase entries. **Do not weaken, remove or bypass that gate.**

## Inspecting the real, immutable reports
The four downloaded GitHub Actions ZIP artifacts each contain five `TEST-*.xml` files from Draw2D, GEF and Zest. The Draw2D JUnit report root has **no nested testsuites**. Every testcase is a **direct child** of the root and all 17,547 have distinct `(classname,name)` identities: the cause is **not a nested-counter double count**.

| Java / runner / target | Artifact ID | Declared `tests` | Actual direct testcase elements | Skipped | Failures / Errors |
|---|---|---:|---:|---:|---|
| Java 21 / Windows / 2024-09 | [14415666696](https://github.com/hsoliwal/gef-classic/actions/runs/44275363429/artifacts/14415666696) | 9,382 | 17,547 | 6 | 0 / 0 |
| Java 21 / Ubuntu / 2024-09 | [14416148234](https://github.com/hsoliwal/gef-classic/actions/runs/44275363429/artifacts/14416148234) | 9,382 | 17,547 | 4 | 0 / 0 |
| Java 25 / Windows / 2024-09 | [14415546903](https://github.com/hsoliwal/gef-classic/actions/runs/44275363429/artifacts/14415546903) | 9,382 | 17,547 | 6 | 0 / 0 |
| Java 25 / Ubuntu / master | [14418415601](https://github.com/hsoliwal/gef-classic/actions/runs/44275363429/artifacts/14418415601) | 9,382 | 17,547 | 4 | 0 / 0 |

Of the **17,547 actual Draw2D testcases**, `GraphicsTest$ScaledGraphicsTest` contributes **9,018** and `GraphicsTest$AWTGraphicsTest` contributes **8,100**. The other **429** come from additional Draw2D classes. The fixed discrepancy is **8,165** records. Other reports (GEF suite 46, Draw2D SWTBot 2, Zest suite 91, Zest SWTBot 32) declare and contain equal testcase counts. This pattern is consistent across both Java versions and both inspected operating systems.

The actual Draw2D suite XML SHA-256 differs per artifact (system properties and runtime details vary), while the test identity count and mismatch remain identical:

| Artifact ID | Raw Draw2D `TEST-Draw2dTestSuite.xml` SHA-256 |
|---|---|
| 14415666696 | `ddd57fccf092c3d5c8c66a261bc208741d92deb19c22939ef6add28a9c79c7e8` |
| 14416148234 | `7ed28c8bd85fc89780d8ffdf83722965653a36eb9820480e9631c7efcd6ded53` |
| 14415546903 | `f680ca17851d4462a16fa9aad3b3166930f2260788a4bc8dcb5371f32aeb87e7` |
| 14418415601 | `4d6875d73c8e6ece2b9de453950b6181e427473774f45e7e82a47435ebd959a5` |

## Correct response
1. Preserve all original raw JUnit XML and the source/test identity hashes.
2. Trace the `@ParameterizedTest` execution-to-Surefire header summary producer in Tycho / JUnit Platform. Do not fix by deleting tests, skipping `AWTGraphicsTest`, or accepting any failed/error testcase.
3. Update the **producer or a transparently qualified reporting adapter**, with test cases proving all direct testcase entries, counts, failures, skips and duplicates are preserved. Test its behavior on authentic artifacts plus source-mutated negatives before promotion.
4. Re-run the same strict SWT-based verifier on the corrected evidence and compare full Win32/GTK/Cocoa platform receipts. Keep GEF release **HOLD** while the mismatch remains.

This is an evidence/diagnostic record. It does **not** assert that the GEF native or release gates have passed.

## Status update (2026-11-25, appended)
Step 3 is delivered to the main build by [GEF #47](https://github.com/hsoliwal/gef-classic/pull/47). Every verify job in `.github/workflows/maven.yml` builds its JUnit XML with the Tycho 5.0.4 OSGi booter rebuilt around the corrected Surefire 3.5.6 reporter (the patches in `tests/m3/surefire356-authentic/`). Step 4 runs the same strict verifier, still pinned at `1202ab3`, on those reports. The automatic push and pull_request runs on head `15087a2` are the first main-build runs with this producer, and both passed. The text above is unchanged. Receipts, hashes and local verification are in the paired [Synexia #19303](https://github.com/hsoliwal/com.synexia/pull/19303). Release stays HOLD for the other open gates.
