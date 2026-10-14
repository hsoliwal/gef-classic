# Project and module ownership
> Source snapshot: `f37f3da20888214c783ba2742a84a5e60571b7ee` in `hsoliwal/gef-classic`. Status: source/manifest observation, not a build or runtime qualification.

[Reference home](README.md) · [Declared packages](PACKAGES.md) · [Lifecycle contracts](LIFECYCLE.md) · [Coverage ledger](COVERAGE.md)

The records below account for 40 distinct metadata-backed paths, including 38 build/bundle/feature units and 35 Eclipse project paths. Those sets overlap. A Maven aggregator, Eclipse workspace project, OSGi bundle, and feature are different identities even when they share a path. `default-reactor` means reachable through parsed, non-profile `<modules>` edges from the root; it does not mean this build has resolved or passed. Profile-only and unreferenced paths remain in scope.

The Tycho build extension in [.mvn/extensions.xml](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/.mvn/extensions.xml) explains why reactor entries need not each contain a checked-in POM. Imports and exports below are manifest declarations, not proof that every exported type is public API: retain `x-internal`, `x-friends`, version and fragment qualifiers. Feature inclusion controls packaging; it is not a Java call dependency.

## Project navigation

| Navigation group | Metadata-backed paths |
| --- | ---: |
| [build-and-releng](modules/build-and-releng.md) | 3 |
| [draw2d](modules/draw2d.md) | 7 |
| [fork-tooling-and-provenance](modules/fork-tooling-and-provenance.md) | 3 |
| [gef](modules/gef.md) | 17 |
| [zest](modules/zest.md) | 10 |

[All default/profile reactor edges](REACTOR.md)
