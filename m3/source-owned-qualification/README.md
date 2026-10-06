# GEF owning source and recipe qualification

The original pinned public GEF source is ef39f9d7f82c0d05c5c32b081d9a48b07579bccc,
tree d5a55a690f73ee8267cb82b4bc1f74235ca2a213. It already owns Maven/Tycho 5.0.4:
2,306 tracked files, 1,406 Java files, 33 default modules plus the root.

The original full native Windows/JDK25 reactor passed all 34 projects with 17,703
recorded testcase elements, zero failure/error entries, and three original GTK/Linux platform assumptions.
The Draw2d XML header reports 9,369 while containing 17,534 testcase elements, matching the
native counter. All five report headers total 9,538; testcase entries total 17,703. These
are report keys/counters and do not establish unique methods or cross-engine test identity.
The exact original source bytes were restored after the native baseline build; three original about.mappings mtimes changed.
The initial 240-second command observation was incomplete; the separately observed exact
Maven Java process exited 0. FINAL_NATIVE_OWNER_RECEIPT.json is authoritative for that baseline.

This saved successor seals the actual isolated source owner with one reviewed 134-byte POM atom:
the owning .m3 OpenRewrite bootstrap declares SLF4J API 2.0.17 as a runtime dependency.
Independent source review passed; exact official postimage application and the unchanged original
bootstrap clean verify passed on the actual owning checkout. All other 2,305 original source paths remain byte exact.
Its unchanged strict production/test compiler contract and original JUnit test pass.
The owned task gate also passes positive and bounded refusal discriminators.
Java source, APIs, tests, GEF root/module/POM/.mvn/target contracts are unchanged.

bootstrap-runtime/recipe.yaml uses the official FindAndReplace 8.90.4 engine.
Its entire preimage/postimage are byte exact, including Maven ${...} placeholders.
The Maven proof carrier disables unrelated YAML property interpolation and executes
exact postimage, fixed point, wrong path and source drift discriminators.
Replay it with `mvn -f m3/source-owned-qualification/bootstrap-runtime/pom.xml verify`.
The source recipe validate gate reuses the existing unchanged native Windows reparse
guard and raw full-source hashes. A relocated source-only carrier must bind m3.gef.root
to the real owning checkout; copied sources are not an admission substitute.

The optional owned-build profile reuses the original full CI flags and waits for the
actual native Maven command terminal exit, preserving original Tycho test timeouts.
It separates raw byte drift from original persisted mtime effects. No module selection,
test/target/JDK downgrade, logging provider, resolver, parser or compiled binary is added.

The root build contract is JavaSE-25. Local Maven 3.9.14 differs from original CI 3.9.12.
The official external Temurin25 SDK is checked through its supplied custody receipt and all
431 runtime files. Moving target URLs/0.0.0 units remain an observed dependency resolution,
not an immutable dependency snapshot. Other OS/architecture tests, GTK/Linux-only cases,
full dependency license custody, repository-wide atom inventory and Synexia JNI/ABI/transpile
admission remain open. Trusted recipe metadata/guard loads before source bodies; no atomic
concurrent-replacement/handle-lifetime claim is made.

Original root LICENSE, NOTICE and source headers remain intact. All 136 GitHub repositories
and the earlier 364 local inputs remain in the parent active goal; this is one qualified project.

SOURCE_REVIEW.json is retained verbatim, including its historical reconciliation-pending field.
ORIGINAL_TEST_REPORT_RECONCILIATION.json is the separate completed counter reconciliation.
MAIN_SOURCE_APPLICATION_RECEIPT.json and MAIN_ORIGINAL_BOOTSTRAP_AFTER_RECEIPT.json bind
the actual application and post-application test. Original full reactor evidence covers the
unchanged native reactor baseline; it was not rerun for this isolated sidecar runtime atom.
