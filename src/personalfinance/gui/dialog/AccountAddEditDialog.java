/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import javax.swing.JTextField;
import personalfinance.exception.ModelException;
import personalfinance.gui.MainFrame;
import personalfinance.model.Account;
import personalfinance.model.Common;
import personalfinance.model.Currency;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;
import personalfinance.settings.Style;

/**
 *
 * @author Serhij
 */
public class AccountAddEditDialog extends AddEditDialog {

    public AccountAddEditDialog(MainFrame frame) {
        super(frame);
    }

    @Override
    protected void init() {
        components.put("LABEL_TITLE", new JTextField());
        components.put("LABEL_CURRENCY", new CommonComboBox(SaveData.getInstance().getEnabledCurrencies().toArray()));
        components.put("LABEL_START_AMOUNT", new JTextField());
        
        icons.put("LABEL_TITLE", Style.ICON_TITLE);
        icons.put("LABEL_CURRENCY", Style.ICON_CURRENCY);
        icons.put("LABEL_START_AMOUNT", Style.ICON_AMOUNT);
        
        values.put("LABEL_START_AMOUNT", Format.amount(0));
    }

    @Override
    protected void setValues() {
        Account account = (Account) c;
        values.put("LABEL_TITLE", account.getTitle());
        values.put("LABEL_CURRENCY", account.getCurrency());
        values.put("LABEL_START_AMOUNT", account.getStartAmount());
    }

    @Override
    protected Common getCommonFromForm() throws ModelException {
        try {
            String title = ((JTextField) components.get("LABEL_TITLE")).getText();
            String startAmount = ((JTextField) components.get("LABEL_START_AMOUNT")).getText();
            Currency currency = (Currency) ((CommonComboBox) components.get("LABEL_CURRENCY")).getSelectedItem();
            return new Account(title, currency, Format.fromAmountToNumber(startAmount));
        } catch (NumberFormatException ex) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }
    
}
