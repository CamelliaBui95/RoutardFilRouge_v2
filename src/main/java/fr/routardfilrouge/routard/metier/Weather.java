package fr.routardfilrouge.routard.metier;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;

public class Weather {
    private City city;
    private Month month;

    private FloatProperty avgTemp;
    private IntegerProperty avgHumidity;
    private FloatProperty avgPrecipitation;

    public Weather(City city, Month month, float avgTemp, int avgHumidity, float avgPrecipitation) {
        this.city = city;
        this.month = month;
        this.avgTemp = new SimpleFloatProperty(avgTemp);
        this.avgHumidity = new SimpleIntegerProperty(avgHumidity);
        this.avgPrecipitation = new SimpleFloatProperty(avgPrecipitation);
    }

    public Weather() {
        this.city = new City();
        this.month = new Month();
        this.avgTemp = new SimpleFloatProperty(0);
        this.avgHumidity = new SimpleIntegerProperty(0);
        this.avgPrecipitation = new SimpleFloatProperty(0);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public float getAvgTemp() {
        return avgTemp.get();
    }

    public FloatProperty avgTempProperty() {
        return avgTemp;
    }

    public void setAvgTemp(float avgTemp) {
        this.avgTemp.set(avgTemp);
    }

    public int getAvgHumidity() {
        return avgHumidity.get();
    }

    public IntegerProperty avgHumidityProperty() {
        return avgHumidity;
    }

    public void setAvgHumidity(int avgHumidity) {
        this.avgHumidity.set(avgHumidity);
    }

    public float getAvgPrecipitation() {
        return avgPrecipitation.get();
    }

    public FloatProperty avgPrecipitationProperty() {
        return avgPrecipitation;
    }

    public void setAvgPrecipitation(float avgPrecipitation) {
        this.avgPrecipitation.set(avgPrecipitation);
    }
}
