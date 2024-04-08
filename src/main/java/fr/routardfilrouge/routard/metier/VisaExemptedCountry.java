package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisaExemptedCountry {
    private Country country;
    private int minDuration;
    private int maxDuration;
    private LocalDate startDate;
    private LocalDate endDate;

    public VisaExemptedCountry(Country country) {
        this.country = country;
    }

    public VisaExemptedCountry() {
        this.country = new Country();
    }


}
