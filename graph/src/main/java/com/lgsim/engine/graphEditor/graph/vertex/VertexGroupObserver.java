package com.lgsim.engine.graphEditor.graph.vertex;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphObservable;
import org.jetbrains.annotations.NotNull;

import java.util.Observable;
import java.util.Observer;
import java.util.function.BiConsumer;

@SuppressWarnings("WeakerAccess")
public class VertexGroupObserver implements Observer {

  private BiConsumer<Graph, IVertex> changeCallback;


  public VertexGroupObserver(@NotNull BiConsumer<Graph, IVertex> changeCallback) {
    this.changeCallback = changeCallback;
  }


  @Override
  public void update(Observable o, Object arg) {
    GraphObservable observable = (GraphObservable) o;
    changeCallback.accept(observable.getGraph(), (IVertex) arg);
  }
}
