package fr.routardfilrouge.routard.metier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Info {
    private int idType;
    private Country country;
    private String infoText;

    public Info(int idType, Country country, String infoText) {
        this.idType = idType;
        this.country = country;
        this.infoText = infoText;
    }
}
