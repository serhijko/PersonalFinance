/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import personalfinance.gui.menu.MainMenu;
import personalfinance.gui.panel.LeftPanel;
import personalfinance.gui.panel.RightPanel;
import personalfinance.gui.panel.TransactionPanel;
import personalfinance.gui.toolbar.MainToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public class MainFrame extends JFrame implements Refresh {
    
    private final GridBagConstraints constraints;
    private final MainMenu mb;
    private final LeftPanel leftPanel;
    private RightPanel rightPanel;
    private final MainToolBar tb;

    public MainFrame() {
        super(Text.get("PROGRAM_NAME"));
        
        setResizable(false);
        setIconImage(Style.ICON_MAIN.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mb = new MainMenu(this);
        setJMenuBar(mb);
        
        setLayout(new GridBagLayout());
        
        constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        
        tb = new MainToolBar();
        add(tb, constraints);
        
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        
        leftPanel = new LeftPanel(this);
        add(leftPanel, constraints);
        
        setRightPanel(new TransactionPanel(this));
        
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        mb.refresh();
        leftPanel.refresh();
        pack();
    }

    public MainMenu getMenu() {
        return mb;
    }

    private void setRightPanel(RightPanel panel) {
        if (rightPanel != null) remove(rightPanel);
        constraints.gridy = 1;
        constraints.gridx = 1;
        rightPanel = panel;
        panel.setBorder(Style.BORDER_PANEL);
        add(rightPanel, constraints);
        pack();
    }
    
}
