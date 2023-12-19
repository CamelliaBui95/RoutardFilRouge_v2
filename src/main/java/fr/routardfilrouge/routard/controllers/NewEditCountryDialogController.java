package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.InfoType;
import fr.routardfilrouge.routard.service.ContinentBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.InfoBean;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;
import java.util.HashMap;

public class NewEditCountryDialogController {
    @FXML
    private TextField countryCodeField;
    @FXML
    private TextField countryNameField;
    @FXML
    private SearchableComboBox continentSearch;
    @FXML
    private SearchableComboBox languageSearch;
    @FXML
    private SearchableComboBox currencySearch;
    @FXML
    private SearchableComboBox infoTypeSearch;
    @FXML
    private TextArea infoTextarea;
    @FXML
    private Button okBtn;
    private Stage dialogStage;
    private MainApp mainApp;
    private Country country;
    private HashMap<InfoType, String> infoCollection;
    private CountryBean countryBean;
    private ContinentBean continentBean;
    private InfoBean infoBean;
    private boolean isNew = false;
    private boolean isOkClicked = false;

    public NewEditCountryDialogController() {

    }

    @FXML
    public void initialize() {

    }
    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }
    @FXML
    private void handleOKClick() {

    }
    @FXML
    private void handleAddNewInfoType() {
        mainApp.showNewElementDialog("New Info Type");
    }
    @FXML
    private void handleAddNewContinent() {
        HashMap<String, String> element = mainApp.showNewElementDialog("New Continent");
        if(element == null || element.isEmpty()) {
            System.out.println("Continent insertion failed");
            return;
        }
        Continent continent = new Continent(element.get("id"), element.get("name"));
        continentBean.postContinent(continent);
    }
    @FXML
    private void handleAddNewLanguage() {

    }
    @FXML
    private void handleAddNewCurrency() {

    }

    private void mapDataToView() {
        ObservableList<Continent> continentsList = continentBean.getContinents();
        continentSearch.setItems(continentsList);
        continentSearch.getSelectionModel().selectFirst();
        infoTypeSearch.setItems(infoBean.getInfoTypes());
        infoTypeSearch.getSelectionModel().selectFirst();


        if(!isNew) {
            countryCodeField.setText(country.getIsoCode());
            countryCodeField.setDisable(!isNew);

            countryNameField.setText(country.getName());

            int indexContinent = continentsList.indexOf(country.getContinent());
            continentSearch.getSelectionModel().select(indexContinent);

            String infoText = infoCollection.get((InfoType) infoTypeSearch.getSelectionModel().getSelectedItem());
            infoTextarea.setText(infoText);
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCountry(Country country) {
        this.country = country;
        infoCollection = infoBean.getInfoSlice(country) == null ? new HashMap<>() : infoBean.getInfoSlice(country);
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setContinentBean(ContinentBean continentBean) {
        this.continentBean = continentBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
        mapDataToView();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
