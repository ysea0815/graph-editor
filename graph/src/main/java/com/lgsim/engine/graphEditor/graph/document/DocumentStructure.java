package com.lgsim.engine.graphEditor.graph.document;

import com.mxgraph.swing.mxGraphOutline;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class DocumentStructure extends JPanel {

  public DocumentStructure() {
    setLayout(new BorderLayout());
  }


  public void setGraphOutline(@NotNull mxGraphOutline graphOutline) {
    graphOutline.setMinimumSize(new Dimension(320, 320));
    add(graphOutline);
    revalidate();
  }
}
