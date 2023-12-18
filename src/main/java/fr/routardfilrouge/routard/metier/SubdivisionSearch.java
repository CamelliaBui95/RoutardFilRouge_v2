package fr.routardfilrouge.routard.metier;

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

    public int getIdSubdivision() {
        return idSubdivision;
    }

    public void setIdSubdivision(int idSubdivision) {
        this.idSubdivision = idSubdivision;
    }

    public String getNameSubdivision() {
        return nameSubdivision;
    }

    public void setNameSubdivision(String nameSubdivision) {
        this.nameSubdivision = nameSubdivision;
    }

    public String getCodeSubdivision() {
        return codeSubdivision;
    }

    public void setCodeSubdivision(String codeSubdivision) {
        this.codeSubdivision = codeSubdivision;
    }

    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
