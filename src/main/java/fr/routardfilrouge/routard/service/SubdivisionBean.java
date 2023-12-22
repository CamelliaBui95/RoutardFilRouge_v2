package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.metier.SubdivisionSearch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.HashMap;

public class SubdivisionBean {
    private ArrayList<Subdivision> subdivisionArr;
    private ObservableList<Subdivision> subdivisions;
    private FilteredList<Subdivision> filteredSubdivisions;
    private SortedList<Subdivision> sortedSubdivisions;
    private SubdivisionSearch subdivisionSearch;

    private HashMap<String, ArrayList<Subdivision>> countryToSubdivisionsMap;

    public SubdivisionBean() {
        subdivisionArr = new ArrayList<>();
        subdivisions = FXCollections.observableArrayList();

        subdivisionArr = DAOFactory.getSubdivisionDAO().getAll();
        subdivisions.addAll(subdivisionArr);

        mapSubdivisionToCountry();

        filteredSubdivisions = new FilteredList<>(subdivisions, null);
        sortedSubdivisions = new SortedList<>(filteredSubdivisions);

        subdivisionSearch = new SubdivisionSearch();
    }

    public void filterSubdivisions(String searchSubdivision, String searchCountry) {
        filteredSubdivisions.setPredicate(sub -> sub.getCountry().getName().toLowerCase().contains(searchCountry.toLowerCase())
                                                    && sub.getSubdivisionName().toLowerCase().contains(searchSubdivision.toLowerCase()));
    }

    public void getSubdivisionsByCountryCode(String countryCode) {
        if(subdivisionSearch.getCodeCountry().equals(countryCode))
            return;

        if(countryCode != null) {
            subdivisionSearch.setCodeCountry(countryCode);
            subdivisions.setAll(DAOFactory.getSubdivisionDAO().getLike(subdivisionSearch));
        }
    }

    public void getSubdivisionsByContinent(Continent continent) {
        if(subdivisionSearch.getContinent() != null && subdivisionSearch.getContinent().equals(continent))
            return;

        if(continent != null) {
            subdivisionSearch.setContinent(continent);
            subdivisions.setAll(DAOFactory.getSubdivisionDAO().getLike(subdivisionSearch));
        }
    }

    public SortedList<Subdivision> getSortedSubdivisions() {
        return sortedSubdivisions;
    }

    public ArrayList<Subdivision> getSubdivisionArr() {
        return subdivisionArr;
    }

    private void mapSubdivisionToCountry() {
        countryToSubdivisionsMap = new HashMap<>();
        for(Subdivision subdivision : subdivisionArr) {
            String countryCode = subdivision.getCountry().getIsoCode();

            countryToSubdivisionsMap.putIfAbsent(countryCode, new ArrayList<>());

            countryToSubdivisionsMap.get(countryCode).add(subdivision);
        }
    }

    public ArrayList<Subdivision> populateSubdivisionsByCountry(Country country) {
        if(country.getIsoCode().isEmpty())
            return subdivisionArr;
        return countryToSubdivisionsMap.get(country.getIsoCode());
    }
}
