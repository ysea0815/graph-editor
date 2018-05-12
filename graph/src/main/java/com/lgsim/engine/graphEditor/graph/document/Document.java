package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.action.*;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings({"WeakerAccess"})
public class Document extends GraphDocumentImpl implements IApplicationWidget {

  private Editor editor;
  private IApplication application;
  private final GraphComponent swingComponent;


  public Document(@NotNull Editor editor, @NotNull Graph graph)
  {
    this.editor = editor;
    application = editor.getApplication();
    swingComponent = new GraphComponent(graph);
    setGraph(graph);
    settingGraphComponent();
    DocumentSupport.installOutlineListeners(this);
    DocumentSupport.installGraphDocumentListeners(this);
    initActions();
  }


  private void settingGraphComponent() {
    swingComponent.setMinimumSize(new Dimension(320, 320));
    swingComponent.setColumnHeaderView(new DocumentRuler(swingComponent, DocumentRuler.ORIENTATION_HORIZONTAL));
    swingComponent.setRowHeaderView(new DocumentRuler(swingComponent, DocumentRuler.ORIENTATION_VERTICAL));
  }


  private void initActions() {
    ApplicationActionMap actionMap = (ApplicationActionMap) getApplicationAction();
    actionMap.put("vertexCell.copy", new VertexCellCopyAction(this));
    actionMap.put("vertexCell.paste", new VertexCellPasteAction(this));
    actionMap.put("vertexCell.delete", new VertexCellDeleteAction(this));
    actionMap.put("vertexCell.cut", new VertexCellCutAction(this));
  }


  @Override
  public @NotNull String getTitle()
  {
    return MessageBundle.get("graphDocument.newDocumentTitle") + (isModified() ? "*" : "");
  }


  @Override
  public @NotNull mxGraphComponent getSwingComponent()
  {
    return swingComponent;
  }


  public @NotNull IApplicationAction getApplicationAction() {
    return getApplication().getApplicationAction();
  }


  @Override
  public @NotNull IApplication getApplication() {
    return application;
  }


  @Override
  public void setApplication(@NotNull IApplication application) {
    this.application = application;
  }


  public Editor getEditor() {
    return editor;
  }


  @Override
  public String toString() {
    return getTitle();
  }
}
