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
        //DAOFactory.getCountryDAO().setContinents(continentsArr);

        continents.addAll(continentsArr);
        continents.add(0, new Continent("", "Continent (" + continents.size() + ")"));
    }

    public ObservableList<Continent> getContinents() {
        return continents;
    }

    public void postContinent(Continent continent) {
        boolean isPosted = DAOFactory.getContinentDAO().post(continent);
        if(isPosted) {
            continentsArr = DAOFactory.getContinentDAO().getAll();
            //DAOFactory.getCountryDAO().setContinents(continentsArr);

            continents.setAll(continentsArr);
            continents.add(0, new Continent("", "Continent (" + continents.size() + ")"));
        }
    }
}
