package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("WeakerAccess")
public class GraphHook {

  private static final Logger log = LoggerFactory.getLogger(GraphHook.class);


  public static void vertexAdded(@NotNull VertexImpl vertex, @NotNull IntCounter counter, @NotNull Graph graph) {
    String id = counter.inc() + "";
    vertex.setID(id);
    vertex.setDisplayName(id);
    graph.addVertex(vertex);
    notifyGraphChanged(graph.getGraphObservable(), vertex);
  }


  private static void notifyGraphChanged(@NotNull GraphObservable observable, @NotNull IVertex vertex) {
    observable.setChanged();
    observable.notifyObservers(vertex);
  }


  public static void vertexRemoved(@NotNull VertexImpl vertex, @NotNull Graph graph) {
    for (IVertex v : vertex.getInputPorts()) {
      v.getOutputPorts().remove(vertex);
    }
    for (IVertex v : vertex.getOutputPorts()) {
      v.getInputPorts().remove(vertex);
    }
    graph.removeVertex(vertex);
    notifyGraphChanged(graph.getGraphObservable(), vertex);
  }


  public static void vertexConnected(@NotNull VertexImpl src, @NotNull VertexImpl dst) {
    boolean cavity = src.isCavity() || dst.isCavity();
    if (cavity) {
      if (!src.getOutputPorts().contains(dst)) {
        src.getOutputPorts().add(dst);
      }
      if (!dst.getInputPorts().contains(src)) {
        dst.getInputPorts().add(src);
      }
    }
  }


  public static void cavityCellMoved(@Nullable mxCell cell, @NotNull Graph graph) {
    log.debug("refresh after cavity cell {} moved", cell);
    graph.refresh();
  }
}
