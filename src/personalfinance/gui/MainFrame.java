/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public class MainFrame extends JFrame {
    
    private GridBagConstraints constraints;
    
    public MainFrame() {
        super(Text.get("PROGRAM_NAME"));
        setResizable(false);
        setIconImage(Style.ICON_MAIN.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridBagLayout());
        
        constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        
        //add toolbar
        
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        //add leftpanel
        pack();
        setLocationRelativeTo(null);
    }
    
}
