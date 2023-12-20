package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Continent {
    private StringProperty continentCode;
    private StringProperty name;

    public Continent(String continentCode, String name) {
        this.continentCode = new SimpleStringProperty(continentCode);
        this.name = new SimpleStringProperty(name);
    }

    public Continent() {
        continentCode = new SimpleStringProperty("");
        name = new SimpleStringProperty("");
    }

    public String getContinentCode() {
        return continentCode.get();
    }

    public StringProperty continentCodeProperty() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode.set(continentCode);
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

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return continentCode.get().equals(continent.continentCode.get()) && name.get().equals(continent.name.get());
    }

}
