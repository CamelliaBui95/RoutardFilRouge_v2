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
    private ObservableList<Country> countries;
    private FilteredList<Country> filteredCountries;
    private SortedList<Country> sortedCountries;
    private ObservableList<InfoType> infoTypes;
    private ObservableList<Continent> continents;
    private ObservableList<String> countryCodes;

    private CountrySearch countrySearch;

    public CountryBean() {
        infoTypes = FXCollections.observableArrayList();
        ArrayList<InfoType> infoTypesArr = DAOFactory.getInfoDAO().getAllType();
        infoTypes.addAll(infoTypesArr);

        continents = FXCollections.observableArrayList();
        ArrayList<Continent> continentArr = DAOFactory.getContinentDAO().getAll();
        continents.addAll(continentArr);

        DAOFactory.getCountryDAO().setInfoTypes(infoTypesArr);
        DAOFactory.getCountryDAO().setContinents(continentArr);

        countries = FXCollections.observableArrayList();
        countries.addAll(DAOFactory.getCountryDAO().getAll());

        countryCodes = FXCollections.observableArrayList();
        countryCodes.addAll(extractCountryCodes());

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

    public ObservableList<InfoType> getInfoTypes() {
        return infoTypes;
    }

    public ObservableList<Continent> getContinents() {
        return continents;
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
}
