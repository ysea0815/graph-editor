package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.widget.Component.VertexTable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class TablePanelImp implements IVertexTable {

    private VertexTable vertexTable;
    private JScrollPane scrollPane;

    public TablePanelImp() {
        vertexTable = new VertexTable();
        scrollPane = new JScrollPane(vertexTable);
    }

    @Override
    public JComponent getSwingComponent() {
        return scrollPane;
    }


    @Override
    public void render(@NotNull IVertex vertex) {
        vertexTable.clearTable();
        vertexTable.renderVertex(vertex);
    }
}
