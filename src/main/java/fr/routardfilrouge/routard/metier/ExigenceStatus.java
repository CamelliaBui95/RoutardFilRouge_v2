package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.NoArgsConstructor;


public class ExigenceStatus {
    private IntegerProperty idStatus;
    private StringProperty statusName;

    public ExigenceStatus() {
        idStatus = new SimpleIntegerProperty(0);
        statusName = new SimpleStringProperty("");
    }

    public ExigenceStatus(int idStatus, String statusName) {
        this.idStatus = new SimpleIntegerProperty(idStatus);
        this.statusName = new SimpleStringProperty(statusName);
    }

    public int getIdStatus() {
        return idStatus.get();
    }

    public IntegerProperty idStatusProperty() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus.set(idStatus);
    }

    public String getStatusName() {
        return statusName.get();
    }

    public StringProperty statusNameProperty() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName.set(statusName);
    }
}
