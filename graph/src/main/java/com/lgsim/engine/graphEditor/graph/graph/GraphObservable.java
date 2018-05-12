package com.lgsim.engine.graphEditor.graph.graph;

import org.jetbrains.annotations.NotNull;

import java.util.Observable;

@SuppressWarnings("WeakerAccess")
public class GraphObservable extends Observable {

  private Graph graph;


  public GraphObservable(@NotNull Graph graph) {
    this.graph = graph;
  }


  public @NotNull Graph getGraph() {
    return graph;
  }


  public void setGraph(@NotNull Graph graph) {
    this.graph = graph;
  }


  @Override
  public synchronized void setChanged() {
    super.setChanged();
  }


  @Override
  public synchronized void clearChanged() {
    super.clearChanged();
  }
}
