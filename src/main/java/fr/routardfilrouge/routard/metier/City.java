package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class City {
    private IntegerProperty idCity;
    private StringProperty cityName;
    private Subdivision subdivision;
    private ClimateType climateType;

    public City(int idCity, String cityName) {
        this.idCity = new SimpleIntegerProperty(idCity);
        this.cityName = new SimpleStringProperty(cityName);
        climateType = new ClimateType();
    }

    public City() {
        this.idCity = new SimpleIntegerProperty(0);
        this.cityName = new SimpleStringProperty("City");
        climateType = new ClimateType();
        subdivision = new Subdivision();
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

    public ClimateType getClimateType() {
        return climateType;
    }

    public void setClimateType(ClimateType climateType) {
        this.climateType = climateType;
    }

    @Override
    public String toString() {
        return getCityName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(idCity, city.idCity) && Objects.equals(cityName, city.cityName) && Objects.equals(subdivision, city.subdivision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity.get(), cityName.get(), subdivision.getSubdivisionName());
    }
}
