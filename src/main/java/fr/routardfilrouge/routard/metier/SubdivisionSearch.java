package fr.routardfilrouge.routard.metier;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubdivisionSearch {
    private int idSubdivision;
    private String nameSubdivision;
    private String codeSubdivision;
    private SubType subType;
    private String codeCountry;
    private Continent continent;

    public SubdivisionSearch() {
        idSubdivision = 0;
        nameSubdivision = "";
        codeSubdivision = "";
        subType = new SubType(0, "");
        codeCountry = "";
        continent = new Continent("", "");
    }
}
