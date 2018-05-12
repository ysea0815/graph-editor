package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.util.exception.EncodeException;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class GraphCodecImplTest {

    @Test
    public void encode() throws EncodeException {
        MockGraph graph = new MockGraph();
        GraphCodecImpl codec = new GraphCodecImpl();
        byte[] bytes = codec.encode(graph);
        String str = new String(bytes);
        System.out.println(str);
    }

    @Test
    public void decode() {
    }
}