package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.ClimateType;
import fr.routardfilrouge.routard.metier.Month;
import fr.routardfilrouge.routard.metier.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ClimateBean {
    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
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

    public ArrayList<Weather> createNewWeatherList(City city) {
        ArrayList<Weather> newWeatherList = new ArrayList<>();
        for(int i = 0 ; i < MONTHS.length; i++) {
            Month month = new Month(i + 1, MONTHS[i]);
            Weather weather = new Weather();
            weather.setMonth(month);
            weather.setCity(city);
            newWeatherList.add(weather);
        }

        return newWeatherList;
    }
}
