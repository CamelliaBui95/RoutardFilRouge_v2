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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;
import java.util.Arrays;

public class CountryDetailController {

    @FXML
    private TextFlow countryCodeText;
    @FXML
    private TextFlow countryNameText;
    @FXML
    private TextFlow continentCodeText;
    @FXML
    private TextFlow continentNameText;
    @FXML
    private TextFlow languageText;
    @FXML
    private TextFlow currencyText;
    @FXML
    private SearchableComboBox<InfoType> infoTypeSearch;
    @FXML
    private TextFlow infoText;
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

    @FXML
    private void handleModifyClick() {
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
        ArrayList<Text> texts = generateTexts();

        countryCodeText.getChildren().add(texts.get(0));
        countryNameText.getChildren().add(texts.get(1));
        continentCodeText.getChildren().add(texts.get(2));
        continentNameText.getChildren().add(texts.get(3));
        languageText.getChildren().add(texts.get(4));
        currencyText.getChildren().add(texts.get(5));
        infoText.getChildren().add(texts.get(6));
    }

    private ArrayList<Text> generateTexts() {

        Text countryCode = new Text(country.getIsoCode());
        Text countryName = new Text(country.getName());
        Text continentCode = new Text(country.getContinent().getContinentCode());
        Text continentName = new Text(country.getContinent().getName());
        Text language = new Text("");
        Text currency = new Text("");

        infoTypeSearch.setItems(countryBean.getInfoTypes());
        infoTypeSearch.getSelectionModel().selectFirst();

        Text info = new Text(country.getInfoCollection().get(infoTypeSearch.getSelectionModel().getSelectedItem()));

        return new ArrayList<>(Arrays.asList(countryCode, countryName, continentCode, continentName, language, currency, info));
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
