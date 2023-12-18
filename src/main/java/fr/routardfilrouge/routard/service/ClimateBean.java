package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.ClimateType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClimateBean {
    private ObservableList<ClimateType> climateTypes;

    public ClimateBean() {
        this.climateTypes = FXCollections.observableArrayList();

        climateTypes.addAll(DAOFactory.getClimateDAO().getAll());
    }

    public ObservableList<ClimateType> getClimateTypes() {
        return climateTypes;
    }
}
