/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.gui.table.CurrencyTableData;
import personalfinance.gui.toolbar.FunctionsToolBar;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class CurrencyPanel extends RightPanel {

    public CurrencyPanel(MainFrame frame) {
        super(frame, new CurrencyTableData(), "CURRENCIES", Style.ICON_PANEL_CURRENCIES, new FunctionsToolBar());
    }
    
}
