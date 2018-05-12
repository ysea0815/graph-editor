package com.lgsim.engine.graphEditor.widget.Component.custom.var.component;

import com.lgsim.engine.graphEditor.widget.Component.InitToolbarAction;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fox-forever on 2018/5/3.
 */
public class CustomMenuBar extends JMenuBar{

    public CustomMenuBar(){

        createMenuItem();
    }

    /**
     * 创建目录
     */
    private void createMenuItem() {

        JMenu file = new JMenu("文件");
        JMenuItem news = setMenuItem("新建参数文件", new InitToolbarAction());
        JMenuItem load = setMenuItem("加载参数文件", new InitToolbarAction());
        JMenuItem save = setMenuItem("保存参数文件", new InitToolbarAction());
        JMenuItem saveAs = setMenuItem("另存为", new InitToolbarAction());
        JMenuItem exit = setMenuItem("退出", new InitToolbarAction());

        List<JMenuItem> list = Arrays.asList(news, load, save, saveAs, exit);
        addMenuItem(file,list);

        JMenu editor = new JMenu("编辑");
        addMenuItem(editor,null);

    }

    private JMenuItem setMenuItem(String name, Action action) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(action);

        return menuItem;
    }

    private void addMenuItem(JMenu menu,List<JMenuItem> list) {
        if (list != null) {
            for (JMenuItem menuItem: list) {
                menu.add(menuItem);
            }
        }
        this.add(menu);
    }
}
