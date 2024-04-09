package fr.routardfilrouge.routard.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryEntryRequirement {
    private Country country;
    private EntryRequirement entryRequirement;
    private String note;
    private ExigenceStatus status;
}
