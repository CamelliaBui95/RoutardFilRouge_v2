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
    private ArrayList<ClimateType> climateTypeArr;

    public ClimateBean() {
        climateTypeArr = new ArrayList<>();
        climateTypes = FXCollections.observableArrayList();

        climateTypeArr = DAOFactory.getClimateDAO().getAll();
        climateTypes.addAll(climateTypeArr);
    }

    public ObservableList<ClimateType> getClimateTypes() {
        return climateTypes;
    }

    public ArrayList<ClimateType> getClimateTypeArr() {
        return climateTypeArr;
    }

    public ArrayList<Weather> getWeather(City city) {
        return DAOFactory.getClimateDAO().getWeather(city);
    }

    public boolean updateWeather(Weather weather) {
        return DAOFactory.getClimateDAO().updateWeather(weather);
    }

    public boolean postWeather(Weather weather) {
        return DAOFactory.getClimateDAO().postWeather(weather);
    }
}
