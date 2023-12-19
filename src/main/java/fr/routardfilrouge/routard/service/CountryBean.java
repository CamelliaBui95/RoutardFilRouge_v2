package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.CountrySearch;
import fr.routardfilrouge.routard.metier.InfoType;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class CountryBean {
    private ArrayList<Country> countriesArr;
    private ObservableList<Country> countries;
    private FilteredList<Country> filteredCountries;
    private SortedList<Country> sortedCountries;
    private ObservableList<String> countryCodes;

    private CountrySearch countrySearch;

    public CountryBean() {

        countriesArr = new ArrayList<>();
        countries = FXCollections.observableArrayList();
        countryCodes = FXCollections.observableArrayList();

        countriesArr = DAOFactory.getCountryDAO().getAll();
        countries.addAll(countriesArr);
        countryCodes.addAll(extractCountryCodes());
        DAOFactory.getInfoDAO().setCountries(countriesArr);

        filteredCountries = new FilteredList<>(countries, null);
        sortedCountries = new SortedList<>(filteredCountries);

        countrySearch = new CountrySearch();
    }

    public void filterCountry(String searchStr) {
        String finalSearchStr = searchStr.toLowerCase();
        filteredCountries.setPredicate(country -> country.getName().toLowerCase().contains(finalSearchStr));
    }

    private ArrayList<String> extractCountryCodes() {
        ArrayList<String> countryCodes = new ArrayList<>();
        for(int i = 0; i < countries.size(); i++)
            countryCodes.add(countries.get(i).getIsoCode());
        return countryCodes;
    }

    public SortedList<Country> getSortedCountries() {
        return sortedCountries;
    }

    public ObservableList<String> getCountryCodes() {
        return countryCodes;
    }

    public void getCountriesByCountryCode(String countryCode) {
        if(countrySearch.getCountryCode().equals(countryCode))
            return;
        if(countryCode != null) {
            countrySearch.setCountryCode(countryCode);
            countries.setAll(DAOFactory.getCountryDAO().getLike(countrySearch));
        }
    }

    public void getCountriesByContinent(Continent continent) {
        if(countrySearch.getContinent() != null && countrySearch.getContinent().equals(continent))
            return;

        if(continent != null) {
            countrySearch.setContinent(continent);
            countries.setAll(DAOFactory.getCountryDAO().getLike(countrySearch));
        }
    }
    public void postCountry(Country country) {
        boolean isPosted = DAOFactory.getCountryDAO().post(country);
        if(isPosted) {
            countriesArr = DAOFactory.getCountryDAO().getAll();
            countries.setAll(countriesArr);
            DAOFactory.getInfoDAO().setCountries(countriesArr);
        }
    }

    public void updateCountry(Country country) {
        boolean isUpdated = DAOFactory.getCountryDAO().update(country);
        if(isUpdated) {
            countriesArr = DAOFactory.getCountryDAO().getAll();
            countries.setAll(countriesArr);
            DAOFactory.getInfoDAO().setCountries(countriesArr);
        }
    }

}
