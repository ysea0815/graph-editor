package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.graph.IEditor;
import com.lgsim.engine.graphEditor.api.widget.IApplicationToolbar;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.graph.document.DocumentButtonTab;
import com.lgsim.engine.graphEditor.graph.document.DocumentContext;
import com.lgsim.engine.graphEditor.graph.stencil.StencilPalette;
import com.lgsim.engine.graphEditor.graph.stencil.StencilSupport;
import com.mxgraph.swing.mxGraphOutline;
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
public class Editor extends JPanel implements IEditor {

  private static final Logger log = LoggerFactory.getLogger(Editor.class);

  private IApplication application;
  private DocumentContext documentContext;
  private List<Document> documents;
  private int currentDocumentIndex;

  private mxGraphOutline graphOutline;
  private JTabbedPane libraryPane;
  private IVertexTable vertexTable;
  private JTabbedPane docTabbedPane;
  private StencilPalette predefinedPalette;
  private StencilPalette userDefinedPalette;


  public Editor(@NotNull IApplication application)
  {
    this.application = application;
    documentContext = new DocumentContext(application);
    documents = new Vector<>();
    initUIComponents();
    loadStencils();
    loadDocuments();
  }


  private void initUIComponents()
  {
    setLayout(new BorderLayout());

    graphOutline = new mxGraphOutline(null);
    libraryPane = new JTabbedPane();
    vertexTable = ImplementationContext.INSTANCE.getVertexTable();
    docTabbedPane = new JTabbedPane();
    JSplitPane westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane, graphOutline);
    JComponent console = ImplementationContext.INSTANCE.getConsole().getSwingComponent();
    JSplitPane consoleDocPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, docTabbedPane, console);
    JSplitPane centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPane, consoleDocPane);
    JSplitPane eastPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPane, vertexTable.getSwingComponent());
    predefinedPalette = StencilSupport.createStencilPalette();
    userDefinedPalette = StencilSupport.createStencilPalette();
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
    graphOutline.setMinimumSize(new Dimension(320, 320));
    vertexTable.getSwingComponent().setMinimumSize(new Dimension(500, 0));
    console.setMinimumSize(new Dimension(0, 210));

    westPane.getRightComponent().setPreferredSize(
        new Dimension((int) vertexTable.getSwingComponent().getPreferredSize().getWidth(), 320)
    );
    eastPane.getRightComponent().setPreferredSize(vertexTable.getSwingComponent().getPreferredSize());

    libraryPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    docTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    /* eastPane is outer-most split pane */
    add(eastPane, BorderLayout.CENTER);
//    add(statusBar, BorderLayout.SOUTH);

    loadStencilPalettes();
  }


  private void loadStencilPalettes() {
    BiConsumer<StencilPalette, String> loadStencilPalette = (palette, title) -> {
      final JScrollPane scrollPane = new JScrollPane(palette);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      libraryPane.add(title, scrollPane);
      libraryPane.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e)
        {
          int w = scrollPane.getWidth() - scrollPane.getVerticalScrollBar().getWidth();
          palette.setPreferredWidth(w);
        }
      });
    };
    loadStencilPalette.accept(predefinedPalette, MessageBundle.get("vertexStencil.predefined"));
    loadStencilPalette.accept(userDefinedPalette, MessageBundle.get("vertexStencil.userDefined"));
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
    if (documents == null) {
      openNewDocument();
    }
    else {
      openLastDocuments();
    }
  }


  public void openNewDocument()
  {
    Document document = new Document(this);
    this.addDocument(document);
  }


  private void addDocument(@NotNull Document document) {
    docTabbedPane.add(document.getTitle(), document.getSwingComponent());
    docTabbedPane.setTabComponentAt(currentDocumentIndex, new DocumentButtonTab(docTabbedPane));
    currentDocumentIndex = documents.size();
    documents.add(currentDocumentIndex, document);
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


  public @NotNull mxGraphOutline getGraphOutline() {
    return graphOutline;
  }


  public @NotNull IApplication getApplication() {
    return application;
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


  @Override
  public @Nullable Document getDocument()
  {
    if (documents.size() > 0) {
      return documents.get(currentDocumentIndex);
    }
    else {
      return null;
    }
  }
}
