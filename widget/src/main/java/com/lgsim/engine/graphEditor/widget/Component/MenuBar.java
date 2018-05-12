package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.ActionBundle;
import com.lgsim.engine.graphEditor.widget.IWidegtImp.IMenuBarImp;
import com.lgsim.engine.graphEditor.widget.util.setfont.SetFont;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBar extends JMenuBar {

    SetFont setFont = new SetFont();

    String p = "com/lgsim/engine/graphEditor/widget/images/toolbar/";//图片公共路径

    public MenuBar() {
    }

    public void bindAction(IApplicationAction action) {

        addFileMenu(action);
        addEditorMenu(action);
        addViewMenu(action);
        addFormatMenu(action);
        addMoveMenu(action);
        addLayoutMenu(action);
        addDrawMenu(action);
        addDefine(action);
        addCalc(action);
        addCoupCalcMenu(action);
        addToolMenu(action);
        addHelpMenu(action);

    }


    private void addFileMenu(IApplicationAction action) {


        JMenu doc = new JMenu(setFont.setFontSize("文件",14));
        JMenuItem news = createMenuItem(setFont.setFontSize("新建...",14), action.getDocumentNewAction(), p + "newfile.png");
        JMenuItem save = createMenuItem(setFont.setFontSize("保存...",14), action.getDocumentSaveAction(), p + "save.png");
        JMenuItem open = createMenuItem(setFont.setFontSize("打开...",14), action.getDocumentOpenAction(), p + "open.png");
        JMenuItem saveAs = createMenuItem(setFont.setFontSize("另存为...",14), null, p + "saveas.png");
        JMenuItem pictureOut = createMenuItem(setFont.setFontSize("图片导出...",14), null, p + "pictureout.png");
        /*JMenuItem network = createMenuItem("file.switch.network", null, "file.switch.network");
        JMenuItem input = createMenuItem("file.import.data", null, "file.import.data");
        JMenuItem out = createMenuItem("file.out.data", null, "file.out.data");*/
        JMenuItem auto = createMenuItem(setFont.setFontSize("Autocad导出...",14), null, p + "auto.png");
        JMenuItem svg = createMenuItem(setFont.setFontSize("SVG导出...",14), null, p + "svg.png");
        JMenuItem flash = createMenuItem(setFont.setFontSize("Flash导出...",14), null, p + "flash.png");
        JMenuItem sliver = createMenuItem(setFont.setFontSize("Sliverlight导出...",14), null, p + "sliver.png");
        JMenuItem pdf = createMenuItem(setFont.setFontSize("PDF导出...",14), null, p + "pdf.png");
        JMenuItem pageSet = createMenuItem(setFont.setFontSize("页面设置...",14), null, p + "pageset.png");
        JMenuItem preview = createMenuItem(setFont.setFontSize("打印预览...",14), null, p + "preview.png");
        JMenuItem print = createMenuItem(setFont.setFontSize("打印...",14), null, p + "print.png");
        JMenuItem set = createMenuItem(setFont.setFontSize("设置",14), null, "");
        //JMenuItem close = createMenuItem("file.close", null, "file.close");
        JMenuItem exit = createMenuItem(setFont.setFontSize("退出",14), action.getApplicationExitAction(), "");

        JMenuItem[] actions = {news, open, save, saveAs, pictureOut, auto, svg, flash,
                sliver, pdf, pageSet, preview, print, set, exit};

        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(4, 7, 13, 18);

        addMenuItem(actionList, doc, separatorList, null);
        add(doc);
    }

    private void addEditorMenu(IApplicationAction action) {

        JMenu doc = new JMenu(setFont.setFontSize("编辑",14));
        JMenuItem undo = createMenuItem(setFont.setFontSize("撤消Ctrl+Z",14), null, p + "undo.png");
        JMenuItem redo = createMenuItem(setFont.setFontSize("重做Ctrl+Z",14), null, p + "redo.png");
        JMenuItem cut = createMenuItem(setFont.setFontSize("剪贴Ctrl+X",14), action.getVertexCellCutAction(), p + "cut.png");
        JMenuItem copy = createMenuItem(setFont.setFontSize("复制Ctrl+Y",14), action.getVertexCellCopyAction(), p + "copy.png");
        JMenuItem paste = createMenuItem(setFont.setFontSize("粘贴Ctrl+V",14), action.getVertexCellPasteAction(), p + "paste.png");
        JMenuItem delete = createMenuItem(setFont.setFontSize("删除",14), action.getVertexCellDeleteAction(), p + "delete.png");
        JMenuItem selectAll = createMenuItem(setFont.setFontSize("选择全部Ctrl+A",14), null, "");
        JMenuItem dimensional = createMenuItem(setFont.setFontSize("选择所有一维形状",14), null, "");
        JMenuItem dimensional2 = createMenuItem(setFont.setFontSize("选择所有二维形状",14), null, "");

        JMenuItem[] actions = {undo, redo, cut, copy, paste, delete, selectAll, dimensional, dimensional2};

        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(2, 6);

        addMenuItem(actionList, doc, separatorList, null);
        add(doc);

    }

    private void addViewMenu(IApplicationAction action) {

        JMenu doc = new JMenu(setFont.setFontSize("视图",14));

        JMenuItem scale = createMenuItem(setFont.setFontSize("标尺",14), null, p + "scale.png");
        JMenuItem grid = createMenuItem(setFont.setFontSize("网格",14), null, p + "vgrid.png");
        JMenuItem reference = createMenuItem(setFont.setFontSize("参考",14), null, p + "reference.png");
        JMenuItem port = createMenuItem(setFont.setFontSize("端口",14), null, p + "port.png");
        JMenuItem shadow = createMenuItem(setFont.setFontSize("阴影",14), null, p + "shadow.png");
        JMenuItem arrow = createMenuItem(setFont.setFontSize("箭头",14), null, p + "arrow.png");
        JMenuItem library = createMenuItem(setFont.setFontSize("元件库浏览器",14), null, p + "combrowsers.png");
        JMenuItem property = createMenuItem(setFont.setFontSize("属性浏览器",14), null, p + "proBrowsers.png");

        JMenu subGridStyle = new JMenu(setFont.setFontSize("网格风格",14));
        JMenuItem mainLine = createMenuItem(setFont.setFontSize("主线",14), null, p + "vgrid.png");
        JMenuItem secondLine = createMenuItem(setFont.setFontSize("主次线",14), null, p + "vgrid.png");
        JMenuItem point = createMenuItem(setFont.setFontSize("主点",14), null, p + "mpoint.png");
        JMenuItem scan = createMenuItem(setFont.setFontSize("隔行扫描",14), null, p + "scan.png");
        JMenuItem horizontal = createMenuItem(setFont.setFontSize("隔行水平",14), null, p + "horizontal.png");
        JMenuItem vertical = createMenuItem(setFont.setFontSize("隔行竖直",14), null, p + "vertical.png");
        JMenuItem[] gridSubActions = {mainLine, secondLine, point, scan, horizontal, vertical};

        List<JMenuItem> gridSubActionList = Arrays.asList(gridSubActions);
        addMenuItem(gridSubActionList, subGridStyle, null, null);

        JMenu subLookLayout = new JMenu(setFont.setFontSize("查看布局",14));
        JMenuItem common = createMenuItem(setFont.setFontSize("普通的",14), null, p + "common.png");
        JMenuItem suit = createMenuItem(setFont.setFontSize("适合",14), null, p + "suit.png");
        JMenuItem stretch = createMenuItem(setFont.setFontSize("拉伸",14), null, p + "stretch.png");
        JMenuItem Hstretch = createMenuItem(setFont.setFontSize("横向拉伸",14), null, p + "hstretch.png");
        JMenuItem Vstretch = createMenuItem(setFont.setFontSize("纵向拉伸",14), null, p + "vstretch.png");

        JMenuItem[] lookLayoutSubActions = {common, suit, stretch, Hstretch, Vstretch};
        List<JMenuItem> lookLayoutSubActionList = Arrays.asList(lookLayoutSubActions);
        addMenuItem(lookLayoutSubActionList, subLookLayout, null, null);


        JMenuItem magnify = createMenuItem(setFont.setFontSize("放大",14), null, p + "magnify.png");
        JMenuItem narrow = createMenuItem(setFont.setFontSize("缩小",14), null, p + "narrow.png");
        JMenuItem ratio = createMenuItem(setFont.setFontSize("放大比例",14), null, "");
        JMenuItem alignGrid = createMenuItem(setFont.setFontSize("对齐网格",14), null, p + "aligrid.png");
        JMenuItem alignScale = createMenuItem(setFont.setFontSize("对齐标尺",14), null, p + "aliscale.png");
        JMenuItem guidance = createMenuItem(setFont.setFontSize("捕捉指引",14), null, p + "catguide.png");
        JMenuItem rotate = createMenuItem(setFont.setFontSize("捕捉旋转",14), null, p + "catrotation.png");

        JMenuItem[] actions = {scale, grid, reference, port, shadow, arrow, library, property, null, null,
                magnify, narrow, ratio, alignGrid, alignScale, guidance, rotate};

        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(8, 10, 13);

        //子菜单的集合
        Map<Integer, JMenu> map = new HashMap();
        map.put(8, subGridStyle);
        map.put(9, subLookLayout);

        addMenuItem(actionList, doc, separatorList, map);
        add(doc);
    }


    private void addFormatMenu(IApplicationAction action) {

        JMenu doc = new JMenu(setFont.setFontSize("格式",14));

        JMenuItem fill = createMenuItem(setFont.setFontSize("填充样式...",14), null, p + "fill.png");
        JMenuItem brush = createMenuItem(setFont.setFontSize("笔画样式...",14), null, p + "brush.png");
        JMenuItem shadow = createMenuItem(setFont.setFontSize("阴影样式...",14), null, p + "shadow.png");
        JMenuItem text = createMenuItem(setFont.setFontSize("文本样式...",14), null, p + "text.png");
        JMenuItem startArrrow = createMenuItem(setFont.setFontSize("开始箭头方式...",14), null, p + "startarrow.png");
        JMenuItem endArrow = createMenuItem(setFont.setFontSize("结束箭头方式...",14), null, p + "endarrow.png");
        JMenuItem connect = createMenuItem(setFont.setFontSize("连接风格...",14), null, p + "connect.png");
        JMenuItem interaction = createMenuItem(setFont.setFontSize("交互性风格...",14), null, p + "interaction.png");
        //JMenuItem unit = createMenuItem(setFont.setFontSize("笔画样式...",14), null, "format.set.unit");
        //JMenuItem save = createMenuItem(setFont.setFontSize("笔画样式...",14), null, "format.save");
        JMenuItem interact = createMenuItem(setFont.setFontSize("模板...",14), null, p + "interact.png");

        JMenuItem[] actions = {fill, brush, shadow, text, startArrrow, endArrow, connect, interaction, interact};
        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(4, 7, 9);

        addMenuItem(actionList, doc, separatorList, null);
        add(doc);

    }

    private void addMoveMenu(IApplicationAction action) {

        JMenu doc = new JMenu(setFont.setFontSize("动作",14));

        JMenu operate = new JMenu(setFont.setFontSize("操作",14));
        JMenuItem relink = createMenuItem(setFont.setFontSize("重连线",14), null, p + "relink.png");
        JMenuItem reverse = createMenuItem(setFont.setFontSize("逆转",14), null, p + "reverse.png");
        JMenuItem refect = createMenuItem(setFont.setFontSize("反射",14), null, p + "refect.png");
        JMenuItem border = createMenuItem(setFont.setFontSize("更新模型的边界",14), null, "");
        JMenuItem[] operateArr = {relink, reverse, refect, border};
        List<JMenuItem> operateList = Arrays.asList(operateArr);
        addMenuItem(operateList, operate, null, null);

        JMenu group = new JMenu(setFont.setFontSize("分组",14));
        JMenuItem group2 = createMenuItem(setFont.setFontSize("组",14), null, p + "group.png");
        JMenuItem cancel = createMenuItem(setFont.setFontSize("取消组合",14), null, p + "cancel.png");
        List<JMenuItem> groupList = Arrays.asList(group2, cancel);
        addMenuItem(groupList, group, null, null);

        JMenuItem layout = createMenuItem(setFont.setFontSize("布局",14), null, "");

        JMenu order = new JMenu(setFont.setFontSize("顺序",14));
        JMenuItem first = createMenuItem(setFont.setFontSize("移到最前",14), null, p + "move1.png");
        JMenuItem last = createMenuItem(setFont.setFontSize("移到最后",14), null, p + "move1.png");
        JMenuItem previous = createMenuItem(setFont.setFontSize("移到上一层",14), null, p + "move2.png");
        JMenuItem next = createMenuItem(setFont.setFontSize("移到下一层",14), null, p + "move2.png");
        List<JMenuItem> orderList = Arrays.asList(first, last, previous, next);
        addMenuItem(orderList, order, null, null);

        JMenu turn = new JMenu(setFont.setFontSize("旋转或者翻转",14));
        JMenuItem left = createMenuItem(setFont.setFontSize("左旋",14), null, p + "left1.png");
        JMenuItem right = createMenuItem(setFont.setFontSize("右旋",14), null, p + "right1.png");
        JMenuItem hFlip = createMenuItem(setFont.setFontSize("水平翻转",14), null, p + "vstretch.png");
        JMenuItem vflip = createMenuItem(setFont.setFontSize("垂直翻转",14), null, p + "hstretch.png");
        JMenuItem[] turnArr = {left, right, hFlip, vflip};
        List<JMenuItem> turnList = Arrays.asList(turnArr);
        addMenuItem(turnList, turn, null, null);

        JMenu tiny = new JMenu(setFont.setFontSize("微调",14));
        JMenuItem tLeft = createMenuItem(setFont.setFontSize("向左微调",14), null, p + "left1.png");
        JMenuItem tRight = createMenuItem(setFont.setFontSize("向右微调",14), null, p + "right2.png");
        JMenuItem tTop = createMenuItem(setFont.setFontSize("向上微调",14), null, p + "top1.png");
        JMenuItem tBottom = createMenuItem(setFont.setFontSize("向底部微调",14), null, p + "bottom1.png");
        JMenuItem[] tinyArr = {tLeft, tRight, tTop, tBottom};
        List<JMenuItem> tinyList = Arrays.asList(tinyArr);
        addMenuItem(tinyList, tiny, null, null);
        JMenuItem[] actions = {null, null, layout, null, null, null};
        List<JMenuItem> actionList = Arrays.asList(actions);

        Map<Integer, JMenu> map = new HashMap<>();
        map.put(1, operate);
        map.put(2, group);
        map.put(4, order);
        map.put(5, turn);
        map.put(6, tiny);

        addMenuItem(actionList, doc, null, map);
        add(doc);
    }

    private void addLayoutMenu(IApplicationAction action) {

        JMenu doc = new JMenu(setFont.setFontSize("布局",14));

        /*JMenuItem search = createMenuItem("layout.search.branch", null, "layout.search.branch");
        JMenuItem last = createMenuItem("layout.last", null, "layout.last");
        JMenuItem next = createMenuItem("layout.next", null, "layout.next");
        JMenuItem bgp = createMenuItem("layout.bgp", null, "layout.bgp");*/

        JMenu align = new JMenu(setFont.setFontSize("对齐",14));
        JMenuItem left = createMenuItem(setFont.setFontSize("左对齐",14), null, p + "left1.png");
        JMenuItem center = createMenuItem(setFont.setFontSize("对齐中心",14), null, p + "center1.png");
        JMenuItem right = createMenuItem(setFont.setFontSize("右对齐",14), null, p + "right1.png");
        JMenuItem top = createMenuItem(setFont.setFontSize("顶部对齐",14), null, p + "top1.png");
        JMenuItem middle = createMenuItem(setFont.setFontSize("中间对齐",14), null, p + "center1.png");
        JMenuItem bottom = createMenuItem(setFont.setFontSize("底部对齐",14), null, p + "bottom1.png");
        JMenuItem grid = createMenuItem(setFont.setFontSize("对齐到网格",14), null, p + "gridstyle.png");
        JMenuItem[] alignArr = {left, center, right, top, middle, bottom, grid};
        List<JMenuItem> alignList = Arrays.asList(alignArr);
        addMenuItem(alignList, align, null, null);

        JMenu size = new JMenu(setFont.setFontSize("尺寸",14));
        JMenuItem width = createMenuItem(setFont.setFontSize("同一宽度",14), null, p + "comwidth1.png");
        JMenuItem height = createMenuItem(setFont.setFontSize("同一高度",14), null, p + "gridstyle.png");
        JMenuItem sSize = createMenuItem(setFont.setFontSize("同一大小",14), null, p + "gridstyle.png");
        JMenuItem sGrid = createMenuItem(setFont.setFontSize("与网格同大小",14), null, p + "gridstyle.png");
        List<JMenuItem> sizeList = Arrays.asList(width, height, sSize, sGrid);
        addMenuItem(sizeList, size, null, null);

        JMenu textCenter = new JMenu(setFont.setFontSize("中心",14));
        JMenuItem hFile = createMenuItem(setFont.setFontSize("文件中心水平",14), null, p + "vstretch.png");
        JMenuItem vFile = createMenuItem(setFont.setFontSize("文件中心垂直",14), null, p + "hstretch.png");
        List<JMenuItem> centerList = Arrays.asList(hFile, vFile);
        addMenuItem(centerList, textCenter, null, null);

        JMenu gap = new JMenu(setFont.setFontSize("间隙",14));
        JMenuItem sameH = createMenuItem(setFont.setFontSize("水平间距相等",14), null, p + "vstretch.png");
        JMenuItem addH = createMenuItem(setFont.setFontSize("增加水平间距",14), null, p + "vstretch.png");
        JMenuItem reduceH = createMenuItem(setFont.setFontSize("减少水平间距",14), null, p + "vstretch.png");
        JMenuItem moveH = createMenuItem(setFont.setFontSize("移动水平间距",14), null, p + "vstretch.png");
        JMenuItem sameV = createMenuItem(setFont.setFontSize("竖直间距相等",14), null, p + "hstretch.png");
        JMenuItem addV = createMenuItem(setFont.setFontSize("增加竖直间距",14), null, p + "hstretch.png");
        JMenuItem reduceV = createMenuItem(setFont.setFontSize("减少竖直间距",14), null, p + "hstretch.png");
        JMenuItem moveV = createMenuItem(setFont.setFontSize("移动竖直间距",14), null, p + "hstretch.png");
        JMenuItem[] gapArr = {sameH, addH, reduceH, moveH, sameV, addV, reduceV, moveV};
        List<JMenuItem> gapList = Arrays.asList(gapArr);
        addMenuItem(gapList, gap, null, null);

        JMenuItem shape = createMenuItem(setFont.setFontSize("布局形状",14), null, p + "layout.png");

        JMenuItem[] actions = {null, null, center, null, shape};
        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(3, 8);

        Map<Integer, JMenu> map = new HashMap<>();
        map.put(1, align);
        map.put(2, size);
        map.put(3, textCenter);
        map.put(4, gap);

        addMenuItem(actionList, doc, separatorList, map);
        add(doc);
    }

    private void addDrawMenu(IApplicationAction action) {

        JMenu doc = new JMenu(ActionBundle.get("tool.name"));

        JMenuItem pointer = createMenuItem("tool.pointer", null, "tool.pointer");
        JMenuItem rectangular = createMenuItem("tool.rectangular", null, "tool.rectangular");
        JMenuItem ellipse = createMenuItem("tool.ellipse", null, "tool.ellipse");
        JMenuItem line = createMenuItem("tool.line", null, "tool.line");
        JMenuItem graph = createMenuItem("tool.ellipse.graph", null, "tool.ellipse.graph");
        JMenuItem arc = createMenuItem("tool.arc", null, "tool.arc");
        JMenuItem fold = createMenuItem("tool.fold.line", null, "tool.fold.line");
        JMenuItem polygon = createMenuItem("tool.polygon", null, "tool.polygon");
        JMenuItem curve = createMenuItem("tool.curve", null, "tool.curve");
        JMenuItem close = createMenuItem("tool.close.curve", null, "tool.close.curve");
        JMenuItem bezier = createMenuItem("tool.bezier.curve", null, "tool.bezier.curve");
        JMenuItem text = createMenuItem("tool.text", null, "tool.text");
        JMenuItem drag = createMenuItem("tool.drag", null, "tool.drag");
        JMenuItem layer = createMenuItem("tool.layer.manage", null, "tool.layer.manage");
        JMenuItem custom = createMenuItem("tool.custom", null, "tool.custom");
        JMenuItem select = createMenuItem("tool.select", null, "tool.select");

        JMenuItem[] actions = {pointer, rectangular, ellipse, line, graph, arc, fold,
                polygon, curve, close, bezier, text, drag, layer, custom, select};
        List<JMenuItem> actionList = Arrays.asList(actions);

        addMenuItem(actionList, doc, null, null);
        add(doc);

    }

    private void addDefine(IApplicationAction action) {

        JMenu doc = new JMenu(ActionBundle.get("define.name"));
        JMenuItem var = createMenuItem("define.var", null, "define.var");

        List<JMenuItem> actionList = Arrays.asList(var);

        addMenuItem(actionList, doc, null, null);
        add(doc);
    }

    private void addCalc(IApplicationAction action) {

        JMenu doc = new JMenu(ActionBundle.get("calc.name"));

        JMenuItem start = createMenuItem("calc.start", action.getSolverCalcAction(), "calc.start");
        JMenuItem argument = createMenuItem("calc.argument", null, "calc.argument");
        JMenuItem trend = createMenuItem("calc.show.trend", null, "calc.show.trend");
        JMenuItem setting = createMenuItem("calc.setting",action.getSolverSettingAction(),"calc.setting");
        List<JMenuItem> actionList = Arrays.asList(start, argument, trend, setting);

        addMenuItem(actionList, doc, null, null);
        add(doc);

    }

    private void addToolMenu(IApplicationAction action) {

        JMenu doc = new JMenu(ActionBundle.get("box.name"));
        JMenuItem note = createMenuItem("box.note", null, "box.note");
        JMenuItem calc = createMenuItem("box.calc", null, "box.calc");
        JMenuItem word = createMenuItem("box.word", null, "box.word");
        JMenuItem excel = createMenuItem("box.excel", null, "box.excel");
        JMenuItem[] arr = {note, calc, word, excel};

        List<JMenuItem> actionList = Arrays.asList(arr);

        addMenuItem(actionList, doc, null, null);
        add(doc);
    }


    private void addCoupCalcMenu(IApplicationAction action) {

        JMenu doc = new JMenu(ActionBundle.get("coup.calc.name"));

        JMenuItem source = createMenuItem("coup.calc.source", null, "coup.calc.source");
        JMenuItem solve = createMenuItem("coup.calc.solve", null, "coup.calc.solve");

        List<JMenuItem> actionList = Arrays.asList(source, solve);

        addMenuItem(actionList, doc, null, null);
        add(doc);
    }

    private void addHelpMenu(IApplicationAction action) {

        JMenu doc = new JMenu(ActionBundle.get("help.name"));
        JMenuItem instructions = createMenuItem("help.instructions", null, "help.instructions");
        JMenuItem about = createMenuItem("help.about", null, "help.about");

        List<JMenuItem> actionList = Arrays.asList(instructions, about);
        addMenuItem(actionList, doc, null, null);
        add(doc);
    }

    public JMenuItem createMenuItem(String key, Action action, String iconPath) {

        //iconPath = "";
        Icon icon = ResourceUtil.lookupImageIcon(iconPath);
        JMenuItem menuItem = new JMenuItem(key, icon);
        menuItem.addActionListener(action);
        return menuItem;
    }

    /**
     * @param addList       菜单各项
     * @param doc           主菜单
     * @param separatorList 分割线
     * @param subMap        <子菜单,位置>
     */
    private void addMenuItem(List<JMenuItem> addList, JMenu doc, List<Integer> separatorList,
                             Map<Integer, JMenu> subMap) {
        int index = 0;
        for (JMenuItem action : addList) {
            index++;
            if (action != null) {
                doc.add(action);
            }
            if (separatorList != null && separatorList.contains(index)) {
                doc.addSeparator();
            }
            if (subMap != null) {
                if (subMap.containsKey(index)) {
                    doc.add(subMap.get(index));
                }
            }
        }
    }
}
