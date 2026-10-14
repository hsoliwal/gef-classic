# Package declarations: draw2d

[All packages](../PACKAGES.md) · [Lifecycle guide](../LIFECYCLE.md) · [Coverage](../COVERAGE.md)

This is a navigation group, not an additional build module. Source revision and inventory rules are recorded in the package index. All entries remain included; a declaration witness is not whole-package semantic review.

## awt

- Origin: [org.eclipse.draw2d.examples/sandbox/awt](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/awt); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [BenchmarkAlphaLines.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/awt/BenchmarkAlphaLines.java); source files: `BenchmarkAlphaLines.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## bidi

- Origin: [org.eclipse.draw2d.examples/sandbox/bidi](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/bidi); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [BenchmarkLevelRuns.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/bidi/BenchmarkLevelRuns.java); source files: `BenchmarkLevelRuns.java`, `IndividualCharacters.java`, `MixedOrientations.java`, `RTL_StyledText.java`
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## gef.bugs

- Origin: [org.eclipse.draw2d.examples/sandbox/gef/bugs](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/gef/bugs); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [BugWithLocalCoordinateSystem.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/gef/bugs/BugWithLocalCoordinateSystem.java); source files: `BugWithLocalCoordinateSystem.java`, `BugWithMouseWheelScrolling.java`, `ComputeSizeAndScrolbars.java`, `Issue1146.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: `org.eclipse.draw2d.examples`: `gef.bugs;x-friends:="org.eclipse.draw2d.tests"`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [AWTGraphics.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/AWTGraphics.java); source files: `AWTGraphics.java`, `AbsoluteBendpoint.java`, `AbstractBackground.java`, `AbstractBorder.java`, `AbstractConnectionAnchor.java`, `AbstractConstraintLayout.java`, `AbstractHintLayout.java`, `AbstractImageFigure.java`, `AbstractLabeledBorder.java`, `AbstractLayout.java`, `AbstractLocator.java`, `AbstractPointListShape.java`, `AbstractRouter.java`, `AccessibleBase.java`, `ActionEvent.java`, `ActionListener.java`, `AncestorHelper.java`, `AncestorListener.java`, `AnchorListener.java`, `Animation.java`, `Animator.java`, `ArrowButton.java`, `ArrowLocator.java`, `AutomaticRouter.java`, `AutoscaleFreeformViewport.java`, `BasicColorProvider.java`, `Bendpoint.java`, `BendpointConnectionRouter.java`, `BendpointLocator.java`, `Border.java`, `BorderLayout.java`, `BufferedGraphicsSource.java`, `Button.java`, `ButtonBorder.java`, `ButtonGroup.java`, `ButtonModel.java`, `ButtonStateTransitionListener.java`, `ChangeEvent.java`, `ChangeListener.java`, `CheckBox.java`, `ChopboxAnchor.java`, `Clickable.java`, `ClickableEventHandler.java`, `ColorConstants.java`, `ColorProvider.java`, `CompoundBorder.java`, `Connection.java`, `ConnectionAnchor.java`, `ConnectionAnchorBase.java`, `ConnectionEndpointLocator.java`, `ConnectionLayer.java`, `ConnectionLocator.java`, `ConnectionRouter.java`, `Container.java`, `CoordinateListener.java`, `Cursors.java`, `DefaultRangeModel.java`, `DeferredUpdateManager.java`, `DelegatingLayout.java`, `Ellipse.java`, `EllipseAnchor.java`, `EventDispatcher.java`, `EventListenerList.java`, `ExclusionSearch.java`, `FanRouter.java`, `Figure.java`, `FigureCanvas.java`, `FigureListener.java`, `FigureUtilities.java`, `FlowLayout.java`, `FocusBorder.java`, `FocusEvent.java`, `FocusListener.java`, `FocusTraverseManager.java`, `FrameBorder.java`, `FreeformFigure.java`, `FreeformHelper.java`, `FreeformLayer.java`, `FreeformLayeredPane.java`, `FreeformLayout.java`, `FreeformListener.java`, `FreeformViewport.java`, `GhostImageFigure.java`, `Graphics.java`, `GraphicsSource.java`, `GridData.java`, `GridLayout.java`, `GroupBoxBorder.java`, `IClippingStrategy.java`, `IFigure.java`, `IImageFigure.java`, `IScalablePane.java`, `IScrollableFigure.java`, `ImageFigure.java`, `ImagePrintFigureOperation.java`, `ImageUtilities.java`, `InputEvent.java`, `KeyEvent.java`, `KeyListener.java`, `Label.java`, `LabelAnchor.java`, `LabeledBorder.java`, `LabeledContainer.java`, `Layer.java`, `LayeredPane.java`, `LayoutAnimator.java`, `LayoutListener.java`, `LayoutManager.java`, `LightweightSystem.java`, `LineBorder.java`, `Locator.java`, `ManhattanConnectionRouter.java`, `MarginBorder.java`, `MidpointLocator.java`, `MouseEvent.java`, `MouseListener.java`, `MouseMotionListener.java`, `MouseWheelListener.java`, `NativeGraphicsSource.java`, `OrderedLayout.java`, `Orientable.java`, `Panel.java`, `Polygon.java`, `PolygonDecoration.java`, `PolygonShape.java`, `Polyline.java`, `PolylineConnection.java`, `PolylineDecoration.java`, `PolylineShape.java`, `PopUpHelper.java`, `PositionConstants.java`, `PrintFigureOperation.java`, `PrintOperation.java`, `PrinterGraphics.java`, `PuristicScrollPane.java`, `RangeModel.java`, `RectangleFigure.java`, `RelativeBendpoint.java`, `RelativeLocator.java`, `RotatableDecoration.java`, `RoundedRectangle.java`, `RoundedRectangleAnchor.java`, `RoutingAnimator.java`, `RoutingListener.java`, `SWTEventDispatcher.java`, `SWTGraphics.java`, `ScalableFigure.java`, `ScalableFreeformLayeredPane.java`, `ScalableLayeredPane.java`, `ScalableLightweightSystem.java`, `ScalablePolygonShape.java`, `ScaledGraphics.java`, `SchemeBorder.java`, `ScrollBar.java`, `ScrollBarLayout.java`, `ScrollPane.java`, `ScrollPaneLayout.java`, `ScrollPaneSolver.java`, `SeparatorBorder.java`, `Shape.java`, `ShortestPathConnectionRouter.java`, `SimpleEtchedBorder.java`, `SimpleLoweredBorder.java`, `SimpleRaisedBorder.java`, `StackLayout.java`, `SubordinateUpdateManager.java`, `TextUtilities.java`, `TitleBarBorder.java`, `Toggle.java`, `ToggleButton.java`, `ToggleModel.java`, `ToolTipHelper.java`, `ToolbarLayout.java`, `TreeSearch.java`, `Triangle.java`, `UpdateListener.java`, `UpdateManager.java`, `Viewport.java`, `ViewportAwareConnectionLayerClippingStrategy.java`, `ViewportLayout.java`, `ViewportUtilities.java`, `XYAnchor.java`, `XYLayout.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.backgrounds`](draw2d.md#orgeclipsedraw2dbackgrounds), [`org.eclipse.draw2d.colors`](draw2d.md#orgeclipsedraw2dcolors), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.backgrounds

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/backgrounds](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/backgrounds); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [AbstractBackgroundBorder.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/backgrounds/AbstractBackgroundBorder.java); source files: `AbstractBackgroundBorder.java`, `BackgroundBorder.java`, `CompoundBackground.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.backgrounds`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.backgrounds.shadows

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/backgrounds/shadows](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/backgrounds/shadows); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [AbstractDropShadowBorder.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/backgrounds/shadows/AbstractDropShadowBorder.java); source files: `AbstractDropShadowBorder.java`, `EllipseDropShadowBorder.java`, `RectangleDropShadowBorder.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.backgrounds`](draw2d.md#orgeclipsedraw2dbackgrounds), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.backgrounds.shadows`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.colors

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/colors](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/colors); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [HSL.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/colors/HSL.java); source files: `HSL.java`
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.colors`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [AbstractExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/AbstractExample.java); source files: `AbstractExample.java`, `ExampleImages.java`, `ExampleUtil.java`, `HelloWorld.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.awt

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/awt](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/awt); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [AWTExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/awt/AWTExample.java); source files: `AWTExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.cg

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/cg](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/cg); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [ObstructionExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/cg/ObstructionExample.java); source files: `ObstructionExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.connections

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/connections](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/connections); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [ConnectionAndAnchorExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/connections/ConnectionAndAnchorExample.java); source files: `ConnectionAndAnchorExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.graph

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/graph](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/graph); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [AbstractGraphDemo.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/graph/AbstractGraphDemo.java); source files: `AbstractGraphDemo.java`, `CompoundGraphDemo.java`, `CompoundGraphTests.java`, `DirectedGraphDemo.java`, `GraphTests.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.hittest

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/hittest](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/hittest); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [EllipseHitting.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/hittest/EllipseHitting.java); source files: `EllipseHitting.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.image

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/image](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/image); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [ImageExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/image/ImageExample.java); source files: `ImageExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.layouts

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/layouts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/layouts); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [FlowLayoutExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/layouts/FlowLayoutExample.java); source files: `FlowLayoutExample.java`, `GridLayoutExample.java`, `ToolbarLayoutExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.path

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/path](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/path); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [PathExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/path/PathExample.java); source files: `PathExample.java`, `PathFigure.java`, `ZoomFigure.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.printing

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/printing](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/printing); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [ImagePrintExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/printing/ImagePrintExample.java); source files: `ImagePrintExample.java`, `PrintExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.scrollpane

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/scrollpane](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/scrollpane); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [InternalFrame.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/scrollpane/InternalFrame.java); source files: `InternalFrame.java`, `ScrollPaneExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.shadows

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/shadows](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/shadows); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [ShadowsExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/shadows/ShadowsExample.java); source files: `ShadowsExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.backgrounds.shadows`](draw2d.md#orgeclipsedraw2dbackgroundsshadows), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.svg

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/svg](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/svg); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [AbstractGraphicsComposite.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/svg/AbstractGraphicsComposite.java); source files: `AbstractGraphicsComposite.java`, `GraphicsAlphaComposite.java`, `GraphicsClippingComposite.java`, `GraphicsLinesComposite.java`, `GraphicsMandelbrotComposite.java`, `GraphicsPathComposite.java`, `GraphicsShapesComposite.java`, `GraphicsStringsComposite.java`, `GraphicsTransformComposite.java`, `Messages.java`, `SVGExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.colors`](draw2d.md#orgeclipsedraw2dcolors), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.svg`](draw2d.md#orgeclipsedraw2dsvg)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.swt

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/swt](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/swt); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [InheritBackgroundExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/swt/InheritBackgroundExample.java); source files: `InheritBackgroundExample.java`, `ShapeStylesExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.text

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/text](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/text); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [BidiBlockExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/text/BidiBlockExample.java); source files: `BidiBlockExample.java`, `CaretExample.java`, `MultiLineLabelExample.java`, `TestBorder.java`, `TextFlowExample.java`, `TextFlowFactory.java`, `TextFlowLargeExample.java`, `TextSurroundingFigureExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext), [`org.eclipse.draw2d.widgets`](draw2d.md#orgeclipsedraw2dwidgets)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.thumbnail

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/thumbnail](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/thumbnail); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [ThumbnailExample.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/thumbnail/ThumbnailExample.java); source files: `ThumbnailExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.parts`](draw2d.md#orgeclipsedraw2dparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.tree

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/tree](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/tree); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [AbstractBranchLayout.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/tree/AbstractBranchLayout.java); source files: `AbstractBranchLayout.java`, `Animation.java`, `HangingLayout.java`, `NormalLayout.java`, `PageNode.java`, `PlusMinus.java`, `SimpleTreeExample.java`, `TreeBranch.java`, `TreeExample.java`, `TreeLayout.java`, `TreeRoot.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.examples`](draw2d.md#orgeclipsedraw2dexamples), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.uml

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/uml](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/uml); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [StickyNote.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/uml/StickyNote.java); source files: `StickyNote.java`, `UMLClassDiagram.java`, `UMLClassFigure.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.examples.zoom

- Origin: [org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/zoom](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/zoom); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [CompartmentFigure.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/src/org/eclipse/draw2d/examples/zoom/CompartmentFigure.java); source files: `CompartmentFigure.java`, `UMLClassFigure.java`, `ZoomContainer.java`, `ZoomExample.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.parts`](draw2d.md#orgeclipsedraw2dparts)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.geometry

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/geometry](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/geometry); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [Dimension.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/geometry/Dimension.java); source files: `Dimension.java`, `Geometry.java`, `Insets.java`, `Interval.java`, `Point.java`, `PointList.java`, `PrecisionDimension.java`, `PrecisionGeometry.java`, `PrecisionPoint.java`, `PrecisionPointList.java`, `PrecisionRectangle.java`, `Ray.java`, `Rectangle.java`, `Straight.java`, `Transform.java`, `Translatable.java`, `Transposer.java`, `Vector.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.geometry`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/geometry/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/geometry/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.graph

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/graph](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/graph); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [BreakCycles.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/graph/BreakCycles.java); source files: `BreakCycles.java`, `Cell.java`, `CollapsedEdges.java`, `CompoundBreakCycles.java`, `CompoundDirectedGraph.java`, `CompoundDirectedGraphLayout.java`, `CompoundHorizontalPlacement.java`, `CompoundPopulateRanks.java`, `CompoundRankSorter.java`, `CompoundTransposeMetrics.java`, `CompoundVerticalPlacement.java`, `ConvertCompoundGraph.java`, `DirectedGraph.java`, `DirectedGraphLayout.java`, `Edge.java`, `EdgeList.java`, `GraphUtilities.java`, `GraphVisitor.java`, `HorizontalPlacement.java`, `InitialRankSolver.java`, `InvertEdges.java`, `LocalOptimizer.java`, `MinCross.java`, `NestingTree.java`, `Node.java`, `NodeCluster.java`, `NodeList.java`, `NodePair.java`, `Obstacle.java`, `Path.java`, `PopulateRanks.java`, `Rank.java`, `RankAssignmentSolver.java`, `RankList.java`, `RankSorter.java`, `RevertableChange.java`, `RouteEdges.java`, `Segment.java`, `ShortestPathRouter.java`, `SortSubgraphs.java`, `SpanningTreeVisitor.java`, `Subgraph.java`, `SubgraphBoundary.java`, `TightSpanningTreeSolver.java`, `TransposeMetrics.java`, `Vertex.java`, `VerticalPlacement.java`, `VirtualNode.java`, `VirtualNodeCreation.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.graph`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/graph/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/graph/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.graph.test

- Origin: [org.eclipse.draw2d.tests/src/org/eclipse/draw2d/graph/test](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/graph/test); owner `org.eclipse.draw2d.tests`; test
- Declaration witness: [DirectedGraphLayoutTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/graph/test/DirectedGraphLayoutTest.java); source files: `DirectedGraphLayoutTest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.internal

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/internal](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/internal); owner `org.eclipse.draw2d`; internal production
- Declaration witness: [AWTUtils.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/internal/AWTUtils.java); source files: `AWTUtils.java`, `AutoscalingAccess.java`, `DrawableFigureUtilities.java`, `DrawableFlowUtilities.java`, `DrawableTextUtilities.java`, `FileImageDataProvider.java`, `ImageUtils.java`, `InternalDraw2dUtils.java`, `Logger.java`, `LoggerContext.java`, `Messages.java`, `MultiValueMap.java`, `NativeGraphics.java`, `SWTUtils.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.internal; x-friends:="org.eclipse.gef,  org.eclipse.zest.core,  org.eclipse.draw2d.examples,  org.eclipse.draw2d.tests,  org.eclipse.gef.examples.flow,  org.eclipse.draw2d.svg"`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/internal/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/internal/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.parts

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/parts](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/parts); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [ScrollableThumbnail.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/parts/ScrollableThumbnail.java); source files: `ScrollableThumbnail.java`, `Thumbnail.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.parts`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/parts/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/parts/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.svg

- Origin: [org.eclipse.draw2d.svg/src/org/eclipse/draw2d/svg](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.svg/src/org/eclipse/draw2d/svg); owner `org.eclipse.draw2d.svg`; production/build
- Declaration witness: [SVGGraphics.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.svg/src/org/eclipse/draw2d/svg/SVGGraphics.java); source files: `SVGGraphics.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.draw2d.svg`: `org.eclipse.draw2d.svg`
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.test

- Origin: [org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test); owner `org.eclipse.draw2d.tests`; test
- Declaration witness: [AbstractTextTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/AbstractTextTest.java); source files: `AbstractTextTest.java`, `AdvancedGraphicsTests.java`, `AnchorNotificationTest.java`, `AutoscaleFreeformViewportTest.java`, `BaseTestCase.java`, `BidiProcessorTest.java`, `ColorConstantTest.java`, `ConnectionEndPointMoveTest.java`, `DeferredUpdateManagerDamageTest.java`, `DimensionTests.java`, `Draw2dTestSuite.java`, `DrawableFigureUtilitiesTest.java`, `FanRouterTest.java`, `FigurePaintingTest.java`, `FigureUtilitiesTest.java`, `FlowBorderTests.java`, `GeometryTest.java`, `GraphicsClipping.java`, `GraphicsTest.java`, `ImagePrintFigureOperationTest.java`, `ImageUtilitiesTest.java`, `InsetsTest.java`, `IntervalTest.java`, `LabelTest.java`, `LayerTest.java`, `LayeredPaneTest.java`, `LocalOptimizerTest.java`, `LookAheadTest.java`, `PaintDamageEraseTest.java`, `PointListTests.java`, `PointTests.java`, `PrecisionDimensionTest.java`, `PrecisionPointListTest.java`, `PrecisionPointTest.java`, `PrecisionRectangleTest.java`, `PrecisionTests.java`, `RectangleTest.java`, `RelativeBendpointTest.java`, `ScalablePolygonShapeTest.java`, `ScrollPaneTests.java`, `ShapeTest.java`, `ShortestPathRoutingTest.java`, `SimpleTextTest.java`, `StraightTest.java`, `TestImages.java`, `TextFlowWrapTest.java`, `TextualTests.java`, `ThumbnailTest.java`, `TransposerTest.java`, `VectorTest.java`, `XYLayoutTest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.graph`](draw2d.md#orgeclipsedraw2dgraph), [`org.eclipse.draw2d.graph.test`](draw2d.md#orgeclipsedraw2dgraphtest), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.parts`](draw2d.md#orgeclipsedraw2dparts), [`org.eclipse.draw2d.test.colors`](draw2d.md#orgeclipsedraw2dtestcolors), [`org.eclipse.draw2d.test.utils`](draw2d.md#orgeclipsedraw2dtestutils), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.test.colors

- Origin: [org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/colors](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/colors); owner `org.eclipse.draw2d.tests`; test
- Declaration witness: [HSLTest.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/colors/HSLTest.java); source files: `HSLTest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d.colors`](draw2d.md#orgeclipsedraw2dcolors)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.test.swtbot

- Origin: [org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/swtbot](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/swtbot); owner `org.eclipse.draw2d.tests`; test
- Declaration witness: [AbstractSWTBotTests.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/swtbot/AbstractSWTBotTests.java); source files: `AbstractSWTBotTests.java`, `FigureTest.java`, `SWTBotTestSuite.java`
- Observed imports of other inventoried namespaces: [`gef.bugs`](draw2d.md#gefbugs), [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal), [`org.eclipse.draw2d.test.utils`](draw2d.md#orgeclipsedraw2dtestutils)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.test.utils

- Origin: [org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/utils](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/utils); owner `org.eclipse.draw2d.tests`; test
- Declaration witness: [Graphics2DStub.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.tests/src/org/eclipse/draw2d/test/utils/Graphics2DStub.java); source files: `Graphics2DStub.java`, `Graphics2DToGraphicsAdapter.java`, `GraphicsRecorder.java`, `GraphicsStub.java`, `Snippet.java`, `TestFigure.java`, `TestLogger.java`, `TestLoggerContext.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.text

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/text](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/text); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [AbstractFlowBorder.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/text/AbstractFlowBorder.java); source files: `AbstractFlowBorder.java`, `BidiChars.java`, `BidiInfo.java`, `BidiProcessor.java`, `BidiProvider.java`, `BlockBox.java`, `BlockFlow.java`, `BlockFlowLayout.java`, `CaretInfo.java`, `CompositeBox.java`, `ContentBox.java`, `FlowAdapter.java`, `FlowBorder.java`, `FlowBox.java`, `FlowContainerLayout.java`, `FlowContext.java`, `FlowFigure.java`, `FlowFigureLayout.java`, `FlowPage.java`, `FlowUtilities.java`, `InlineFlow.java`, `InlineFlowLayout.java`, `LineBox.java`, `LineRoot.java`, `NestedLine.java`, `PageFlowLayout.java`, `ParagraphTextLayout.java`, `SimpleTextLayout.java`, `TextFlow.java`, `TextFragmentBox.java`, `TextLayout.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.text`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/text/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/text/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.widgets

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/widgets](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/widgets); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [ImageBorder.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/widgets/ImageBorder.java); source files: `ImageBorder.java`, `MultiLineLabel.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.text`](draw2d.md#orgeclipsedraw2dtext)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.widgets`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/widgets/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/widgets/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## org.eclipse.draw2d.zoom

- Origin: [org.eclipse.draw2d/src/org/eclipse/draw2d/zoom](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/zoom); owner `org.eclipse.draw2d`; production/build
- Declaration witness: [AbstractZoomManager.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/zoom/AbstractZoomManager.java); source files: `AbstractZoomManager.java`, `DefaultScrollPolicy.java`, `IZoomScrollPolicy.java`, `MouseLocationZoomScrollPolicy.java`, `ZoomListener.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d), [`org.eclipse.draw2d.geometry`](draw2d.md#orgeclipsedraw2dgeometry), [`org.eclipse.draw2d.internal`](draw2d.md#orgeclipsedraw2dinternal)
- Export accounting: `org.eclipse.draw2d`: `org.eclipse.draw2d.zoom`
- Existing package prose: [org.eclipse.draw2d/src/org/eclipse/draw2d/zoom/package.html](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d/src/org/eclipse/draw2d/zoom/package.html)
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## swt.bugs

- Origin: [org.eclipse.draw2d.examples/sandbox/swt/bugs](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/swt/bugs); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [BidiMixedOrientations.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/swt/bugs/BidiMixedOrientations.java); source files: `BidiMixedOrientations.java`, `Bug23147.java`, `Bug23332.java`, `Bug45640.java`, `Bug54989.java`, `Bug68003.java`, `Bug81467.java`, `EscapeStolen.java`, `GTKTreeItemAccess.java`, `IconVsImage.java`, `MnemonicsFix.java`, `PR_75430.java`, `PR_85876.java`, `TreeTest.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


## swt.transforms

- Origin: [org.eclipse.draw2d.examples/sandbox/swt/transforms](https://github.com/hsoliwal/gef-classic/tree/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/swt/transforms); owner `org.eclipse.draw2d.examples`; example
- Declaration witness: [AbstractSWTTransform.java](https://github.com/hsoliwal/gef-classic/blob/f37f3da20888214c783ba2742a84a5e60571b7ee/org.eclipse.draw2d.examples/sandbox/swt/transforms/AbstractSWTTransform.java); source files: `AbstractSWTTransform.java`, `AlphaLines.java`, `FontScaling.java`, `PathStuff.java`, `RegionTransforms.java`, `RotateGradient.java`, `RotatedClipRect.java`, `TextSize.java`, `ThinLines.java`, `TransformTemplate.java`
- Observed imports of other inventoried namespaces: [`org.eclipse.draw2d`](draw2d.md#orgeclipsedraw2d)
- Export accounting: no exact Export-Package clause found in fetched manifests; Java public visibility alone does not make it an exported bundle API
- Semantic status: inventory only unless explicitly reviewed in the lifecycle guide


