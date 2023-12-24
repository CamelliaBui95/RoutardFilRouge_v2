package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.ClimateType;
import fr.routardfilrouge.routard.metier.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ClimateBean {
    private ObservableList<ClimateType> climateTypes;

    public ClimateBean() {
        this.climateTypes = FXCollections.observableArrayList();

        climateTypes.addAll(DAOFactory.getClimateDAO().getAll());
    }

    public ObservableList<ClimateType> getClimateTypes() {
        return climateTypes;
    }

    public ArrayList<Weather> getWeather(City city) {
        return DAOFactory.getClimateDAO().getWeather(city);
    }
}
