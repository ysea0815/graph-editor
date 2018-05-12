package com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo;

import java.util.List;
import java.util.Map;

/**
 * 图类
 */
public class TestGraph {
    //private List<TestVertex> vertices;//节点集合
    private Map<String,TestVertex> vertices;//节点集合
    private List<TestEdge> edges;//边界集合

    /*public TestGraph(List<TestVertex> vertices, List<TestEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }*/

    public TestGraph(Map<String, TestVertex> vertices, List<TestEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    /*public List<TestVertex> getVertices() {
        return vertices;
    }*/
    public Map<String, TestVertex> getVertices() {
        return vertices;
    }

    public List<TestEdge> getEdges() {
        return edges;
    }
}
