package fr.routardfilrouge.routard.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministrativeRequirement {
    private AdministrativeDocument administrativeDocument;
    private String note;
    private ExigenceStatus status;
}
