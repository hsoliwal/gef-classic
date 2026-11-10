# Release readiness for M3JDK21 (Java 21): gef-classic

Measured on 2026-11-09 against fork master `a98142b17e267b2340bf2b148133ac05a240e997` (upstream master `9c9f17364e1b`).
Tools and raw evidence: [`docs/m3/jdk21/release-20261109`](../jdk21/release-20261109/). Every gate ran locally on CPU; fork CI runs are not counted
(startup failures and queue starvation on this account, 2026-11).

| # | Gate | Verdict | Evidence |
| --- | --- | --- | --- |
| G1 | No Java 22+ requirement on master: BREE above JavaSE-21 (0), build/IDE pins above 21 (0), unnamed `_` (0), `import module` (0) | PASS | CENSUS.tsv (code view; hits inside strings, text blocks and comments: CENSUS_MASKED.tsv) |
| G2 | Java-21 compile gate: JDK 25 javac `--release 21` minus `--release 25` over 0 bundles (0 files, 0 roots); 0 baseline rows cancel | PASS (residue 0) | GATE_RESIDUE.tsv, GATE_SUMMARY.tsv |
| G2b | Parse gate: every tracked Java source (1435 files, test bundles included, test-input folders excluded), JDK 25 javac `--release 21` stopped after parsing | PASS | PARSE_GATE.tsv |
| G3 | Recipe fixed point (`apply_jdk21.sh` + `apply_jdk21_pins.sh` change nothing on master) | PASS | RECIPE_CHANGES.tsv |
| G4 | Metadata consistent: Tycho bundle/feature versions match their pom, no EE above 21 in a Java-21 bundle | PASS | META.tsv |
| G5 | Full Tycho build of this repository on JDK 21 (`mvn -B clean verify -DskipTests`, fork parent + SDK target from the fork aggregator, other components from the upstream 4.42 I-build repository) | PENDING | not run yet |
| G6 | Eclipse SDK product build and launch on JDK 21 | PENDING | aggregator verdict, eclipse.platform.releng.aggregator docs/m3/release/RELEASE_READINESS.md |

## What this PR changes

Documentation only. Fork master `a98142b17e26` passes G1 to G4: upstream master declares no bundle above JavaSE-21 (0 gated bundles),
no Java 22+ construct in any source (parse gate), metadata consistent. gef-classic is not an Eclipse SDK submodule of the aggregator;
G5 for it was not part of the 2026-11-09 build order (jdt.debug, equinox, platform, platform.ui, jdt.ui, jdt.core).

## Re-run

```bash
git clone --depth 1 https://github.com/hsoliwal/gef-classic.git && git -C gef-classic remote add upstream <upstream>
git -C gef-classic fetch --depth 1 --filter=blob:none upstream master:refs/remotes/upstream/master
bash docs/m3/jdk21/release-20261109/gate_master.sh gef-classic    # paths at the top of the script; JAVAC = JDK 25 javac
bash docs/m3/jdk21/release-20261109/meta_check.sh gef-classic > META.tsv
```

The gate compiles every bundle that upstream master declares JavaSE-22 or newer, plus the 2026-10-20 list, against the
newest jar of every bundle in a p2 pool. Errors in both runs are classpath noise (APIs newer than the pool); what remains
after `--release 21` is the Java 22+ residue and must be empty.
