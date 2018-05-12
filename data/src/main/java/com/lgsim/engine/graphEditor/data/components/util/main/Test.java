package com.lgsim.engine.graphEditor.data.components.util.main;

import com.lgsim.engine.graphEditor.data.components.template.GetVariable;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2018/4/14.
 */
public class Test {

    public static void main(String[] args) {

        String path = "com/lgsim/engine/graphEditor/data/test/yytest.json";
        GetVariable getVariable = new GetVariable();
        Map<String,Double> map = getVariable.getVariable(path);

        System.out.println(map.get("c"));

        String josnstr = "";
        JSONObject jsonObject = JSONObject.fromObject(josnstr);
        Set<String> set = jsonObject.keySet();
        for (String key : set) {
            System.out.println(key);
        }
    }
}
