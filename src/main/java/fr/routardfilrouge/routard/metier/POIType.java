package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class POIType {
    private IntegerProperty idType;
    private StringProperty typeName;

    public POIType(int idType, String typeName) {
        this.idType = new SimpleIntegerProperty(idType);
        this.typeName = new SimpleStringProperty(typeName);
    }

    public POIType() {
        idType = new SimpleIntegerProperty(0);
        typeName = new SimpleStringProperty("");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        POIType poiType = (POIType) o;
        return idType.get() == poiType.idType.get() && Objects.equals(typeName.get(), poiType.typeName.get());
    }

    @Override
    public String toString() {
        return getTypeName();
    }
}
