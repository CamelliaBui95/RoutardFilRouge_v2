package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Continent {
    private StringProperty continentCode;
    private StringProperty name;

    public Continent(String continentCode, String name) {
        this.continentCode = new SimpleStringProperty(continentCode);
        this.name = new SimpleStringProperty(name);
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
}
