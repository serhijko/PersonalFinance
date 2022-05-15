/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import personalfinance.settings.Format;
import personalfinance.settings.Settings;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public class PersonalFinance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        System.out.println(Format.dateMonth(new Date()));
        //System.out.println(Text.get("PROGRAM_NAME"));
        //System.out.println(Arrays.toString(Text.getMonths()));
    }
    
    private static void init() {
        try {
            Settings.init();
            Text.init();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Settings.FONT_ROBOTO_LIGHT));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(PersonalFinance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
