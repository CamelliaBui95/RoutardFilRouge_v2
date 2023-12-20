package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class POI {
    private IntegerProperty idPOI;
    private StringProperty POIName;
    private Subdivision subdivision;
    private POIType type;

    public POI(IntegerProperty idPOI, StringProperty POIName, Subdivision subdivision, POIType type) {
        this.idPOI = idPOI;
        this.POIName = POIName;
        this.subdivision = subdivision;
        this.type = type;
    }

    public POI() {
        idPOI = new SimpleIntegerProperty(0);
        POIName = new SimpleStringProperty("");
        subdivision = new Subdivision();
        type = new POIType();
    }

    public int getIdPOI() {
        return idPOI.get();
    }

    public IntegerProperty idPOIProperty() {
        return idPOI;
    }

    public void setIdPOI(int idPOI) {
        this.idPOI.set(idPOI);
    }

    public String getPOIName() {
        return POIName.get();
    }

    public StringProperty POINameProperty() {
        return POIName;
    }

    public void setPOIName(String POIName) {
        this.POIName.set(POIName);
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public POIType getType() {
        return type;
    }

    public void setType(POIType type) {
        this.type = type;
    }
}
