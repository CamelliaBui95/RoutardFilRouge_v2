package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.InfoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;

public class CountryBean {
    private ObservableList<Country> countries;
    private FilteredList<Country> filteredCountries;
    private SortedList<Country> sortedCountries;
    private ObservableList<InfoType> infoTypes;

    public CountryBean() {
        infoTypes = FXCollections.observableArrayList();
        ArrayList<InfoType> infoTypesArr = DAOFactory.getInfoDAO().getAllType();
        infoTypes.addAll(infoTypesArr);

        countries = FXCollections.observableArrayList();
        DAOFactory.getCountryDAO().setInfoTypes(infoTypesArr);
        countries.addAll(DAOFactory.getCountryDAO().getAll());

        filteredCountries = new FilteredList<>(countries, null);
        sortedCountries = new SortedList<>(filteredCountries);
    }

    public void filterCountry(String searchStr) {
        String finalSearchStr = searchStr.toLowerCase();
        filteredCountries.setPredicate(country -> country.getName().toLowerCase().contains(finalSearchStr));
    }

    public SortedList<Country> getSortedCountries() {
        return sortedCountries;
    }

    public ObservableList<InfoType> getInfoTypes() {
        return infoTypes;
    }
}
