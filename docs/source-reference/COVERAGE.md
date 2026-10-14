# Semantic coverage ledger

Source revision: `f37f3da20888214c783ba2742a84a5e60571b7ee`. Review date: 2026-10-14. Scope: public source in `hsoliwal/gef-classic`, including production, examples, tests, internal code, build/packaging units, scripts and proving/provenance material. Publication status: Markdown-only documentation draft. This ledger uses editorial evidence labels; it does not claim implementation of an M3 promotion kernel or semantic fixed point.

[Reference home](README.md) · [Modules](MODULES.md) · [Packages](PACKAGES.md) · [API contracts](LIFECYCLE.md) · [Other artifacts](OTHER_ARTIFACTS.md)

## Counting rules

Repository, Eclipse project, build unit, Java namespace and API contract are independent dimensions. `project` here means a checked-in Eclipse `.project`; a Maven aggregator can be a build unit without that workspace project marker. `module` here means a POM, OSGi bundle, feature or a referenced reactor unit, not every directory. Project and module identities may share one path. A Java namespace is established by the actual package declaration, not its folder name. Repeated namespaces are retained by source origin in PACKAGES.md. Export-only namespaces are unresolved source records rather than invented implementations.

| Dimension | Discovered/accounted | Semantic complete | Partial semantic review | Remaining semantics / limits |
| --- | ---: | ---: | ---: | --- |
| Repository | 1 | 0 | 1 | Exhaustive lower-level review and native/build verification remain open |
| Eclipse project paths | 35 | 0 | Not individually completed | Every record is manifest-accounted in MODULES.md; per-project behavior/configuration review remains partial |
| Build/bundle/feature units | 38 | 0 | Not individually completed | All metadata/references accounted; manifest declarations do not prove dependency resolution or public contract completeness |
| Declared named Java namespaces | 159 | 0 | 9 | 150 namespaces have declaration/navigation coverage but no package-semantic review in this addition |
| Export-only namespaces | 2 | 0 | 0 | Source/provenance unresolved; retained in scope |
| Selected lifecycle contract groups | 6 | Not a whole-API completion denominator | 6 | Group-level source review only; not an exhaustive count of public methods |
| Other source/binary artifacts | 6 | 0 | 0 | Tree/path accounting only; 0 JAR interiors not inspected |

No discovered ordinary source component was excluded to improve a coverage percentage. All declared namespace records remain included. The explicit export-only and binary gaps prevent a complete-repository claim. Build failures are not reported because no build ran; not-run stages must not be presented as passing.

## Partial package-semantic records

All records below are OBSERVED / PARTIAL at the source revision. Their documented claims live in LIFECYCLE.md; API members outside those explicit claims remain unreviewed. Every other named namespace is DECLARATION-ACCOUNTED / SEMANTICS-PENDING as listed in PACKAGES.md.

- `org.eclipse.draw2d`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.commands`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.editparts`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.ui.parts`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.examples.shapes`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.examples.shapes.parts`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.examples.shapes.model.commands`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established
- `org.eclipse.gef.test`: selected implementation/caller/test contracts reviewed; [lifecycle guide](LIFECYCLE.md); whole-package/API completeness not established

## Claim ledger

| Claim group | Evidence scope | Status | Open verification |
| --- | --- | --- | --- |
| GEF-01 | Viewer/control creation, hooking, root replacement and editor disposal; exact source permalinks in the matching guide section | OBSERVED; relevant bodies reviewed | Runtime/native checks NOT_RUN; edge cases described in guide remain open |
| GEF-02 | Edit-part model/registry, child attachment, activation/removal; exact source permalinks in the matching guide section | OBSERVED; relevant bodies reviewed | Runtime/native checks NOT_RUN; edge cases described in guide remain open |
| GEF-03 | Graphical figures/connections and policy delegation; exact source permalinks in the matching guide section | OBSERVED; relevant bodies reviewed | Runtime/native checks NOT_RUN; edge cases described in guide remain open |
| GEF-04 | Command stack guards, events, save point, disposal and exception boundary; exact source permalinks in the matching guide section | OBSERVED; relevant bodies reviewed | Runtime/native checks NOT_RUN; edge cases described in guide remain open |
| GEF-05 | Shapes example caller/model/command/save lifecycle and source/prose mismatch; exact source permalinks in the matching guide section | OBSERVED; relevant bodies reviewed | Runtime/native checks NOT_RUN; edge cases described in guide remain open |
| GEF-06 | Draw2D Canvas/figure/update-manager ownership; exact source permalinks in the matching guide section | OBSERVED; relevant bodies reviewed | Runtime/native checks NOT_RUN; edge cases described in guide remain open |

## Static verification receipts

- INV-01 PASS: recursive Git tree at `f37f3da20888214c783ba2742a84a5e60571b7ee` returned `truncated=false`; 2787 entries accounted by the source/metadata inventory
- INV-02 PASS: 1410 Java file contents fetched; package-declaration scan identified 159 named namespaces, 160 named-namespace/directory records and 9 default-package files. This is lexical declaration extraction, not Java compilation
- INV-03 PASS: 11 POMs, 25 OSGi manifests and 7 feature descriptors plus project metadata parsed without a recorded parse error. 34 module edges retained with default/profile activation labels. 34 units are reachable from the root through non-profile edges, including the root itself
- INV-04 PASS: recomputed UTF-8 Git blob SHA-1 for 1514 fetched source/metadata/doc objects; zero mismatches against the connector's blob SHAs. This is a content-identity check, not semantic equivalence
- DOC-01: local link/path and generated-file validation is recorded in the PR publication update after checking the final files. It is not a repository test suite
- BUILD-01 NOT_RUN: Maven/Tycho compilation and dependency/target resolution; documentation-only connector workflow
- TEST-01 NOT_RUN: JUnit, SWTBot and example execution
- UI-01 NOT_RUN: native display interactions, visible-screen capture and pixel validation
- RESOURCE-01 NOT_RUN: CPU, heap, native-memory/resource budgets and repeated lifecycle stress
- COMPAT-01 NOT_RUN: complete API/Javadoc compatibility and every-platform qualification

Only public repository content is included. No private requirement essays, chat transcripts, credentials, hidden notes or external private data are published. Upstream and file-level license notices are unchanged. Binary/provenance entries are not license-compatibility judgments.

## Completion gate still open

To complete an individual package, establish its purpose/public surface, dependencies, examples, errors and state/resource/threading contracts from relevant complete bodies/callers; reconcile inline documentation; and link actual executed verification receipts when required. A source directory, export declaration, test filename or draft PR does not meet that gate alone. The next work should expand semantic review to the currently pending namespaces and resolve the explicitly listed discrepancies without silently changing behavior as part of documentation.
