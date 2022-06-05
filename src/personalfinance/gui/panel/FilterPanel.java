/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import personalfinance.gui.MainButton;
import personalfinance.gui.MainFrame;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;
import personalfinance.settings.HandlerCode;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class FilterPanel extends AbstractPanel {

    public FilterPanel(MainFrame frame) {
        super(frame);
        init();
    }

    @Override
    protected final void init() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        setLayout(layout);
        MainButton left = new MainButton(Style.ICON_LEFT, null, HandlerCode.LEFT);
        MainButton step = new MainButton(Format.getTitleFilter(SaveData.getInstance().getFilter()), null, HandlerCode.STEP);
        MainButton right = new MainButton(Style.ICON_RIGHT, null, HandlerCode.RIGHT);
        
        setBorder(Style.BORDER_FILTER_PANEL);
        
        step.setFont(Style.FONT_BUTTON_FILTER);
        step.setPreferredSize(new Dimension(Style.WIDTH_FILTER_BUTTON, left.getPreferredSize().height));
        
        add(left);
        add(step);
        add(right);
    }
    
}
