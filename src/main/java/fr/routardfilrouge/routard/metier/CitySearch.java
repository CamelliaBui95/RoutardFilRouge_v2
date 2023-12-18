package fr.routardfilrouge.routard.metier;

public class CitySearch {
    private String cityName;
    private int cityID;
    private Subdivision subdivision;
    private String countryCode;
    private Continent continent;
    private ClimateType climateType;

    public CitySearch() {
        cityName = "";
        cityID = 0;
        continent = new Continent("","");
        subdivision = new Subdivision(0,"", new Country());
        climateType = new ClimateType("", "");
        countryCode = "";
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public ClimateType getClimateType() {
        return climateType;
    }

    public void setClimateType(ClimateType climateType) {
        this.climateType = climateType;
    }
}
