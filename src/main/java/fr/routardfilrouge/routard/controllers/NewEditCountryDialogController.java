package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.InfoType;
import fr.routardfilrouge.routard.service.ContinentBean;
import fr.routardfilrouge.routard.service.CountryBean;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;

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
    private Country country;
    private CountryBean countryBean;
    private ContinentBean continentBean;
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

    }
    @FXML
    private void handleAddNewContinent() {

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
        infoTypeSearch.setItems(countryBean.getInfoTypes());
        infoTypeSearch.getSelectionModel().selectFirst();

        if(!isNew) {
            countryCodeField.setText(country.getIsoCode());
            countryCodeField.setDisable(!isNew);

            countryNameField.setText(country.getName());

            int indexContinent = continentsList.indexOf(country.getContinent());
            continentSearch.getSelectionModel().select(indexContinent);

            String infoText = country.getInfoCollection().get((InfoType) infoTypeSearch.getSelectionModel().getSelectedItem());
            infoTextarea.setText(infoText);
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setContinentBean(ContinentBean continentBean) {
        this.continentBean = continentBean;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;

        mapDataToView();
    }
}
