package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.ExigenceStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ExigenceStatusBean {
    private ArrayList<ExigenceStatus> exigenceStatuses;
    private ObservableList<ExigenceStatus> observableExigenceStatuses;

    public ExigenceStatusBean() {
        exigenceStatuses = DAOFactory.getExigenceStatusDAO().getAll();
        observableExigenceStatuses = FXCollections.observableArrayList(exigenceStatuses);
        observableExigenceStatuses.add(0, new ExigenceStatus(0, "Statuses (" + exigenceStatuses.size() + ")"));
    }
}
