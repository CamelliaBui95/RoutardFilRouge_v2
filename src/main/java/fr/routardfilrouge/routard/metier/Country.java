package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Country {
    private StringProperty isoCode;
    private StringProperty name;
    private Continent continent;
    private Currency currency;

    @Getter
    @Setter
    private ArrayList<Language> languages;

    @Getter
    @Setter
    private ArrayList<CountryEntryRequirement> administrativeReqs;

    @Getter
    @Setter
    private ArrayList<CountryEntryRequirement> medicalReqs;

    public Country(String isoCode, String name, Continent continent, Currency currency) {
        this.isoCode = new SimpleStringProperty(isoCode);
        this.name = new SimpleStringProperty(name);
        this.continent = continent;
        this.currency = currency;
        this.languages = new ArrayList<>();
    }

    public Country(String isoCode, String name, Continent continent) {
        this.isoCode = new SimpleStringProperty(isoCode);
        this.name = new SimpleStringProperty(name);
        this.continent = continent;
        this.currency = new Currency();
        this.languages = new ArrayList<>();
    }

    public Country() {
        isoCode = new SimpleStringProperty("");
        name = new SimpleStringProperty("Country");
        continent = new Continent();
        currency = new Currency();
        this.languages = new ArrayList<>();
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
