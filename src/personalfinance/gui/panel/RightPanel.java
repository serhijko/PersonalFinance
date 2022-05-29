/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import personalfinance.gui.table.TableData;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import personalfinance.gui.EnableEditDelete;
import personalfinance.gui.MainFrame;
import personalfinance.gui.Refresh;
import personalfinance.gui.toolbar.AbstractToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Serhij
 */
abstract public class RightPanel extends AbstractPanel {
    
    protected TableData td;
    
    private String title;
    private ImageIcon icon;
    private JPanel[] panels;

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon, JPanel[] panels) {
        super(frame);
        this.td = td;
        this.title = title;
        this.icon = icon;
        this.panels = panels;
    }

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon, AbstractToolBar tb) {
        this(frame, td, title, icon, new JPanel[]{tb});
    }

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon) {
        this(frame, td, title, icon, new JPanel[]{});
    }

    @Override
    public void refresh() {
        super.refresh();
        if (td != null) td.refresh();
        for (JPanel panel : panels) {
            if (panel instanceof Refresh) ((Refresh) panel).refresh();
        }
    }
    
    private void enabledEditDelete() {
        for (JPanel panel : panels) {
            if (panel instanceof EnableEditDelete) ((EnableEditDelete) panel).setEnabledEditDelete(false);
        }
        frame.getMenu().setEnabledEditDelete(false);
        
        if (td != null) {
            if (td.getSelectedRow() != -1) {
                for (JPanel panel : panels) {
                    if (panel instanceof EnableEditDelete) ((EnableEditDelete) panel).setEnabledEditDelete(true);
                }
                frame.getMenu().setEnabledEditDelete(true);
            }
        }
    }

    @Override
    protected void init() {
        enabledEditDelete();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel header = new JLabel(Text.get(title));
        header.setFont(Style.FONT_LABEL_HEADER);
        header.setIcon(icon);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(header);
        
        if (panels.length == 0) add(Box.createVerticalStrut(Style.PADDING_PANEL_EMPTY));
        
        for (JPanel panel : panels) {
            add(panel);
            add(Box.createVerticalStrut(Style.PADDING_PANEL));
        }
        
        if (td != null) {
            JScrollPane scroll = new JScrollPane(td);
            add(scroll);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            ListSelectionModel selectionModel = td.getSelectionModel();
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    enabledEditDelete();
                }
            });
        }
    }
    
}
