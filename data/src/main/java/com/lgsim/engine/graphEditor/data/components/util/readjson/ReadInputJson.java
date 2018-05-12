package com.lgsim.engine.graphEditor.data.components.util.readjson;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Element;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 读取json中的数据存到类中
 */
public class ReadInputJson {

    /**
     * 读取json中的数据存到类中
     * @param path json路径
     * @return list集合
     */
    public Collection<IVertex> readJson(String path) {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String jsonstr = null;
        try {
            jsonstr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }

        //String jsonstr = new String(bytes);

        JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        JSONObject components = JSONObject.fromObject(jsonObject.get("Components"));
        JSONArray coms = JSONArray.fromObject(components.get("Component"));

        Element element = new Element();
        Collection<IVertex> list = new ArrayList<>();

        for (Object comObject : coms) {
            JSONObject jComObject = (JSONObject)comObject;

            Collection<IVertexStencil> elements = element.getPredefinedStencils();

            for (IVertexStencil component : elements) {
                if(jComObject.getString("Type").equals(component.getID())) {
                    Component com = (Component)component;
                    JSONArray armNodes = JSONArray.fromObject(jComObject.get("ArmNodes"));
                    JSONArray features = JSONArray.fromObject(jComObject.get("Feature"));
                    JSONObject feature = JSONObject.fromObject(features.getJSONObject(0));
                    JSONArray values = JSONArray.fromObject(feature.get("Value"));

                    com.setComponentName(jComObject.getString("Name"));
                    if(jComObject.getString("Type").equals("1") ||
                            jComObject.getString("Type").equals("3")) {
                        com.setOutPorts(armNodes);
                    }else if(jComObject.getString("Type").equals("2") ||
                            jComObject.getString("Type").equals("4")) {
                        com.setInPorts(armNodes);
                    }else {
                        ArrayList<String> inpors = new ArrayList<>();
                        ArrayList<String> outpors = new ArrayList<>();
                        inpors.add(armNodes.getString(0));
                        outpors.add(armNodes.getString(1));
                        com.setInPorts(inpors);
                        com.setOutPorts(outpors);
                    }
                    com.setFeatureName(feature.getString("Name"));
                    for (int i = 0;i < values.size(); i++) {
                        try {
                            com.getArguments().get(i).setValue(values.getDouble(i));
                        }catch (IndexOutOfBoundsException e) {
                            System.out.println(jComObject.getString("Type"));
                        }

                    }

                    list.add(com);
                }
            }

        }

        return list;
    }
}
