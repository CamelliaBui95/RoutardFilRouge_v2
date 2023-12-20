package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class POI {
    private IntegerProperty idPOI;
    private StringProperty POIName;
    private float longitude;
    private float latitude;
    private Subdivision subdivision;
    private POIType type;

    public POI(int idPOI, String POIName, Subdivision subdivision, POIType type) {
        this.idPOI = new SimpleIntegerProperty(idPOI);
        this.POIName = new SimpleStringProperty(POIName);
        this.subdivision = subdivision;
        this.type = type;
        longitude = 0;
        latitude = 0;
    }

    public POI() {
        idPOI = new SimpleIntegerProperty(0);
        POIName = new SimpleStringProperty("");
        subdivision = new Subdivision();
        type = new POIType();
        longitude = 0;
        latitude = 0;
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

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return getPOIName();
    }
}
