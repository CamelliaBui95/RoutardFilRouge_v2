package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.EntryReqServicesBean;
import fr.routardfilrouge.routard.service.CountryBean;
import javafx.beans.property.SimpleIntegerProperty;
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
    private TableView<CountryEntryRequirement> adminReqTableView;
    @FXML
    private TableColumn<CountryEntryRequirement, String> documentNameCol;
    @FXML
    private TableColumn<CountryEntryRequirement, String> documentNoteCol;
    @FXML
    private TableColumn<CountryEntryRequirement, String> documentTypeCol;
    @FXML
    private TableColumn<CountryEntryRequirement, String> documentReqStatusCol;

    @FXML
    private TableView<CountryEntryRequirement> medicalReqTableView;
    @FXML
    private TableColumn<CountryEntryRequirement, String> medicalReqNameCol;
    @FXML
    private TableColumn<CountryEntryRequirement, String> medicalNoteCol;
    @FXML
    private TableColumn<CountryEntryRequirement, String> medicalReqTypeCol;
    @FXML
    private TableColumn<CountryEntryRequirement, String> medicalReqStatusCol;

    @FXML
    private TableView<VisaExemptedCountry> visaExemptedCountryTableView;
    @FXML
    private TableColumn<VisaExemptedCountry, String> exemptedCountryCodeCol;
    @FXML
    private TableColumn<VisaExemptedCountry, String> exemptedCountryNameCol;
    @FXML
    private TableColumn<VisaExemptedCountry, String> minExemptedDurationCol;
    @FXML
    private TableColumn<VisaExemptedCountry, String> maxExemptedDurationCol;

    private CountryBean countryBean;

    @Setter
    private EntryReqServicesBean entryReqServicesBean;

    @Setter
    private MainApp mainApp;

    private Country selectedCountry;

    @FXML
    private void initialize() {
        setUpAdminReqTableView();
        setUpMedicalReqTableView();
        setUpCountryView();
        setUpVisaExemptedCountryTableView();
    }

    private void setUpCountryView() {
        countryCodeCol.setCellValueFactory(cell -> cell.getValue().isoCodeProperty());
        countryNameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());

        countrySearchField.textProperty().addListener((ob, o, n) -> this.countryBean.filterCountry(n));

        countryTableView.setOnMouseClicked(e -> {
            Country selectedCountry = countryTableView.getSelectionModel().getSelectedItem();

            if(selectedCountry != null) {
                if(selectedCountry.getAdministrativeReqs() == null) {
                    entryReqServicesBean.fetchAdministrativeReqsForCountry(selectedCountry);
                    entryReqServicesBean.fetchMedicalReqsForCountry(selectedCountry);
                    entryReqServicesBean.fetchVisaExemptedCountries(selectedCountry);
                }

                this.selectedCountry = selectedCountry;
                setAdminReqs();
                setMedicalReqs();
                setVisaExemptedCountries();
            }
        });
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;

        SortedList<Country> sortedCountries = this.countryBean.getSortedCountries();
        sortedCountries.comparatorProperty().bind(countryTableView.comparatorProperty());

        countryTableView.setItems(sortedCountries);
    }

    private void setAdminReqs() {
        ArrayList<CountryEntryRequirement> adminReqs = selectedCountry.getAdministrativeReqs();

        ObservableList<CountryEntryRequirement> adminReqObservableList = FXCollections.observableArrayList(adminReqs);
        SortedList<CountryEntryRequirement> sortedAdminReqs = new SortedList<>(adminReqObservableList);
        sortedAdminReqs.comparatorProperty().bind(adminReqTableView.comparatorProperty());

        adminReqTableView.setItems(sortedAdminReqs);
    }

    private void setMedicalReqs() {
        ArrayList<CountryEntryRequirement> medicalReqs = selectedCountry.getMedicalReqs();
        
        ObservableList<CountryEntryRequirement> medicalReqObservableList = FXCollections.observableArrayList(medicalReqs);
        SortedList<CountryEntryRequirement> sortedMedicalReqs = new SortedList<>(medicalReqObservableList);
        sortedMedicalReqs.comparatorProperty().bind(medicalReqTableView.comparatorProperty());

        medicalReqTableView.setItems(sortedMedicalReqs);
    }

    private void setVisaExemptedCountries() {
        ArrayList<VisaExemptedCountry> visaExemptedCountries = selectedCountry.getVisaExemptedCountries();

        ObservableList<VisaExemptedCountry> visaExemptedCountryObservableList = FXCollections.observableArrayList(visaExemptedCountries);
        SortedList<VisaExemptedCountry> sortedVisaExemptedCountries = new SortedList<>(visaExemptedCountryObservableList);
        sortedVisaExemptedCountries.comparatorProperty().bind(visaExemptedCountryTableView.comparatorProperty());

        visaExemptedCountryTableView.setItems(sortedVisaExemptedCountries);
    }

    private void setUpAdminReqTableView() {
        documentNameCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEntryRequirement().getEntryReqName()));
        documentNoteCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNote()));
        documentTypeCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEntryRequirement().getEntryReqType().getReqTypeName()));
        documentReqStatusCol.setCellValueFactory(cell -> cell.getValue().getStatus().statusNameProperty());

        adminReqTableView.setOnMouseClicked(e -> {
            CountryEntryRequirement selectedAdminReq = adminReqTableView.getSelectionModel().getSelectedItem();

            if(selectedAdminReq != null && selectedCountry != null)
               handleEditAdministrativeReq(selectedAdminReq);
        });
    }

    private void setUpMedicalReqTableView() {
        medicalReqNameCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEntryRequirement().getEntryReqName()));
        medicalNoteCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNote()));
        medicalReqTypeCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEntryRequirement().getEntryReqType().getReqTypeName()));
        medicalReqStatusCol.setCellValueFactory(cell -> cell.getValue().getStatus().statusNameProperty());

        medicalReqTableView.setOnMouseClicked(e -> {
            CountryEntryRequirement selectedMedicalReq = medicalReqTableView.getSelectionModel().getSelectedItem();

            if(selectedMedicalReq != null && selectedCountry != null)
                handleEditMedicalReq(selectedMedicalReq);
        });
    }

    private void setUpVisaExemptedCountryTableView() {
        exemptedCountryCodeCol.setCellValueFactory(cell -> cell.getValue().getCountry().isoCodeProperty());
        exemptedCountryNameCol.setCellValueFactory(cell -> cell.getValue().getCountry().nameProperty());
        minExemptedDurationCol.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getMinDuration())));
        maxExemptedDurationCol.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getMaxDuration())));
    }

    private void handleEditAdministrativeReq(CountryEntryRequirement selectedAdministrativeReq) {

        boolean isOkClicked = mainApp.showNewEditEntryReqDialog(selectedAdministrativeReq);

        if(!isOkClicked)
            return;

        boolean isUpdated = entryReqServicesBean.updateEntryReqForCountry(selectedAdministrativeReq);

        if(isUpdated) {
            entryReqServicesBean.fetchAdministrativeReqsForCountry(selectedCountry);
            setAdminReqs();
        }
    }

    private void handleEditMedicalReq(CountryEntryRequirement selectedMedicalReq) {

        boolean isOkClicked = mainApp.showNewEditEntryReqDialog(selectedMedicalReq);

        if(!isOkClicked)
            return;

        boolean isUpdated = entryReqServicesBean.updateEntryReqForCountry(selectedMedicalReq);

        if(isUpdated) {
            entryReqServicesBean.fetchMedicalReqsForCountry(selectedCountry);
            setMedicalReqs();
        }
    }
}
