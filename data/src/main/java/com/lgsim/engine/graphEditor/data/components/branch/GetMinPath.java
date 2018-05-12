package com.lgsim.engine.graphEditor.data.components.branch;

import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestDijkstraAlgorithm;
import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestEdge;
import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestGraph;
import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestVertex;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 获得图的最短路径
 */
public class GetMinPath {

    //private List<TestVertex> nodes;
    private Map<String,TestVertex> nodes;
    private List<TestEdge> edges;
    private Map<String,TestVertex> vertices;

    /**
     * 获取最短路径
     * @param path 数据路径
     * @param start 开始节点
     * @param end 结束节点
     */
    public List<String> getMinpath(String path, String start, String end) {

        BranchPoint branchPoint = new BranchPoint();
        List<String> minPath = new ArrayList<>();
        //String minPath = "";

        //nodes = new ArrayList<>();
        nodes = new HashMap<>();
        edges = new ArrayList<>();
        //vertices = branchPoint.getPoint(path);
        //vertices = branchPoint.getAllVertices(path);
        nodes = branchPoint.getAllVertices(path);
        /*for (Map.Entry<String,TestVertex> entry : vertices.entrySet()) {
            TestVertex location = new TestVertex(entry.getKey(),entry.getKey());
            //nodes.add(location);
            nodes = branchPoint.getAllVertices(path);

        }*/

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String jsonstr = null;
        try {
            jsonstr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }

        JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        JSONObject components = JSONObject.fromObject(jsonObject.get("Components"));
        JSONArray coms = JSONArray.fromObject(components.get("Component"));

        for (Object comObject : coms) {
            JSONObject jComObject = (JSONObject)comObject;
            String type = jComObject.getString("Type");

            if (!type.equals("1") && !type.equals("2")) {
                JSONArray arms = JSONArray.fromObject(jComObject.get("ArmNodes"));
                /*int sourceLocNo = arms.getInt(0) - 1;
                //int
                int destLocNo = arms.getInt(1) - 1;*/
                String sourceLocNo = arms.getString(0);
                String cent = jComObject.getString("Name") + "_" + type;
                String destLocNo = arms.getString(1);
                //addLane("",sourceLocNo,destLocNo,0);
                addLane("",sourceLocNo,cent,0);
                addLane("",cent,destLocNo,0);
            }

        }
        TestGraph graph = new TestGraph(nodes,edges);
        TestDijkstraAlgorithm dijkstra = new TestDijkstraAlgorithm(graph);

        dijkstra.execute(nodes.get(start));
        LinkedList<TestVertex> vertexPath = dijkstra.getPath(nodes.get(end));

        for (TestVertex vertex : vertexPath) {
            minPath.add(vertex.getName());
            //minPath = min.toString();
            //System.out.println(vertex);
        }
        return minPath;
    }

    /**
     * 添加节点
     * @param laneId 节点id
     * @param sourceLocNo 起始节点的下标
     * @param destLocNo 结束节点的下标
     * @param duration
     */
    private void addLane(String laneId,String sourceLocNo,String destLocNo,int duration) {
        TestEdge lane = new TestEdge(laneId,nodes.get(sourceLocNo),nodes.get(destLocNo),duration);
        edges.add(lane);
    }
}
