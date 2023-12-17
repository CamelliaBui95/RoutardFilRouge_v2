package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subdivision {
    private IntegerProperty idSubdivision;
    private StringProperty subdivisionCode;
    private StringProperty subdivisionName;
    private Country country;
    private SubType subType;

    public Subdivision(Integer idSubdivision, String subdivisionName, Country country) {
        this.idSubdivision = new SimpleIntegerProperty(idSubdivision);
        this.subdivisionName = new SimpleStringProperty(subdivisionName);
        this.country = country;
        this.subdivisionCode = new SimpleStringProperty();
    }

    public int getIdSubdivision() {
        return idSubdivision.get();
    }

    public IntegerProperty idSubdivisionProperty() {
        return idSubdivision;
    }

    public void setIdSubdivision(int idSubdivision) {
        this.idSubdivision.set(idSubdivision);
    }

    public String getSubdivisionCode() {
        return subdivisionCode.get();
    }

    public StringProperty subdivisionCodeProperty() {
        return subdivisionCode;
    }

    public void setSubdivisionCode(String subdivisionCode) {
        this.subdivisionCode.set(subdivisionCode);
    }

    public String getSubdivisionName() {
        return subdivisionName.get();
    }

    public StringProperty subdivisionNameProperty() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName.set(subdivisionName);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "Subdivision=" + subdivisionName + "-Country=" + country.getName();
    }
}
