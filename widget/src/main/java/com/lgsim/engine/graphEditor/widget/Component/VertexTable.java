package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.unit.IUnit;
import com.lgsim.engine.graphEditor.api.unit.IUnitBundle;
import com.lgsim.engine.graphEditor.api.unit.IUnitsContext;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

@SuppressWarnings("all")
public class VertexTable extends JTable {

    private final List<TableModelListener> tableModelListeners = new Vector<>();

    private List<TableCellEditor> comboBoxes = new ArrayList<>();

    private int row;

    private boolean flag = false;

    public VertexTable() {
        // TODO 表头国际化
        DefaultTableModel model = (DefaultTableModel) dataModel;
        model.setColumnIdentifiers(new String[]{"名称", "值", "单位", "说明"});
    }

    @Override
    public Class<?> getColumnClass(int column) {
        Class[] types = new Class[]{String.class, Double.class, String.class, String.class};
        return types[column];
    }

    public void renderVertex(@NotNull IVertex vertex) {
        for (int i = 0; i < vertex.getArguments().size(); i++) {
            renderVertexArgument(vertex.getArguments().get(i), i);
        }
        ((DefaultTableModel) dataModel).fireTableDataChanged();
    }

    private void renderVertexArgument(@NotNull IVertexArgument vertexArgument, int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) dataModel;
        String name = vertexArgument.getID();
        double value = vertexArgument.getValue();
        String unitName = getUnitVector(vertexArgument.getUnit()) == null ? "" : getUnitVector(vertexArgument.getUnit()).get(0).getName();
        String description = vertexArgument.getDescription();
        model.addRow(new Object[]{name, value, unitName, description});
        addVertexArgumentListener(vertexArgument, rowIndex);
        addComboBoxToTable(vertexArgument);
    }

    private void addComboBoxToTable(IVertexArgument vertexArgument) {
        JComboBox<IUnit> comboBox = createUnitComboBox(vertexArgument.getUnit());
        setComboBoxListener(comboBox, vertexArgument);
        DefaultCellEditor cellEditor = new DefaultCellEditor(comboBox);
        comboBoxes.add(cellEditor);
    }

    private void setComboBoxListener(JComboBox<IUnit> comboBox, IVertexArgument vertexArgument) {
        comboBox.addItemListener(new ItemListener() {
            private IVertexArgument argument = vertexArgument;

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    flag = true;
                    IUnit unit = (IUnit) comboBox.getSelectedItem();
                    unitConversion(unit, argument);
                }
            }
        });
    }

    private void unitConversion(IUnit unit, IVertexArgument vertexArgument) {

        double newVar = vertexArgument.getValue() * unit.getScale();

        this.setValueAt(newVar, row, 1);

    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        int modelColumn = convertColumnIndexToModel(column);
        if (modelColumn == 2) {
            this.row = row;
            return comboBoxes.get(row);
        }
        return super.getCellEditor(row, column);
    }

    private JComboBox<IUnit> createUnitComboBox(String unit) {
        Vector<IUnit> unitVector = getUnitVector(unit);
        JComboBox<IUnit> comboBox = new JComboBox<>();
        if (unitVector != null) {
            comboBox = new JComboBox<>(unitVector);
        }

        return comboBox;
    }

    private Vector<IUnit> getUnitVector(String unit) {
        IUnitsContext unitsContext = getUnitsContextInstance();
        Vector<IUnit> unitVector = null;
        Collection<IUnitBundle> unitBundles = unitsContext.getSupportUnitBundles();
        for (IUnitBundle unitBundle : unitBundles) {
            if (StringUtils.equals(unit, unitBundle.getID())) {
                unitVector = changeIUnitCollectionToVector(unitBundle.getUnitFamily());
                break;
            }
        }
        return unitVector;
    }

    private Vector<IUnit> changeIUnitCollectionToVector(Collection<IUnit> units) {
        Vector<IUnit> vector = new Vector<>();
        for (IUnit unit : units) {
            vector.add(unit);
        }
        return vector;
    }

    private void addVertexArgumentListener(@NotNull IVertexArgument arg, int rowIndex) {
        TableModelListener listener = new TableModelListener() {
            private IVertexArgument argument = arg;

            @Override
            public void tableChanged(TableModelEvent e) {
                if ((e.getFirstRow() == rowIndex) && (e.getColumn() == 1) && !flag) {
                    double value = (double) VertexTable.this.getModel().getValueAt(e.getFirstRow(), e.getColumn());
                    argument.setValue(value);
                }
            }
        };
        tableModelListeners.add(listener);
        getModel().addTableModelListener(listener);
    }


    private IUnitsContext getUnitsContextInstance() {
        IUnitsContext unitsContext = null;
        try {
            unitsContext = ImplementationUtil.getInstanceOf(IUnitsContext.class);
        } catch (InstantiationException e) {
            ExceptionManager.INSTANCE.dealWith(e);
        }
        return unitsContext;
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) dataModel;
        if (model.getRowCount() > 0) {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for (TableModelListener listener : tableModelListeners) {
                model.removeTableModelListener(listener);
            }
            tableModelListeners.clear();
        }
    }
}
