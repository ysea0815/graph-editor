package com.lgsim.engine.graphEditor.data.components.branch;

import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestVertex;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 分支的所有点
 */
public class BranchPoint {

    /**
     * 获取所有腔节点并将所有节点分类
     * @param path json文件路径
     * @return Map<节点类型,节点>
     */
    public Map<String,TestVertex> getPoint(String path) {

        Map<String,TestVertex> points = new HashMap<>();
        List<String> armps = new ArrayList<>();

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

            JSONObject jcomObject = (JSONObject)comObject;
            JSONArray arms = JSONArray.fromObject(jcomObject.get("ArmNodes"));

            for (int i = 0;i < arms.size();i ++) {
                armps.add(arms.getString(i));
            }
        }

        Map<String,Integer> maps = getCommonPoint(armps);
        for (Map.Entry<String,Integer> entry : maps.entrySet()) {
            TestVertex vertex = new TestVertex();
            if (entry.getValue() > 2) {
                vertex.setId("3");
                vertex.setName(entry.getKey());
                points.put(vertex.getName(),vertex);
            }else {
                a:for (Object comObject : coms) {

                    JSONObject jcomObject = (JSONObject)comObject;
                    String type = jcomObject.getString("Type");
                    JSONArray arms = JSONArray.fromObject(jcomObject.get("ArmNodes"));

                    for (int i = 0;i < arms.size();i++) {
                        if (arms.getString(i).equals(entry.getKey())) {
                            if (type.equals("1") || type.equals("2")) {
                                //points.put(entry.getKey(),"1");
                                vertex.setId("1");
                                vertex.setName(entry.getKey());
                                points.put(vertex.getName(),vertex);
                                break a;
                            }else {
                                //points.put(entry.getKey(),"2");
                                vertex.setId("2");
                                vertex.setName(entry.getKey());
                                points.put(vertex.getName(),vertex);
                            }
                        }
                    }
                }
            }
        }
        return points;
    }

    /**
     * 获得所有节点中相同的节点，并将节点和该节点的数量存入Map中
     * @param list 存着所有节点的集合
     * @return 点和该节点的数量的map集合
     */
    public Map<String,Integer> getCommonPoint(List<String> list) {
        List<String> list2 = new ArrayList<>();
        Map<String,Integer> maps = new HashMap<>();

        for (int i = 0;i < list.size();i ++) {
            for (int j = list.size() - 1;j > i;j --) {
                if(list.get(j).equals(list.get(i)) && list2.contains(list.get(i)) == false) {
                    list2.add(list.get(j));
                }
            }
        }
        for (int z = 0;z < list2.size();z++) {
            int index = 0;
            for (int x = 0;x < list.size();x++) {
                if (list2.get(z).equals(list.get(x))) {
                    ++index;
                }
                maps.put(list2.get(z),index);
            }
        }

        return maps;
    }

    /**
     * 获取图中所有节点
     * @param path 数据路径
     * @return 节点集合
     */
    public Map<String,TestVertex> getAllVertices(String path) {

        Map<String,TestVertex> vertices = new TreeMap<>();
        /*Map<String,String> vertices1 = new HashMap<>();
        Map<String,String> vertices2 = new HashMap<>();*/
        vertices = getPoint(path);

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
            TestVertex vertex = new TestVertex();
            JSONObject jComObject = (JSONObject)comObject;
            String type = jComObject.getString("Type");
            String name = jComObject.getString("Name");

            if (!type.equals("1") && !type.equals("2")) {
                //vertices.put(name + "_" + type,type);
                vertex.setName(name + "_" + type);
                vertex.setId(type);
                vertices.put(vertex.getName(),vertex);
            }
        }

        return vertices;
    }
}
