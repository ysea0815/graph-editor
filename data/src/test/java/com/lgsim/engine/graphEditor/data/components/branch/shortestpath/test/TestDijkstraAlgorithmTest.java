package com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test;

import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestDijkstraAlgorithm;
import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestEdge;
import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestGraph;
import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestVertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TestDijkstraAlgorithmTest {

    private List<TestVertex> nodes;
    private List<TestEdge> edges;

    @Test
    public void execute() {
        /*nodes = new ArrayList<>();
        edges = new ArrayList<>();

        for (int i = 0;i < 11; i++) {
            TestVertex location = new TestVertex("Node_" + i,"Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0",0,1,85);
        addLane("Edge_1",0,2,217);
        addLane("Edge_2",0,4,173);
        addLane("Edge_3",2,6,186);
        addLane("Edge_4",2,7,103);
        addLane("Edge_5",3,7,183);
        addLane("Edge_6",5,8,250);
        addLane("Edge_7",8,9,84);
        addLane("Edge_8",7,9,167);
        addLane("Edge_9",4,9,502);
        addLane("Edge_10",9,10,40);
        addLane("Edge_11",1,10,600);

        TestGraph graph = new TestGraph(nodes,edges);
        TestDijkstraAlgorithm dijkstra = new TestDijkstraAlgorithm(graph);

        dijkstra.execute(nodes.get(0));
        LinkedList<TestVertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (TestVertex vertex : path) {
            System.out.println(vertex);
        }*/
    }

    /**
     * 添加节点
     * @param laneId 节点id
     * @param sourceLocNo 起始节点的下标
     * @param destLocNo 结束节点的下标
     * @param duration
     */
    private void addLane(String laneId,int sourceLocNo,int destLocNo,int duration) {
        TestEdge lane = new TestEdge(laneId,nodes.get(sourceLocNo),nodes.get(destLocNo),destLocNo);
        edges.add(lane);
    }
}