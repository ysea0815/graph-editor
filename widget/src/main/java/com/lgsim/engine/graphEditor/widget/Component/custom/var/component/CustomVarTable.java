package com.lgsim.engine.graphEditor.widget.Component.custom.var.component;

import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

/**
 * Created by fox-forever on 2018/5/2.
 */
public class CustomVarTable extends JTable {

    public CustomVarTable() {
        setHeader();
    }

    private void setHeader() {
        DefaultTableModel model = (DefaultTableModel) dataModel;
        String[] header = {"变量ID", "变量名称", "变量值", "备注"};
        model.setColumnIdentifiers(header);
    }

    public void createHeader(String[] columnName){
        DefaultTableModel model = (DefaultTableModel) dataModel;
        model.setColumnIdentifiers(columnName);
    }


    public void renderTableValue(Vector vector) {
        DefaultTableModel model = (DefaultTableModel) dataModel;
        model.addRow(vector);
        model.fireTableDataChanged();

    }

    public void deleteSelectRow() {
        DefaultTableModel model = (DefaultTableModel) dataModel;
        int a = this.getSelectedRow();
        if (a != -1) {
            model.removeRow(a);
            model.fireTableDataChanged();
        }
    }
}
