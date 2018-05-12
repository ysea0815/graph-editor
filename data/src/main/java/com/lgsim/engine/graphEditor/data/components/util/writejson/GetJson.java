package com.lgsim.engine.graphEditor.data.components.util.writejson;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Parameter;
import com.lgsim.engine.graphEditor.data.components.util.jsonformattool.JsonFormatTool;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 取出类中的数据生成json的byte[]数据
 */
public class GetJson {
    /**
     * 取出类中的数据生成json的byte[]数据
     * @param list 节点集合
     * @return byte[]数据
     */
    public byte[] getJson(Collection<IVertex> list) {
        JsonFormatTool tool = new JsonFormatTool();

        //VertexImpl vertex = new VertexImpl();

        String str1 = "{\"Name\": \"TestAutomationInput\",\"User\": \"LiZongyao\",\"SimplifiedInput\": \"1\",\"SolveSwirl\": \"-1\",\"OutputFormat\": \"2\",\"Parameters\": {\"Parameter\": []},\"SimulationData\": {\"SimulationType\": \"CompressibleSteady\",\"Material\": \"Gas\",\"TurbineData\": {\"RotationalSpeed\": [ \"0\",\"0\",\"0\"]},\"Restart\": \"0\",\"ConvergenceData\": {\"MaxIter\": \"500\",\"RelaxFactor\": [\"0.3\",\"0.2\",\"0.5\"],\"ConvergenceData\": [\"1E-08\",\"1E-08\",\"1E-08\"]}},\"Nodes\": {\"Node\": []},\"Components\":{\"Component\":[";
        String str2 = "]}";
        String str3 = "}";
        String str4 = "";
        for(IVertex vertex : list) {
            Component component = (Component)vertex;

            ArrayList<String> argument = new ArrayList<>();
            ArrayList<String> ports = new ArrayList<>();

            String value = "";
            String po = "";

            for (IVertexArgument parameter : vertex.getArguments()) {
                value = "\"" + String.valueOf(parameter.getValue()) + "\"";
                argument.add(value);

            }
            for (IVertex port : vertex.getInputPorts()) {
                po = "\"" + String.valueOf(port.getID()) + "\"";
                ports.add(po);
            }
            for (IVertex port : vertex.getOutputPorts()) {
                po = "\"" + String.valueOf(port.getID()) + "\"";
                ports.add(po);
            }
            for (String port : component.getInPorts()) {
                po = "\"" + port + "\"";
                ports.add(po);
            }
            for (String port : component.getOutPorts()) {
                po = "\"" + port + "\"";
                ports.add(po);
            }

            str4 += "{" +
                    "\"Name\":\"" + ((Component) vertex).getComponentName() + '\"' +
                    ",\"Type\":\"" + vertex.getTypeID() + '\"' +
                    ",\"ArmNodes\":" + ports +
                    ",\"Feature\":[{\"Name\":\"" + "Input" + "\"" +
                    ",\"Value\":" + argument + "}]" +
                    "},";
        }

        String str = str4.substring(0,str4.length()-1);
        /*String str4 = "{" +
                "\"Name\":\"" + vertex.getID() + '\"' +
                ",\"Type\":\"" + vertex.getTypeID() + '\"' +
                *//*",\"ArmNodes\":" + armnodes +
                ",\"Feature\":" + feature +*//*
                '}';*/
        String json = tool.formatJson(str1 + str + str2 + str3);
        byte[] bjson = json.getBytes();

        return bjson;
    }
}
