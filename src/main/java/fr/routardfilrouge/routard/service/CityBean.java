package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;

public class CityBean {
    private ArrayList<City> citiesArr;
    private ObservableList<City> cities;
    private FilteredList<City> filteredCities;
    private SortedList<City> sortedCities;

    private CitySearch citySearch;

    public CityBean() {
        citiesArr = new ArrayList<>();
        cities = FXCollections.observableArrayList();

        citiesArr = DAOFactory.getCityDAO().getAll();
        cities.addAll(citiesArr);

        filteredCities = new FilteredList<>(cities, null);
        sortedCities = new SortedList<>(filteredCities);

        citySearch = new CitySearch();
    }

    public void filterCity(String cityName, String subdivisionName, String countryName) {
        String finalCityName = cityName.toLowerCase();
        String finalSubdivisionName = subdivisionName.toLowerCase();
        String finalCountryName = countryName.toLowerCase();

        filteredCities.setPredicate(city -> {
            Subdivision subdivision = city.getSubdivision();
            Country country = subdivision.getCountry();

            return country.getName().toLowerCase().contains(finalCountryName)
                    && subdivision.getSubdivisionName().toLowerCase().contains(finalSubdivisionName)
                    && city.getCityName().toLowerCase().contains(finalCityName);
        });
    }

    public void getCitiesByCountryCode(String countryCode) {
        if(citySearch.getCountryCode().equals(countryCode))
            return;
        if(countryCode != null) {
            citySearch.setCountryCode(countryCode);
            cities.setAll(DAOFactory.getCityDAO().getLike(citySearch));
        }
    }

    public void getCitiesByContinent(Continent continent) {
        if(citySearch.getContinent() != null && citySearch.getContinent().equals(continent))
            return;

        if(continent != null) {
            citySearch.setContinent(continent);
            cities.setAll(DAOFactory.getCityDAO().getLike(citySearch));
        }
    }

    public boolean postCity(City city) {
        boolean isPosted = DAOFactory.getCityDAO().post(city);

        if(isPosted) {
            citiesArr = DAOFactory.getCityDAO().getAll();
            cities.setAll(DAOFactory.getCityDAO().getLike(citySearch));
        }

        return isPosted;
    }

    public boolean updateCity(City city) {
        boolean isUpdated = DAOFactory.getCityDAO().update(city);

        if(isUpdated) {
            citiesArr = DAOFactory.getCityDAO().getAll();
            cities.setAll(DAOFactory.getCityDAO().getLike(citySearch));
        }

        return isUpdated;
    }

    public SortedList<City> getSortedCities() {
        return sortedCities;
    }

    public ArrayList<City> getCitiesArr() {
        return citiesArr;
    }
}
