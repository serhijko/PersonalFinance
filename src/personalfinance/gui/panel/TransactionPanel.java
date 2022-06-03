/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.gui.table.TransactionTableData;
import personalfinance.gui.toolbar.FunctionsToolBar;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class TransactionPanel extends RightPanel {

    public TransactionPanel(MainFrame frame) {
        super(frame, new TransactionTableData(), "TRANSACTIONS", Style.ICON_PANEL_TRANSACTIONS, new FunctionsToolBar());
    }
    
}
