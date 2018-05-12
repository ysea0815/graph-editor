package com.lgsim.engine.graphEditor.data.components.branch;

import com.lgsim.engine.graphEditor.data.components.branch.shortestpath.test.testpojo.TestVertex;

import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {

        BranchPoint branchPoint = new BranchPoint();
        GetBranch getBranch = new GetBranch();
        String path = "com/lgsim/engine/graphEditor/data/test/test1.json";
        String path1 = "com/lgsim/engine/graphEditor/data/test/test2.json";
        //Map<String,String> list = branchPoint.getAllVertices(path);
        Map<String,TestVertex> list = branchPoint.getAllVertices(path1);
        GetMinPath getMinPath = new GetMinPath();
        getMinPath.getMinpath(path,"1","4_111");

        System.out.println("--------------------");
        System.out.println("所有节点:" + list);
        System.out.println("--------------------");
        System.out.println("输入腔节点:" + getBranch.getInputCavityVertex(path1));
        System.out.println("--------------------");
        System.out.println("输出腔节点:" + getBranch.getOutputCavityVertex(path1));
        System.out.println("--------------------");
        System.out.println("所有不重复分支:" + getBranch.getBranch(path1));
    }
}
