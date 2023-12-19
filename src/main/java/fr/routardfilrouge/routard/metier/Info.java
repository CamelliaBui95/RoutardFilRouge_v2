package fr.routardfilrouge.routard.metier;

public class Info {
    private int idType;
    private Country country;
    private String infoText;

    public Info(int idType, Country country, String infoText) {
        this.idType = idType;
        this.country = country;
        this.infoText = infoText;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
}
