package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InfoType {
    private int idType;
    private String infoType;

    public InfoType(int idType, String infoType) {
        this.idType = idType;
        this.infoType = infoType;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    @Override
    public String toString() {
        return infoType;
    }
}
