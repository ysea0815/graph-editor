package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.data.components.util.jsonformattool.JsonFormatTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class testIo {
    public static void main(String[] args) throws IOException {
        String a = "100";
        String b = "200";
        double c = Double.valueOf(a) + Double.parseDouble(b);
        String d = String.valueOf(c);
        System.out.println(d);
    }
}
