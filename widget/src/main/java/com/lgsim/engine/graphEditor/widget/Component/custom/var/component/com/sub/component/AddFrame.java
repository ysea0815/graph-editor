package com.lgsim.engine.graphEditor.widget.Component.custom.var.component.com.sub.component;


import javax.swing.*;
import java.util.Vector;

/**
 * Created by fox-forever on 2018/5/2.
 */
public class AddFrame extends JFrame {

    private JTextField textName,textValue;

    private JTextArea textArea;

    private static int num = 0;

    public AddFrame() {
        createFrame();
        showFrame();
    }


    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> {
            num++;
            Vector<String> vector = new Vector<>();
            vector.clear();
            vector.add(String.valueOf(num));
            vector.add(textName.getText());
            vector.add(textValue.getText());
            vector.add(textArea.getText());
            TableOperation.renderTable(vector);
            closeFrame();
        });
        return button;
    }


    private void closeFrame(){
        textName.setText("");
        textValue.setText("");
        textArea.setText("");
        this.setVisible(false);
    }

    private void createFrame() {
        Box boxV = Box.createVerticalBox();
        Box top = Box.createHorizontalBox();
        Box center = Box.createHorizontalBox();
        Box bottom = Box.createHorizontalBox();

        JLabel varName = new JLabel("变量名称");
        textName = new JTextField(10);

        JLabel varValue = new JLabel("变量值");
        textValue = new JTextField(10);

        textArea = new JTextArea("备注", 5, 24);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton addButton = createButton("添加");
        JButton cancelButton = new JButton("取消");

        top.add(varName);
        top.add(Box.createHorizontalStrut(10));
        top.add(textName);

        center.add(varValue);
        center.add(Box.createHorizontalStrut(22));
        center.add(textValue);

        bottom.add(addButton);
        bottom.add(Box.createHorizontalStrut(10));
        bottom.add(cancelButton);

        boxV.add(Box.createVerticalStrut(30));
        boxV.add(top);
        boxV.add(Box.createVerticalStrut(5));
        boxV.add(center);
        boxV.add(Box.createVerticalStrut(15));

        JPanel panel = new JPanel();
        panel.add(boxV);
        panel.add(scrollPane);

        panel.add(bottom);

        this.add(panel);
    }

    private void showFrame() {

        this.setTitle("添加变量");
        this.setSize(350, 320);
        this.setResizable(false);
        this.setVisible(false);
    }
}
