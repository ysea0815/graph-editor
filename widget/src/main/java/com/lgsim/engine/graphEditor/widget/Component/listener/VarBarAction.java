package com.lgsim.engine.graphEditor.widget.Component.listener;

import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component.TableOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VarBarAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] header = {"变量ID", "变量名称", "变量值", "备注"};
        TableOperation.createHeader(header);
    }
}
