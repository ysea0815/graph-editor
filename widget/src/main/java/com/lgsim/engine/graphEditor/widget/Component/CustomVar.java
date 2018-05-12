package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.CustomMenuBar;
import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.CustomVarEditBar;
import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.CustomVarToolbar;
import com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component.TableOperation;
import javax.swing.*;



/**
 * Created by fox-forever on 2018/5/2.
 */

@SuppressWarnings("all")
public class CustomVar extends JFrame {

    private CustomVarEditBar editBar = new CustomVarEditBar();

    private CustomVarToolbar toolbar = new CustomVarToolbar();

    private CustomMenuBar menuBar = new CustomMenuBar();

    private TableOperation table = new TableOperation();


    public CustomVar() {
        showFrame();

    }

    private void showFrame() {

        Box box = Box.createVerticalBox();
        box.add(toolbar);
        box.add(editBar);
        box.add(table.getTable());

        this.setJMenuBar(menuBar);
        this.add(box);
        this.setTitle("自定义变量");
        this.setIconImage(getImage().getImage());
        this.setSize(600, 600);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private ImageIcon getImage() {
        String path = "com/lgsim/engine/graphEditor/widget/png/monkey.png";
        return ResourceUtil.lookupImageIcon(path);
        }

    public static void main(String[] args) {
        new CustomVar();
    }
}
