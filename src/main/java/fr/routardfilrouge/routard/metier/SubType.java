package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SubType {
    private IntegerProperty idType;
    private StringProperty typeName;

    public SubType(Integer idType, String typeName) {
        this.idType = new SimpleIntegerProperty(idType);
        this.typeName = new SimpleStringProperty(typeName);
    }

    public int getIdType() {
        return idType.get();
    }

    public IntegerProperty idTypeProperty() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType.set(idType);
    }

    public String getTypeName() {
        return typeName.get();
    }

    public StringProperty typeNameProperty() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName.set(typeName);
    }
}
