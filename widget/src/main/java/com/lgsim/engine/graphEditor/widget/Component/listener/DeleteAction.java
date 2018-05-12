package com.lgsim.engine.graphEditor.widget.Component.listener;

import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component.TableOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractAction {


    @Override
    public void actionPerformed(ActionEvent e) {
        TableOperation.deleteRow();
    }
}
