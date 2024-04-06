package fr.routardfilrouge.routard.metier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountrySearch {
    private String countryCode;
    private String countryName;
    private Continent continent;
    private Currency currency;
    private ClimateType climateType;
    private Language language;

    public CountrySearch() {
        this.continent = new Continent("", "");
        this.currency = new Currency("","");
        this.climateType = new ClimateType("", "");
        this.language = new Language("", "");
        this.countryCode = "";
        this.countryName = "";
    }
}
