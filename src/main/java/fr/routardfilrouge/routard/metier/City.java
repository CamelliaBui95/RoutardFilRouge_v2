package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class City {
    private IntegerProperty idCity;
    private StringProperty cityName;
    private Subdivision subdivision;

    public City(int idCity, String cityName) {
        this.idCity = new SimpleIntegerProperty(idCity);
        this.cityName = new SimpleStringProperty(cityName);
    }

    public int getIdCity() {
        return idCity.get();
    }

    public IntegerProperty idCityProperty() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity.set(idCity);
    }

    public String getCityName() {
        return cityName.get();
    }

    public StringProperty cityNameProperty() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName.set(cityName);
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    @Override
    public String toString() {
        return "idCity=" + getIdCity() +
                ", cityName=" + getCityName() + ", subdivision=" + (subdivision != null ? subdivision.getSubdivisionName() : "");
    }
}
