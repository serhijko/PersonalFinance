/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
public class AboutDialog extends JDialog{

    public AboutDialog() {
        super();
        init();
        setTitle(Text.get("DIALOG_ABOUT_TITLE"));
        setIconImage(Style.ICON_ABOUT.getImage());
        setResizable(false);
    }

    private void init() {
        JEditorPane pane = new JEditorPane("text/html", Text.get("ABOUT"));
        pane.setEditable(false);
        pane.setOpaque(false);
        
        pane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent he) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(he.getEventType())) {
                    try {
                        Desktop.getDesktop().browse(he.getURL().toURI());
                    } catch (URISyntaxException | IOException ex) {
                        //Logger.getLogger(AboutDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        add(pane);
        pack();
        setLocationRelativeTo(null);
    }
    
}
