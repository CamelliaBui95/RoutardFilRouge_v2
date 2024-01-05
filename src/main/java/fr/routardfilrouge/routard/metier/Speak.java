package fr.routardfilrouge.routard.metier;

public class Speak {
    private String idLanguage;
    private Country country;

    public Speak(String idLanguage, Country country) {
        this.idLanguage = idLanguage;
        this.country = country;
    }

    public String getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
