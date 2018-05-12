package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.widget.IApplicationToolbar;
import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import com.lgsim.engine.graphEditor.api.widget.console.IConsole;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.graph.action.*;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.graph.document.DocumentButtonTab;
import com.lgsim.engine.graphEditor.graph.document.DocumentContext;
import com.lgsim.engine.graphEditor.graph.document.DocumentStructure;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.stencil.StencilPalette;
import com.lgsim.engine.graphEditor.graph.stencil.StencilSupport;
import com.lgsim.engine.graphEditor.graph.vertex.VertexGroupPalette;
import com.lgsim.engine.graphEditor.graph.vertex.VertexSupport;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;

@SuppressWarnings("WeakerAccess")
public class Editor extends JPanel implements IApplicationWidget {

  private static final Logger log = LoggerFactory.getLogger(Editor.class);

  private IApplication application;
  private DocumentContext documentContext;
  private List<Document> documents;
  private int currentDocumentIndex;

  private IConsole console;

  private DocumentStructure documentStructure;
  private JTabbedPane libraryPane;
  private IVertexTable vertexTable;
  private JTabbedPane docTabbedPane;
  private StencilPalette predefinedPalette;
  private StencilPalette userDefinedPalette;
  private VertexGroupPalette vertexGroupPalette;


  public Editor(@NotNull IApplication application)
  {
    setApplication(application);
    documentContext = new DocumentContext(this);
    documents = new Vector<>();
    console = ImplementationContext.INSTANCE.getConsole();
    initUIComponents();
    initActions();
    loadStencils();
    loadDocuments();
  }


  private void initUIComponents()
  {
    setLayout(new BorderLayout());

    documentStructure = new DocumentStructure();
    libraryPane = new JTabbedPane();
    vertexTable = ImplementationContext.INSTANCE.getVertexTable();
    docTabbedPane = new JTabbedPane();
    JSplitPane westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane, documentStructure);
    JSplitPane consoleDocPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, docTabbedPane, console.getSwingComponent());
    JSplitPane centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPane, consoleDocPane);
    JSplitPane eastPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPane, vertexTable.getSwingComponent());
    predefinedPalette = StencilSupport.createStencilPalette();
    userDefinedPalette = StencilSupport.createStencilPalette();
    vertexGroupPalette = VertexSupport.createVertexPalette();
    IApplicationToolbar applicationToolbar = ImplementationContext.INSTANCE.getApplicationToolbar();

    JToolBar toolbar = applicationToolbar.getSwingComponent();
    toolbar.setOrientation(JToolBar.HORIZONTAL);
    add(toolbar, BorderLayout.NORTH);

    westPane.setContinuousLayout(true);
    centerPane.setContinuousLayout(true);
    eastPane.setContinuousLayout(true);
    consoleDocPane.setContinuousLayout(true);

    final int dividerSize = 1;
    westPane.setDividerSize(dividerSize);
    centerPane.setDividerSize(dividerSize);
    eastPane.setDividerSize(dividerSize);
    consoleDocPane.setDividerSize(dividerSize);

    westPane.setBorder(null);
    centerPane.setBorder(null);
    eastPane.setBorder(null);
    libraryPane.setBorder(null);

    westPane.setResizeWeight(1);
    centerPane.setResizeWeight(0);
    eastPane.setResizeWeight(1);
    consoleDocPane.setResizeWeight(1);

    libraryPane.setMinimumSize(new Dimension(320, 0));
    vertexTable.getSwingComponent().setMinimumSize(new Dimension(320, 0));
    // TODO 需要在IConsole实例中维护一个
    console.getSwingComponent().setMinimumSize(new Dimension(0, 210));

    westPane.getRightComponent().setPreferredSize(
        new Dimension((int) vertexTable.getSwingComponent().getPreferredSize().getWidth(), 320)
    );
    eastPane.getRightComponent().setPreferredSize(vertexTable.getSwingComponent().getPreferredSize());

    libraryPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    docTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    /* eastPane is outer-most split pane */
    add(eastPane, BorderLayout.CENTER);
//    add(statusBar, BorderLayout.SOUTH);

    loadPalettes();
  }


  private void initActions() {
    ApplicationActionMap actionMap = (ApplicationActionMap) application.getApplicationAction();
    actionMap.put("editor.newDocument", new EditorNewDocumentAction(this));
    actionMap.put("editor.openDocument", new EditorOpenDocumentAction(this));
    actionMap.put("editor.closeDocument", new EditorCloseDocumentAction(this));
    actionMap.put("editor.saveDocument", new EditorSaveDocumentAction(this));
    actionMap.put("solver.calc", new SolverCalcAction(this));
    actionMap.put("solver.setting", new SolverSettingAction(this));
  }


  private void loadPalettes() {
    BiConsumer<JComponent, String> loadPalette = (palette, title) -> {
      final JScrollPane scrollPane = new JScrollPane(palette);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      libraryPane.add(title, scrollPane);
      libraryPane.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e)
        {
          int width = scrollPane.getWidth() - scrollPane.getVerticalScrollBar().getWidth();
          int cols = Math.max(1, width / 55);
          setPreferredSize(new Dimension(width, (getComponentCount() * 55 / cols) + 30));
          revalidate();
        }
      });
    };
    loadPalette.accept(predefinedPalette, MessageBundle.get("vertexStencil.predefined"));
    loadPalette.accept(userDefinedPalette, MessageBundle.get("vertexStencil.userDefined"));
    loadPalette.accept(vertexGroupPalette, MessageBundle.get("vertexGroup.name"));
  }


  private void loadStencils()
  {
    final IStencilContext context = ImplementationContext.INSTANCE.getStencilContext();
    final List<IVertexStencil> predefinedStencils = context.getPredefinedStencils();
    final List<IVertexStencil> userDefinedStencils = context.getUserDefinedStencils();
    BiConsumer<StencilPalette, List<IVertexStencil>> addStencils = (palette, stencils) -> {
      for (IVertexStencil stencil : stencils) {
        palette.addStencil(stencil);
      }
    };

    addStencils.accept(predefinedPalette, predefinedStencils);
    log.debug("load {} predefined stencils", predefinedPalette.getLoadStencilCount());
    addStencils.accept(userDefinedPalette, userDefinedStencils);
    log.debug("load {} user defined stencils", userDefinedPalette.getLoadStencilCount());
  }


  private void loadDocuments()
  {
    final List<Document> documents = getLastDocuments();
    if (documents != null) {
      openLastDocuments();
    }
  }


  public void addDocument(@NotNull Document document) {
    docTabbedPane.add(document.getTitle(), document.getSwingComponent());
    docTabbedPane.setTabComponentAt(currentDocumentIndex, new DocumentButtonTab(docTabbedPane));
    currentDocumentIndex = documents.size();
    documents.add(currentDocumentIndex, document);
    installGraphObservers((Graph) document.getGraph());
  }


  private void installGraphObservers(@NotNull Graph graph) {
    graph.getGraphObservable().addObserver(vertexGroupPalette.getVertexGroupObserver());
  }


  private void openLastDocuments()
  {
    log.debug("load last documents");
  }


  @Nullable
  private List<Document> getLastDocuments()
  {
    return null;
  }


  public DocumentStructure getDocumentStructure() {
    return documentStructure;
  }


  public @NotNull JTabbedPane getDocTabbedPane() {
    return docTabbedPane;
  }


  public int getCurrentDocumentIndex() {
    return currentDocumentIndex;
  }


  public IVertexTable getVertexTable() {
    return vertexTable;
  }


  public DocumentContext getDocumentContext() {
    return documentContext;
  }


  /**
   * 获取编辑的文档
   *
   * @return 编辑的文档，如果没有，返回{@code null}
   */
  public @Nullable Document getDocument()
  {
    if (documents.size() > 0) {
      return documents.get(currentDocumentIndex);
    }
    else {
      return null;
    }
  }


  @Override
  public @NotNull IApplication getApplication() {
    return application;
  }


  @Override
  public void setApplication(@NotNull IApplication application) {
    this.application = application;
  }


  @Override
  public @NotNull JComponent getSwingComponent() {
    return this;
  }


  public IConsole getConsole() {
    return console;
  }
}
