/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.gui.table.AccountTableData;
import personalfinance.gui.toolbar.FunctionsToolBar;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class AccountPanel extends RightPanel {

    public AccountPanel(MainFrame frame) {
        super(frame, new AccountTableData(), "ACCOUNTS", Style.ICON_PANEL_ACCOUNTS, new FunctionsToolBar());
    }
    
}
