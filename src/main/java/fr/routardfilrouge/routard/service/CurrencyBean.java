package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CurrencyBean {
    private ArrayList<Currency> currenciesArr;
    private ObservableList<Currency> currencies;

    public CurrencyBean(){
        currencies = FXCollections.observableArrayList();
        currenciesArr = DAOFactory.getCurrencyDAO().getAll();
        DAOFactory.getCountryDAO().setCurrency(currenciesArr);

        currencies.addAll(currenciesArr);
        currencies.add(0,new Currency("", "Currency (" + currencies.size() + ")"));
    }
    public ObservableList<Currency> getCurrencies(){
        return currencies;
    }

    public ArrayList<Currency> getCurrenciesArr(){
        return currenciesArr;
    }

    public void postCurrency(Currency currency){
        boolean isPosted = DAOFactory.getCurrencyDAO().post(currency);
        if(isPosted){
            currenciesArr = DAOFactory.getCurrencyDAO().getAll();
            DAOFactory.getCountryDAO().setCurrency(currenciesArr);
            currencies.setAll(currenciesArr);
            currencies.add(0, new Currency("", "Currency (" + currencies.size() + ")"));
        }
    }
}
