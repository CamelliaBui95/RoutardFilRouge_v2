package fr.routardfilrouge.routard.metier;

import fr.routardfilrouge.routard.dao.DAOFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Country {
    private StringProperty isoCode;
    private StringProperty name;
    private Continent continent;

    public Country(String isoCode, String name, Continent continent) {
        this.isoCode = new SimpleStringProperty(isoCode);
        this.name = new SimpleStringProperty(name);
        this.continent = continent;
    }

    public Country() {
        isoCode = new SimpleStringProperty("");
        name = new SimpleStringProperty("");
        continent = new Continent();
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

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return isoCode.get().equals(country.isoCode.get()) && name.get().equals(country.getName()) && continent.equals(country.continent);
    }

}
