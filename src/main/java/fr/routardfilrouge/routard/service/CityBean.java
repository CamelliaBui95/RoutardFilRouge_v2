package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class CityBean {
    private ObservableList<City> cities;
    private FilteredList<City> filteredCities;
    private SortedList<City> sortedCities;

    private CitySearch citySearch;

    public CityBean() {
        cities = FXCollections.observableArrayList();
        cities.addAll(DAOFactory.getCityDAO().getAll());

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

    public SortedList<City> getSortedCities() {
        return sortedCities;
    }
}
