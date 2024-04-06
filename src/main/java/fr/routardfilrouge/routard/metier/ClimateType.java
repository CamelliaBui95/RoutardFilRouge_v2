package fr.routardfilrouge.routard.metier;

import lombok.Data;

@Data
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

    @Override
    public String toString() {
        if(climateCode.isEmpty())
            return climateName;

        return climateCode + " - " + climateName;
    }
}
