package com.lgsim.engine.graphEditor.widget.Test;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

public class JTableLearning extends JScrollPane {
    private JTable table;
    private JTable jg_table;
    private JScrollPane jsp;
    
//    public static void main(String[] args) {
//      //  EventQueue.invokeLater(new Runnable() {
//          //  public void run() {
//              //  try {
//                    JTableLearning frame = new JTableLearning();
//                    frame.setVisible(true);
//               // } catch (Exception e) {
//                 //   e.printStackTrace();
//               // }
//          //  }
//       // });
//    }

    
    public JTableLearning() {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 100,100);
       // getContentPane().setLayout(new BorderLayout(0, 0));

        //JScrollPane scrollPane = new JScrollPane();

//        TableModel dataModel = new AbstractTableModel() {
//            public int getColumnCount() {
//                return 5;
//            }
//
//            public int getRowCount() {
//                return 5;
//            }
//
//            public Object getValueAt(int row, int col) {
//                return new Integer(row * col);
//            }
//        };
        
        AbstractTableModel tm;
        final Vector<Vector<String>> vect;// 声明一个向量对象
    //    JScrollPane jsp;// 声明一个滚动杠对象
        final String title[] = { "职工号", "职工名", "性别", "出生日期", "工资" }; // 二维表列名
        vect = new Vector();// 实例化向量
        tm = new AbstractTableModel() {
            public int getColumnCount() {
                return title.length;
            }// 取得表格列数

            public int getRowCount() {
                return vect.size();
            }// 取得表格行数

            public Object getValueAt(int row, int column) {
                if (!vect.isEmpty())
                    return ((Vector) vect.elementAt(row)).elementAt(column);
                else
                    return null;
            }// 取得单元格中的属性值

            public String getColumnName(int column) {
                return title[column];
            }// 设置表格列名

            public void setValueAt(Object value, int row, int column) {
            } // 数据模型不可编辑，该方法设置为空

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }// 取得列所属对象类

            public boolean isCellEditable(int row, int column) {
                return false;
            }// 设置单元格不可编辑，为缺省实现
        };
        

        vect.removeAllElements();// 初始化向量对象
        tm.fireTableStructureChanged();// 更新表格内容

        Vector<String> list = new Vector();
        
        list.add("fda");
        list.add("aaa");
        list.add("ssa");
        list.add("dda");
        list.add("mma");

        vect.addElement(list);
        vect.addElement(list);vect.addElement(list);vect.addElement(list);
        vect.addElement(list);vect.addElement(list);vect.addElement(list);
        tm.fireTableStructureChanged();
        // };
        // 定制表格：
        jg_table = new JTable(tm);// 生成自己的数据模型
        jg_table.setToolTipText("显示全部查询结果");
        // 设置帮助提示
      //  jg_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // 设置表格调整尺寸模式
       // jg_table.setCellSelectionEnabled(false);
        // 设置单元格选择方式
      //  jg_table.setShowVerticalLines(true);//
        // 设置是否显示单元格间的分割线
       // jg_table.setShowHorizontalLines(true);

       // jsp = new JScrollPane(jg_table);// 给表格加上滚动杠

        // JTable table = new JTable(dataModel);
        // JScrollPane scrollpane = new JScrollPane(table);

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                resizeTable(true);
//            }
//        });

       // this.add(jg_table);
        this.setViewportView(jg_table);
        //this.setVisible(true);
       // getContentPane().add(jsp, BorderLayout.CENTER);
       // this.add(jsp);
       // scrollPane.setViewportView(table);
    }
    public void resizeTable(boolean bool) {
        Dimension containerwidth = null;
        if (!bool) {
            //初始化时，父容器大小为首选大小，实际大小为0
            containerwidth = this.getPreferredSize();
        } else {
            //界面显示后，如果父容器大小改变，使用实际大小而不是首选大小
            containerwidth = this.getSize();
        }
        //计算表格总体宽度 getTable().
        int allwidth = jg_table.getIntercellSpacing().width;
        for (int j = 0; j < jg_table.getColumnCount(); j++) {
            //计算该列中最长的宽度
            int max = 0;
            for (int i = 0; i < jg_table.getRowCount(); i++) {
                int width = jg_table.getCellRenderer(i, j).getTableCellRendererComponent
                  (jg_table, jg_table.getValueAt(i, j), false,
                  false, i, j).getPreferredSize().width;
                if (width > max) {
                    max = width;
                }
            }
            //计算表头的宽度
            int headerwidth = jg_table.getTableHeader().
              getDefaultRenderer().getTableCellRendererComponent(
                      jg_table, jg_table.getColumnModel().
              getColumn(j).getIdentifier(), false, false,
              -1, j).getPreferredSize().width;
            //列宽至少应为列头宽度
            max += headerwidth;
            //设置列宽
            jg_table.getColumnModel().
              getColumn(j).setPreferredWidth(max);
            //给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
            allwidth += max + jg_table.getIntercellSpacing().width;
        }
        allwidth += jg_table.getIntercellSpacing().width;
        //如果表格实际宽度大小父容器的宽度，则需要我们手动适应；否则让表格自适应
        if (allwidth > containerwidth.width) {
            jg_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            jg_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
}


}
