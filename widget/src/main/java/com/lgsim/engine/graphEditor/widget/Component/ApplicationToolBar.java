package com.lgsim.engine.graphEditor.widget.Component;


import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.ActionBundle;
import com.lgsim.engine.graphEditor.widget.util.setfont.SetFont;

import javax.swing.*;
import java.util.*;

public class ApplicationToolBar extends JToolBar {

    private InitToolbarAction action = new InitToolbarAction();

    SetFont setf = new SetFont();

    public ApplicationToolBar() {

        init();

    }

    public void init(){
        //String str1 = "<html><font style=\"font-size:12px;\">新建...</font></html>";
        //JButton news = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/newfile.png", action, ActionBundle.get("file.new"));
        JButton news = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/newfile.png", action, setf.setFontSize("新建...",14));
        JButton open = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/open.png", action, setf.setFontSize("打开...(Ctrl+O)",14));
        JButton save = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/save.png", action, setf.setFontSize("保存...(Ctrl+S)",14));
        JButton cut = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/cut.png", action, setf.setFontSize("剪切(Ctrl+X)",14));
        JButton copy = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/copy.png", action, setf.setFontSize("复制(Ctrl+C)",14));
        JButton paste = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/paste.png", action, setf.setFontSize("粘贴(Ctrl+V)",14));
        JButton delete = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/delete.png", action, setf.setFontSize("删除",14));
        JButton undo = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/undo.png", action, setf.setFontSize("撤消(Ctrl+Z)",14));
        JButton redo = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/redo.png", action, setf.setFontSize("重做(Ctrl+Y)",14));
        /*JButton search = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/", action, ActionBundle.get("layout.search.branch"));//查找分支
        JButton last = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/", action, ActionBundle.get("layout.last"));
        JButton next = setTools("", action, ActionBundle.get("layout.next"));
        JButton start = setTools("", action, ActionBundle.get("calc.start"));
        JButton argument = setTools("", action, ActionBundle.get("calc.argument"));//参数分析
        JButton trend = setTools("", action, ActionBundle.get("calc.show.trend"));//显示曲线*/
        JButton print = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/print.png", action, setf.setFontSize("打印...(Ctrl+P)",14));
        JButton preview = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/preview.png", action, setf.setFontSize("打印预览...",14));
        JButton pictureOut = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/pictureout.png", action, setf.setFontSize("图片导出...",14));//图片导出
        JButton auto = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/auto.png", action, setf.setFontSize("Autocad导出...",14));
        JButton svg = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/svg.png", action, setf.setFontSize("SVG导出...",14));
        JButton flash = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/flash.png", action, setf.setFontSize("Flash导出...",14));
        JButton sliver = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/sliver.png", action, setf.setFontSize("Sliverlight导出...",14));
        JButton pdf = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/pdf.png", action, setf.setFontSize("PDF导出...",14));
        JButton about = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/about.png", action, setf.setFontSize("关于...",14));
        JButton toolbar = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/toolbar.png", action, setf.setFontSize("工具栏选项",14));

        JButton[] button1 = {news, open, save, cut, copy, paste, delete, undo, redo
                , print, preview, pictureOut, auto, svg, flash, sliver, pdf, about, toolbar};
        List<JButton> standardList = Arrays.asList(button1);
        addToolBar(standardList);


        /*JButton grid = setTools("", action, ActionBundle.get("align.grid"));
        JButton same = setTools("", action, ActionBundle.get("size.same.grid"));
        JButton left = setTools("", action, ActionBundle.get("align.left"));
        JButton center = setTools("", action, ActionBundle.get("align.center"));
        JButton right = setTools("", action, ActionBundle.get("align.right"));
        JButton top = setTools("", action, ActionBundle.get("align.top"));
        JButton middle = setTools("", action, ActionBundle.get("align.middle"));
        JButton bottom = setTools("", action, ActionBundle.get("align.bottom"));//对齐底部

        JButton[] button2 = {grid, same, left, center, right, top, middle, bottom};
        List<JButton> layoutList = Arrays.asList(button2);
        addToolBar(layoutList);


        JButton hFlip = setTools("", action, ActionBundle.get("turn.H.flip"));
        JButton vFlip = setTools("", action, ActionBundle.get("turn.V.flip"));
        JButton tLeft = setTools("", action, ActionBundle.get("turn.left"));
        JButton tRight = setTools("", action, ActionBundle.get("turn.right"));//右旋

        JButton[] button3 = {hFlip, vFlip, tLeft, tRight};
        List<JButton> moveList = Arrays.asList(button3);
        addToolBar(moveList);


        JButton b = setTools("", action, ActionBundle.get("style.B"));//加粗
        JButton i = setTools("", action, ActionBundle.get("style.I"));
        JButton u = setTools("", action, ActionBundle.get("style.U"));//下划线

        JButton[] button4 = {b, i, u};
        List<JButton> styleList = Arrays.asList(button4);
        addToolBar(styleList);


        JButton rectangular = setTools("", action, ActionBundle.get("tool.rectangular"));
        JButton ellipse = setTools("", action, ActionBundle.get("tool.ellipse"));
        JButton line = setTools("", action, ActionBundle.get("tool.line"));
        JButton graph = setTools("", action, ActionBundle.get("tool.ellipse.graph"));

        JButton[] button5 = {rectangular, ellipse, line, graph};
        List<JButton> toolList = Arrays.asList(button5);
        addToolBar(toolList);*/


        JButton vGrid = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/vgrid.png", action, setf.setFontSize("网格",14));
        JButton vScale = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/scale.png", action, setf.setFontSize("标尺",14));
        JButton reference = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/reference.png", action, setf.setFontSize("参考",14));
        JButton port = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/port.png", action, setf.setFontSize("端口",14));
        JButton arrow = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/arrow.png", action, setf.setFontSize("箭头",14));
        JButton shadow = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/shadow.png", action, setf.setFontSize("阴影",14));
        JButton comBrowsers = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/combrowsers.png", action, setf.setFontSize("元件库浏览器",14));
        JButton proBrowsers = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/proBrowsers.png", action, setf.setFontSize("属性浏览器",14));
        JButton gridStyle = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/gridstyle.png", action, setf.setFontSize("网格风格",14));
        JButton viewLayout = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/viewlayout.png", action, setf.setFontSize("查看布局",14));

        JButton[] button2 = {vGrid, vScale, reference, port, arrow, shadow, comBrowsers, proBrowsers, gridStyle, viewLayout};
        List<JButton> viewList = Arrays.asList(button2);
        addToolBar(viewList);


        /*JButton pointer = setTools("", action, ActionBundle.get("tool.pointer"));
        JButton control = setTools("", action, ActionBundle.get("toolBar.control.pel"));*/
        JButton magnify = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/magnify.png", action, setf.setFontSize("放大",14));
        JButton narrow = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/narrow.png", action, setf.setFontSize("缩小",14));
        JButton aliGrid = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/aligrid.png", action, setf.setFontSize("对齐网格",14));
        JButton aliScale = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/aliscale.png", action, setf.setFontSize("对齐标尺",14));
        JButton catGuide = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/catguide.png", action, setf.setFontSize("捕捉指引",14));
        JButton catRotation = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/catrotation.png", action, setf.setFontSize("捕捉旋转",14));
        JButton toolbar1 = setTools("com/lgsim/engine/graphEditor/widget/images/toolbar/toolbar.png", action, setf.setFontSize("工具栏选项",14));

        JButton[] button3 = {magnify, narrow, aliGrid, aliScale, catGuide, catRotation, toolbar1};
        List<JButton> pelList = Arrays.asList(button3);
        addToolBar(pelList);

        /*JButton pNews = setTools("", action, ActionBundle.get("pel.new"));
        JButton pOpen = setTools("", action, ActionBundle.get("pel.open"));
        JButton pSave = setTools("", action, ActionBundle.get("pel.save"));
        JButton pClose = setTools("", action, ActionBundle.get("pel.close"));

        JButton[] button8 = {pNews, pOpen, pSave, pClose};
        List<JButton> pBpelList = Arrays.asList(button8);
        addToolBar(pBpelList);

        JButton custom = setTools("", action, ActionBundle.get("toolBar.custom"));
        List<JButton> customList = Arrays.asList(custom);
        addToolBar(customList);*/

    }

    public void bindActions(IApplicationAction action) {
        createTools(action);
    }

    private void createTools(IApplicationAction action) {

        JButton news = setTools("", action.getDocumentNewAction(), ActionBundle.get("file.new"));
        JButton open = setTools("", action.getDocumentOpenAction(), ActionBundle.get("file.open"));
        JButton save = setTools("", action.getDocumentSaveAction(), ActionBundle.get("file.save"));
        JButton cut = setTools("", action.getVertexCellCutAction(), ActionBundle.get("editor.cut"));
        JButton copy = setTools("", action.getVertexCellCopyAction(), ActionBundle.get("editor.copy"));
        JButton paste = setTools("", action.getVertexCellPasteAction(), ActionBundle.get("editor.paste"));
        JButton delete = setTools("", action.getVertexCellDeleteAction(), ActionBundle.get("editor.delete"));
        JButton undo = setTools("", null, ActionBundle.get("editor.undo"));
        JButton redo = setTools("", null, ActionBundle.get("editor.redo"));
        JButton search = setTools("", null, ActionBundle.get("layout.search.branch"));//查找分支
        JButton last = setTools("", null, ActionBundle.get("layout.last"));
        JButton next = setTools("", null, ActionBundle.get("layout.next"));
        JButton start = setTools("", null, ActionBundle.get("calc.start"));
        JButton argument = setTools("", null, ActionBundle.get("calc.argument"));//参数分析
        JButton trend = setTools("", null, ActionBundle.get("calc.show.trend"));//显示曲线
        JButton print = setTools("", null, ActionBundle.get("file.print"));
        JButton preview = setTools("", null, ActionBundle.get("file.preview"));
        JButton pictureOut = setTools("", null, ActionBundle.get("file.pictureOut"));//图片导出
        JButton auto = setTools("", null, ActionBundle.get("file.auto"));
        JButton svg = setTools("", null, ActionBundle.get("file.svg"));
        JButton flash = setTools("", null, ActionBundle.get("file.flash"));
        JButton sliver = setTools("", null, ActionBundle.get("file.sliver"));
        JButton pdf = setTools("", null, ActionBundle.get("file.pdf"));

        JButton[] button1 = {news, open, save, cut, copy, paste, delete, undo, redo, search, last, next
                , start, argument, trend, print, preview, pictureOut, auto, svg, flash, sliver, pdf};
        List<JButton> standardList = Arrays.asList(button1);
        addToolBar(standardList);


        JButton grid = setTools("", null, ActionBundle.get("align.grid"));
        JButton same = setTools("", null, ActionBundle.get("size.same.grid"));
        JButton left = setTools("", null, ActionBundle.get("align.left"));
        JButton center = setTools("", null, ActionBundle.get("align.center"));
        JButton right = setTools("", null, ActionBundle.get("align.right"));
        JButton top = setTools("", null, ActionBundle.get("align.top"));
        JButton middle = setTools("", null, ActionBundle.get("align.middle"));
        JButton bottom = setTools("", null, ActionBundle.get("align.bottom"));//对齐底部

        JButton[] button2 = {grid, same, left, center, right, top, middle, bottom};
        List<JButton> layoutList = Arrays.asList(button2);
        addToolBar(layoutList);


        JButton hFlip = setTools("", null, ActionBundle.get("turn.H.flip"));
        JButton vFlip = setTools("", null, ActionBundle.get("turn.V.flip"));
        JButton tLeft = setTools("", null, ActionBundle.get("turn.left"));
        JButton tRight = setTools("", null, ActionBundle.get("turn.right"));//右旋

        JButton[] button3 = {hFlip, vFlip, tLeft, tRight};
        List<JButton> moveList = Arrays.asList(button3);
         addToolBar(moveList);


        JButton b = setTools("", null, ActionBundle.get("style.B"));//加粗
        JButton i = setTools("", null, ActionBundle.get("style.I"));
        JButton u = setTools("", null, ActionBundle.get("style.U"));//下划线

        JButton[] button4 = {b, i, u};
        List<JButton> styleList = Arrays.asList(button4);
        addToolBar(styleList);


        JButton rectangular = setTools("", null, ActionBundle.get("tool.rectangular"));
        JButton ellipse = setTools("", null, ActionBundle.get("tool.ellipse"));
        JButton line = setTools("", null, ActionBundle.get("tool.line"));
        JButton graph = setTools("", null, ActionBundle.get("tool.ellipse.graph"));

        JButton[] button5 = {rectangular, ellipse, line, graph};
        List<JButton> toolList = Arrays.asList(button5);
        addToolBar(toolList);


        JButton vGrid = setTools("", null, ActionBundle.get("view.grid"));
        JButton vScale = setTools("", null, ActionBundle.get("view.scale"));
        JButton reference = setTools("", null, ActionBundle.get("view.reference"));
        JButton port = setTools("", null, ActionBundle.get("view.port"));

        JButton[] button6 = {vGrid, vScale, reference, port};
        List<JButton> viewList = Arrays.asList(button6);
        addToolBar(viewList);


        JButton pointer = setTools("", null, ActionBundle.get("tool.pointer"));
        JButton control = setTools("", null, ActionBundle.get("toolBar.control.pel"));
        JButton magnify = setTools("", null, ActionBundle.get("view.magnify"));
        JButton narrow = setTools("", null, ActionBundle.get("view.narrow"));

        JButton[] button7 = {pointer, control, magnify, narrow};
        List<JButton> pelList = Arrays.asList(button7);
        addToolBar(pelList);

        JButton pNews = setTools("", null, ActionBundle.get("pel.new"));
        JButton pOpen = setTools("", null, ActionBundle.get("pel.open"));
        JButton pSave = setTools("", null, ActionBundle.get("pel.save"));
        JButton pClose = setTools("", null, ActionBundle.get("pel.close"));

        JButton[] button8 = {pNews, pOpen, pSave, pClose};
        List<JButton> pBpelList = Arrays.asList(button8);
        addToolBar(pBpelList);

        JButton custom = setTools("", null, ActionBundle.get("toolBar.custom"));
        List<JButton> customList = Arrays.asList(custom);
        addToolBar(customList);


    }

    public JButton setTools(String image, Action action, String tip) {

        JButton button = new JButton(getImageIcon(image));
        button.setToolTipText(tip);
        button.addActionListener(action);
        return button;
    }

    public Icon getImageIcon(String iconPath) {
        //iconPath = "com/lgsim/engine/graphEditor/widget/png/monkey.png";
        Icon icon = ResourceUtil.lookupImageIcon(iconPath);
        return icon;
    }

    public void addToolBar(List<JButton> buttonList) {
        JToolBar toolBar = new JToolBar();
        for (JButton button : buttonList) {
            if (button != null) {
                toolBar.add(button);
               // toolBar.setVisible(true);
            }
            this.add(toolBar);
        }
    }
}
