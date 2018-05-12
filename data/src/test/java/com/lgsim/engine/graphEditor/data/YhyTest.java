package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Element;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadInputJson;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadJson;
import com.lgsim.engine.graphEditor.data.components.util.writejson.GetJson;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;

import java.util.ArrayList;
import java.util.Collection;

//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
//import net.sf.json.JSONObject;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        Collection<IVertex> list = new ArrayList<>();
        ReadInputJson readInputJson = new ReadInputJson();
        GetJson getJson = new GetJson();

        //String path = "com/lgsim/engine/graphEditor/data/test.inp";
        String path = "com/lgsim/engine/graphEditor/data/test/case-0418-2-new9.inp";
        list = readInputJson.readJson(path);
        byte[] bytes = getJson.getJson(list);
        String str = new String(bytes);
        System.out.println("------------------------------------");
        //System.out.println(list);
        System.out.println("------------------------------------");
        System.out.println(str);
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");

    }
}

