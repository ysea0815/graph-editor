package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.ComponentArm;
import com.lgsim.engine.graphEditor.data.components.template.Parameter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockGraph implements IGraph {
    @Override
    public @NotNull Collection<IVertex> getVertexes() {
        List<IVertex> list = new ArrayList<>();

        List<IVertexArgument> arguments1 = new ArrayList<>();
        Parameter parameter11 = new Parameter();
        parameter11.setParameterValue(30000);
        Parameter parameter12 = new Parameter();
        parameter12.setParameterValue(800);
        Parameter parameter13 = new Parameter();
        parameter13.setParameterValue(0.0015);
        Parameter parameter14 = new Parameter();
        parameter14.setParameterValue(0);
        arguments1.add(parameter11);
        arguments1.add(parameter12);
        arguments1.add(parameter13);
        arguments1.add(parameter14);

        List<IVertexArgument> arguments2 = new ArrayList<>();
        Parameter parameter21 = new Parameter();
        parameter21.setParameterValue(0.0015);
        Parameter parameter22 = new Parameter();
        parameter22.setParameterValue(0.0015);
        Parameter parameter23 = new Parameter();
        parameter23.setParameterValue(5.0);
        arguments2.add(parameter21);
        arguments2.add(parameter22);
        arguments2.add(parameter23);

        List<IVertexArgument> arguments3 = new ArrayList<>();
        Parameter parameter31 = new Parameter();
        parameter31.setParameterValue(1000000);
        Parameter parameter32 = new Parameter();
        parameter32.setParameterValue(300);
        Parameter parameter33 = new Parameter();
        parameter33.setParameterValue(0.000475);
        Parameter parameter34 = new Parameter();
        parameter34.setParameterValue(0);
        arguments3.add(parameter31);
        arguments3.add(parameter32);
        arguments3.add(parameter33);
        arguments3.add(parameter34);

        VertexImpl v1 = new VertexImpl();
        v1.setTypeID("1");
        v1.setID("1");
        v1.setArguments(arguments1);

        VertexImpl v2 = new VertexImpl();
        v2.setID("2");
        v2.setTypeID("111");
        v2.setArguments(arguments2);

        VertexImpl v3 = new VertexImpl();
        v3.setID("3");
        v3.setTypeID("2");
        v3.setArguments(arguments3);

        List<IVertex> inputPorts1 = new ArrayList<>();
        List<IVertex> outputPorts1 = new ArrayList<>();
        outputPorts1.add(v2);
        v1.setInputPorts(inputPorts1);
        v1.setOutputPorts(outputPorts1);

        List<IVertex> inputPorts2 = new ArrayList<>();
        List<IVertex> outputPorts2 = new ArrayList<>();
        inputPorts2.add(v1);
        outputPorts2.add(v3);
        v2.setInputPorts(inputPorts2);
        v2.setOutputPorts(outputPorts2);

        List<IVertex> inputPorts3 = new ArrayList<>();
        List<IVertex> outputPorts3 = new ArrayList<>();
        inputPorts3.add(v2);
        v3.setInputPorts(inputPorts3);
        v3.setOutputPorts(outputPorts3);

        list.add(v1);
        list.add(v2);
        list.add(v3);
        return list;
    }

    @Override
    public void retrieveCalcOutputs(@NotNull IGraph graph) {

    }
}
