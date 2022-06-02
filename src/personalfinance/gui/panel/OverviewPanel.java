/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.gui.table.TransactionTableData;
import personalfinance.settings.Settings;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class OverviewPanel extends RightPanel {

    public OverviewPanel(MainFrame frame) {
        super(frame, new TransactionTableData(Settings.COUNT_OVERVIEW_ROWS), "LAST_TRANSACTIONS", Style.ICON_PANEL_OVERVIEW);
    }
    
}
