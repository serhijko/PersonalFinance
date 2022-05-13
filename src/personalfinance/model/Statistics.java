/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.model;

import java.util.HashMap;
import java.util.List;
import personalfinance.saveload.SaveData;

/**
 *
 * @author Serhij
 */
public class Statistics {
    
    public static double getBalanceCurrency(Currency currency) {
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for (Account account : sd.getAccounts()) {
            if (currency.equals(account.getCurrency())) amount += account.getAmount();
        }
        return amount;
    }
    
    public static double getBalance(Currency currency) {
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for (Account account : sd.getAccounts()) {
            amount += account.getAmount() * account.getCurrency().getRateByCurrency(currency);
        }
        return amount;
    }
    
    public static HashMap<String, Double> getDataForChartOnIncomeArticles() {
        return getDataForChartOnArticles(true);
    }
    
    public static HashMap<String, Double> getDataForChartOnExpArticles() {
        return getDataForChartOnArticles(false);
    }
    
    private static HashMap<String, Double> getDataForChartOnArticles(boolean income) {
        List<Transaction> transactions = SaveData.getInstance().getTransactions();
        HashMap<String, Double> data = new HashMap();
        for (Transaction transaction : transactions) {
            if ((income && transaction.getAmount() > 0) || (!income && transaction.getAmount() < 0)) {
                String key = transaction.getArticle().getTitle();
                double summa = 0;
                double amount = transaction.getAmount();
                if (!income) amount *= -1;
                if (data.containsKey(key)) summa = data.get(key);
                summa += amount * transaction.getAccount().getCurrency().getRateByCurrency(SaveData.getInstance().getBaseCurrency());
                data.put(key, round(summa));
            }
        }
        return data;
    }

    private static double round(double value) {
        return (double) Math.round(value * 100 / 100);
    }
    
}
