package com.lgsim.engine.graphEditor.data.components.template;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 获得变量参数的map集合
 */
public class GetVariable {

    /**
     * 获得变量参数的map集合
     * @param path 文件路径
     * @return map集合
     */
    public Map<String,Double> getVariable(String path) {

        Map<String,Double> variables = new HashMap<>();

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String str = "";

        try {
            str = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }

        JSONObject jsonObject = JSONObject.fromObject(str);

        JSONObject par = JSONObject.fromObject(jsonObject.get("Parameters"));
        JSONArray parameters = JSONArray.fromObject(par.get("Parameter"));

        for (Object object : parameters) {
            JSONObject jObject = (JSONObject)object;
            Variable variable = new Variable();

            Set<String> set = jObject.keySet();

            for (String key : set) {
                variable.setName(key);
                variable.setValue(jObject.getDouble(key));
            }

            variables.put(variable.getName(),variable.getValue());
        }
        return variables;
    }
}
