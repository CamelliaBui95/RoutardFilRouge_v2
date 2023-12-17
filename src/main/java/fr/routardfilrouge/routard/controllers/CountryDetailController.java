package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.ClimateType;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.InfoType;
import fr.routardfilrouge.routard.service.CountryBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.SearchableComboBox;

public class CountryDetailController {

    @FXML
    private TextField countryCodeField;
    @FXML
    private TextField countryNameField;
    @FXML
    private TextField continentCodeField;
    @FXML
    private TextField continentField;
    @FXML
    private TextField languageField;
    @FXML
    private TextField currencyField;
    @FXML
    private SearchableComboBox<InfoType> infoTypeSearch;
    @FXML
    private TextArea infoField;
    @FXML
    private Label dateLabel;
    @FXML
    private Label authorLabel;

    private CountryBean countryBean;
    private MainApp mainApp;

    private Country country;

    @FXML
    private void handleNewClick() {
        mainApp.showNewCountryDialog();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
        fillInDetails();
    }

    private void fillInDetails() {
        countryCodeField.setText(country.getIsoCode());
        countryNameField.setText(country.getName());
        continentCodeField.setText(country.getContinent().getContinentCode());
        continentField.setText(country.getContinent().getName());

        infoTypeSearch.setItems(countryBean.getInfoTypes());
        infoTypeSearch.getSelectionModel().selectFirst();

        String defaultInfo = country.getInfoCollection().get(infoTypeSearch.getSelectionModel().getSelectedItem());
        infoField.setText(defaultInfo);
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
