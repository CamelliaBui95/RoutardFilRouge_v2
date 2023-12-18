package fr.routardfilrouge.routard.metier;

public class ClimateType {
    private String climateCode;
    private String climateName;

    public ClimateType(String climateCode, String climateName) {
        this.climateCode = climateCode;
        this.climateName = climateName;
    }

    public String getClimateCode() {
        return climateCode;
    }

    public void setClimateCode(String climateCode) {
        this.climateCode = climateCode;
    }

    public String getClimateName() {
        return climateName;
    }

    public void setClimateName(String climateName) {
        this.climateName = climateName;
    }

    @Override
    public String toString() {
        if(climateCode.isEmpty())
            return climateName;

        return climateCode + " - " + climateName;
    }
}
