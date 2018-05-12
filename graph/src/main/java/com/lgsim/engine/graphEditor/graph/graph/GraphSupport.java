package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.CollectionUtil;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventSource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public class GraphSupport {

  public static @Nullable IVertex extractVertex(@NotNull mxCell cell) {
    Object value = cell.getValue();
    if (value instanceof IVertex) {
      return (IVertex) value;
    }
    else {
      return null;
    }
  }


  public static void applyGraphSettings(@NotNull Graph graph) {
    graph.setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
    graph.setSplitEnabled(false);
    graph.setAllowDanglingEdges(false);
    graph.setCellsResizable(false);
    graph.setCellsEditable(false);
    graph.setKeepEdgesInForeground(true);
  }


  public static void repaintCavity(boolean disableContainsCavities, @NotNull Graph graph,
                                   @NotNull VertexImpl src, @NotNull VertexImpl dst)
  {
    mxCell source = graph.getSourceNode();
    mxCell target = graph.getTargetNode();
    mxCell edge = graph.getEdge();
    if (disableContainsCavities) {
      boolean notContains = !GraphSupport.isCavity(source) && !GraphSupport.isCavity(target);
      if (notContains) {
        repaintCavity(source, edge, target, graph, src, dst);
      }
    }
    else {
      repaintCavity(source, edge, target, graph, src, dst);
    }
  }


  private static void repaintCavity(@NotNull mxCell source, @NotNull mxCell edge,
                                    @NotNull mxCell target, @NotNull Graph graph,
                                    @NotNull VertexImpl src, @NotNull VertexImpl dst)
  {
    graph.removeCells(new mxCell[]{edge});
    Point position = getCavityPosition(source.getGeometry().getPoint(), target.getGeometry().getPoint());
    Object p = graph.getDefaultParent();
    mxCell cavityCell = createCavityCell(position, p, graph);
    VertexImpl cavity = (VertexImpl) cavityCell.getValue();
    graph.insertEdge(p, null, null, source, cavityCell);
    graph.insertEdge(p, null, null, cavityCell, target);
    GraphHook.vertexConnected(src, cavity);
    GraphHook.vertexConnected(cavity, dst);
  }


  private static @NotNull mxCell createCavityCell(@NotNull Point position, @NotNull Object p, @NotNull Graph graph) {
    final IVertexStencil stencil = ImplementationContext.INSTANCE.getStencilContext().getCavityStencil();
    VertexImpl vertex = GraphSupport.createVertex(stencil, true);
    mxCell cell = (mxCell) graph.insertVertex(p, null, vertex, position.x, position.y, 48, 48);
    GraphHook.vertexAdded(vertex, graph.getVertexCounter(), graph);
    settingCavityCellStyle(cell, stencil);
    return cell;
  }


  private static void settingCavityCellStyle(@NotNull mxCell cavity, @NotNull IVertexStencil stencil) {
    String icon = stencil.getGraphIcon();
    cavity.setStyle("glass=1;rounded=1;shadow=1;imageWidth=32;imageHeight=32;arcSize=48;icon;image=/" + icon);
  }


  private static Point getCavityPosition(Point from, Point to)
  {
    int x = (from.x + to.x) / 2;
    int y = (from.y + to.y) / 2;
    return new Point(x, y);
  }


  public static mxEventSource.mxIEventListener cellsMovedListener(@NotNull Graph graph) {
    return cellsMovedListener(cells -> {
      for (mxCell cell : cells) {
        if (isCavity(cell)) {
          GraphHook.cavityCellMoved(cell, graph);
        }
      }
    });
  }


  private static mxEventSource.mxIEventListener cellsMovedListener(@NotNull Consumer<mxCell[]> movedCellsConsumer) {
    return (sender, evt) -> {
      Object[] xs = (Object[]) evt.getProperties().get("cells");
      if (xs != null) {
        mxCell[] cells = new mxCell[xs.length];
        int idx = 0;
        for (Object x : xs) {
          if (x instanceof mxCell) {
            cells[idx] = (mxCell) x;
            idx += 1;
          }
        }
        movedCellsConsumer.accept(Arrays.copyOfRange(cells, 0, idx));
      }
    };
  }


  /* predicates */
  public static boolean isCavity(@NotNull mxCell cell) {
    IVertex vertex = extractVertex(cell);
    if (vertex == null) {
      return false;
    }
    else {
      return vertex.isCavity();
    }
  }


  @Contract(pure = true)
  public static @NotNull VertexImpl createVertex(@NotNull IVertexStencil stencil, boolean cavity)
  {
    String ID = "";
    String typeID = stencil.getID();
    List<IVertexArgument> arguments = CollectionUtil.cloneList(stencil.getArguments());
    List<IVertexOutput> outputs = CollectionUtil.cloneList(stencil.getOutputs());
    List<IVertex> inputPorts = CollectionUtil.emptyList();
    List<IVertex> outputPorts = CollectionUtil.emptyList();
    return new VertexImpl(ID, typeID, arguments, outputs, inputPorts, outputPorts, cavity, ID);
  }
}
