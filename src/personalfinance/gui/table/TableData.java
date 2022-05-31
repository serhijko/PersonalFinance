/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import personalfinance.gui.Refresh;
import personalfinance.gui.table.model.MainTableModel;
import personalfinance.gui.table.renderer.MainTableCellRenderer;
import personalfinance.gui.table.renderer.TableHeaderIconRenderer;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public abstract class TableData extends JTable implements Refresh {
    
    private final String[] columns;
    private final ImageIcon[] icons;

    public TableData(MainTableModel model, String[] columns, ImageIcon[] icons) {
        super(model);
        this.columns = columns;
        this.icons = icons;
        
        getTableHeader().setFont(Style.FONT_TABLE_HEADER);
        setFont(Style.FONT_TABLE);
        setRowHeight(getRowHeight() + Style.TABLE_ADD_ROW_HEIGHT);
        
        setAutoCreateRowSorter(true);
        setPreferredScrollableViewportSize(Style.DIMENSION_TABLE_SHOW_SIZE);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        
        MainTableCellRenderer renderer = new MainTableCellRenderer();
        setDefaultRenderer(String.class, renderer);
        
    }

    @Override
    public void refresh() {
        int selectedRow = getSelectedRow();
        ((MainTableModel) getModel()).refresh();
        /*for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }*/
        if (selectedRow != -1 && selectedRow < getRowCount()) setRowSelectionInterval(selectedRow, selectedRow);
    }
    
}
