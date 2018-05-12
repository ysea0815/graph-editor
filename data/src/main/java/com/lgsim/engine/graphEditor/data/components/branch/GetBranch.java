package com.lgsim.engine.graphEditor.data.components.branch;

import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestVertex;
import com.lgsim.engine.graphEditor.data.components.util.tool.GetComponents;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取所有分支
 */
public class GetBranch {

    /**
     * 获取所有分支数据
     * @param path
     * @return
     */
    public List<List<String>> getBranch(String path) {

        List<List<String>> branches = new ArrayList<>();
        Map<String,TestVertex> inputMaps = getInputCavityVertex(path);
        Map<String,TestVertex> outputMaps = getOutputCavityVertex(path);
        Map<String,TestVertex> branchMaps = getBranchVertex(path);
        List<String> inputs = new ArrayList<>();
        List<String> outputs = new ArrayList<>();
        List<String> branchVertex = new ArrayList<>();
        GetMinPath getPath = new GetMinPath();

        for (Map.Entry<String,TestVertex> entryIn : inputMaps.entrySet()) {
            inputs.add(entryIn.getKey());
        }
        for (Map.Entry<String,TestVertex> entryOut : outputMaps.entrySet()) {
            outputs.add(entryOut.getKey());
        }
        for (Map.Entry<String,TestVertex> entryBranch : branchMaps.entrySet()) {
            branchVertex.add(entryBranch.getKey());
        }

        a : for (int i = 0;i < inputs.size();i++) {
                b : for (int j = 0;j < outputs.size();j++) {
                    String start = inputs.get(i);
                    String end = outputs.get(j);
                    int index = 0;

                    if (start.equals(end)) {
                        outputs.remove(end);
                        --j;
                    }else if (!start.equals(end)) {
                        try {
                            List<String> singlePath = getPath.getMinpath(path,start,end);
                            c : for (String vertex : branchVertex) {
                                if (singlePath.get(0).equals(vertex) || singlePath.get(singlePath.size() - 1).equals(vertex)) {
                                    for (int k = 1;k < singlePath.size() - 1;k++) {
                                        for (String vex : branchVertex) {
                                            if (singlePath.get(k).equals(vex)) {
                                                ++index;
                                            }
                                        }

                                    }
                                    if (index == 0) {
                                        branches.add(singlePath);
                                        break c;
                                    }
                                //break b;
                                }
                            }
                        } catch (Exception e) {
                            e.getStackTrace();
                        }

                    }
                }
        }

        return branches;
    }

    /**
     * 获取输入腔节点
     * @param path json路径
     * @return 输入腔节点集合
     */
    public Map<String,TestVertex> getInputCavityVertex(String path) {

        BranchPoint branchPoint = new BranchPoint();
        Map<String,TestVertex> allCavities = branchPoint.getPoint(path);
        Map<String,TestVertex> inputCavityVertex = new HashMap<>();

        for (Map.Entry<String,TestVertex> entry : allCavities.entrySet()) {
            if (entry.getValue().getId().equals("1")) {
                GetComponents getComs = new GetComponents();
                JSONArray coms = getComs.getComponents(path);

                for (Object comObject : coms) {
                    JSONObject jCom = (JSONObject)comObject;
                    JSONArray arms = JSONArray.fromObject(jCom.get("ArmNodes"));
                    if (jCom.getString("Type").equals("1")) {
                        for (int i = 0;i < arms.size();i++) {
                            if (entry.getKey().equals(arms.getString(i))) {
                                inputCavityVertex.put(entry.getKey(),entry.getValue());
                            }
                        }
                    }
                }
            }else if (entry.getValue().getId().equals("3")){
                inputCavityVertex.put(entry.getKey(),entry.getValue());
            }
        }
        return inputCavityVertex;
    }

    /**
     * 获得输出腔节点
     * @param path json路径
     * @return 输出腔节点集合
     */
    public Map<String,TestVertex> getOutputCavityVertex(String path) {

        BranchPoint branchPoint = new BranchPoint();
        Map<String,TestVertex> allCavities = branchPoint.getPoint(path);
        Map<String,TestVertex> outputCavityVertex = new HashMap<>();

        for (Map.Entry<String,TestVertex> entry : allCavities.entrySet()) {

            if (entry.getValue().getId().equals("1")) {
                GetComponents getComs = new GetComponents();
                JSONArray coms = getComs.getComponents(path);

                for (Object comObject : coms) {
                    JSONObject jCom = (JSONObject)comObject;
                    JSONArray arms = JSONArray.fromObject(jCom.get("ArmNodes"));

                    if (jCom.getString("Type").equals("2")) {
                        for (int i = 0;i < arms.size();i++) {
                            if (entry.getKey().equals(arms.getString(i))) {
                                outputCavityVertex.put(entry.getKey(),entry.getValue());
                            }
                        }
                    }
                }
            }else if (entry.getValue().getId().equals("3")) {
                outputCavityVertex.put(entry.getKey(),entry.getValue());
            }
        }
        return outputCavityVertex;
    }

    /**
     * 获得所有腔节点中的"3"类型点
     * @param path json路径
     * @return "3"类型点集合
     */
    public Map<String,TestVertex> getBranchVertex(String path) {

        BranchPoint branchPoint = new BranchPoint();
        Map<String,TestVertex> allCavities = branchPoint.getPoint(path);
        Map<String,TestVertex> branchVertices = new HashMap<>();

        for (Map.Entry<String,TestVertex> entry : allCavities.entrySet()) {

            if (entry.getValue().getId().equals("3")) {
                branchVertices.put(entry.getKey(),entry.getValue());
            }
        }
        return branchVertices;
    }
}
