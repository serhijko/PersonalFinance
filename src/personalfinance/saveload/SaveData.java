/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.saveload;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import personalfinance.exception.ModelException;
import personalfinance.model.*;

/**
 *
 * @author Serhij
 */
public final class SaveData {
    
    private static SaveData instance;
    private List<Article> articles = new ArrayList();
    private List<Currency> currencies = new ArrayList();
    private List<Account> accounts = new ArrayList();
    private List<Transaction> transactions = new ArrayList();
    private List<Transfer> transfers = new ArrayList();
    
    private final Filter filter;
    private Common oldCommon;
    private boolean saved = true;

    private SaveData() {
        load();
        this.filter = new Filter();
    }

    public void load() {
        SaveLoad.load(this);
        sort();
        for (Account a : accounts) {
            a.setAmountFromTransactionsAndTransfers(transactions, transfers);
        }
    }

    public void clear() {
        articles.clear();
        currencies.clear();
        accounts.clear();
        transactions.clear();
        transfers.clear();
    }

    private void sort() {
        this.articles.sort((Article a1, Article a2) -> a1.getTitle().compareToIgnoreCase(a2.getTitle()));
        this.accounts.sort((Account a1, Account a2) -> a1.getTitle().compareToIgnoreCase(a2.getTitle()));
        this.transactions.sort((Transaction t1, Transaction t2) -> (int) t2.getDate().compareTo(t1.getDate()));
        this.transfers.sort((Transfer t1, Transfer t2) -> (int) t2.getDate().compareTo(t1.getDate()));
        this.currencies.sort(new Comparator<Currency>() {
            @Override
            public int compare(Currency c1, Currency c2) {
                if (c1.isBase()) return -1;
                if (c2.isBase()) return 1;
                if (c1.isOn() ^ c2.isOn()) {
                    if (c2.isOn()) return 1;
                    else return -1;
                }
                return c1.getTitle().compareToIgnoreCase(c2.getTitle());
            }
        });
    }
    
    public void save() {
        SaveLoad.save(this);
        saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public Filter getFilter() {
        return filter;
    }

    public static SaveData getInstance() {
        if (instance == null) instance = new SaveData();
        return instance;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setArticles(List<Article> articles) {
        if (articles != null) this.articles = articles;
    }

    public void setCurrencies(List<Currency> currencies) {
        if (currencies != null) this.currencies = currencies;
    }

    public void setAccounts(List<Account> accounts) {
        if (accounts != null) this.accounts = accounts;
    }

    public void setTransactions(List<Transaction> transactions) {
        if (transactions != null) this.transactions = transactions;
    }

    public void setTransfers(List<Transfer> transfers) {
        if (transfers != null) this.transfers = transfers;
    }
    
    public Currency getBaseCurrency() {
        for (Currency currency : currencies)
            if (currency.isBase()) return currency;
        return new Currency();
    }
    
    public ArrayList<Currency> getEnabledCurrencies() {
        ArrayList<Currency> list = new ArrayList<>();
        for (Currency currency : currencies)
            if (currency.isOn()) list.add(currency);
        return list;
    }
    
    public List<Transaction> getFilterTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        for (Transaction transaction : transactions)
            if (filter.check(transaction.getDate())) list.add(transaction);
        return list;
    }
    
    public List<Transfer> getFilterTransfers() {
        ArrayList<Transfer> list = new ArrayList<>();
        for (Transfer transfer : transfers)
            if (filter.check(transfer.getDate())) list.add(transfer);
        return list;
    }
    
    public List<Transaction> getTransactionsOnCount(int count) {
        return new ArrayList<>(transactions.subList(0, Math.min(count, transactions.size())));
    }
    
    public Common getOldCommon() {
        return oldCommon;
    }
    
    public void add(Common c) throws ModelException {
        List ref = getRef(c);
        if (ref.contains(c)) throw new ModelException(ModelException.IS_EXISTS);
        ref.add(c);
        c.postAdd(this);
        sort();
        saved = false;
    }
    
    public void edit(Common oldC, Common newC) throws ModelException {
        List ref = getRef(oldC);
        if (ref.contains(newC) && oldC != ref.get(ref.indexOf(newC))) throw new ModelException(ModelException.IS_EXISTS);
        ref.set(ref.indexOf(oldC), newC);
        oldCommon = oldC;
        newC.postEdit(this);
        sort();
        saved = false;
    }
    
    public void remove(Common c) {
        getRef(c).remove(c);
        c.postRemove(this);
        saved = false;
    }

    private List getRef(Common c) {
        if (c instanceof Account) return accounts;
        else if (c instanceof Article) return articles;
        else if (c instanceof Currency) return currencies;
        else if (c instanceof Transaction) return transactions;
        else if (c instanceof Transfer) return transfers;
        return null;
    }

    @Override
    public String toString() {
        return "SaveData{" + "articles=" + articles + ", currencies=" + currencies + ", accounts=" + accounts + ", transactions=" + transactions + ", transfers=" + transfers + '}';
    }
    
    public void updateCurrencies() throws Exception {
        HashMap<String, Double> rates = RateCurrency.getRates(getBaseCurrency());
        for (Currency c : currencies)
            c.setRate(rates.get(c.getCode()));
        for (Account a : accounts)
            a.getCurrency().setRate(rates.get(a.getCurrency().getCode()));
        saved = false;
    }
    
}
