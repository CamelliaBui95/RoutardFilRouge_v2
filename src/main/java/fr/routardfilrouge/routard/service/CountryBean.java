package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.*;
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

    public ArrayList<Country> getCountriesArr() {
        return countriesArr;
    }

    public void getCountriesByCountryCode(String countryCode) {
        if (countrySearch.getCountryCode().equals(countryCode))
            return;
        if (countryCode != null) {
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
    public void getCountriesByCurrency(Currency currency){
        if(countrySearch.getCurrency() != null && countrySearch.getCurrency().equals(currency))
            return;
        if(currency != null){
            countrySearch.setCurrency(currency);
            countries.setAll(DAOFactory.getCountryDAO().getLike(countrySearch));
        }
    }
    public void getCountriesByLanguage(Language language) {
        if(countrySearch.getLanguage() != null && countrySearch.getLanguage().equals(language))
            return;
        if(language != null) {
            countrySearch.setLanguage(language);
            countries.setAll(DAOFactory.getCountryDAO().getLike(countrySearch));
        }
    }
    public void getCountriesByClimate(ClimateType climateType) {
        if(countrySearch.getClimateType() != null && countrySearch.getClimateType().equals(climateType))
            return;

        if(climateType != null) {
            countrySearch.setClimateType(climateType);
            countries.setAll(DAOFactory.getCountryDAO().getLike(countrySearch));
        }
    }
    public boolean postCountry(Country country) {
        boolean isPosted = DAOFactory.getCountryDAO().post(country);
        if(isPosted) {
            countriesArr = DAOFactory.getCountryDAO().getAll();
            countries.setAll(countriesArr);
            DAOFactory.getInfoDAO().setCountries(countriesArr);
        }
        return isPosted;
    }
    public boolean updateCountry(Country country) {
        boolean isUpdated = DAOFactory.getCountryDAO().update(country);
        if(isUpdated) {
            countriesArr = DAOFactory.getCountryDAO().getAll();
            countries.setAll(countriesArr);
            DAOFactory.getInfoDAO().setCountries(countriesArr);
        }
        return isUpdated;
    }

    public boolean deleteCountry(Country country) {
        boolean isDeleted = DAOFactory.getCountryDAO().delete(country);

        if(isDeleted) {
            countriesArr = DAOFactory.getCountryDAO().getAll();
            countries.setAll(countriesArr);
            DAOFactory.getInfoDAO().setCountries(countriesArr);
        }
        return isDeleted;
    }

}
