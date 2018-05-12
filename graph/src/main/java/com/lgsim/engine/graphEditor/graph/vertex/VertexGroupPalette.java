package com.lgsim.engine.graphEditor.graph.vertex;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
public class VertexGroupPalette extends JPanel {

  private static final Logger log = LoggerFactory.getLogger("VertexGroupPalette");

  private final VertexGroupObserver vertexGroupObserver;


  public VertexGroupPalette() {
    setLayout(new FlowLayout());
    vertexGroupObserver = new VertexGroupObserver(this::render);
  }


  private void render(@NotNull Graph graph, @NotNull IVertex vertex) {
    Collection<IVertex> vertexes = graph.getVertexes();
    boolean add = vertexes.contains(vertex);
    boolean remove = !add;
    if (add) {
      log.debug("capture graph changed, add {}", vertex);
    }
    if (remove) {
      log.debug("capture graph changed, remove {}", vertex);
    }
  }


  public VertexGroupObserver getVertexGroupObserver() {
    return vertexGroupObserver;
  }
}
