package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.AdministrativeServicesBean;
import fr.routardfilrouge.routard.service.CountryBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;

import java.util.ArrayList;


public class EntryReqViewController {
    @FXML
    private TextField countrySearchField;

    @FXML
    private TableView<Country> countryTableView;
    @FXML
    private TableColumn<Country, String> countryCodeCol;
    @FXML
    private TableColumn<Country, String> countryNameCol;

    @FXML
    private TableView<AdministrativeRequirement> adminReqTableView;
    @FXML
    private TableColumn<AdministrativeRequirement, String> documentNameCol;
    @FXML
    private TableColumn<AdministrativeRequirement, String> documentNoteCol;
    @FXML
    private TableColumn<AdministrativeRequirement, String> documentTypeCol;
    @FXML
    private TableColumn<AdministrativeRequirement, String> documentReqStatusCol;

    @FXML
    private TableView<VisaExemptedCountry> visaExemptedCountryTableView;

    private CountryBean countryBean;

    @Setter
    private AdministrativeServicesBean adminReqBean;

    @Setter
    private MainApp mainApp;

    private Country selectedCountry;

    @FXML
    private void initialize() {
        setAdminReqTableView();
        setUpCountryView();
    }

    private void setUpCountryView() {
        countryCodeCol.setCellValueFactory(cell -> cell.getValue().isoCodeProperty());
        countryNameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());

        countrySearchField.textProperty().addListener((ob, o, n) -> this.countryBean.filterCountry(n));

        countryTableView.setOnMouseClicked(e -> {
            Country selectedCountry = countryTableView.getSelectionModel().getSelectedItem();

            if(selectedCountry != null) {
                if(selectedCountry.getAdministrativeReqs() == null)
                    adminReqBean.fetchAdministrativeReqsForCountry(selectedCountry);

                this.selectedCountry = selectedCountry;
                displayAdminReqs();
            }
        });
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;

        SortedList<Country> sortedCountries = this.countryBean.getSortedCountries();
        sortedCountries.comparatorProperty().bind(countryTableView.comparatorProperty());
        countryTableView.setItems(sortedCountries);
    }

    private void displayAdminReqs() {
        ArrayList<AdministrativeRequirement> adminReqs = selectedCountry.getAdministrativeReqs();

        ObservableList<AdministrativeRequirement> adminReqObservableList = FXCollections.observableArrayList(adminReqs);
        SortedList<AdministrativeRequirement> sortedAdminReqs = new SortedList<>(adminReqObservableList);

        adminReqTableView.setItems(sortedAdminReqs);
    }

    private void setAdminReqTableView() {
        documentNameCol.setCellValueFactory(cell -> cell.getValue().getAdministrativeDocument().documentNameProperty());
        documentNoteCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNote()));
        documentTypeCol.setCellValueFactory(cell -> cell.getValue().getAdministrativeDocument().getDocumentType().typeNameProperty());
        documentReqStatusCol.setCellValueFactory(cell -> cell.getValue().getStatus().statusNameProperty());

        adminReqTableView.setOnMouseClicked(e -> {
            AdministrativeRequirement selectedAdminReq = adminReqTableView.getSelectionModel().getSelectedItem();

            if(selectedAdminReq != null && selectedCountry != null)
                mainApp.showNewEditAdminReqDialog(selectedCountry, selectedAdminReq, false);
        });
    }
}
