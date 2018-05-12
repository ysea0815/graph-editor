package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.Editor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public abstract class EditorAction extends AbstractAction {

  protected Editor editor;


  public EditorAction(@NotNull Editor editor) {
    this.editor = editor;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
  }
}
