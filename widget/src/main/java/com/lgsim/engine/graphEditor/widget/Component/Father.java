package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.widget.Test.JTableLearning;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fox-forever on 2018/5/2.
 */
public class Father extends JFrame{

    //VertexTable tablePanel;

    //SolverOutputPanel panel;

    JTableLearning table;
    JScrollPane pane = new JScrollPane();
    public Father(){

       // panel = new SolverOutputPanel();

        table = new JTableLearning();

        this.add(table);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){

        new Father();
    }
}
