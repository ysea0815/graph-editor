package com.lgsim.engine.graphEditor.widget.util.setfont;

/**
 * 字体设置工具类
 */
public class SetFont {

    /**
     * 设置字体大小
     * @param str 设置前字体
     * @param size 大小
     * @return font 设置后的字体
     */
    public String setFontSize(String str,int size) {

        String font = "<html><font style=\"font-size:" + size +
                "px;\">" + str + "</font></html>";
        return font;
    }
}
