/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table;

import javax.swing.ImageIcon;
import personalfinance.gui.table.model.ArticleTableModel;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class ArticleTableData extends TableData {
    
    private static final String[] columns = new String[]{"TITLE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_TITLE};

    public ArticleTableData() {
        super(new ArticleTableModel(columns), columns, icons);
    }
    
}
