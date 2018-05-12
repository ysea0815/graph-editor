package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.graph.graph.Graph;
import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public class DocumentGraphCodec {

  public static @NotNull byte[] encode(@NotNull Graph graph) {
    return SerializationUtils.serialize(graph);
  }


  public static @NotNull Graph decode(@NotNull byte[] graph) {
    Object o = SerializationUtils.deserialize(graph);
    return (Graph) o;
  }
}
