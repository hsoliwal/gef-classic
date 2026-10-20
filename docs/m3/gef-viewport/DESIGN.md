<!--
 Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
 SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
-->
# Draw2d / GEF / Zest on the viewport model

Status (2026-10-19): Draw2d leaf implemented behind `-Ddraw2d.viewport.mode=viewport` (default `original`),
differential identical to stock, headless benchmarks below. GEF EditPart deltas and Zest are the next leaves.

The goal: a diagram of 10^5 figures costs work proportional to what is visible or changed, not to the number of
figures, with Draw2d's public API and observable behaviour unchanged (BCP).

## 1. Inventory: the O(n) paths, measured on stock

Headless GEF figure stack (`FreeformViewport > ScalableFreeformLayeredPane > FreeformLayer/FreeformLayout`,
`RectangleFigure` nodes on a jittered grid, viewport 1200x800, one JVM per run, median of 15-41 runs);
`M3ViewportBench`, raw rows in `evidence/`. Stock = fork master `eb83209d` compiled with javac.

| path | stock code | stock 10^4 | stock 10^5 | viewport 10^5 | viewport + trustConstraints 10^5 | upstream issues addressed |
|---|---|---|---|---|---|---|
| paint, damage 50x50 | `Figure.paintChildren` visits every child | 0.10-0.52 ms | 2.56 ms | 21 us | 13 us | eclipse-gef/gef-classic#303 (clip push/restore garbage per child: off-screen children no longer get `clipRect`/`restoreState`); #952 (Batik `SVGGraphics`, merged 2026-09-22: the culled paint feeds any `Graphics`, SVG export included) |
| paint, whole viewport | same | 0.14-0.73 ms | 3.18 ms | 0.12-0.59 ms | 0.55 ms | #303 |
| scroll + paint | same + validation | 0.67-2.2 ms | 9.88 ms | 0.71-0.87 ms | 1.19 ms | #425 (validate/repaint cycle cost), #430 (paint-event storms: each repaint now costs O(visible)) |
| zoom 0.25 + paint | same, under ScaledGraphics | 0.15 ms | 2.64 ms | 0.14-0.23 ms | 0.28 ms | #303 |
| `findFigureAt` | `Layer.containsPoint` + `findDescendantAtExcluding` loop over all children | 48-109 us | 1906 us | 1.3 us | 2.3 us | #546 (large Zest graphs: mouse moves over 900+ nodes) |
| `findMouseEventTargetAt` | same loops | 47-146 us | 1449 us | 2.6 us | 1.5 us | #546 |
| move one node + update | `XYLayout.layout` + `children.forEach(validate)` + extent + `setFreeformBounds` loops | 0.83-2.3 ms | 106 ms | 5.1-5.6 ms | 48 us | #425; #282 (`fireFigureMoved` on resize only, merged: the index listens to that event for both move and resize) |
| add one node + update | same | 1.3-1.8 ms | 139 ms | 5.7-6.2 ms | 51 us | #425 |
| remove middle node + update | same + `ArrayList.remove` | 1.6-24 ms | 169 ms | 4.2-6.4 ms | 0.51 ms | #425 |
| freeform extent after a move | `FreeformHelper.getFreeformExtent` unions all children | 0.33-0.39 ms | 3.29 ms | 2.5-7.3 us | 4.7 us | #282 (bendpoint/extent recomputation per move) |

Upstream issue numbers are eclipse-gef/gef-classic issues (all closed upstream by workarounds or partial fixes;
none of them removed the O(n) loops). Found with `gh issue list --search performance`; a GEF-level issue on
`refreshChildren` was not found.

Rendering seams this leaf keeps intact: Draw2d paints through `Graphics` (`SWTGraphics`, `ScaledGraphics`,
`AWTGraphics`, and the Batik `SVGGraphics` of #952), so the culled `paintChildren` applies to screen, print and
SVG export alike, and figures outside the clip neither paint nor, when valid, validate. The drawn plane of the SWT
viewport programme (render-modes track, the M3 painter behind `-Dswt.widget.mode=m3`) is a `Graphics`-compatible
target for the same culled paint, not a second paint loop.

Ranges are spreads between runs (JIT and machine load; the agents share the machine). JFR attribution of the
stock 10^5 run: hit tests are 75% `Layer.containsPoint`; moves/adds/removes are ~87% `XYLayout.layout` plus the
children `validate()` loop. Not O(n) in practice: `DeferredUpdateManager` damage merging (per dirty figure, one
union), `ArrayList.remove` (a memmove).

Not measured headless (need a Display or the GEF/Zest bundles), from code reading:
- **GEF `AbstractEditPart.refreshChildren`:** rebuilds a model-to-EditPart HashMap of all children on every
  refresh, then walks positions; one model insertion at index 0 makes every later position mismatch and calls
  `reorderChild` (two `ArrayList` shifts plus figure remove/add) for each: O(n^2) for one insert.
- **Zest:** `Graph` keeps nodes and connections in one `ZestRootLayer` child list (`indexOf` for decorations and
  highlight: O(n)); spring layout is O(n^2) per iteration; rendering is the Draw2d paint path above.
- **Connections:** `Polyline` overrides `getBounds`/`containsPoint`/`primTranslate`; the connection layer stays on
  the stock path in this leaf (always-asked children, see 3.2).

## 2. Model: one child index per large container (DOOP)

Containers with at least `draw2d.viewport.minChildren` (64) children get an `M3FigureChildren` on first use
(paint, hit test, validate, layout, extent). It holds lanes only, keyed by an int slot per child:

| lane | class | content |
|---|---|---|
| slot registry | `M3ChildSlots` | slot -> figure facade, figure -> slot (identity open addressing, backward-shift deletion), z-order label per slot (list labelling with gaps), trust bits per slot |
| bounds | `M3SpatialGrid` | x, y, width, height int arrays; uniform-grid cells (power-of-two size from the mean child extent) as doubly linked int entry lists; always-tested list for overflowing or very large rectangles |
| validity | `M3ChildValidation` | bit per slot while the child may be invalid, pending worklist |
| XY layout | `M3XYLayoutPlan` | constraint object and its four ints as last applied, dirty bits, origin of the last pass |
| freeform extent | `M3FreeformExtent`, `M3ExtentTree` | min/max segment tree over (x, y, right, bottom) of nonempty trusted children; FreeformFigure children listed apart |

The Figure objects stay the authority and the API: the index mirrors their bounds and flags and never holds state
of its own that a figure could contradict. `M3FigureTrust` gives each class a bit per question the index may answer
from lanes; a class earns a bit only if it (or a superclass below `Figure`) declares none of the methods the
stock answer depends on (`getBounds`, `setBounds`, `primTranslate`, `fireFigureMoved`, `getFlag`, `isVisible`,
`isEnabled`, `containsPoint(int,int)`, `findFigureAt(int,int,TreeSearch)`, `validate`, `isValid`, `setValid`).
Children without the bit are "always asked": they are visited with the stock calls every time.

Hooks (recipe `recipe/META-INF/rewrite/m3-draw2d-viewport.yml`, applied by `tools/apply_recipe.sh`, second pass
UNCHANGED): `Figure` gets the field `m3Children` and the accessor `m3ChildIndex()`, and one-line delegations in
`add`, `remove`, `fireFigureMoved`, `setValid`, `validate`, `paintChildren`, `findDescendantAtExcluding`,
`findMouseEventTargetInDescendantsAt`; `Layer.containsPoint`, `XYLayout.layout`,
`AbstractConstraintLayout.setConstraint`, `FreeformHelper.getFreeformExtent` and
`FreeformHelper.setFreeformBounds` get one delegation each. In the original mode no index is ever created and every
hook is a null test.

## 3. Paths

### 3.1 Paint (`M3ChildQueries.paintChildren`)
Grid query with the graphics clip; candidates = trusted grid hits + always-asked children, sorted by label (z-order);
each candidate gets exactly the stock body (`isVisible`, `getBounds().intersects(getClip(...))`, `clipRect`,
`paint`, `restoreState`). Skipped children are trusted children whose bounds miss the clip, for which the stock loop
paints nothing. Falls back to the stock loop under an `IClippingStrategy` or when a quarter or more of the children
meet the clip (zoomed out).

### 3.2 Hit tests
`findDescendantAtExcluding`, `findMouseEventTargetInDescendantsAt` and `Layer.containsPoint` run the stock loop
bodies over the grid's point candidates (top-most first for the first two, list order for `containsPoint`).
For a trusted child whose bounds miss the point, stock `findFigureAt`/`containsPoint` returns null/false before
any other call (`containsPoint` short-circuits `search.prune`), so skipping it is not observable.

### 3.3 Validation (`M3ChildValidation`)
`Figure.validate` ends with `children.forEach(IFigure::validate)`; a valid trusted child returns at once. The index
validates, in list order, only flagged children (every child when the index is created, then the `setValid(false)`
calls) and the always-asked ones; children invalidated during the pass are picked up when ahead of the cursor, as
the stock loop would reach them.

### 3.4 XY layout (`M3XYLayoutPlan`)
Stock `XYLayout.layout` sets every child's bounds to its constraint; `Figure.setBounds` does nothing when they are
unchanged. The plan lays out, in list order with the stock calls, only children that were moved or added, whose
constraint was replaced (`setConstraint` hook) or changed in place (snapshot compare), that are sized by preferred
size (`-1`), or whose classes override `setBounds`. Origin or layout-manager changes, and subclasses of `XYLayout`
other than `FreeformLayout`, take the full stock pass. How the plan finds constraints changed in place is the
constraint policy, `-Ddraw2d.viewport.constraints=`:

| policy | a pass visits | O(...) per delta | differs from stock when |
|---|---|---|---|
| `exact` | every child whose constraint differs from its snapshot (int sweep over all slots) | O(n) | never |
| `attributed` (default) | the changed children; the snapshot sweep only after a direct `revalidate`/`invalidate` of the container | O(changed) | a constraint changed in place is followed only by add, remove, `setConstraint`, a resize or a child revalidate of that container, never by a direct `revalidate` (stock then lays it out on the next unrelated pass; here it waits for a direct one) |
| `trusted` (`trustConstraints=true`) | the changed children | O(changed) | any constraint is changed in place |

Attribution: Figure announces (`M3FigureChildren.explain`) the `revalidate`/`invalidate` it makes itself in `add`,
`remove`, `setConstraint`, a resize in `setBounds`, a child's `revalidate`, and `FreeformHelper.invalidate`, right
before the call; `Figure.invalidate` of the container consumes the announcement. Any other invalidation (client
code after an in-place change, `invalidateTree`, a border or layout-manager change) is direct and arms the sweep.
Attribution is off (every invalidation direct) for containers whose class overrides `invalidate` or `revalidate`
(trust bit `INVALIDATION`) and while the container has layout listeners (`LayoutNotifier`), whose callbacks may
change constraints. `-Ddraw2d.viewport.verify=true` sweeps after every attributed or trusted pass and throws when
the sweep finds more than the worklist.

### 3.5 Freeform extent (`M3FreeformExtent`)
Stock: copy of the first child's rectangle, then the union of every nonempty one, after every child move. Here:
the first child and the always-asked/FreeformFigure children are folded in live; the rest come from the segment
tree root. `setFreeformBounds` visits only the FreeformFigure children, in list order.

### 3.6 Known edges (documented, not hidden)
- A child whose bounds are changed without `figureMoved` (mutating the rectangle returned by `getBounds()`, which
  `Figure.setBounds` already documents as unsupported) is invisible to the index; `-Ddraw2d.viewport.verify=true`
  detects it and throws.
- If paint or validation modifies the parent's child list, stock's `ArrayList` iteration throws
  `ConcurrentModificationException` (or not, depending on position); the index throws it after the modifying child.
- Fewer `Graphics.getClip` queries are made (queries only; no drawing operation differs).
- Attributed policy (default): an XY constraint changed in place takes effect at the next direct `revalidate`
  of its container, not at the next layout the container gets for another reason (table in 3.4); `exact`
  restores the stock timing.

### 3.7 Hidden viewports (`M3Visibility`, brief rule 24)
Stock Draw2d validates and paints a hidden canvas: the posted update runs whether or not the canvas can be seen.
In the viewport mode `LightweightSystem` registers its canvas with `M3Visibility`; while the canvas is hidden
(disposed, not visible itself or through an ancestor such as an unselected editor tab, or with an empty client
area) the posted update (`DeferredUpdateManager.UpdateRequest.run`) returns at once: model changes still reach
the figures and the child indexes, invalid figures and dirty regions accumulate, but nothing is validated or
painted. When the canvas shows, the window system paints all of it once; the first update validates once and
drops the regions dirtied while hidden instead of painting them a second time (and when the window system's
paint comes first, it drops them). An explicit `performUpdate` while hidden still validates (the caller asked for
current bounds) but does not paint. Gate: `M3HiddenViewportTest` (hide, 10^4 changes with 100 posted updates,
show: 0 validation and 0 paint passes while hidden, then exactly 1 validation pass and 1 paint pass).

### 3.8 Viewport deltas: strips and pieces (`M3Damage`, `M3DirtyPieces`, brief rule 24)
Stock `Figure.setBounds` damages the whole old bounds (through the parent) and the whole new bounds, and the update
manager repairs one rectangle, the union of every dirty region. A canvas resize therefore repaints the window, a
freeform extent change repaints every layer, and two small changes far apart repaint everything between them.
In the viewport mode (`-Ddraw2d.viewport.damage=stock` turns it off):
- **Strips.** A bounds change of a figure whose painting does not depend on its size (trust bit `SIZE_FREE`: no
  paint, paintFigure, paintBorder, paintClientArea or paintChildren override below Figure except the reviewed
  size-free ones of Viewport, ScalableLayeredPane, ScalableFreeformLayeredPane and ConnectionLayer), without a
  border or clipping strategy, damages only old minus new (through the parent, as `erase`) and new minus old
  (through the figure), as up to four bands each, when it only resizes or when the figure keeps its children in
  place on a move (`KEEPS_CHILDREN`: FreeformLayer, FreeformLayeredPane). The pixels the old and new bounds share
  stay valid: opaque fills are uniform, children keep their place and paint themselves where they move.
- **Pieces.** Every `addDirtyRegion` is also kept as a piece (up to 16 per repair). At repair time the pieces are
  clipped up the parent chain like the stock contributions; pieces that overlap or whose union is at most 1.25
  times their areas merge; when the merged pieces cover at most 3/4 of the stock damage rectangle (none or one
  piece included), each is repaired with its own clip. The stock damage unions a figure's regions before clipping
  them, so the strips a shrinking viewport uncovers beyond its shrinking root become the whole root; clipped
  first, they vanish. Update listeners still receive the stock damage rectangle and map.
- **Proof.** A frame raster (`M3FrameRaster`): every drawing operation stamps its device bounding box (lines and
  polylines their segments, at positions that do not depend on the clip), cut by the clip, with a value derived
  from the operation and the colours. Repairs paint into one persistent raster, as a canvas keeps its pixels;
  pixels a resize exposes start as a marker no operation writes. After every flush the raster must equal a fresh
  paint of the whole canvas. The recorded differential runs in this frame mode for every viewport variant (the
  repair operations legitimately differ from stock), and in operation mode for `damage=stock` (identical repair
  operations) and the original mode.
- **Gate.** `M3ViewportDeltaTest`: 40 canvas resize steps (width only, height only, both growing, both shrinking)
  on 10^5 nodes validate no child and paint at most the children meeting the exposed or uncovered strips; 13 zoom
  steps validate no child (every visible pixel changes, so the visible children paint); every step's raster equals
  a fresh paint.

## 4. Proof

- **Recorded differential** (`M3ViewportDifferentialTest`, `M3Draw2dScript`): stock Draw2d and the M3 build in
  separate class loaders run the same seeded public-API scripts over a mixed population (shapes, polylines, nested
  containers, classes overriding `setBounds`/`validate`/`containsPoint`, invisible, empty, negative and overflowing
  rectangles, mouse listeners, figure listeners): adds, removes, z-order changes, constraint replacement and
  in-place mutation, direct `setBounds`, visibility, enablement, preferred sizes, scroll, zoom, canvas resizes,
  paint (graphics operation hash), the four hit tests, freeform extents, repairs, validate and figure-moved events,
  and every node's final bounds. Repairs are logged as the canvas frame raster (3.8) for the viewport variants and
  as the repair operations (hash and count) for the original mode and the viewport mode with
  `damage=stock`. 24 seeds x 400 steps x 7 variants (viewport attributed, exact, trusted without in-place changes,
  without verify, delta-only, stock damage, original): identical. Verify mode is on in the M3 loader. Mutation
  check: reversing the hit-test order fails at seed 1.
- **Delta gate** (`DELTA_GATE.tsv`, `M3DeltaGate`): delta-only scripts (24 seeds x 400 steps of add, remove,
  `setConstraint`, `setBounds`, visibility, enablement, z-order, preferred size, front bursts, emptying a
  container, inside a fixed frame) match stock line for line, and from the first flush on the M3 build counts
  zero full XY layouts, constraint sweeps, stock paint loops and full validations, and neither build adds a dirty
  region covering a whole layer, pane, viewport or the root. Under the exact policy the same steps sweep
  (`exactPolicySweepsOnDeltaSteps`), so the counters are live.
- **Pixels** on the hidden desktop: next commit (`M3ViewportPixelUiTest`).
- **Coverage:** `tools/test.sh` (JaCoCo); report in `coverage/`.

## 5. Next leaves

1. **GEF EditPart deltas, one model** (user 2026-10-19): `refreshChildren` never walks everything. Delta-aware
   models hand an `M3Patch` (remove/insert/move runs over model ids, copied by recipe from M3jdk21 `M3ListDiff`/
   `M3Patch`) to the EditPart, which applies it to its child list and the figures in O(changed); the stock
   `refreshChildren` entry point computes the same patch with the LIS diff (O(n log n) once, O(changed) EditPart and
   figure work) instead of O(n^2) reorders for one insertion. No second model store: the EditPart child list stays
   the only list; the diff keeps int lanes only.
2. Connections: trust `Polyline`/`PolylineConnection` through their bounds cache (`bounds = null` invalidation
   fires `figureMoved`), so connection layers are culled too.
3. Zest: `ZestRootLayer` z-layers as label ranges, `indexOf` replaced by slot lookups; Zest example.
4. `DeferredUpdateManager`: damage as a short rectangle list instead of one union (opt-in: changes paint calls).
5. Examples bundle: 10^5-figure diagram (scroll, zoom, hit test) and an original/viewport timing overlay.
