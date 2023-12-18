package fr.routardfilrouge.routard.metier;

import fr.routardfilrouge.routard.dao.DAOFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class Country {
    private StringProperty isoCode;
    private StringProperty name;
    private Continent continent;

    private HashMap<InfoType, String> infoCollection;

    public Country(String isoCode, String name, Continent continent) {
        this.isoCode = new SimpleStringProperty(isoCode);
        this.name = new SimpleStringProperty(name);
        this.continent = continent;
        infoCollection = new HashMap<>();
    }

    public Country() {

    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getIsoCode() {
        return isoCode.get();
    }

    public StringProperty isoCodeProperty() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode.set(isoCode);
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public HashMap<InfoType, String> getInfoCollection() {
        return infoCollection;
    }

    public void addInfo(InfoType infoType, String info) {
        infoCollection.put(infoType, info);
    }

    @Override
    public String toString() {
        return "Country=" + getName();
    }
}
