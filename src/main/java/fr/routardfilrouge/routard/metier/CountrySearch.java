package fr.routardfilrouge.routard.metier;

public class CountrySearch {
    private String countryCode;
    private String countryName;

    private Continent continent;
    private Currency currency;

    public CountrySearch() {
        this.continent = new Continent("", "");
        this.currency = new Currency("","");
        this.countryCode = "";
        this.countryName = "";
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
}
