package com.lgsim.engine.graphEditor.graph.vertex;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class VertexSupport {

  @Contract(pure = true)
  public static @NotNull VertexGroupPalette createVertexPalette() {
    return new VertexGroupPalette();
  }


  public static @NotNull Map<String, List<IVertex>> groupByVertexType(@NotNull List<IVertex> vertices) {
    Map<String, List<IVertex>> map = new Hashtable<>();
    for (IVertex vertex : vertices) {
      String typeID = vertex.getTypeID();
      List<IVertex> ls = map.computeIfAbsent(typeID, k -> new Vector<>());
      ls.add(vertex);
    }
    return map;
  }


  public static @Nullable String lookupVertexTypeName(@NotNull IStencilContext context, @NotNull String typeID) {
    List<IVertexStencil> stencils = context.getPredefinedStencils();
    stencils.addAll(context.getUserDefinedStencils());
    for (IVertexStencil stencil : stencils) {
      if (stencil.getID().equals(typeID)) {
        return stencil.getName();
      }
    }
    return null;
  }
}
