/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import javax.swing.JPanel;
import personalfinance.gui.MainFrame;
import personalfinance.gui.table.TransferTableData;
import personalfinance.gui.toolbar.FunctionsToolBar;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class TransferPanel extends RightPanel {

    public TransferPanel(MainFrame frame) {
        super(frame, new TransferTableData(), "TRANSFERS", Style.ICON_PANEL_TRANSFERS,
                new JPanel[] {new FunctionsToolBar(), new FilterPanel(frame)});
    }
    
}