/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import personalfinance.gui.Refresh;
import personalfinance.model.Common;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public abstract class MainTableModel extends AbstractTableModel implements Refresh {
    
    protected List<? extends Common> data;
    protected List<String> columns = new ArrayList<>();

    public MainTableModel(List<? extends Common> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Text.get(columns.get(columnIndex));
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = getValueAt(0, columnIndex);
        if (obj == null) return Object.class;
        return obj.getClass();
    }

    @Override
    public void refresh() {
        updateData();
        fireTableStructureChanged();
        fireTableDataChanged();
    }

    protected abstract void updateData();
    
}