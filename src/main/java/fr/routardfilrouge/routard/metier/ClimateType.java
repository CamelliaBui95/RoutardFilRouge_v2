package fr.routardfilrouge.routard.metier;

import java.util.Objects;

public class ClimateType {
    private String climateCode;
    private String climateName;

    public ClimateType(String climateCode, String climateName) {
        this.climateCode = climateCode;
        this.climateName = climateName;
    }

    public ClimateType() {
        this.climateCode = "";
        this.climateName = "Climate";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClimateType that = (ClimateType) o;
        return Objects.equals(climateCode, that.climateCode) && Objects.equals(climateName, that.climateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(climateCode, climateName);
    }
}
