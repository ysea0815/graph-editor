package com.lgsim.engine.graphEditor.widget.Component.listener;

import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component.AddFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by fox-forever on 2018/5/2.
 */
public class AddAction extends AbstractAction{

    private AddFrame addFrame = new AddFrame();

    @Override
    public void actionPerformed(ActionEvent e) {
            addFrame.setVisible(true);
    }
}
