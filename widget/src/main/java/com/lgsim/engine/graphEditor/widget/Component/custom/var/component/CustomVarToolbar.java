package com.lgsim.engine.graphEditor.widget.Component.custom.var.component;

import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.Component.InitToolbarAction;
import com.lgsim.engine.graphEditor.widget.Component.listener.ListBarAction;
import com.lgsim.engine.graphEditor.widget.Component.listener.VarBarAction;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fox-forever on 2018/5/2.
 */
@SuppressWarnings("all")
public class CustomVarToolbar extends JToolBar {

    private JToolBar toolBar = new JToolBar();

    public CustomVarToolbar() {
        createToolbar();
    }

    public void addToolbar(List<JButton> list) {
        if (list != null) {
            for (JButton button : list) {
                toolBar.add(button);
            }
        }
        this.add(toolBar);
    }

    private void createToolbar() {
        JButton var = setToolbar("变量", new VarBarAction());
        JButton list = setToolbar("列表", new ListBarAction());
        JButton fx = setToolbar("函数", new InitToolbarAction());
        JButton file = setToolbar("文件", new InitToolbarAction());
        JButton back = setToolbar("返回", new InitToolbarAction());

        List<JButton> buttons = Arrays.asList(var, list, fx, file, back);
        addToolbar(buttons);
    }

    private JButton setToolbar(String name, Action action) {
        JButton button = new JButton(name, getImage());
        button.addActionListener(action);
        return button;
    }

    private Icon getImage() {
        String path = "com/lgsim/engine/graphEditor/widget/png/monkey.png";
        ImageIcon imageIcon = ResourceUtil.lookupImageIcon(path);
        return imageIcon;
    }
}
