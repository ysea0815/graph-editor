package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxConnectionConstraint;
import com.mxgraph.view.mxGraph;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("WeakerAccess")
public class Graph extends mxGraph implements IGraph {

  private final IntCounter vertexCounter = new IntCounter(1);
  private transient mxCell sourceNode;
  private transient mxCell targetNode;
  private transient mxCell edge;
  private final Collection<IVertex> vertexes = new Vector<>();
  private final GraphObservable graphObservable = new GraphObservable(this);


  public Graph()
  {
    initialize();
  }


  public void initialize() {
    GraphSupport.applyGraphSettings(this);
    addListener(mxEvent.CELLS_MOVED, GraphSupport.cellsMovedListener(this));
  }


  public mxCell getSourceNode() {
    return sourceNode;
  }


  public void setSourceNode(mxCell sourceNode) {
    this.sourceNode = sourceNode;
  }


  public mxCell getTargetNode() {
    return targetNode;
  }


  public void setTargetNode(mxCell targetNode) {
    this.targetNode = targetNode;
  }


  public mxCell getEdge() {
    return edge;
  }


  public void setEdge(mxCell edge) {
    this.edge = edge;
  }


  @Override
  public String getToolTipForCell(Object cell)
  {
    return "";
  }


  @Override
  public boolean isCellSelectable(Object cell)
  {
    return !model.isEdge(cell);
  }


  @Override
  public void cellsAdded(Object[] cells, Object parent, Integer index, Object source, Object target, boolean absolute) {
    if (cells != null) {
      for (Object x : cells) {
        if (x instanceof mxCell) {
          mxCell cell = (mxCell) x;
          Object value = cell.getValue();
          if (value instanceof VertexImpl) {
            VertexImpl vertex = (VertexImpl) value;
            GraphHook.vertexAdded(vertex, vertexCounter, this);
          }
        }
      }
    }
    super.cellsAdded(cells, parent, index, source, target, absolute);
  }


  @Override
  public Object createEdge(Object parent, String id, Object value, Object source, Object target, String style)
  {
    final mxCell edge = (mxCell) super.createEdge(parent, id, value, source, target, style);
    /* remove vertexes then remove connected edges respectively */
    edge.getGeometry().setRelative(false);
    return edge;
  }


  @Override
  public void cellConnected(Object edge, Object terminal, boolean source, mxConnectionConstraint constraint) {
    if (edge instanceof mxCell) {
      setEdge((mxCell) edge);
    }
    if (terminal instanceof mxCell) {
      if (source) {
        setSourceNode((mxCell) terminal);
      }
      else {
        setTargetNode((mxCell) terminal);
        Object srcVal = getSourceNode().getValue();
        Object dstVal = getTargetNode().getValue();
        if ((srcVal instanceof VertexImpl) && (dstVal instanceof VertexImpl)) {
          VertexImpl srcVertex = (VertexImpl) srcVal;
          VertexImpl dstVertex = (VertexImpl) dstVal;
          if (srcVertex.isCavity() || dstVertex.isCavity()) {
            GraphHook.vertexConnected(srcVertex, dstVertex);
          }
          else {
            GraphSupport.repaintCavity(true, this, srcVertex, dstVertex);
          }
        }
      }
    }
    super.cellConnected(edge, terminal, source, constraint);
  }


  @Override
  public void cellsRemoved(Object[] cells) {
    if (cells != null) {
      for (Object x : cells) {
        if (x instanceof mxCell) {
          Object value = ((mxCell) x).getValue();
          if (value instanceof VertexImpl) {
            GraphHook.vertexRemoved((VertexImpl) value, this);
          }
        }
      }
    }
    super.cellsRemoved(cells);
  }


  @Override
  public @NotNull Collection<IVertex> getVertexes()
  {
    return vertexes;
  }


  @Override
  public void retrieveCalcOutputs(@NotNull IGraph graph) {
    for (IVertex v : vertexes) {
      List<IVertexOutput> outputs = lookupOutputs(graph, v);
      if (outputs != null) {
        v.setOutputs(outputs);
      }
    }
  }


  private @Nullable List<IVertexOutput> lookupOutputs(@NotNull IGraph graph, @NotNull IVertex vertex) {
    for (IVertex v : graph.getVertexes()) {
      if (v.getID().equals(vertex.getID())) {
        return v.getOutputs();
      }
    }
    return null;
  }


  public void addVertex(@NotNull IVertex vertex) {
    vertexes.add(vertex);
  }


  public void removeVertex(@NotNull IVertex vertex) {
    vertexes.remove(vertex);
  }


  public IntCounter getVertexCounter() {
    return vertexCounter;
  }


  public GraphObservable getGraphObservable() {
    return graphObservable;
  }
}
