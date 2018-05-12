package com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component;

import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.CustomVarTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableOperation extends JComponent {

    private static CustomVarTable table;

    public TableOperation() {
        table = new CustomVarTable();

    }

    public JComponent getTable() {
        return new JScrollPane(table);
    }

    public static void renderTable(Vector vector) {
        table.renderTableValue(vector);
    }

    public static void deleteRow(){
        table.deleteSelectRow();
    }

    public static void createHeader(String[] columnName){
        table.createHeader(columnName);
    }
}
