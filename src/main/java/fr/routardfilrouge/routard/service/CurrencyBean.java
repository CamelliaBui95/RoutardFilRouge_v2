package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CurrencyBean {
    private ArrayList<Currency> currenciesArr;
    private ObservableList<Currency> currencies;

    public CurrencyBean(){
        currencies = FXCollections.observableArrayList();
        currenciesArr = DAOFactory.getCurrencyDAO().getAll();


        currencies.addAll(currenciesArr);
        currencies.add(0,new Currency("", "Currency (" + currencies.size() + ")"));
    }

    public void postCurrency(Currency currency){
        boolean isPosted = DAOFactory.getCurrencyDAO().post(currency);
        if(isPosted){
            currenciesArr = DAOFactory.getCurrencyDAO().getAll();

            currencies.setAll(currenciesArr);
            currencies.add(0, new Currency("", "Currency (" + currencies.size() + ")"));
        }
    }
}
