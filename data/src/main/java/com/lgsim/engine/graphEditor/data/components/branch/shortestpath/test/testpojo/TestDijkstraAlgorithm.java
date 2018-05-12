package com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo;


import java.util.*;

/**
 * 算法类
 */
public class TestDijkstraAlgorithm {
    //private List<TestVertex> nodes;//节点
    private Map<String,TestVertex> nodes;//节点
    private List<TestEdge> edges;//边界
    private Set<TestVertex> settledNodes;//固定的点
    private Set<TestVertex> unSettledNodes;//不固定的点
    private Map<TestVertex,TestVertex> predecessors;
    private Map<TestVertex,Integer> distance;

    public TestDijkstraAlgorithm(TestGraph graph) {
        //this.nodes = new ArrayList<>(graph.getVertices());
        this.nodes = new HashMap<>(graph.getVertices());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    /**
     * 执行
     * @param source 节点
     */
    public void execute(TestVertex source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();

        distance.put(source,0);//起点
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
            TestVertex node = getMiniumum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    /**
     * 发现最小距离
     * @param node 节点
     */
    private void findMinimalDistances(TestVertex node) {
        List<TestVertex> adjacentNodes = getNeighbors(node);

        for (TestVertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node,target)) {
                distance.put(target,getShortestDistance(node) + getDistance(node,target));
                predecessors.put(target,node);
                unSettledNodes.add(target);
            }
        }
    }

    /**
     * 获得初始节点与目标节点的距离
     * @param node 初始节点
     * @param target 目标节点
     * @return 距离
     */
    private int getDistance(TestVertex node,TestVertex target) {
        for (TestEdge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("should not happen");
    }

    /**
     * 获得邻近节点
     * @param node 节点
     * @return 邻近节点集合
     */
    private List<TestVertex> getNeighbors(TestVertex node) {
        List<TestVertex> neighbors = new ArrayList<>();
        for (TestEdge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    /**
     * 判断是否是固定的点
     * @param vertex 节点
     * @return true 是 false 不是
     */
    private boolean isSettled(TestVertex vertex) {
        return settledNodes.contains(vertex);
    }

    /**
     * 获得最小距离节点
     * @param vertices
     * @return
     */
    private TestVertex getMiniumum(Set<TestVertex> vertices) {
        TestVertex minimum = null;
        for (TestVertex vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            }else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    /**
     * 获得最小距离
     * @param destination 终点
     * @return 距离
     */
    private int getShortestDistance(TestVertex destination) {
        Integer d = distance.get(destination);

        if (d == null) {
            return Integer.MAX_VALUE;
        }else {
            return d;
        }
    }

    /**
     * 获得路径
     * @param target 目标节点
     * @return path 路径
     */
    public LinkedList<TestVertex> getPath(TestVertex target) {
        LinkedList<TestVertex> path = new LinkedList<>();
        TestVertex step = target;

        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);

        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }
}
