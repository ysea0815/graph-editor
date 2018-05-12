package com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo;

/**
 * 边界类
 */
public class TestEdge {
    private String id;
    private TestVertex source;//起点
    private TestVertex destination;//终点
    private int weight;

    public TestEdge(String id, TestVertex source, TestVertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestVertex getSource() {
        return source;
    }

    public void setSource(TestVertex source) {
        this.source = source;
    }

    public TestVertex getDestination() {
        return destination;
    }

    public void setDestination(TestVertex destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
