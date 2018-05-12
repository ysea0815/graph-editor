package com.lgsim.engine.graphEditor.data.components.util.excel;

import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用jxl.jar进行Excel的导入导出
 */
public class TestXls {

    /**
     * 导出Excel
     * @param path 文件路径
     * @throws IOException
     */
    public void writeXls(String path) throws IOException {
        //OutputStream os = new FileOutputStream("C:\\Users\\admin\\Desktop\\test\\test.xls");
        File file = new File(path);
        //创建可写入的Excel工作簿，并将内容写入到输出流
        WritableWorkbook wk = Workbook.createWorkbook(file);

        //创建可写入的Excel工作表
        WritableSheet sheet = wk.createSheet("信息表",0);

        //把单元格(column,row)到单元格(column1,row1)进行合并
        //mergeCells(column,row,column1,row1)
        try {
            sheet.mergeCells(0,0,1,0);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        //创建WritableFont字体对象
        WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"),
                12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);

        //创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
        WritableCellFormat titleFormat = new WritableCellFormat();

        //设置字体格式
        titleFormat.setFont(titleFont);

        try {
            //设置文本水平居中形式
            titleFormat.setAlignment(Alignment.CENTRE);
            //设置文本垂直居中对齐
            titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            //设置背景颜色
            titleFormat.setBackground(Colour.GRAY_25);
            //设置自动换行
            titleFormat.setWrap(true);
            //添加label对象
            Label lab_00 = new Label(0,0,"学生信息一览表",titleFormat);
            //将定义好的label对象添加到工作表中
            sheet.addCell(lab_00);

            WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
            cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"),
                    10,WritableFont.BOLD,false));
            cloumnTitleFormat.setAlignment(Alignment.CENTRE);

            Label lab_01 = new Label(0,1,"姓名",cloumnTitleFormat);
            Label lab_11 = new Label(1,1,"年龄",cloumnTitleFormat);
            Label lab_02 = new Label(0,2,"景天",cloumnTitleFormat);

            //定义数字格式
            NumberFormat nf = new NumberFormat("0");
            WritableCellFormat wcf = new WritableCellFormat(nf);
            //定义数值对象
            Number numlab_12 = new Number(1,2,20,cloumnTitleFormat);

            sheet.addCell(lab_01);
            sheet.addCell(lab_11);
            sheet.addCell(lab_02);
            sheet.addCell(numlab_12);

            wk.write();
            wk.close();
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    /**
     * 导入Excel
     * @param path 文件路径
     * @return Student集合
     * @throws IOException
     * @throws BiffException
     */
    public List<Student> readXls(String path) throws IOException,BiffException {
        List<Student> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);

        Workbook wk = Workbook.getWorkbook(fis);
        //获取第一张sheet表
        Sheet sheet = wk.getSheet(0);
        //获取总行数
        int rowNum = sheet.getRows();
        int colNum = sheet.getColumns();
        //开始遍历每一行
        for (int i = 2;i < rowNum;i++) {
            Student student = new Student();

            for (int j = 0;j < colNum;j++) {

                //判断单元行的内容类型
                if(sheet.getCell(j,i).getType() == CellType.LABEL) {
                    //getCell(j,i) 获取第j列第i行的单元格
                    //getContents() 获取单元格的内容 适用于字符串
                    student.setName(sheet.getCell(j,i).getContents());
                }else {
                    //转换数值单元格
                    NumberCell numberCell = (NumberCell)sheet.getCell(j,i);
                    student.setAge((int)numberCell.getValue());
                }
            }
            //getCell(j,i) 获取第j列第i行的单元格
            //getContents() 获取单元格的内容 适用于字符串
            /*student.setName(sheet.getCell(0,i).getContents());
            if(sheet.getCell(1,i).getType() == CellType.NUMBER) {
                NumberCell numberCell = (NumberCell) sheet.getCell(1,i);
                student.setAge((int) numberCell.getValue());
            }*/
            list.add(student);
        }
        fis.close();
        wk.close();
        return list;
    }
}
