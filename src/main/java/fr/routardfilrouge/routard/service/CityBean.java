package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Subdivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class CityBean {
    private ObservableList<City> cities;
    private FilteredList<City> filteredCities;
    private SortedList<City> sortedCities;

    public CityBean() {
        cities = FXCollections.observableArrayList();
        cities.addAll(DAOFactory.getCityDAO().getAll());

        filteredCities = new FilteredList<>(cities, null);
        sortedCities = new SortedList<>(filteredCities);

    }

    public void filterCityByCountry(String countryName) {
        String finalCountryName = countryName.toLowerCase();
        filteredCities.setPredicate(city -> city.getSubdivision().getCountry().getName().toLowerCase().contains(finalCountryName));
    }

    public void filterCityBySubdivision(String subdivisionName, String countryName) {
        String finalSubdivisionName = subdivisionName.toLowerCase();
        String finalCountryName = countryName.toLowerCase();
        filteredCities.setPredicate(city -> city.getSubdivision().getCountry().getName().toLowerCase().contains(finalCountryName) &&
                                            city.getSubdivision().getSubdivisionName().toLowerCase().contains(finalSubdivisionName));
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

    public SortedList<City> getSortedCities() {
        return sortedCities;
    }
}
