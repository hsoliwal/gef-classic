# Model, edit-part, command and viewer lifecycles

[Reference home](README.md) · [All module owners](MODULES.md) · [All declared packages](PACKAGES.md) · [Coverage ledger](COVERAGE.md)

This guide describes the inspected source at `f37f3da20888214c783ba2742a84a5e60571b7ee`. It is an implementation-reading guide for the fork, not a replacement for the upstream public interfaces and not a runtime qualification. The selected complete method bodies and their relevant callers were inspected; other classes in the same packages remain partial coverage.

## Responsibility and dependency boundaries

- `org.eclipse.draw2d` owns figures, canvas integration, event dispatch and painting. `LightweightSystem` bridges an SWT Canvas to a root figure and an UpdateManager. It does not own an application's domain model or command history. See [LightweightSystem.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/LightweightSystem.java)
- `org.eclipse.gef` owns the controller contracts (`EditPart`, `EditPartViewer`, `EditDomain`, policies and tools). The model is an application object. GEF does not require a particular model superclass or persistence format. `AbstractEditPart.setModel` only assigns the reference; it does not subscribe to application events. See [EditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/EditPart.java) and [AbstractEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editparts/AbstractEditPart.java)
- `org.eclipse.gef.commands` owns undo/redo history and change notifications. A command changes the model; an edit part responds to model events and refreshes visuals. Calling a figure setter directly is not a substitute for a model command when undo/persistence is required
- `org.eclipse.gef.editparts` supplies lifecycle implementations and model/visual registry integration. `org.eclipse.gef.ui.parts` binds these to SWT controls and Eclipse editors. The workbench shell/control lifecycle, application model lifetime, and command-history lifetime must be accounted for separately
- The Shapes example demonstrates the dependency chain: `ShapesEditor` configures a factory/root/viewer; `ShapeEditPart` installs edit policies and listens to model properties; commands in `org.eclipse.gef.examples.shapes.model.commands` mutate model objects. Example classes are not promoted to framework API by this guide

The module inventory retains Draw2D SVG, Zest core/layouts/Cloudio, documentation, examples, features, tests and fork tooling as separate owners. This selected GEF lifecycle review does not claim equivalent semantic review of every Zest or SVG API.

## GEF-01: viewer initialization and control ownership

[GraphicalEditor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/parts/GraphicalEditor.java) creates a `ScrollingGraphicalViewer`, creates its control, assigns the viewer, then calls `configureGraphicalViewer`, `hookGraphicalViewer`, and `initializeGraphicalViewer` in that order. Configure factories and the root before installing the model contents.

[AbstractEditPartViewer.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/parts/AbstractEditPartViewer.java) has two `setContents` overloads. The object overload asserts that an `EditPartFactory` exists and asks it to create the content edit part. The edit-part overload delegates to the root's `setContents`. Neither overload is a persistence operation.

A control is attached with `setControl`: the old control is unhooked before the new one is hooked. Hooking adds the dispose listener and activates an existing root. Unhooking removes that listener, disposes the context-menu provider, and deactivates the root. `handleDispose` disposes the viewer resource manager if created, then calls `setControl(null)`. `setRootEditPart` deactivates the old active root and clears its viewer before assigning/activating the replacement. Do not bypass these paths by retaining a control or root that has already been detached.

The editor's own `dispose` removes its command-stack and workbench-selection listeners, deactivates the active tool via the domain, disposes its action registry, and delegates to the superclass. It does **not** call `CommandStack.dispose` in this body. Do not claim that closing this editor implementation automatically disposes every application command or domain object; applications with an independently owned/shared stack need an explicit ownership decision.

## GEF-02: edit-part attachment, activation and removal

The relevant implementation is [AbstractEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editparts/AbstractEditPart.java); `EditPart` remains the public usage contract.

1. Set the model before the part is attached/registered. `setModel` itself performs no registry migration or listener rebinding
2. `addChild` inserts the child and sets its parent, attaches its visual, calls `addNotify`, activates it if the parent is active, and then notifies child-added listeners
3. `addNotify` registers model/visual/accessibility associations, creates edit policies, propagates notification to existing children, and refreshes
4. `activate` marks the part active, activates policies and children, then fires activation notification. Extend this method to subscribe to application-model events and pair that work with deactivation
5. `deactivate` deactivates children and policies, clears active state, then sends the deactivation notification
6. `removeChild` notifies listeners before removal, deactivates the child when the parent is active, calls `removeNotify`, removes its visual, clears its parent, and removes it from the child list
7. `removeNotify` clears selection/focus associations as needed, recursively notifies children, and unregisters accessibility/visual/model mappings

`getModelChildren` must return a non-null list callers must not modify. `refreshChildren` compares the current children with that model list, reuses/reorders matching parts, creates missing parts through the viewer factory, and removes surplus parts. The initial position check uses object identity, while the lookup map uses normal model-key equality/hash behavior. Models with unstable equality/hash keys can therefore affect registry and reconciliation behavior; do not replace that semantics with an identity-only claim.

`registerModel` inserts the model-to-part mapping in the viewer registry. Multiple parts for one equal model key need a deliberate registration strategy. A registration is not ownership of the model's memory or resources.

## GEF-03: graphical parts and policy responsibilities

[AbstractGraphicalEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editparts/AbstractGraphicalEditPart.java) lazily creates its figure through `getFigure`, inserts child figures into the content pane, registers the figure in the viewer's visual map, and unregisters it on removal. Source connections are activated after the base activation and deactivated before the base deactivation. `setLayoutConstraint` forwards the constraint to the child figure's parent.

In `AbstractEditPart`, `getCommand(Request)` chains contributions from installed policies. `getTargetEditPart` and feedback handling also delegate to policies. Installing a replacement policy on an active part deactivates the old policy, assigns the new host, and activates the new policy. A null policy can reserve a slot; the iteration API skips null entries. Keep model changes in commands and feedback in policies rather than using refresh methods to mutate the model.

[ShapeEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/ShapeEditPart.java) is a concrete example: activation/deactivation add/remove a `PropertyChangeListener`; size/location properties call `refreshVisuals`; connection properties refresh source/target connections. Its visual refresh sets the parent's layout constraint from model location and size. It handles elliptical and rectangular models and throws for an unrecognized model class. Extending the model family therefore also requires an intentional figure/anchor mapping.

## GEF-04: command-stack state machine and failure boundary

Evidence: [CommandStack.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/commands/CommandStack.java), [Command.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/commands/Command.java), [CompoundCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/commands/CompoundCommand.java). These bodies establish the following behavior:

| Operation | Guard / ordering | State, ownership and failure consequences |
| --- | --- | --- |
| `execute(command)` | Null or non-executable commands return immediately. Redo history is flushed before PRE_EXECUTE; the command body runs before it is pushed | Discarded redo commands are disposed. A successful body is added to undo history; a positive undo limit disposes oldest entries as needed. POST_EXECUTE is issued from `finally` around the body/history section |
| `undo()` | Returns if the top command cannot undo; otherwise pops it before PRE_UNDO and calls its body | The command moves to redo only after a successful body. POST_UNDO is in `finally`. Failure does not automatically restore the popped entry or roll back model state |
| `redo()` | Uses `canRedo`, pops redo, issues PRE_REDO, calls redo | Moves to undo only after success; POST_REDO is in `finally`. A stale Javadoc reference to `canUndo` does not replace the implemented `canRedo` guard |
| `markSaveLocation()` | PRE notification, records current undo depth, ordinary listener notification, POST notification | It marks history state; it does not save a document |
| `isDirty()` | Compares undo depth with saved depth | It cannot detect model changes made outside this history; save-location invalidation also handles branching after undo |
| `flush()` | PRE_FLUSH, disposes redo and undo histories, resets save location to zero, notifications | Use only when dropping history is intended. It is not model rollback |
| `dispose()` | Disposes commands in both histories | Does not perform `flush`'s save-location reset or event sequence |

A post event means an attempted operation reached that finally block, **not** that the operation succeeded. PRE listeners are called before entering that try/finally, and listener exceptions can interrupt the sequence. This is not a transaction manager: there is no automatic compensation for a command that partly mutates a model then throws. Handle validation and recovery in the application command design.

`Command` defaults are deliberately weak: `canExecute` and `canUndo` return true, execution/undo/disposal do nothing, `redo` calls `execute`, and `canRedo` delegates to `canExecute`. A real command must supply the semantics it needs, including captured old state and resource cleanup. `dispose` may occur while a command is executed, undone or redone; do not reuse a disposed command.

`CompoundCommand` requires a nonempty list with all children executable/undoable/redoable for the corresponding guard. Execute and redo follow insertion order; undo reverses it. Disposal visits children. `unwrap` yields the unexecutable singleton for zero children, the sole child for one, or the compound itself otherwise. No rollback-on-child-exception is implemented.

The listener collection uses copy-on-write storage, which supports mutation of listeners during notification. This does not make the whole multi-step command stack or an SWT model/viewer safe for concurrent use. See [CommandStackTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/CommandStackTest.java): its concurrent-modification tests concern listener modification during callbacks, not proof of parallel editing.

## GEF-05: an end-to-end example, including save state

Use [ShapesEditor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/ShapesEditor.java) as the concrete launch/editor example rather than inventing an unrelated skeleton:

- Its constructor supplies a `DefaultEditDomain`
- `configureGraphicalViewer` installs `ShapesEditPartFactory`, `ScalableFreeformRootEditPart`, a key handler and a context menu
- `initializeGraphicalViewer` sets the model contents and adds a drop listener
- `ShapeEditPart` translates model notifications back to the visual lifecycle described above
- `doSave` serializes the diagram to the selected workspace file and calls `markSaveLocation` only after `setContents` succeeds. The current example catches and prints `CoreException`/`IOException`; it is not a production error-reporting template

[ShapeSetConstraintCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ShapeSetConstraintCommand.java) rejects null constructor inputs, copies the new bounds, accepts move/resize request kinds, captures old bounds on first execution, uses new bounds on redo, and restores old bounds on undo. The retained request and model are application objects, not immutable value snapshots.

[ShapeCreateCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ShapeCreateCommand.java) checks null inputs in `canExecute`, sets location and a positive supplied size during execution, adds the shape on redo, and removes it on undo. Its constructor's Javadoc advertises `IllegalArgumentException` for null arguments, but the inspected constructor only assigns references. The observed null guard is `canExecute`; this discrepancy remains a documentation/API-contract follow-up, with no source behavior changed here. Its bounds reference is retained rather than defensively copied.

## GEF-06: Draw2D painting and teardown

[LightweightSystem.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/LightweightSystem.java) wires an SWT Canvas to a root figure, dispatcher and update manager. The normal use is Canvas creation, LightweightSystem creation with that Canvas, then `setContents` with the top-level figure. `setContents` removes previous contents from the root before adding the new figure. This method is not a null-clearing/disposal API.

`setControl` chooses native versus buffered graphics based on the Canvas double-buffering style, installs drawable text/flow utilities, attaches listeners, and sizes the root. Canvas disposal resets those text utilities and calls the update manager's disposal through the event handler. `paint(GC)` delegates to the update manager; the GC supplied by the SWT paint event is not transferred to application ownership by that call. Keep SWT control and explicitly allocated native-resource ownership separate from the Java figure tree.

## Source-linked review and tests

The source tests below were located/read as indicated. **None was executed in this documentation pass.**

- [CommandStackTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/CommandStackTest.java): event detail/order expectations and listeners removing themselves during notification; reviewed body
- [GEFTestSuite.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/GEFTestSuite.java): suite discovery/navigation; not a claim all constituent tests ran
- [ShapeEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/ShapeEditPart.java), [ShapesEditor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/ShapesEditor.java), [ShapeCreateCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ShapeCreateCommand.java), [ShapeSetConstraintCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ShapeSetConstraintCommand.java): example caller/callee bodies reviewed, not executed examples
- The Draw2D/GEF/Zest test POMs in [MODULES.md](MODULES.md) are the build authority. UI/SWTBot lanes and their native display requirements remain distinct from non-UI tests

Still open: exhaustive API/Javadoc review for other commands/policies/tools, renderer/resource subclasses, all Zest and SVG contracts, integration test execution, failure-path coverage for exceptions during stack notifications, and the source/Javadoc discrepancies called out above. No M3 semantic fixed point, compatibility proof or performance result is asserted.
