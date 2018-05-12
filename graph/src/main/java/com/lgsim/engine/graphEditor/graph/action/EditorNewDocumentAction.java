package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.graph.graph.Graph;

import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class EditorNewDocumentAction extends EditorAction {

  public EditorNewDocumentAction(Editor editor) {
    super(editor);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    Graph graph = new Graph();
    Document document = new Document(editor, graph);
    editor.addDocument(document);
  }
}
