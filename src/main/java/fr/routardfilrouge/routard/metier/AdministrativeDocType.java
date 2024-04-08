package fr.routardfilrouge.routard.metier;

import javafx.beans.property.*;

import java.util.Objects;

public class AdministrativeDocType {
    private IntegerProperty idType;
    private StringProperty typeName;

    public AdministrativeDocType() {
        idType = new SimpleIntegerProperty(0);
        typeName = new SimpleStringProperty("");
    }

    public AdministrativeDocType(int idType, String typeName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministrativeDocType that = (AdministrativeDocType) o;
        return Objects.equals(idType.get(), that.idType.get()) && Objects.equals(typeName.get(), that.typeName.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType, typeName);
    }
}
