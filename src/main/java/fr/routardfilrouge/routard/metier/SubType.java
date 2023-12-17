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
}
