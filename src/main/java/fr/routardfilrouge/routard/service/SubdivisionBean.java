package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.*;
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
    private ArrayList<SubType> typesArr;
    private ObservableList<SubType> types;

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

        typesArr = new ArrayList<>();
        types = FXCollections.observableArrayList();

        typesArr = DAOFactory.getSubdivisionDAO().getTypes();
        types.addAll(typesArr);
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

    public boolean postType(SubType type) {
        boolean isPosted = DAOFactory.getSubdivisionDAO().postType(type);
        if(isPosted) {
            typesArr = DAOFactory.getSubdivisionDAO().getTypes();
            types.setAll(typesArr);
        }
        return isPosted;
    }

    public boolean postSub(Subdivision subdivision) {
        boolean isPosted = DAOFactory.getSubdivisionDAO().post(subdivision);
        if(isPosted) {
            subdivisionArr = DAOFactory.getSubdivisionDAO().getAll();
            subdivisions.setAll(subdivisionArr);
        }
        return isPosted;
    }

    public boolean updateSub(Subdivision subdivision) {
        boolean isUpdated = DAOFactory.getSubdivisionDAO().update(subdivision);
        if(isUpdated) {
            subdivisionArr = DAOFactory.getSubdivisionDAO().getAll();
            subdivisions.setAll(subdivisionArr);
        }
        return isUpdated;
    }

    public boolean deleteSub(Subdivision subdivision) {
        boolean isDeleted = DAOFactory.getSubdivisionDAO().delete(subdivision);
        if(isDeleted) {
            subdivisionArr = DAOFactory.getSubdivisionDAO().getAll();
            subdivisions.setAll(subdivisionArr);
        }
        return isDeleted;
    }

    public SortedList<Subdivision> getSortedSubdivisions() {
        return sortedSubdivisions;
    }

    public ArrayList<Subdivision> getSubdivisionArr() {
        return subdivisionArr;
    }

    public ArrayList<SubType> getTypesArr() {
        return typesArr;
    }

    public ObservableList<SubType> getTypes() {
        return types;
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
