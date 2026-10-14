# Package declarations: gef

[All packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md) · [Coverage](../COVERAGE.md)

This is a navigation group, not an additional build module. Source revision and inventory rules are recorded in the package index. All entries remain included; a declaration witness is not whole-package semantic review.

## org.eclipse.gef

- Origin: [org.eclipse.gef/src/org/eclipse/gef](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef); owner `org.eclipse.gef`; production/build
- Declaration witness: [AccessibleAnchorProvider.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/AccessibleAnchorProvider.java); source files: `AccessibleAnchorProvider.java`, `AccessibleEditPart.java`, `AccessibleHandleProvider.java`, `AutoexposeHelper.java`, `CompoundSnapToHelper.java`, `ConnectionEditPart.java`, `ContextMenuProvider.java`, `DefaultEditDomain.java`, `Disposable.java`, `DragTracker.java`, `EditDomain.java`, `EditPart.java`, `EditPartFactory.java`, `EditPartListener.java`, `EditPartViewer.java`, `EditPolicy.java`, `ExposeHelper.java`, `GEF.java`, `GEFColorProvider.java`, `GEFPlugin.java`, `GraphicalEditPart.java`, `GraphicalViewer.java`, `Handle.java`, `KeyHandler.java`, `KeyStroke.java`, `LayerConstants.java`, `MouseWheelHandler.java`, `MouseWheelHelper.java`, `MouseWheelZoomHandler.java`, `NodeEditPart.java`, `NodeListener.java`, `Request.java`, `RequestConstants.java`, `RootEditPart.java`, `SelectionManager.java`, `SharedCursors.java`, `SharedImages.java`, `SharedMessages.java`, `SnapToGeometry.java`, `SnapToGrid.java`, `SnapToGuides.java`, `SnapToHelper.java`, `ThemeConstants.java`, `Tool.java`, `TreeEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.handles`](gef.md#orgeclipsegefhandles), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.commands

- Origin: [org.eclipse.gef/src/org/eclipse/gef/commands](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/commands); owner `org.eclipse.gef`; production/build
- Declaration witness: [Command.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/commands/Command.java); source files: `Command.java`, `CommandStack.java`, `CommandStackEvent.java`, `CommandStackEventListener.java`, `CommandStackListener.java`, `CompoundCommand.java`, `ForwardUndoCompoundCommand.java`, `UnexecutableCommand.java`
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.commands`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/commands/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/commands/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.dnd

- Origin: [org.eclipse.gef/src/org/eclipse/gef/dnd](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/dnd); owner `org.eclipse.gef`; production/build
- Declaration witness: [AbstractTransferDragSourceListener.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/dnd/AbstractTransferDragSourceListener.java); source files: `AbstractTransferDragSourceListener.java`, `AbstractTransferDropTargetListener.java`, `DelegatingDragAdapter.java`, `DelegatingDropAdapter.java`, `SimpleObjectTransfer.java`, `TemplateTransfer.java`, `TemplateTransferDragSourceListener.java`, `TemplateTransferDropTargetListener.java`, `TransferDragSourceListener.java`, `TransferDropTargetListener.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.dnd`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.editparts

- Origin: [org.eclipse.gef/src/org/eclipse/gef/editparts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editparts); owner `org.eclipse.gef`; production/build
- Declaration witness: [AbstractConnectionEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editparts/AbstractConnectionEditPart.java); source files: `AbstractConnectionEditPart.java`, `AbstractEditPart.java`, `AbstractGraphicalEditPart.java`, `AbstractTreeEditPart.java`, `FreeformGraphicalRootEditPart.java`, `GraphicalRootEditPart.java`, `GridLayer.java`, `GuideLayer.java`, `HierarchicalGridLayer.java`, `IScrollableEditPart.java`, `LayerManager.java`, `RootTreeEditPart.java`, `ScalableFreeformRootEditPart.java`, `ScalableRootEditPart.java`, `SimpleRootEditPart.java`, `ViewportAutoexposeHelper.java`, `ViewportExposeHelper.java`, `ViewportHelper.java`, `ViewportMouseWheelHelper.java`, `ZoomManager.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.zoom`](draw2d.md#orgeclipsedraw2dzoom), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.editparts`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/editparts/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editparts/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.editpolicies

- Origin: [org.eclipse.gef/src/org/eclipse/gef/editpolicies](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editpolicies); owner `org.eclipse.gef`; production/build
- Declaration witness: [AbstractEditPolicy.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editpolicies/AbstractEditPolicy.java); source files: `AbstractEditPolicy.java`, `AbstractTreeContainerEditPolicy.java`, `BendpointEditPolicy.java`, `ComponentEditPolicy.java`, `ConnectionEditPolicy.java`, `ConnectionEndpointEditPolicy.java`, `ConstrainedLayoutEditPolicy.java`, `ContainerEditPolicy.java`, `DirectEditPolicy.java`, `FeedbackHelper.java`, `FlowLayoutEditPolicy.java`, `GraphicalEditPolicy.java`, `GraphicalNodeEditPolicy.java`, `LayoutEditPolicy.java`, `NonResizableEditPolicy.java`, `OrderedLayoutEditPolicy.java`, `ResizableEditPolicy.java`, `RootComponentEditPolicy.java`, `ScrollableSelectionFeedbackEditPolicy.java`, `SelectionEditPolicy.java`, `SelectionHandlesEditPolicy.java`, `SnapFeedbackPolicy.java`, `StrokePointList.java`, `TreeContainerEditPolicy.java`, `XYLayoutEditPolicy.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.colors`](draw2d.md#orgeclipsedraw2dcolors), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.handles`](gef.md#orgeclipsegefhandles), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.util`](gef.md#orgeclipsegefutil)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.editpolicies`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/editpolicies/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/editpolicies/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples

- Origin: [org.eclipse.gef.examples/src/org/eclipse/gef/examples](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples/src/org/eclipse/gef/examples); owner `org.eclipse.gef.examples`; example
- Declaration witness: [Messages.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples/src/org/eclipse/gef/examples/Messages.java); source files: `Messages.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.editor

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/editor](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/editor); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1GraphicalEditor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/editor/Digraph1GraphicalEditor.java); source files: `Digraph1GraphicalEditor.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.digraph1.factory`](gef.md#orgeclipsegefexamplesdigraph1factory), [`org.eclipse.gef.examples.digraph1.model`](gef.md#orgeclipsegefexamplesdigraph1model), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: `org.eclipse.gef.examples.digraph1`: `org.eclipse.gef.examples.digraph1.editor;x-friends:="org.eclipse.gef.examples.digraph2"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.editpart

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/editpart](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/editpart); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1GraphEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/editpart/Digraph1GraphEditPart.java); source files: `Digraph1GraphEditPart.java`, `Digraph1NodeEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.digraph1.figure`](gef.md#orgeclipsegefexamplesdigraph1figure), [`org.eclipse.gef.examples.digraph1.model`](gef.md#orgeclipsegefexamplesdigraph1model), [`org.eclipse.gef.examples.digraph1.policy`](gef.md#orgeclipsegefexamplesdigraph1policy)
- Export accounting: `org.eclipse.gef.examples.digraph1`: `org.eclipse.gef.examples.digraph1.editpart;x-friends:="org.eclipse.gef.examples.digraph2"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.factory

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/factory](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/factory); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1EditPartFactory.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/factory/Digraph1EditPartFactory.java); source files: `Digraph1EditPartFactory.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.digraph1.editpart`](gef.md#orgeclipsegefexamplesdigraph1editpart), [`org.eclipse.gef.examples.digraph1.model`](gef.md#orgeclipsegefexamplesdigraph1model)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.figure

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/figure](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/figure); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1NodeFigure.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/figure/Digraph1NodeFigure.java); source files: `Digraph1NodeFigure.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.gef.examples.digraph1`: `org.eclipse.gef.examples.digraph1.figure;x-friends:="org.eclipse.gef.examples.digraph2"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.model

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/model](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/model); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1Graph.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/model/Digraph1Graph.java); source files: `Digraph1Graph.java`, `Digraph1Node.java`
- Export accounting: `org.eclipse.gef.examples.digraph1`: `org.eclipse.gef.examples.digraph1.model;x-friends:="org.eclipse.gef.examples.digraph2"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.policy

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/policy](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/policy); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1XYLayoutEditPolicy.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/policy/Digraph1XYLayoutEditPolicy.java); source files: `Digraph1XYLayoutEditPolicy.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: `org.eclipse.gef.examples.digraph1`: `org.eclipse.gef.examples.digraph1.policy;x-friends:="org.eclipse.gef.examples.digraph2"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.rcp

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/rcp](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/rcp); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1Application.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/rcp/Digraph1Application.java); source files: `Digraph1Application.java`, `Digraph1Perspective.java`, `Digraph1WorkbenchAdvisor.java`, `Digraph1WorkbenchWindowAdvisor.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph1.view

- Origin: [org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/view](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/view); owner `org.eclipse.gef.examples.digraph1`; example
- Declaration witness: [Digraph1GraphicalView.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph1/src/org/eclipse/gef/examples/digraph1/view/Digraph1GraphicalView.java); source files: `Digraph1GraphicalView.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.digraph1.factory`](gef.md#orgeclipsegefexamplesdigraph1factory), [`org.eclipse.gef.examples.digraph1.model`](gef.md#orgeclipsegefexamplesdigraph1model), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph2.editor

- Origin: [org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/editor](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/editor); owner `org.eclipse.gef.examples.digraph2`; example
- Declaration witness: [Digraph2GraphicalEditor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/editor/Digraph2GraphicalEditor.java); source files: `Digraph2GraphicalEditor.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.digraph1.editor`](gef.md#orgeclipsegefexamplesdigraph1editor), [`org.eclipse.gef.examples.digraph2.factory`](gef.md#orgeclipsegefexamplesdigraph2factory), [`org.eclipse.gef.examples.digraph2.model`](gef.md#orgeclipsegefexamplesdigraph2model)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph2.editpart

- Origin: [org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/editpart](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/editpart); owner `org.eclipse.gef.examples.digraph2`; example
- Declaration witness: [Digraph2EdgeEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/editpart/Digraph2EdgeEditPart.java); source files: `Digraph2EdgeEditPart.java`, `Digraph2GraphEditPart.java`, `Digraph2NodeEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.examples.digraph1.editpart`](gef.md#orgeclipsegefexamplesdigraph1editpart), [`org.eclipse.gef.examples.digraph2.figure`](gef.md#orgeclipsegefexamplesdigraph2figure), [`org.eclipse.gef.examples.digraph2.model`](gef.md#orgeclipsegefexamplesdigraph2model), [`org.eclipse.gef.examples.digraph2.policy`](gef.md#orgeclipsegefexamplesdigraph2policy)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph2.factory

- Origin: [org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/factory](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/factory); owner `org.eclipse.gef.examples.digraph2`; example
- Declaration witness: [Digraph2EditPartFactory.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/factory/Digraph2EditPartFactory.java); source files: `Digraph2EditPartFactory.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.digraph2.editpart`](gef.md#orgeclipsegefexamplesdigraph2editpart), [`org.eclipse.gef.examples.digraph2.model`](gef.md#orgeclipsegefexamplesdigraph2model)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph2.figure

- Origin: [org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/figure](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/figure); owner `org.eclipse.gef.examples.digraph2`; example
- Declaration witness: [Digraph2ConnectionAnchor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/figure/Digraph2ConnectionAnchor.java); source files: `Digraph2ConnectionAnchor.java`, `Digraph2NodeFigure.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef.examples.digraph1.figure`](gef.md#orgeclipsegefexamplesdigraph1figure)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph2.model

- Origin: [org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/model](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/model); owner `org.eclipse.gef.examples.digraph2`; example
- Declaration witness: [Digraph2Edge.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/model/Digraph2Edge.java); source files: `Digraph2Edge.java`, `Digraph2Graph.java`, `Digraph2Node.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.examples.digraph1.model`](gef.md#orgeclipsegefexamplesdigraph1model)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.digraph2.policy

- Origin: [org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/policy](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/policy); owner `org.eclipse.gef.examples.digraph2`; example
- Declaration witness: [Digraph2ConnectionEditPolicy.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.digraph2/src/org/eclipse/gef/examples/digraph2/policy/Digraph2ConnectionEditPolicy.java); source files: `Digraph2ConnectionEditPolicy.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [FlowEditorPaletteFactory.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/FlowEditorPaletteFactory.java); source files: `FlowEditorPaletteFactory.java`, `FlowImages.java`, `FlowPlugin.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef.examples.flow.model`](gef.md#orgeclipsegefexamplesflowmodel), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.actions

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/actions](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/actions); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [FlowActionBarContributor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/actions/FlowActionBarContributor.java); source files: `FlowActionBarContributor.java`, `FlowContextMenuProvider.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.figures

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/figures](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/figures); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [EndTag.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/figures/EndTag.java); source files: `EndTag.java`, `ParallelActivityFigure.java`, `SequentialActivityFigure.java`, `SimpleActivityLabel.java`, `StartTag.java`, `SubgraphFigure.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef.examples.flow`](gef.md#orgeclipsegefexamplesflow), [`org.eclipse.gef.examples.flow.parts`](gef.md#orgeclipsegefexamplesflowparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.model

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/model](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/model); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [Activity.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/model/Activity.java); source files: `Activity.java`, `ActivityDiagram.java`, `FlowElement.java`, `ParallelActivity.java`, `SequentialActivity.java`, `StructuredActivity.java`, `Transition.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.model.commands

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/model/commands](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/model/commands); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [AddAndAssignSourceCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/model/commands/AddAndAssignSourceCommand.java); source files: `AddAndAssignSourceCommand.java`, `AddCommand.java`, `ConnectionCreateCommand.java`, `CreateAndAssignSourceCommand.java`, `CreateCommand.java`, `DeleteCommand.java`, `DeleteConnectionCommand.java`, `OrphanChildCommand.java`, `ReconnectSourceCommand.java`, `ReconnectTargetCommand.java`, `RenameActivityCommand.java`, `SplitTransitionCommand.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.examples.flow.model`](gef.md#orgeclipsegefexamplesflowmodel)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.parts

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/parts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/parts); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [ActivityCellEditorLocator.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/parts/ActivityCellEditorLocator.java); source files: `ActivityCellEditorLocator.java`, `ActivityDiagramPart.java`, `ActivityDirectEditManager.java`, `ActivityPart.java`, `ActivityPartFactory.java`, `BottomAnchor.java`, `DummyLayout.java`, `GraphLayoutManager.java`, `LayoutAnimator.java`, `ParallelActivityPart.java`, `RoutingAnimator.java`, `SequentialActivityPart.java`, `SimpleActivityPart.java`, `StructuredActivityPart.java`, `TopAnchor.java`, `TransitionPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.examples.flow`](gef.md#orgeclipsegefexamplesflow), [`org.eclipse.gef.examples.flow.figures`](gef.md#orgeclipsegefexamplesflowfigures), [`org.eclipse.gef.examples.flow.model`](gef.md#orgeclipsegefexamplesflowmodel), [`org.eclipse.gef.examples.flow.policies`](gef.md#orgeclipsegefexamplesflowpolicies), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.policies

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/policies](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/policies); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [ActivityContainerEditPolicy.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/policies/ActivityContainerEditPolicy.java); source files: `ActivityContainerEditPolicy.java`, `ActivityContainerHighlightEditPolicy.java`, `ActivityDirectEditPolicy.java`, `ActivityEditPolicy.java`, `ActivityNodeEditPolicy.java`, `ActivitySourceEditPolicy.java`, `SimpleActivitySelectionEditPolicy.java`, `StructuredActivityDirectEditPolicy.java`, `StructuredActivityLayoutEditPolicy.java`, `TransitionEditPolicy.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.examples.flow.figures`](gef.md#orgeclipsegefexamplesflowfigures), [`org.eclipse.gef.examples.flow.model`](gef.md#orgeclipsegefexamplesflowmodel), [`org.eclipse.gef.examples.flow.model.commands`](gef.md#orgeclipsegefexamplesflowmodelcommands), [`org.eclipse.gef.examples.flow.parts`](gef.md#orgeclipsegefexamplesflowparts), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.flow.ui

- Origin: [org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/ui](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/ui); owner `org.eclipse.gef.examples.flow`; example
- Declaration witness: [FlowCreationWizard.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.flow/src/org/eclipse/gef/examples/flow/ui/FlowCreationWizard.java); source files: `FlowCreationWizard.java`, `FlowEditor.java`, `FlowWizardPage1.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.flow`](gef.md#orgeclipsegefexamplesflow), [`org.eclipse.gef.examples.flow.actions`](gef.md#orgeclipsegefexamplesflowactions), [`org.eclipse.gef.examples.flow.model`](gef.md#orgeclipsegefexamplesflowmodel), [`org.eclipse.gef.examples.flow.parts`](gef.md#orgeclipsegefexamplesflowparts), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [LogicContextMenuProvider.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/LogicContextMenuProvider.java); source files: `LogicContextMenuProvider.java`, `LogicCreationWizard.java`, `LogicEditor.java`, `LogicMessages.java`, `LogicPlugin.java`, `LogicWizardPage1.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.parts`](draw2d.md#orgeclipsedraw2dparts), [`org.eclipse.draw2d.zoom`](draw2d.md#orgeclipsedraw2dzoom), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.logicdesigner.actions`](gef.md#orgeclipsegefexampleslogicdesigneractions), [`org.eclipse.gef.examples.logicdesigner.dnd`](gef.md#orgeclipsegefexampleslogicdesignerdnd), [`org.eclipse.gef.examples.logicdesigner.edit`](gef.md#orgeclipsegefexampleslogicdesigneredit), [`org.eclipse.gef.examples.logicdesigner.figures`](gef.md#orgeclipsegefexampleslogicdesignerfigures), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.examples.logicdesigner.palette`](gef.md#orgeclipsegefexampleslogicdesignerpalette), [`org.eclipse.gef.examples.logicdesigner.rulers`](gef.md#orgeclipsegefexampleslogicdesignerrulers), [`org.eclipse.gef.examples.logicdesigner.tools`](gef.md#orgeclipsegefexampleslogicdesignertools), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts), [`org.eclipse.gef.ui.rulers`](gef.md#orgeclipsegefuirulers)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.actions

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/actions](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/actions); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [DecrementRetargetAction.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/actions/DecrementRetargetAction.java); source files: `DecrementRetargetAction.java`, `IncrementDecrementAction.java`, `IncrementRetargetAction.java`, `LogicActionBarContributor.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.logicdesigner`](gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.actions`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.dnd

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/dnd](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/dnd); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [FileTransferDragSourceListener.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/dnd/FileTransferDragSourceListener.java); source files: `FileTransferDragSourceListener.java`, `TextTransferDropTargetListener.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.examples.logicdesigner.edit`](gef.md#orgeclipsegefexampleslogicdesigneredit)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.dnd`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.edit

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/edit](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/edit); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [CircuitEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/edit/CircuitEditPart.java); source files: `CircuitEditPart.java`, `ContainerHighlightEditPolicy.java`, `GateEditPart.java`, `GraphicalPartFactory.java`, `LEDEditPart.java`, `LEDEditPolicy.java`, `LabelCellEditorLocator.java`, `LabelDirectEditPolicy.java`, `LogicContainerEditPart.java`, `LogicContainerEditPolicy.java`, `LogicContainerTreeEditPart.java`, `LogicDiagramEditPart.java`, `LogicEditPart.java`, `LogicElementEditPolicy.java`, `LogicElementMoveHandle.java`, `LogicFlowContainerEditPart.java`, `LogicFlowEditPolicy.java`, `LogicLabelEditManager.java`, `LogicLabelEditPart.java`, `LogicLabelEditPolicy.java`, `LogicNodeEditPolicy.java`, `LogicResizableEditPolicy.java`, `LogicTreeContainerEditPolicy.java`, `LogicTreeEditPart.java`, `LogicTreeEditPolicy.java`, `LogicXYLayoutEditPolicy.java`, `NativeDropRequest.java`, `OutputEditPart.java`, `TreePartFactory.java`, `WireBendpointEditPolicy.java`, `WireEditPart.java`, `WireEditPolicy.java`, `WireEndpointEditPolicy.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.zoom`](draw2d.md#orgeclipsedraw2dzoom), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.examples.logicdesigner`](gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.examples.logicdesigner.figures`](gef.md#orgeclipsegefexampleslogicdesignerfigures), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.examples.logicdesigner.model.commands`](gef.md#orgeclipsegefexampleslogicdesignermodelcommands), [`org.eclipse.gef.examples.logicdesigner.tools`](gef.md#orgeclipsegefexampleslogicdesignertools), [`org.eclipse.gef.handles`](gef.md#orgeclipsegefhandles), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.edit`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.figures

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/figures](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/figures); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [AndGateFeedbackFigure.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/figures/AndGateFeedbackFigure.java); source files: `AndGateFeedbackFigure.java`, `AndGateFigure.java`, `BentCornerFigure.java`, `CircuitBorder.java`, `CircuitFeedbackBorder.java`, `CircuitFeedbackFigure.java`, `CircuitFigure.java`, `FigureFactory.java`, `FixedConnectionAnchor.java`, `GateFigure.java`, `GroundFeedbackFigure.java`, `GroundFigure.java`, `LEDFeedbackFigure.java`, `LEDFigure.java`, `LabelFeedbackFigure.java`, `LabelFigure.java`, `LiveOutputFeedbackFigure.java`, `LiveOutputFigure.java`, `LogicEditorColors.java`, `LogicFlowBorder.java`, `LogicFlowFeedbackBorder.java`, `LogicFlowFeedbackFigure.java`, `NodeFigure.java`, `OrGateFeedbackFigure.java`, `OrGateFigure.java`, `OutputFigure.java`, `XOrGateFeedbackFigure.java`, `XOrGateFigure.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.handles`](gef.md#orgeclipsegefhandles)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.figures`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.handlers

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/handlers](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/handlers); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [LogicPrintHandler.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/handlers/LogicPrintHandler.java); source files: `LogicPrintHandler.java`, `PrintModeDialog.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.logicdesigner`](gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.examples.logicdesigner.edit`](gef.md#orgeclipsegefexampleslogicdesigneredit), [`org.eclipse.gef.print`](gef.md#orgeclipsegefprint), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.model

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/model](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/model); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [AndGate.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/model/AndGate.java); source files: `AndGate.java`, `Circuit.java`, `DimensionPropertySource.java`, `Gate.java`, `GroundOutput.java`, `LED.java`, `LiveOutput.java`, `LocationPropertySource.java`, `LogicDiagram.java`, `LogicDiagramFactory.java`, `LogicElement.java`, `LogicFlowContainer.java`, `LogicGuide.java`, `LogicLabel.java`, `LogicNumberCellEditorValidator.java`, `LogicRuler.java`, `LogicSubpart.java`, `OrGate.java`, `SimpleOutput.java`, `Wire.java`, `WireBendpoint.java`, `XORGate.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.logicdesigner`](gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.model`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.model.commands

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/model/commands](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/model/commands); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [AddCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/model/commands/AddCommand.java); source files: `AddCommand.java`, `BendpointCommand.java`, `ChangeGuideCommand.java`, `CloneCommand.java`, `ConnectionCommand.java`, `CreateBendpointCommand.java`, `CreateCommand.java`, `CreateGuideCommand.java`, `DeleteBendpointCommand.java`, `DeleteCommand.java`, `DeleteGuideCommand.java`, `LogicLabelCommand.java`, `MoveBendpointCommand.java`, `MoveGuideCommand.java`, `OrphanChildCommand.java`, `ReorderPartCommand.java`, `SetConstraintCommand.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.examples.logicdesigner`](gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.model.commands`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.palette

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/palette](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/palette); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [LogicPaletteCustomizer.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/palette/LogicPaletteCustomizer.java); source files: `LogicPaletteCustomizer.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.palette`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.rulers

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/rulers](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/rulers); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [LogicRulerProvider.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/rulers/LogicRulerProvider.java); source files: `LogicRulerProvider.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.examples.logicdesigner.model.commands`](gef.md#orgeclipsegefexampleslogicdesignermodelcommands), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers)
- Export accounting: `org.eclipse.gef.examples.logic`: `org.eclipse.gef.examples.logicdesigner.rulers`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.logicdesigner.tools

- Origin: [org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/tools](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/tools); owner `org.eclipse.gef.examples.logic`; example
- Declaration witness: [LogicCreationTool.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.logic/src/org/eclipse/gef/examples/logicdesigner/tools/LogicCreationTool.java); source files: `LogicCreationTool.java`, `LogicResizeTracker.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.logicdesigner`](gef.md#orgeclipsegefexampleslogicdesigner), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.palette

- Origin: [org.eclipse.gef.examples/src/org/eclipse/gef/examples/palette](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples/src/org/eclipse/gef/examples/palette); owner `org.eclipse.gef.examples`; example
- Declaration witness: [PaletteSnippet1.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples/src/org/eclipse/gef/examples/palette/PaletteSnippet1.java); source files: `PaletteSnippet1.java`, `PaletteSnippet2.java`, `PaletteSnippet3.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.backgrounds`](draw2d.md#orgeclipsedraw2dbackgrounds), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples`](gef.md#orgeclipsegefexamples), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.customize`](gef.md#orgeclipsegefuipalettecustomize), [`org.eclipse.gef.ui.palette.editparts`](gef.md#orgeclipsegefuipaletteeditparts), [`org.eclipse.gef.util`](gef.md#orgeclipsegefutil)
- Export accounting: `org.eclipse.gef.examples`: `org.eclipse.gef.examples.palette;x-friends:="org.eclipse.gef.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.shapes

- Origin: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes); owner `org.eclipse.gef.examples.shapes`; example
- Declaration witness: [Messages.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/Messages.java); source files: `Messages.java`, `ShapesCreationWizard.java`, `ShapesEditor.java`, `ShapesEditorActionBarContributor.java`, `ShapesEditorContextMenuProvider.java`, `ShapesEditorPaletteFactory.java`, `ShapesExampleMessages.java`, `ShapesPlugin.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.shapes.model`](gef.md#orgeclipsegefexamplesshapesmodel), [`org.eclipse.gef.examples.shapes.parts`](gef.md#orgeclipsegefexamplesshapesparts), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Existing package prose: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.shapes.handler

- Origin: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/handler](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/handler); owner `org.eclipse.gef.examples.shapes`; example
- Declaration witness: [ColorProviderExpression.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/handler/ColorProviderExpression.java); source files: `ColorProviderExpression.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.examples.shapes`](gef.md#orgeclipsegefexamplesshapes)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.shapes.model

- Origin: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model); owner `org.eclipse.gef.examples.shapes`; example
- Declaration witness: [Connection.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/Connection.java); source files: `Connection.java`, `EllipticalShape.java`, `ModelElement.java`, `RectangularShape.java`, `Shape.java`, `ShapesDiagram.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef.examples.shapes`](gef.md#orgeclipsegefexamplesshapes)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Existing package prose: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.shapes.model.commands

- Origin: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands); owner `org.eclipse.gef.examples.shapes`; example
- Declaration witness: [ConnectionCreateCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/ConnectionCreateCommand.java); source files: `ConnectionCreateCommand.java`, `ConnectionDeleteCommand.java`, `ConnectionReconnectCommand.java`, `ShapeCreateCommand.java`, `ShapeDeleteCommand.java`, `ShapeSetConstraintCommand.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.examples.shapes`](gef.md#orgeclipsegefexamplesshapes), [`org.eclipse.gef.examples.shapes.model`](gef.md#orgeclipsegefexamplesshapesmodel), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Existing package prose: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/model/commands/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.shapes.parts

- Origin: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts); owner `org.eclipse.gef.examples.shapes`; example
- Declaration witness: [ConnectionEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/ConnectionEditPart.java); source files: `ConnectionEditPart.java`, `DiagramEditPart.java`, `DiagramTreeEditPart.java`, `ShapeComponentEditPolicy.java`, `ShapeEditPart.java`, `ShapeTreeEditPart.java`, `ShapesEditPartFactory.java`, `ShapesTreeEditPartFactory.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.examples.shapes.model`](gef.md#orgeclipsegefexamplesshapesmodel), [`org.eclipse.gef.examples.shapes.model.commands`](gef.md#orgeclipsegefexamplesshapesmodelcommands), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Existing package prose: [org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.shapes/src/org/eclipse/gef/examples/shapes/parts/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [AppendableCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/AppendableCommand.java); source files: `AppendableCommand.java`, `GraphicalTextViewer.java`, `SelectionModel.java`, `SelectionRange.java`, `SelectionRangeRequest.java`, `TextCommand.java`, `TextEditor.java`, `TextLocation.java`, `TextUtilities.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.examples.text.actions`](gef.md#orgeclipsegefexamplestextactions), [`org.eclipse.gef.examples.text.edit`](gef.md#orgeclipsegefexamplestextedit), [`org.eclipse.gef.examples.text.model`](gef.md#orgeclipsegefexamplestextmodel), [`org.eclipse.gef.examples.text.tools`](gef.md#orgeclipsegefexamplestexttools), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.actions

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/actions](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/actions); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [BooleanStyleAction.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/actions/BooleanStyleAction.java); source files: `BooleanStyleAction.java`, `ChangeFontContributionItem.java`, `MultiStyleAction.java`, `ResizeFontContributionItem.java`, `StyleComboContributionItem.java`, `StyleListener.java`, `StyleProvider.java`, `StyleRetargetAction.java`, `StyleService.java`, `TextActionBarContributor.java`, `TextActionConstants.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.examples.text`](gef.md#orgeclipsegefexamplestext), [`org.eclipse.gef.examples.text.model`](gef.md#orgeclipsegefexamplestextmodel), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.actions`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.edit

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/edit](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/edit); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [AbstractTextPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/edit/AbstractTextPart.java); source files: `AbstractTextPart.java`, `BlockEditPolicy.java`, `BlockTextPart.java`, `CompoundTextPart.java`, `ContainerTreePart.java`, `DocumentPart.java`, `ExampleTreePart.java`, `FontCache.java`, `ImportPart.java`, `ImportsPart.java`, `InlineTextPart.java`, `TextEditPart.java`, `TextFlowPart.java`, `TextRunTreePart.java`, `TextStyleManager.java`, `TextualEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.examples.text`](gef.md#orgeclipsegefexamplestext), [`org.eclipse.gef.examples.text.actions`](gef.md#orgeclipsegefexamplestextactions), [`org.eclipse.gef.examples.text.figures`](gef.md#orgeclipsegefexamplestextfigures), [`org.eclipse.gef.examples.text.model`](gef.md#orgeclipsegefexamplestextmodel), [`org.eclipse.gef.examples.text.model.commands`](gef.md#orgeclipsegefexamplestextmodelcommands), [`org.eclipse.gef.examples.text.requests`](gef.md#orgeclipsegefexamplestextrequests), [`org.eclipse.gef.examples.text.tools`](gef.md#orgeclipsegefexamplestexttools)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.edit`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.figures

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/figures](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/figures); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [BulletBorder.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/figures/BulletBorder.java); source files: `BulletBorder.java`, `CommentPage.java`, `Images.java`, `ListItemBorder.java`, `StyleConstants.java`, `TreeBorder.java`, `TreeItemBorder.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.figures`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.model

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/model](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/model); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [Block.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/model/Block.java); source files: `Block.java`, `CanvasStyle.java`, `Container.java`, `InlineContainer.java`, `ModelElement.java`, `ModelLocation.java`, `ModelUtil.java`, `Notifier.java`, `Style.java`, `TestModelUtil.java`, `TextRun.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.model`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.model.commands

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/model/commands](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/model/commands); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [ApplyBooleanStyle.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/model/commands/ApplyBooleanStyle.java); source files: `ApplyBooleanStyle.java`, `ApplyMultiStyle.java`, `ChangeString.java`, `CompoundEditCommand.java`, `CompoundTextCommand.java`, `ConvertElementCommand.java`, `ExampleTextCommand.java`, `InsertModelElement.java`, `MergeWithPrevious.java`, `MiniEdit.java`, `NestElementCommand.java`, `ProcessMacroCommand.java`, `PromoteElementCommand.java`, `RemoveRange.java`, `RemoveText.java`, `SingleEditCommand.java`, `SubdivideElement.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.examples.text`](gef.md#orgeclipsegefexamplestext), [`org.eclipse.gef.examples.text.edit`](gef.md#orgeclipsegefexamplestextedit), [`org.eclipse.gef.examples.text.model`](gef.md#orgeclipsegefexamplestextmodel)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.model.commands`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.requests

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/requests](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/requests); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [CaretRequest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/requests/CaretRequest.java); source files: `CaretRequest.java`, `SearchResult.java`, `TextRequest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.text`](gef.md#orgeclipsegefexamplestext), [`org.eclipse.gef.examples.text.edit`](gef.md#orgeclipsegefexamplestextedit), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.requests`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.tools

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/tools](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/tools); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [SelectionRangeDragTracker.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/tools/SelectionRangeDragTracker.java); source files: `SelectionRangeDragTracker.java`, `TextTool.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.examples.text`](gef.md#orgeclipsegefexamplestext), [`org.eclipse.gef.examples.text.actions`](gef.md#orgeclipsegefexamplestextactions), [`org.eclipse.gef.examples.text.edit`](gef.md#orgeclipsegefexamplestextedit), [`org.eclipse.gef.examples.text.requests`](gef.md#orgeclipsegefexamplestextrequests), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools)
- Export accounting: `org.eclipse.gef.examples.text`: `org.eclipse.gef.examples.text.tools`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.text.wizard

- Origin: [org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/wizard](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/wizard); owner `org.eclipse.gef.examples.text`; example
- Declaration witness: [NewFileWizardPage.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.text/src/org/eclipse/gef/examples/text/wizard/NewFileWizardPage.java); source files: `NewFileWizardPage.java`, `TextEditorWizard.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.ui.pde.internal

- Origin: [org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal); owner `org.eclipse.gef.examples.ui.pde`; example
- Declaration witness: [GefExamplesPlugin.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/GefExamplesPlugin.java); source files: `GefExamplesPlugin.java`
- Export accounting: `org.eclipse.gef.examples.ui.pde`: `org.eclipse.gef.examples.ui.pde.internal;x-internal:=true`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.ui.pde.internal.l10n

- Origin: [org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/l10n](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/l10n); owner `org.eclipse.gef.examples.ui.pde`; example
- Declaration witness: [Messages.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/l10n/Messages.java); source files: `Messages.java`
- Export accounting: `org.eclipse.gef.examples.ui.pde`: `org.eclipse.gef.examples.ui.pde.internal.l10n;x-internal:=true`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.examples.ui.pde.internal.wizards

- Origin: [org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/wizards](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/wizards); owner `org.eclipse.gef.examples.ui.pde`; example
- Declaration witness: [FlowExampleNewWizard.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.examples.ui.pde/src/org/eclipse/gef/examples/ui/pde/internal/wizards/FlowExampleNewWizard.java); source files: `FlowExampleNewWizard.java`, `LogicExampleNewWizard.java`, `ProjectUnzipperNewWizard.java`, `ShapesExampleNewWizard.java`, `TextExampleNewWizard.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.examples.ui.pde.internal`](gef.md#orgeclipsegefexamplesuipdeinternal), [`org.eclipse.gef.examples.ui.pde.internal.l10n`](gef.md#orgeclipsegefexamplesuipdeinternall10n)
- Export accounting: `org.eclipse.gef.examples.ui.pde`: `org.eclipse.gef.examples.ui.pde.internal.wizards;x-internal:=true`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.handles

- Origin: [org.eclipse.gef/src/org/eclipse/gef/handles](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/handles); owner `org.eclipse.gef`; production/build
- Declaration witness: [AbstractHandle.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/handles/AbstractHandle.java); source files: `AbstractHandle.java`, `BendpointCreationHandle.java`, `BendpointHandle.java`, `BendpointMoveHandle.java`, `ConnectionEndHandle.java`, `ConnectionEndpointHandle.java`, `ConnectionHandle.java`, `ConnectionStartHandle.java`, `CornerTriangleBorder.java`, `HandleBounds.java`, `MoveHandle.java`, `MoveHandleLocator.java`, `NonResizableHandle.java`, `NonResizableHandleKit.java`, `RelativeHandleLocator.java`, `ResizableHandleKit.java`, `ResizeHandle.java`, `SquareHandle.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.handles`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/handles/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/handles/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.internal

- Origin: [org.eclipse.gef/src/org/eclipse/gef/internal](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal); owner `org.eclipse.gef`; internal production
- Declaration witness: [ColorProviderService.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ColorProviderService.java); source files: `ColorProviderService.java`, `GEFMessages.java`, `Internal.java`, `InternalCursor.java`, `InternalGEFPlugin.java`, `InternalImages.java`, `Logger.java`, `LoggerContext.java`, `PropertySourceAdapterFactory.java`, `SimpleAutoscaledRootEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts), [`org.eclipse.gef.util`](gef.md#orgeclipsegefutil)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.internal;x-friends:="org.eclipse.gef.examples.text,org.eclipse.gef.examples.logic"`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/internal/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.internal.ui.palette

- Origin: [org.eclipse.gef/src/org/eclipse/gef/internal/ui/palette](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ui/palette); owner `org.eclipse.gef`; internal production
- Declaration witness: [InternalPaletteSettingsDialog.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ui/palette/InternalPaletteSettingsDialog.java); source files: `InternalPaletteSettingsDialog.java`, `PaletteSelectionTool.java`, `ToolbarDropdownContributionItem.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.customize`](gef.md#orgeclipsegefuipalettecustomize)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.internal.ui.palette;x-friends:="org.eclipse.gef.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.internal.ui.palette.editparts

- Origin: [org.eclipse.gef/src/org/eclipse/gef/internal/ui/palette/editparts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ui/palette/editparts); owner `org.eclipse.gef`; internal production
- Declaration witness: [ColumnsLayout.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ui/palette/editparts/ColumnsLayout.java); source files: `ColumnsLayout.java`, `DetailedLabelFigure.java`, `DrawerEditPart.java`, `DrawerFigure.java`, `EditPartTipHelper.java`, `GroupEditPart.java`, `IPaletteStackEditPart.java`, `OverlayScrollPaneLayout.java`, `PaletteContainerFlowLayout.java`, `PaletteScrollBar.java`, `PaletteStackEditPart.java`, `PinFigure.java`, `PinnablePaletteStackEditPart.java`, `PinnablePaletteStackFigure.java`, `SeparatorEditPart.java`, `SliderPaletteEditPart.java`, `TemplateEditPart.java`, `ToolEntryEditPart.java`, `ToolbarEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.backgrounds`](draw2d.md#orgeclipsedraw2dbackgrounds), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.editparts`](gef.md#orgeclipsegefuipaletteeditparts)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.internal.ui.palette.editparts;x-friends:="org.eclipse.gef.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.internal.ui.rulers

- Origin: [org.eclipse.gef/src/org/eclipse/gef/internal/ui/rulers](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ui/rulers); owner `org.eclipse.gef`; internal production
- Declaration witness: [DragGuidePolicy.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/internal/ui/rulers/DragGuidePolicy.java); source files: `DragGuidePolicy.java`, `GuideEditPart.java`, `GuideFigure.java`, `GuidePlaceHolder.java`, `RulerContextMenuProvider.java`, `RulerDragTracker.java`, `RulerEditPart.java`, `RulerEditPartFactory.java`, `RulerFigure.java`, `RulerLayout.java`, `RulerRootEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.zoom`](draw2d.md#orgeclipsedraw2dzoom), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.editpolicies`](gef.md#orgeclipsegefeditpolicies), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.internal.ui.rulers;x-friends:="org.eclipse.gef.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.palette

- Origin: [org.eclipse.gef/src/org/eclipse/gef/palette](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/palette); owner `org.eclipse.gef`; production/build
- Declaration witness: [CombinedTemplateCreationEntry.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/palette/CombinedTemplateCreationEntry.java); source files: `CombinedTemplateCreationEntry.java`, `ConnectionCreationToolEntry.java`, `CreationToolEntry.java`, `MarqueeToolEntry.java`, `PaletteContainer.java`, `PaletteDrawer.java`, `PaletteEntry.java`, `PaletteGroup.java`, `PaletteListener.java`, `PaletteRoot.java`, `PaletteSeparator.java`, `PaletteStack.java`, `PaletteTemplateEntry.java`, `PaletteToolbar.java`, `PanningSelectionToolEntry.java`, `SelectionToolEntry.java`, `ToolEntry.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.palette`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/palette/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/palette/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.print

- Origin: [org.eclipse.gef/src/org/eclipse/gef/print](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/print); owner `org.eclipse.gef`; production/build
- Declaration witness: [ImagePrintGraphicalViewerOperation.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/print/ImagePrintGraphicalViewerOperation.java); source files: `ImagePrintGraphicalViewerOperation.java`, `PrintGraphicalViewerOperation.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.print`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.requests

- Origin: [org.eclipse.gef/src/org/eclipse/gef/requests](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/requests); owner `org.eclipse.gef`; production/build
- Declaration witness: [AlignmentRequest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/requests/AlignmentRequest.java); source files: `AlignmentRequest.java`, `BendpointRequest.java`, `ChangeBoundsRequest.java`, `CreateConnectionRequest.java`, `CreateRequest.java`, `CreationFactory.java`, `DirectEditRequest.java`, `DropRequest.java`, `ForwardedRequest.java`, `GroupRequest.java`, `LocationRequest.java`, `ReconnectRequest.java`, `SelectionRequest.java`, `SimpleFactory.java`, `TargetRequest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.requests`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/requests/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/requests/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.rulers

- Origin: [org.eclipse.gef/src/org/eclipse/gef/rulers](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/rulers); owner `org.eclipse.gef`; production/build
- Declaration witness: [RulerChangeListener.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/rulers/RulerChangeListener.java); source files: `RulerChangeListener.java`, `RulerProvider.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.rulers`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.test

- Origin: [org.eclipse.gef.tests/src/org/eclipse/gef/test](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test); owner `org.eclipse.gef.tests`; test
- Declaration witness: [CommandStackTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/CommandStackTest.java); source files: `CommandStackTest.java`, `DirectEditManagerTest.java`, `DragEditPartsTrackerTest.java`, `GEFTestSuite.java`, `GraphicalViewerTest.java`, `HandleLocatorTests.java`, `PaletteColorProviderTest.java`, `PaletteCustomizerTest.java`, `PinnablePaletteStackEditPartTests.java`, `RulerLayoutTests.java`, `ToolUtilitiesTest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.handles`](gef.md#orgeclipsegefhandles), [`org.eclipse.gef.internal.ui.palette.editparts`](gef.md#orgeclipsegefinternaluipaletteeditparts), [`org.eclipse.gef.internal.ui.rulers`](gef.md#orgeclipsegefinternaluirulers), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.test.swtbot`](gef.md#orgeclipsegeftestswtbot), [`org.eclipse.gef.test.utils`](gef.md#orgeclipsegeftestutils), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.customize`](gef.md#orgeclipsegefuipalettecustomize), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.test.swtbot

- Origin: [org.eclipse.gef.tests/src/org/eclipse/gef/test/swtbot](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/swtbot); owner `org.eclipse.gef.tests`; test
- Declaration witness: [AbstractSWTBotEditorTests.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/swtbot/AbstractSWTBotEditorTests.java); source files: `AbstractSWTBotEditorTests.java`, `AbstractSWTBotTests.java`, `FlowDiagramTests.java`, `LogicDiagramTests.java`, `PaletteSettingsDialogTests.java`, `PaletteSnippetTests.java`, `SWTBotTestSuite.java`, `ShapesDiagramTests.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.examples.logicdesigner.edit`](gef.md#orgeclipsegefexampleslogicdesigneredit), [`org.eclipse.gef.examples.logicdesigner.model`](gef.md#orgeclipsegefexampleslogicdesignermodel), [`org.eclipse.gef.examples.logicdesigner.rulers`](gef.md#orgeclipsegefexampleslogicdesignerrulers), [`org.eclipse.gef.examples.palette`](gef.md#orgeclipsegefexamplespalette), [`org.eclipse.gef.internal.ui.palette`](gef.md#orgeclipsegefinternaluipalette), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.test.swtbot.utils`](gef.md#orgeclipsegeftestswtbotutils), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.customize`](gef.md#orgeclipsegefuipalettecustomize)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.test.swtbot.utils

- Origin: [org.eclipse.gef.tests/src/org/eclipse/gef/test/swtbot/utils](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/swtbot/utils); owner `org.eclipse.gef.tests`; test
- Declaration witness: [SWTBotGefPalette.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/swtbot/utils/SWTBotGefPalette.java); source files: `SWTBotGefPalette.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.test.utils

- Origin: [org.eclipse.gef.tests/src/org/eclipse/gef/test/utils](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/utils); owner `org.eclipse.gef.tests`; test
- Declaration witness: [TestGraphicalEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef.tests/src/org/eclipse/gef/test/utils/TestGraphicalEditPart.java); source files: `TestGraphicalEditPart.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.tools

- Origin: [org.eclipse.gef/src/org/eclipse/gef/tools](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/tools); owner `org.eclipse.gef`; production/build
- Declaration witness: [AbstractConnectionCreationTool.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/tools/AbstractConnectionCreationTool.java); source files: `AbstractConnectionCreationTool.java`, `AbstractTool.java`, `CellEditorLocator.java`, `ConnectionBendpointTracker.java`, `ConnectionCreationTool.java`, `ConnectionDragCreationTool.java`, `ConnectionEndpointTracker.java`, `CreationTool.java`, `DelayedDirectEditHelper.java`, `DeselectAllTracker.java`, `DirectEditManager.java`, `DragEditPartsTracker.java`, `DragTreeItemsTracker.java`, `MarqueeDragTracker.java`, `MarqueeSelectionTool.java`, `PanningSelectionTool.java`, `ResizeTracker.java`, `SelectEditPartTracker.java`, `SelectionTool.java`, `SimpleDragTracker.java`, `TargetingTool.java`, `ToolUtilities.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.handles`](gef.md#orgeclipsegefhandles), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.util`](gef.md#orgeclipsegefutil)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.tools`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/tools/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/tools/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.actions

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/actions](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/actions); owner `org.eclipse.gef`; production/build
- Declaration witness: [ActionBarContributor.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/actions/ActionBarContributor.java); source files: `ActionBarContributor.java`, `ActionRegistry.java`, `AlignmentAction.java`, `AlignmentRetargetAction.java`, `Clipboard.java`, `CopyRetargetAction.java`, `CopyTemplateAction.java`, `CreateGuideAction.java`, `DeleteAction.java`, `DeleteRetargetAction.java`, `DirectEditAction.java`, `EditorPartAction.java`, `GEFActionConstants.java`, `MatchHeightAction.java`, `MatchHeightRetargetAction.java`, `MatchSizeAction.java`, `MatchSizeRetargetAction.java`, `MatchWidthAction.java`, `MatchWidthRetargetAction.java`, `PasteRetargetAction.java`, `PasteTemplateAction.java`, `PrintAction.java`, `RedoAction.java`, `RedoRetargetAction.java`, `SaveAction.java`, `SelectAllAction.java`, `SelectionAction.java`, `SetActivePaletteToolAction.java`, `StackAction.java`, `ToggleGridAction.java`, `ToggleRulerVisibilityAction.java`, `ToggleSnapToGeometryAction.java`, `UndoAction.java`, `UndoRetargetAction.java`, `UpdateAction.java`, `WorkbenchPartAction.java`, `ZoomAction.java`, `ZoomComboContributionItem.java`, `ZoomInAction.java`, `ZoomInRetargetAction.java`, `ZoomOutAction.java`, `ZoomOutRetargetAction.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.zoom`](draw2d.md#orgeclipsedraw2dzoom), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.internal.ui.rulers`](gef.md#orgeclipsegefinternaluirulers), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.print`](gef.md#orgeclipsegefprint), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.actions`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/ui/actions/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/actions/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.palette

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/palette](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette); owner `org.eclipse.gef`; production/build
- Declaration witness: [ChangeIconSizeAction.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette/ChangeIconSizeAction.java); source files: `ChangeIconSizeAction.java`, `CustomizeAction.java`, `DefaultPaletteViewerPreferences.java`, `FlyoutPaletteComposite.java`, `LayoutAction.java`, `PaletteColorProvider.java`, `PaletteContextMenuProvider.java`, `PaletteCustomizer.java`, `PaletteEditPartFactory.java`, `PaletteMessages.java`, `PaletteViewer.java`, `PaletteViewerPreferences.java`, `PaletteViewerProvider.java`, `PinDrawerAction.java`, `SettingsAction.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.internal.ui.palette`](gef.md#orgeclipsegefinternaluipalette), [`org.eclipse.gef.internal.ui.palette.editparts`](gef.md#orgeclipsegefinternaluipaletteeditparts), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette.customize`](gef.md#orgeclipsegefuipalettecustomize), [`org.eclipse.gef.ui.palette.editparts`](gef.md#orgeclipsegefuipaletteeditparts), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts), [`org.eclipse.gef.ui.views.palette`](gef.md#orgeclipsegefuiviewspalette)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.palette`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/ui/palette/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.palette.customize

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/palette/customize](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette/customize); owner `org.eclipse.gef`; production/build
- Declaration witness: [DefaultEntryPage.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette/customize/DefaultEntryPage.java); source files: `DefaultEntryPage.java`, `DrawerEntryPage.java`, `EntryPage.java`, `EntryPageContainer.java`, `PaletteContainerFactory.java`, `PaletteCustomizationAction.java`, `PaletteCustomizerDialog.java`, `PaletteDrawerFactory.java`, `PaletteEntryFactory.java`, `PaletteGroupFactory.java`, `PaletteLabelProvider.java`, `PaletteSeparatorFactory.java`, `PaletteSettingsDialog.java`, `PaletteStackFactory.java`, `PaletteTreeProvider.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.widgets`](draw2d.md#orgeclipsedraw2dwidgets), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.internal.ui.palette`](gef.md#orgeclipsegefinternaluipalette), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.palette.customize`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.palette.editparts

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/palette/editparts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette/editparts); owner `org.eclipse.gef`; production/build
- Declaration witness: [IPinnableEditPart.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/palette/editparts/IPinnableEditPart.java); source files: `IPinnableEditPart.java`, `PaletteAnimator.java`, `PaletteEditPart.java`, `PaletteToolbarLayout.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.internal.ui.palette.editparts`](gef.md#orgeclipsegefinternaluipaletteeditparts), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.tools`](gef.md#orgeclipsegeftools), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.palette.editparts`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.parts

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/parts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/parts); owner `org.eclipse.gef`; production/build
- Declaration witness: [AbstractEditPartViewer.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/parts/AbstractEditPartViewer.java); source files: `AbstractEditPartViewer.java`, `ContentOutlinePage.java`, `DomainEventDispatcher.java`, `GraphicalEditor.java`, `GraphicalEditorWithFlyoutPalette.java`, `GraphicalEditorWithPalette.java`, `GraphicalViewerImpl.java`, `GraphicalViewerKeyHandler.java`, `PaletteViewerKeyHandler.java`, `ScrollingGraphicalViewer.java`, `SelectionSynchronizer.java`, `Splitter.java`, `TreeViewer.java`, `TreeViewerTransfer.java`, `TreeViewerTransferDragListener.java`, `TreeViewerTransferDropListener.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.dnd`](gef.md#orgeclipsegefdnd), [`org.eclipse.gef.editparts`](gef.md#orgeclipsegefeditparts), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.internal.ui.palette.editparts`](gef.md#orgeclipsegefinternaluipaletteeditparts), [`org.eclipse.gef.palette`](gef.md#orgeclipsegefpalette), [`org.eclipse.gef.requests`](gef.md#orgeclipsegefrequests), [`org.eclipse.gef.ui.actions`](gef.md#orgeclipsegefuiactions), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette), [`org.eclipse.gef.ui.palette.editparts`](gef.md#orgeclipsegefuipaletteeditparts), [`org.eclipse.gef.ui.properties`](gef.md#orgeclipsegefuiproperties), [`org.eclipse.gef.ui.views.palette`](gef.md#orgeclipsegefuiviewspalette), [`org.eclipse.gef.util`](gef.md#orgeclipsegefutil)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.parts`
- Existing package prose: [org.eclipse.gef/src/org/eclipse/gef/ui/parts/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/parts/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.properties

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/properties](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/properties); owner `org.eclipse.gef`; production/build
- Declaration witness: [SetPropertyValueCommand.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/properties/SetPropertyValueCommand.java); source files: `SetPropertyValueCommand.java`, `UndoablePropertySheetEntry.java`, `UndoablePropertySheetPage.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.gef.commands`](gef.md#orgeclipsegefcommands), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.properties`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.rulers

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/rulers](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/rulers); owner `org.eclipse.gef`; production/build
- Declaration witness: [RulerComposite.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/rulers/RulerComposite.java); source files: `RulerComposite.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.internal.ui.rulers`](gef.md#orgeclipsegefinternaluirulers), [`org.eclipse.gef.rulers`](gef.md#orgeclipsegefrulers), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.rulers`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.ui.views.palette

- Origin: [org.eclipse.gef/src/org/eclipse/gef/ui/views/palette](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/views/palette); owner `org.eclipse.gef`; production/build
- Declaration witness: [PalettePage.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/ui/views/palette/PalettePage.java); source files: `PalettePage.java`, `PaletteView.java`, `PaletteViewerPage.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.internal`](gef.md#orgeclipsegefinternal), [`org.eclipse.gef.ui.palette`](gef.md#orgeclipsegefuipalette)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.ui.views.palette`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.gef.util

- Origin: [org.eclipse.gef/src/org/eclipse/gef/util](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/util); owner `org.eclipse.gef`; production/build
- Declaration witness: [EditPartUtilities.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.gef/src/org/eclipse/gef/util/EditPartUtilities.java); source files: `EditPartUtilities.java`, `FlagSupport.java`, `IToolTipHelperFactory.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.gef`](gef.md#orgeclipsegef), [`org.eclipse.gef.ui.parts`](gef.md#orgeclipsegefuiparts)
- Export accounting: `org.eclipse.gef`: `org.eclipse.gef.util`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


