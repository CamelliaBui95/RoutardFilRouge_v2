package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class SubType {
    private IntegerProperty idType;
    private StringProperty typeName;

    public SubType(Integer idType, String typeName) {
        this.idType = new SimpleIntegerProperty(idType);
        this.typeName = new SimpleStringProperty(typeName);
    }

    public SubType() {
        this.idType = new SimpleIntegerProperty(0);
        this.typeName = new SimpleStringProperty("Type");
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
    public String toString() {
        return getTypeName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubType subType = (SubType) o;
        return idType.get() == subType.idType.get() && typeName.get().equals(subType.typeName.get());
    }

}
