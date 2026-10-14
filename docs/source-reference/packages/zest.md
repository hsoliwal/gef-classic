# Package declarations: zest

[All packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md) · [Coverage](../COVERAGE.md)

This is a navigation group, not an additional build module. Source revision and inventory rules are recorded in the package index. All entries remain included; a declaration witness is not whole-package semantic review.

## org.eclipse.zest.cloudio

- Origin: [org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio); owner `org.eclipse.zest.cloudio`; production/build
- Declaration witness: [Activator.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/Activator.java); source files: `Activator.java`, `CloudOptionsComposite.java`, `ICloudLabelProvider.java`, `IEditableCloudLabelProvider.java`, `Messages.java`, `TagCloud.java`, `TagCloudViewer.java`, `Word.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio.layout`](zest.md#orgeclipsezestcloudiolayout), [`org.eclipse.zest.cloudio.util`](zest.md#orgeclipsezestcloudioutil)
- Export accounting: `org.eclipse.zest.cloudio`: `org.eclipse.zest.cloudio`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.cloudio.layout

- Origin: [org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/layout](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/layout); owner `org.eclipse.zest.cloudio`; production/build
- Declaration witness: [DefaultLayouter.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/layout/DefaultLayouter.java); source files: `DefaultLayouter.java`, `ILayouter.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.cloudio.util`](zest.md#orgeclipsezestcloudioutil)
- Export accounting: `org.eclipse.zest.cloudio`: `org.eclipse.zest.cloudio.layout`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.cloudio.util

- Origin: [org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/util](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/util); owner `org.eclipse.zest.cloudio`; production/build
- Declaration witness: [CloudMatrix.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.cloudio/src/org/eclipse/zest/cloudio/util/CloudMatrix.java); source files: `CloudMatrix.java`, `RectTree.java`, `SmallRect.java`
- Export accounting: `org.eclipse.zest.cloudio`: `org.eclipse.zest.cloudio.util`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.viewers

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/viewers](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/viewers); owner `org.eclipse.zest.core`; production/build
- Declaration witness: [AbstractStructuredGraphViewer.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/AbstractStructuredGraphViewer.java); source files: `AbstractStructuredGraphViewer.java`, `AbstractZoomableViewer.java`, `EntityConnectionData.java`, `GraphViewer.java`, `IConnectionStyleBezierExtension.java`, `IConnectionStyleProvider.java`, `IConnectionStyleProvider2.java`, `IEntityConnectionStyleBezierExtension.java`, `IEntityConnectionStyleProvider.java`, `IEntityConnectionStyleProvider2.java`, `IEntityStyleProvider.java`, `IFigureProvider.java`, `IGraphContentProvider.java`, `IGraphEntityContentProvider.java`, `IGraphEntityRelationshipContentProvider.java`, `INestedContentProvider.java`, `ISelfStyleProvider.java`, `IZoomableWorkbenchPart.java`, `ZoomContributionViewItem.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.zoom`](draw2d.md#orgeclipsedraw2dzoom), [`org.eclipse.zest.core.viewers.decorators`](zest.md#orgeclipsezestcoreviewersdecorators), [`org.eclipse.zest.core.viewers.internal`](zest.md#orgeclipsezestcoreviewersinternal), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.viewers`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.viewers.decorators

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/decorators](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/decorators); owner `org.eclipse.zest.core`; production/build
- Declaration witness: [GraphLabelDecorator.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/decorators/GraphLabelDecorator.java); source files: `GraphLabelDecorator.java`, `IConnectionStyleDecorator.java`, `IEntityConnectionStyleDecorator.java`, `IEntityStyleDecorator.java`, `IGraphLabelDecorator.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.decorators`](zest.md#orgeclipsezestcorewidgetsdecorators)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.viewers.decorators`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.viewers.internal

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/internal](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/internal); owner `org.eclipse.zest.core`; internal production
- Declaration witness: [AbstractStylingModelFactory.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/viewers/internal/AbstractStylingModelFactory.java); source files: `AbstractStylingModelFactory.java`, `GraphItemStyler.java`, `GraphModelEntityFactory.java`, `GraphModelEntityRelationshipFactory.java`, `GraphModelFactory.java`, `IStylingGraphModelFactory.java`, `SharedMessages.java`, `TreeModelEntityFactory.java`, `ZoomManager.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.zest.core.viewers`](zest.md#orgeclipsezestcoreviewers), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.decorators`](zest.md#orgeclipsezestcorewidgetsdecorators)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.viewers.internal;x-internal:=true`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.widgets

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/widgets](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets); owner `org.eclipse.zest.core`; production/build
- Declaration witness: [CGraphNode.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/CGraphNode.java); source files: `CGraphNode.java`, `ConstraintAdapter.java`, `DAGExpandCollapseManager.java`, `DefaultSubgraph.java`, `FigureSubgraph.java`, `FisheyeListener.java`, `Graph.java`, `GraphConnection.java`, `GraphContainer.java`, `GraphDragSupport.java`, `GraphItem.java`, `GraphNode.java`, `HideNodeHelper.java`, `IContainer.java`, `IContainer2.java`, `ILabeledFigure.java`, `IStyleableFigure.java`, `InternalLayoutContext.java`, `InternalNodeLayout.java`, `LayoutFilter.java`, `PrunedSuccessorsSubgraph.java`, `SubgraphFactory.java`, `ZestStyles.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.zest.core.viewers.internal`](zest.md#orgeclipsezestcoreviewersinternal), [`org.eclipse.zest.core.widgets.custom`](zest.md#orgeclipsezestcorewidgetscustom), [`org.eclipse.zest.core.widgets.decorators`](zest.md#orgeclipsezestcorewidgetsdecorators), [`org.eclipse.zest.core.widgets.gestures`](zest.md#orgeclipsezestcorewidgetsgestures), [`org.eclipse.zest.core.widgets.internal`](zest.md#orgeclipsezestcorewidgetsinternal), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.constraints`](zest.md#orgeclipsezestlayoutsconstraints), [`org.eclipse.zest.layouts.dataStructures`](zest.md#orgeclipsezestlayoutsdatastructures), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.widgets`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.widgets.custom

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/custom](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/custom); owner `org.eclipse.zest.core`; production/build
- Declaration witness: [LabelSubgraph.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/custom/LabelSubgraph.java); source files: `LabelSubgraph.java`, `TriangleSubgraph.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.internal`](zest.md#orgeclipsezestcorewidgetsinternal), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.widgets.custom`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.widgets.decorators

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/decorators](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/decorators); owner `org.eclipse.zest.core`; production/build
- Declaration witness: [GraphDecorator.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/decorators/GraphDecorator.java); source files: `GraphDecorator.java`, `IGraphDecorator.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.widgets.decorators`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.widgets.gestures

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/gestures](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/gestures); owner `org.eclipse.zest.core`; production/build
- Declaration witness: [RotateGestureListener.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/gestures/RotateGestureListener.java); source files: `RotateGestureListener.java`, `ZoomGestureListener.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.viewers.internal`](zest.md#orgeclipsezestcoreviewersinternal), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.widgets.gestures`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.core.widgets.internal

- Origin: [org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/internal](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/internal); owner `org.eclipse.zest.core`; internal production
- Declaration witness: [AligningBendpointLocator.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.core/src/org/eclipse/zest/core/widgets/internal/AligningBendpointLocator.java); source files: `AligningBendpointLocator.java`, `AspectRatioFreeformLayer.java`, `BasicGraphDecorator.java`, `CachedLabel.java`, `ContainerFigure.java`, `ExpandGraphLabel.java`, `GraphLabel.java`, `LoopAnchor.java`, `NodeSearchDialog.java`, `PolylineArcConnection.java`, `RevealListener.java`, `RoundedChopboxAnchor.java`, `ZestRootLayer.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.zest.core.viewers.internal`](zest.md#orgeclipsezestcoreviewersinternal), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.decorators`](zest.md#orgeclipsezestcorewidgetsdecorators)
- Export accounting: `org.eclipse.zest.core`: `org.eclipse.zest.core.widgets.internal;x-friends:="org.eclipse.zest.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples

- Origin: [org.eclipse.zest.examples/src/org/eclipse/zest/examples](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples); owner `org.eclipse.zest.examples`; example
- Declaration witness: [Messages.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/Messages.java); source files: `Messages.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.application

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [Application.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/Application.java); source files: `Application.java`, `ApplicationActionBarAdvisor.java`, `ApplicationWorkbenchAdvisor.java`, `ApplicationWorkbenchWindowAdvisor.java`, `Messages.java`, `PerspectiveFactory.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.examples.cloudio.application.actions`](zest.md#orgeclipsezestexamplescloudioapplicationactions)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.application.about

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/about](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/about); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [AboutDialog.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/about/AboutDialog.java); source files: `AboutDialog.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.cloudio.layout`](zest.md#orgeclipsezestcloudiolayout), [`org.eclipse.zest.examples.cloudio.application`](zest.md#orgeclipsezestexamplescloudioapplication)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.application.actions

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/actions](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/actions); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [AboutAction.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/actions/AboutAction.java); source files: `AboutAction.java`, `AbstractTagCloudAction.java`, `DeselectAllAction.java`, `ExportImageAction.java`, `LoadFileAction.java`, `LoadStopWordsAction.java`, `SelectAllAction.java`, `ZoomFitAction.java`, `ZoomInAction.java`, `ZoomOutAction.java`, `ZoomResetAction.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.examples.cloudio.application`](zest.md#orgeclipsezestexamplescloudioapplication), [`org.eclipse.zest.examples.cloudio.application.about`](zest.md#orgeclipsezestexamplescloudioapplicationabout), [`org.eclipse.zest.examples.cloudio.application.data`](zest.md#orgeclipsezestexamplescloudioapplicationdata), [`org.eclipse.zest.examples.cloudio.application.ui`](zest.md#orgeclipsezestexamplescloudioapplicationui)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.application.data

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/data](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/data); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [Type.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/data/Type.java); source files: `Type.java`, `TypeCollector.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.application.ui

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/ui](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/ui); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [CloudioApplicationPlugin.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/ui/CloudioApplicationPlugin.java); source files: `CloudioApplicationPlugin.java`, `TagCloudViewPart.java`, `TypeLabelProvider.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.cloudio.layout`](zest.md#orgeclipsezestcloudiolayout), [`org.eclipse.zest.examples.cloudio.application`](zest.md#orgeclipsezestexamplescloudioapplication), [`org.eclipse.zest.examples.cloudio.application.data`](zest.md#orgeclipsezestexamplescloudioapplicationdata)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.application.ui.customization

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/ui/customization](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/ui/customization); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [CharacterLabelProvider.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/application/ui/customization/CharacterLabelProvider.java); source files: `CharacterLabelProvider.java`, `CharacterLayouter.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.cloudio.layout`](zest.md#orgeclipsezestcloudiolayout), [`org.eclipse.zest.examples.cloudio.application.data`](zest.md#orgeclipsezestexamplescloudioapplicationdata), [`org.eclipse.zest.examples.cloudio.application.ui`](zest.md#orgeclipsezestexamplescloudioapplicationui)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.cloudio.snippets

- Origin: [org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/snippets](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/snippets); owner `org.eclipse.zest.examples.cloudio`; example
- Declaration witness: [TagCloudSnippet.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.cloudio/src/org/eclipse/zest/examples/cloudio/snippets/TagCloudSnippet.java); source files: `TagCloudSnippet.java`, `TagCloudViewerSnippet.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.examples.cloudio.application`](zest.md#orgeclipsezestexamplescloudioapplication)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.jface

- Origin: [org.eclipse.zest.examples/src/org/eclipse/zest/examples/jface](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/jface); owner `org.eclipse.zest.examples`; example
- Declaration witness: [CustomFigureJFaceSnippet.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/jface/CustomFigureJFaceSnippet.java); source files: `CustomFigureJFaceSnippet.java`, `GraphJFaceSnippet1.java`, `GraphJFaceSnippet10.java`, `GraphJFaceSnippet11.java`, `GraphJFaceSnippet2.java`, `GraphJFaceSnippet3.java`, `GraphJFaceSnippet4.java`, `GraphJFaceSnippet5.java`, `GraphJFaceSnippet6.java`, `GraphJFaceSnippet7.java`, `GraphJFaceSnippet8.java`, `GraphJFaceSnippet9.java`, `ManhattanLayoutJFaceSnippet.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.viewers`](zest.md#orgeclipsezestcoreviewers), [`org.eclipse.zest.core.viewers.decorators`](zest.md#orgeclipsezestcoreviewersdecorators), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.examples`](zest.md#orgeclipsezestexamples), [`org.eclipse.zest.examples.uml`](zest.md#orgeclipsezestexamplesuml), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms)
- Export accounting: `org.eclipse.zest.examples`: `org.eclipse.zest.examples.jface;x-friends:="org.eclipse.zest.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.layouts

- Origin: [org.eclipse.zest.examples/src/org/eclipse/zest/examples/layouts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/layouts); owner `org.eclipse.zest.examples`; example
- Declaration witness: [ContainerResizeGraphSnippet.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/layouts/ContainerResizeGraphSnippet.java); source files: `ContainerResizeGraphSnippet.java`, `ContainersGraphSnippet.java`, `CustomFigureGraphSnippet.java`, `CustomLayout.java`, `DAGExample.java`, `FilterGraphSnippet.java`, `FisheyeGraphSnippet.java`, `RadialLayoutExample.java`, `SimpleGraphSnippet.java`, `SpaceTreeBuilding.java`, `SpaceTreeExample.java`, `SpringLayoutExample.java`, `SpringLayoutProgress.java`, `SugiyamaLayoutExample.java`, `TreeLayoutExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.examples`](zest.md#orgeclipsezestexamples), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.swt

- Origin: [org.eclipse.zest.examples/src/org/eclipse/zest/examples/swt](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/swt); owner `org.eclipse.zest.examples`; example
- Declaration witness: [AnimationSnippet.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/swt/AnimationSnippet.java); source files: `AnimationSnippet.java`, `CustomLayout.java`, `GraphSnippet1.java`, `GraphSnippet10.java`, `GraphSnippet11.java`, `GraphSnippet12.java`, `GraphSnippet13.java`, `GraphSnippet14.java`, `GraphSnippet2.java`, `GraphSnippet3.java`, `GraphSnippet4.java`, `GraphSnippet5.java`, `GraphSnippet6.java`, `GraphSnippet7.java`, `GraphSnippet8.java`, `GraphSnippet9.java`, `HelloWorld.java`, `LayoutExample.java`, `ManhattanLayoutGraphSnippet.java`, `NestedGraphSnippet.java`, `NestedGraphSnippet2.java`, `PaintSnippet.java`, `ZoomSnippet.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.internal`](zest.md#orgeclipsezestcorewidgetsinternal), [`org.eclipse.zest.examples`](zest.md#orgeclipsezestexamples), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces)
- Export accounting: `org.eclipse.zest.examples`: `org.eclipse.zest.examples.swt;x-friends:="org.eclipse.zest.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.examples.uml

- Origin: [org.eclipse.zest.examples/src/org/eclipse/zest/examples/uml](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/uml); owner `org.eclipse.zest.examples`; example
- Declaration witness: [CompartmentFigure.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples/src/org/eclipse/zest/examples/uml/CompartmentFigure.java); source files: `CompartmentFigure.java`, `UMLClassFigure.java`, `UMLExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms)
- Export accounting: `org.eclipse.zest.examples`: `org.eclipse.zest.examples.uml;x-friends:="org.eclipse.zest.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts); owner `org.eclipse.zest.layouts`; production/build
- Declaration witness: [Filter.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/Filter.java); source files: `Filter.java`, `InvalidLayoutConfiguration.java`, `LayoutAlgorithm.java`, `LayoutBendPoint.java`, `LayoutEntity.java`, `LayoutGraph.java`, `LayoutItem.java`, `LayoutIterationEvent.java`, `LayoutRelationship.java`, `LayoutStyles.java`, `NestedLayoutEntity.java`, `Stoppable.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.constraints`](zest.md#orgeclipsezestlayoutsconstraints), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces), [`org.eclipse.zest.layouts.progress`](zest.md#orgeclipsezestlayoutsprogress)
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.algorithms

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/algorithms](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/algorithms); owner `org.eclipse.zest.layouts`; production/build
- Declaration witness: [AbstractLayoutAlgorithm.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/algorithms/AbstractLayoutAlgorithm.java); source files: `AbstractLayoutAlgorithm.java`, `AlgorithmHelper.java`, `BoxLayoutAlgorithm.java`, `CompositeLayoutAlgorithm.java`, `ContinuousLayoutAlgorithm.java`, `DirectedGraphLayoutAlgorithm.java`, `GridLayoutAlgorithm.java`, `HorizontalLayoutAlgorithm.java`, `HorizontalShift.java`, `HorizontalShiftAlgorithm.java`, `HorizontalTreeLayoutAlgorithm.java`, `RadialLayoutAlgorithm.java`, `SpaceTreeLayoutAlgorithm.java`, `SpringLayoutAlgorithm.java`, `SugiyamaLayoutAlgorithm.java`, `TreeLayoutAlgorithm.java`, `TreeLayoutObserver.java`, `VerticalLayoutAlgorithm.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.constraints`](zest.md#orgeclipsezestlayoutsconstraints), [`org.eclipse.zest.layouts.dataStructures`](zest.md#orgeclipsezestlayoutsdatastructures), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces), [`org.eclipse.zest.layouts.progress`](zest.md#orgeclipsezestlayoutsprogress)
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts.algorithms`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.algorithms.internal

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/algorithms/internal](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/algorithms/internal); owner `org.eclipse.zest.layouts`; internal production
- Declaration witness: [CycleChecker.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/algorithms/internal/CycleChecker.java); source files: `CycleChecker.java`, `DynamicScreen.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.dataStructures`](zest.md#orgeclipsezestlayoutsdatastructures)
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts.algorithms.internal`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.constraints

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/constraints](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/constraints); owner `org.eclipse.zest.layouts`; production/build
- Declaration witness: [BasicEdgeConstraints.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/constraints/BasicEdgeConstraints.java); source files: `BasicEdgeConstraints.java`, `BasicEntityConstraint.java`, `EntityPriorityConstraint.java`, `LabelLayoutConstraint.java`, `LayoutConstraint.java`
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts.constraints`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.dataStructures

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/dataStructures](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/dataStructures); owner `org.eclipse.zest.layouts`; production/build
- Declaration witness: [BendPoint.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/dataStructures/BendPoint.java); source files: `BendPoint.java`, `DisplayIndependentDimension.java`, `DisplayIndependentPoint.java`, `DisplayIndependentRectangle.java`, `InternalNode.java`, `InternalRelationship.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.constraints`](zest.md#orgeclipsezestlayoutsconstraints)
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts.dataStructures`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.exampleStructures

- Origin: [org.eclipse.zest.examples.layouts/src/org/eclipse/zest/layouts/exampleStructures](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.layouts/src/org/eclipse/zest/layouts/exampleStructures); owner `org.eclipse.zest.examples.layouts`; example
- Declaration witness: [SimpleFilter.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.layouts/src/org/eclipse/zest/layouts/exampleStructures/SimpleFilter.java); source files: `SimpleFilter.java`, `SimpleGraph.java`, `SimpleNode.java`, `SimpleRelationship.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.dataStructures`](zest.md#orgeclipsezestlayoutsdatastructures), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces)
- Export accounting: `org.eclipse.zest.examples.layouts`: `org.eclipse.zest.layouts.exampleStructures;x-internal:=true`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.exampleUses

- Origin: [org.eclipse.zest.examples.layouts/src/org/eclipse/zest/layouts/exampleUses](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.layouts/src/org/eclipse/zest/layouts/exampleUses); owner `org.eclipse.zest.examples.layouts`; example
- Declaration witness: [Messages.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.examples.layouts/src/org/eclipse/zest/layouts/exampleUses/Messages.java); source files: `Messages.java`, `SimpleSWTExample.java`, `SimpleSwingExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.exampleStructures`](zest.md#orgeclipsezestlayoutsexamplestructures)
- Export accounting: `org.eclipse.zest.examples.layouts`: `org.eclipse.zest.layouts.exampleUses;x-internal:=true`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.interfaces

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/interfaces](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/interfaces); owner `org.eclipse.zest.layouts`; production/build
- Declaration witness: [ConnectionLayout.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/interfaces/ConnectionLayout.java); source files: `ConnectionLayout.java`, `ContextListener.java`, `EntityLayout.java`, `ExpandCollapseManager.java`, `GraphStructureListener.java`, `LayoutContext.java`, `LayoutListener.java`, `NodeLayout.java`, `PruningListener.java`, `SubgraphLayout.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.dataStructures`](zest.md#orgeclipsezestlayoutsdatastructures)
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts.interfaces`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.layouts.progress

- Origin: [org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/progress](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/progress); owner `org.eclipse.zest.layouts`; production/build
- Declaration witness: [ProgressEvent.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.layouts/src/org/eclipse/zest/layouts/progress/ProgressEvent.java); source files: `ProgressEvent.java`, `ProgressListener.java`
- Export accounting: `org.eclipse.zest.layouts`: `org.eclipse.zest.layouts.progress`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.tests

- Origin: [org.eclipse.zest.tests/src/org/eclipse/zest/tests](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests); owner `org.eclipse.zest.tests`; test
- Declaration witness: [GraphSelectionTests.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/GraphSelectionTests.java); source files: `GraphSelectionTests.java`, `GraphTests.java`, `GraphViewerTests.java`, `IFigureProviderTests.java`, `LayoutAlgorithmTest.java`, `LayoutAlgorithmTests.java`, `ZestTestSuite.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.zest.core.viewers`](zest.md#orgeclipsezestcoreviewers), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.internal`](zest.md#orgeclipsezestcorewidgetsinternal), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces), [`org.eclipse.zest.layouts.progress`](zest.md#orgeclipsezestlayoutsprogress), [`org.eclipse.zest.tests.cloudio`](zest.md#orgeclipsezesttestscloudio)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.tests.cloudio

- Origin: [org.eclipse.zest.tests/src/org/eclipse/zest/tests/cloudio](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/cloudio); owner `org.eclipse.zest.tests`; test
- Declaration witness: [TagCloudTests.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/cloudio/TagCloudTests.java); source files: `TagCloudTests.java`, `TagCloudViewerTests.java`, `TestLabelProvider.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.zest.cloudio`](zest.md#orgeclipsezestcloudio), [`org.eclipse.zest.cloudio.layout`](zest.md#orgeclipsezestcloudiolayout)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.tests.examples

- Origin: [org.eclipse.zest.tests/src/org/eclipse/zest/tests/examples](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/examples); owner `org.eclipse.zest.tests`; test
- Declaration witness: [AbstractGraphTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/examples/AbstractGraphTest.java); source files: `AbstractGraphTest.java`, `GraphJFaceTests.java`, `GraphSWTTests.java`, `GraphUMLTests.java`, `SWTBotTestSuite.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.viewers`](zest.md#orgeclipsezestcoreviewers), [`org.eclipse.zest.core.viewers.decorators`](zest.md#orgeclipsezestcoreviewersdecorators), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.internal`](zest.md#orgeclipsezestcorewidgetsinternal), [`org.eclipse.zest.examples.jface`](zest.md#orgeclipsezestexamplesjface), [`org.eclipse.zest.examples.swt`](zest.md#orgeclipsezestexamplesswt), [`org.eclipse.zest.examples.uml`](zest.md#orgeclipsezestexamplesuml), [`org.eclipse.zest.layouts`](zest.md#orgeclipsezestlayouts), [`org.eclipse.zest.layouts.algorithms`](zest.md#orgeclipsezestlayoutsalgorithms), [`org.eclipse.zest.layouts.interfaces`](zest.md#orgeclipsezestlayoutsinterfaces), [`org.eclipse.zest.tests.utils`](zest.md#orgeclipsezesttestsutils)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.zest.tests.utils

- Origin: [org.eclipse.zest.tests/src/org/eclipse/zest/tests/utils](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/utils); owner `org.eclipse.zest.tests`; test
- Declaration witness: [ISWTBotGraphContainer.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.zest.tests/src/org/eclipse/zest/tests/utils/ISWTBotGraphContainer.java); source files: `ISWTBotGraphContainer.java`, `SWTBotGraph.java`, `SWTBotGraphConnection.java`, `SWTBotGraphContainer.java`, `SWTBotGraphItem.java`, `SWTBotGraphNode.java`, `Snippet.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.zest.core.widgets`](zest.md#orgeclipsezestcorewidgets), [`org.eclipse.zest.core.widgets.internal`](zest.md#orgeclipsezestcorewidgetsinternal)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


