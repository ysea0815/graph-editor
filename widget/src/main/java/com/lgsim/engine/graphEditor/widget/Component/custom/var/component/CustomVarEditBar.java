package com.lgsim.engine.graphEditor.widget.Component.custom.var.component;

import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.Component.InitToolbarAction;
import com.lgsim.engine.graphEditor.widget.Component.listener.AddAction;
import com.lgsim.engine.graphEditor.widget.Component.listener.DeleteAction;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fox-forever on 2018/5/2.
 */
public class CustomVarEditBar extends JToolBar {

    private JToolBar toolBar = new JToolBar();

    public CustomVarEditBar() {
        createToolbar();
    }

    public void addToolbar(List<JButton> list) {
        if (list != null) {
            for(JButton button: list){
                toolBar.add(button);
            }
        }

        this.add(toolBar);
    }

    private void createToolbar() {
        JButton add = getJButton("添加", new AddAction());
        JButton revise = getJButton("修改", new InitToolbarAction());
        JButton del = getJButton("删除", new DeleteAction());

        List<JButton> list = Arrays.asList(add, revise, del);

        addToolbar(list);
    }

    private JButton getJButton(String name, Action action) {
        JButton button = new JButton(getIcon());
        button.setToolTipText(name);
        button.addActionListener(action);

        return button;
    }

    private Icon getIcon() {

        String path = "com/lgsim/engine/graphEditor/widget/png/monkey.png";
        return ResourceUtil.lookupImageIcon(path);
    }
}
