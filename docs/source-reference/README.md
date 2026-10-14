# GEF Classic source reference

This reference documents the experimental `hsoliwal/gef-classic` fork at `f37f3da20888214c783ba2742a84a5e60571b7ee`. The [upstream project](https://github.com/eclipse-gef/gef-classic), its authorship, public APIs and existing notices remain authoritative. M3 research direction is not a statement of upstream endorsement, measured performance, or verified compatibility. No product source, build configuration, CI or license is changed by this reference.

## Choose the level you need

- [Project and module ownership](MODULES.md): every POM/bundle/feature/project boundary discovered at the snapshot, declared exports/dependencies, owned namespaces and reactor edges
- [Declared Java packages](PACKAGES.md): every named namespace and its source origins, including examples, tests, internal code and fork proving/tooling
- [API lifecycle guide](LIFECYCLE.md): substantive reviewed contracts for model/edit-part/viewer attachment, command history, example save state and Draw2D teardown
- [Other source and binary artifacts](OTHER_ARTIFACTS.md): scripts, embedded JavaScript and binary artifacts retained in scope with explicit review status
- [Semantic coverage ledger](COVERAGE.md): what is inventoried, what has behavior-level documentation, exact checks and remaining gaps

## What the inventory establishes

At this revision there are 35 Eclipse project paths, 38 build/bundle/feature units, and 159 declared named Java namespaces. The build units comprise overlapping Maven/OSGi/feature records; do not add the counts as independent projects. The 1410 Java files span 160 named-namespace/directory records plus 9 default-package files. Package names reused by test/proof trees remain separate origins.

All Java file contents and the listed metadata were fetched and matched against their Git blob SHAs. That verifies the inspected text's identity. It does not verify its behavior. The 6 selected lifecycle contract groups are partial semantic coverage, and 2 export-only namespaces still need source/provenance resolution. The inventories are a review map, not a claim of complete package/API documentation.

## Build and test entry points

Follow the [existing contributor/development guide](../../CONTRIBUTING.md); it remains the detailed environment and build authority. The root POM uses JavaSE-25, Jenkins selects JDK 25, and .mvn/maven.config pins Tycho 5.0.4. From the repository root, the ordinary verification command is `mvn clean verify`. UI tests need the native SWT runtime and a display; compiler/test reports and the p2 output are described in the contributor guide.

These commands are instructions, not execution receipts. Compilation, JUnit, UI/native execution, screenshot validation, resource budgets and performance measurement were **NOT_RUN** for this documentation pass. No release signing/promotion profile was invoked. No claim of merge readiness follows from publication as a draft PR.

## Updating this reference

When source changes, compare the new manifest/API bodies against the recorded revision before updating a claim. Preserve inactive/profile-only paths and provenance artifacts. Add caller/error/ownership/test evidence when upgrading a package from inventory to semantic documentation. Retain contradictions explicitly until a separately reviewed source or inline-documentation change resolves them. Do not replace unknown behavior with a generic lifecycle rule for an entire toolkit.
