package com.lgsim.engine.graphEditor.widget.Component.listener;

import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.CustomVarTable;
import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component.TableOperation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class ListBarAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] header = {"列表ID", "列表名称", "列表参数", "备注"};
        TableOperation.createHeader(header);
    }
}
