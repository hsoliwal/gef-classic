# GEF JUnit report producer delivered to the main build — status note (2026-11-25)

This note is additive to the
[2026-11-18 evidence record](GEF-JUNIT-DIRECT-CASE-EVIDENCE-20261118.md).
It does not edit that record or the
[2026-11-23 red/green proof](../../tests/m3/surefire356-authentic/README.md).
It records step 3 of that record's *Correct response*: the producer is
updated. Step 4 (re-run the same strict verifier on the corrected evidence)
is what the main build now does on every run.

## What changed

The build change is in `.github/workflows/maven.yml` only. No product
source, test source, POM, Maven command or verifier changes.

1. A new job, `report-producer` (Ubuntu 24.04, Java 21, Maven 3.9.12),
   builds the JUnit XML producer from source:
   - checks out Apache Surefire `25ea054860a5c1e5932b360d8aa0a31944c2b841`
     (3.5.6) and Tycho `c4349ee27dfde2817dfc7121b5f84bfb9eeb0195` (5.0.4,
     the version in `.mvn/maven.config`), and requires clean trees, the
     reporter preimage hashes and the Git blob IDs of the two sealed
     patches in `tests/m3/surefire356-authentic/`;
   - applies only those two patches and requires the postimage hashes
     `06235aef…` (reporter) and `df96bf5d…` (regression test);
   - builds `maven-surefire-common` and runs the complete
     `StatelessXmlReporterTest` class: 12 cases must pass, including
     `testCountsRepeatedMethodNameInvocationsInXml`;
   - rebuilds the unchanged Tycho OSGi booter, which embeds the reporter;
   - requires the identities qualified by the 2026-11-23 hosted
     differential run
     [45154851672](https://github.com/hsoliwal/gef-classic/actions/runs/45154851672):
     booter JAR `73c9822161a262d9f8fa3169219f15be316a73b6f9df8ea94210eb22939a42cc`,
     embedded `StatelessXmlReporter.class`
     `cdd12416d7e9a0e18795f95747f28b945b7ee0f542f6f8c1d86f1ab86d97152a`,
     booter POM `a1101fdfbdd5d2a6dde3d0a7738e3a7b98164f5b7d8bb091586a6e8d304de312`.
     Different bytes fail the job and need re-qualification. The job
     uploads the booter, its POM, the build logs and the raw reporter
     JUnit XML (gzip-compressed, so the unit-test publisher does not count
     Surefire's own tests as GEF tests).
2. Every `verify` job now needs that producer. Before the unchanged Maven
   command it checks the downloaded JAR and POM hashes and installs the
   booter into the job's Maven local repository at its stock coordinates,
   `org.eclipse.tycho:org.eclipse.tycho.surefire.osgibooter:5.0.4`.
   Tycho resolves the booter from there for each forked OSGi test JVM.
3. The strict report gate is unchanged: SWT
   [`readiness.py`](https://github.com/hsoliwal/eclipse.platform.swt/blob/1202ab304c3b81b73b628d201b987941d16c94d2/tools/release/readiness.py)
   at `1202ab304c3b81b73b628d201b987941d16c94d2` (SHA-256
   `4fb52bdc9415c290a7ad06c74d46e8e342bff954c7d6c8e03d12d24562f896ca`).
   It still requires every declared `<testsuite>` counter to equal the
   per-`<testcase>` count.
4. After the gate, each job requires that the local booter is still the
   qualified one and that the OSGi runtime configuration of each test
   bundle (`target/work/configuration/config.ini`, `osgi.bundles`) names
   exactly that file. Both Draw2D and Zest run two executions in one work
   directory, so this file shows the last execution's runtime; the strict
   gate covers the reports of both.
5. `gef-source.txt` now also records the Surefire and Tycho revisions and
   the booter and reporter hashes. A last `always()` step removes the
   booter from `~/.m2` so the shared `setup-java` Maven cache never
   carries it into a build that did not ask for it.

The `native-sparse-redraw` jobs are unchanged. They keep the stock booter
and do not run the strict parser.

## Why this is the producer route, not a reporting adapter

The header is corrected where it is written: by `StatelessXmlReporter` in
the forked test JVM. No XML file is edited after the fact. Stock 3.5.6
groups the runs of a report by source name and method name and declares one
result per group, but writes one `<testcase>` per run. The Draw2D suite has
one source name (the suite) and inherited parameterized methods with the
same names in `GraphicsTest$AWTGraphicsTest` and
`GraphicsTest$ScaledGraphicsTest`, so its header undercounted. The corrected
reporter counts what it serializes. Without reruns that is every report
entry and its own outcome. With `rerunFailingTestsCount > 0` the per-method
result is unchanged and only successful entries add more than one testcase.

The one adapter-like part is CI-only: the rebuilt booter shadows the stock
coordinates in the job's local repository. It is declared in the workflow
header comment, bound by hashes, recorded in `gef-source.txt` and removed
before the cache is saved. Local developer builds keep the stock booter.

## Local verification (2026-11-25, Windows 11, Temurin 21.0.13.1+2, Maven 3.9.12)

| Gate | Result |
|---|---|
| Surefire tree at `25ea054…` | `9078f317361a7ae0dc0b46edaedbbec47a9c4bc3`, as in run 45154851672 |
| Tycho tree at `c4349ee…` | `4be8df29d3f384ce52320deec544b2aa1ba59162`, as in run 45154851672 |
| Preimages, patch blob IDs, postimages | all match |
| Original reporter plus the regression test only | RED: 1 test, 1 failure, `expected:<[2]> but was:<[1]>` |
| Corrected reporter, complete `StatelessXmlReporterTest` | GREEN: 12 tests, 0 failures, errors or skips |
| Corrected `StatelessXmlReporter.class` | `cdd12416…`, the qualified identity |
| Rebuilt Tycho booter | built from the unchanged source (tree clean afterwards); embedded reporter class `cdd12416…` |
| Booter POM | `a1101fdf…`, byte-identical to the POM published on Maven Central |
| `install-file` over a cached stock booter, then a new resolution | the corrected JAR replaces the stock one and is kept |
| Real stock vs corrected reporter, Draw2D shape (one suite source, the same 3 method names in the AWT and Scaled subclasses, plus 1 skip) | stock declares `tests=4` for 7 testcases: the unchanged strict verifier refuses it (`declared=4 observed=7`); corrected declares `tests=7 skipped=1`: accepted |
| Real stock vs corrected reporter, EMF Xcore shape (one name skipped, then passed) | stock declares `tests=6 skipped=0` for 7 testcases: refused (`declared=6 observed=7`, the EMF CI error); corrected declares `tests=7 skipped=1`: accepted |
| The workflow's Python checks, run against a simulated runner layout | pass on qualified inputs; refuse a stock booter, a tampered JAR, a missing test runtime and booter bytes other than the pin |

The local booter JAR (`6fb8ea86…`) is not the Linux-built pin. Its embedded
reporter class is identical. Compared with the stock booter on Maven Central
(built with JDK 25), the only other differing entries are the JDK 21
compilation of `SurefireLoader$1.class`, `Build-Jdk-Spec` in the manifest
and the Sisu index, which gets CRLF line endings on Windows. The pin was
produced on Linux with JDK 21 by the hosted run and reproduced by the
2026-11-23 local rebuild recorded in the README.

The full GEF reactor was not run locally: its Draw2D and SWTBot suites open
real SWT windows on this interactive Windows desktop. The 2026-11-23 hosted
differential already ran it on GTK with this producer and found identical
testcase multisets and strict acceptance of every Draw2D, GEF and Zest
report.

## Status

- CI: the pull request run is the first main-build run with this producer.
  Its result is recorded on the pull request, not in this file, so that
  recording it does not need another run.
- Release stays **HOLD** for the gates the earlier records list as open:
  the full Windows/GTK/Cocoa native matrix, pixels, resources and
  performance.
- This route keeps the strict verifier. It does not depend on
  hsoliwal/eclipse.platform.swt#597 or hsoliwal/gef-classic#46.
- Rights: Apache Surefire (Apache-2.0) with the sealed patch and Tycho
  (EPL-2.0, rebuilt unchanged) are CI test infrastructure only. They are
  not redistributed with GEF.
