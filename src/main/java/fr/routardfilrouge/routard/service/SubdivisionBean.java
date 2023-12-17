package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Subdivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class SubdivisionBean {
    private ObservableList<Subdivision> subdivisions;
    private FilteredList<Subdivision> filteredSubdivisions;
    private SortedList<Subdivision> sortedSubdivisions;


    public SubdivisionBean() {
        subdivisions = FXCollections.observableArrayList();
        subdivisions.addAll(DAOFactory.getSubdivisionDAO().getAll());

        filteredSubdivisions = new FilteredList<>(subdivisions, null);
        sortedSubdivisions = new SortedList<>(filteredSubdivisions);
    }

    public void filterSubdivisions(String searchStr) {
        String finalSearchStr = searchStr.toLowerCase();
        filteredSubdivisions.setPredicate(sub -> sub.getSubdivisionName().toLowerCase().contains(finalSearchStr));
    }

    public SortedList<Subdivision> getSortedSubdivisions() {
        return sortedSubdivisions;
    }
}
