package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

@SuppressWarnings("WeakerAccess")
public class EditorSaveDocumentAction extends EditorAction {

  private static final Logger log = LoggerFactory.getLogger(EditorSaveDocumentAction.class);
  private JFileChooser saveLocationChooser;


  public EditorSaveDocumentAction(Editor editor) {
    super(editor);
    saveLocationChooser = new JFileChooser();
  }


  @Override
  public void actionPerformed(ActionEvent event)
  {
    int code = saveLocationChooser.showOpenDialog(editor.getSwingComponent());
    if (code == JFileChooser.APPROVE_OPTION) {
      File entry = saveLocationChooser.getSelectedFile();
      Document document = editor.getDocument();
      if (document != null) {
        document.setEntryFilePath(entry.getPath());
        editor.getDocumentContext().put(document);
        log.debug("save document {}", document);
      }
    }
  }
}
