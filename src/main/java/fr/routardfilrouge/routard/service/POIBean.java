package fr.routardfilrouge.routard.service;


import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.POI;
import fr.routardfilrouge.routard.metier.POISearch;
import fr.routardfilrouge.routard.metier.POIType;
import fr.routardfilrouge.routard.metier.Subdivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;

public class POIBean {
    ObservableList<POI> pois;
    FilteredList<POI> filteredPOIs;
    SortedList<POI> sortedPOIs;
    ArrayList<POIType> categoriesArr;
    ObservableList<POIType> categories;
    private POISearch poiSearch;

    public POIBean() {
        pois = FXCollections.observableArrayList();
        categoriesArr = new ArrayList<>();
        filteredPOIs = new FilteredList<>(pois, null);
        sortedPOIs = new SortedList<>(filteredPOIs);
        poiSearch = new POISearch();

        categories = FXCollections.observableArrayList();
        categoriesArr = DAOFactory.getPoiDAO().getAllCategories();
        categories.addAll(categoriesArr);
    }

    public void filterPOIbyName(String searchStr) {
        String finalSearchStr = searchStr.toLowerCase();
        filteredPOIs.setPredicate(poi -> poi.getPOIName().toLowerCase().contains(finalSearchStr));
    }

    public SortedList<POI> getSortedPOIs() {
        return sortedPOIs;
    }

    public ObservableList<POIType> getCategories() {
        return categories;
    }

    public ArrayList<POIType> getCategoriesArr() {
        return categoriesArr;
    }

    public void getPOIsBySubdivision(Subdivision subdivision) {
        if(poiSearch.getSubdivision() != null && poiSearch.getSubdivision().equals(subdivision))
            return;

        if(subdivision != null) {
            poiSearch.setSubdivision(subdivision);
            pois.setAll(DAOFactory.getPoiDAO().getLike(poiSearch));
        }
    }

    public void getPOIsByCategory(POIType category) {
        if(poiSearch.getType() != null && poiSearch.getType().equals(category))
            return;

        if(category != null) {
            poiSearch.setType(category);
            pois.setAll(DAOFactory.getPoiDAO().getLike(poiSearch));
        }
    }
}
