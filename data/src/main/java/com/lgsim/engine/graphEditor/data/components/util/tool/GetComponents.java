package com.lgsim.engine.graphEditor.data.components.util.tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取json数据中的元件集合
 */
public class GetComponents {

    /**
     * 获取json数据中的元件集合
     * @param path json路径
     * @return coms
     */
    public JSONArray getComponents(String path) {

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

        return coms;
    }
}
