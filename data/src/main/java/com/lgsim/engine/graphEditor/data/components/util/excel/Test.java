package com.lgsim.engine.graphEditor.data.components.util.excel;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, BiffException {
        TestXls testXls = new TestXls();
        String path = "C:\\Users\\admin\\Desktop\\test\\test.xlsx";
        try {
            testXls.writeXls(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Student> list = testXls.readXls(path);
        System.out.println(list);
    }
}
