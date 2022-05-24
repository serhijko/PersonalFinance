/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import javax.swing.JOptionPane;
import personalfinance.gui.MainFrame;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public class ErrorDialog {
    
    public static void show(MainFrame frame, String text) {
        JOptionPane.showMessageDialog(frame, text, Text.get("ERROR"), JOptionPane.ERROR_MESSAGE);
    }
    
}
