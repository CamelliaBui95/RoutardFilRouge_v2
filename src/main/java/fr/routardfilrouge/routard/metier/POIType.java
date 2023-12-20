package fr.routardfilrouge.routard.metier;

public class POIType {
    private int idType;
    private String typeName;

    public POIType(int idType, String typeName) {
        this.idType = idType;
        this.typeName = typeName;
    }

    public POIType() {
        idType = 0;
        typeName = "";
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
