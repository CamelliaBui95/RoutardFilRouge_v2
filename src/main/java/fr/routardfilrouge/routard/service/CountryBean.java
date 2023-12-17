package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class CountryBean {
    private ObservableList<Country> countries;
    private FilteredList<Country> filteredCountries;
    private SortedList<Country> sortedCountries;

    public CountryBean() {
        countries = FXCollections.observableArrayList();
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

}
