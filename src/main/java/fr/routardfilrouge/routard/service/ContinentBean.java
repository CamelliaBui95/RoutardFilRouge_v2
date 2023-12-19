package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Continent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ContinentBean {
    private ArrayList<Continent> continentsArr;
    private ObservableList<Continent> continents;
    public ContinentBean() {
        continents = FXCollections.observableArrayList();
        continentsArr = DAOFactory.getContinentDAO().getAll();

        continents.addAll(continentsArr);
    }

    public ObservableList<Continent> getContinents() {
        return continents;
    }

    public ArrayList<Continent> getContinentsArr() {
        return continentsArr;
    }
}
