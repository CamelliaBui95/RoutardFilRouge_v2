package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.ClimateType;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.InfoType;
import fr.routardfilrouge.routard.service.ContinentBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.InfoBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.SearchableComboBox;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    private InfoBean infoBean;
    private MainApp mainApp;
    private Country country;

    private HashMap<InfoType, String> infoCollection;
    private InfoType selectedInfoType;

    @FXML
    private void handleNewClick() {
        boolean isOkCLicked = mainApp.showNewCountryDialog(new Country(), "New Country", true);

        if(isOkCLicked)
            fillInDetails();
    }
    @FXML
    private void handleModifyClick() {
        boolean isOkClicked = mainApp.showNewCountryDialog(country, "Modify Country", false);

        if(isOkClicked)
            fillInDetails();
    }
    @FXML
    private void handleDeleteClick() {
        countryBean.deleteCountry(country);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
        infoCollection = infoBean.getInfoSlice(country) == null ? new HashMap<>() : infoBean.getInfoSlice(country);
        fillInDetails();
    }

    private void fillInDetails() {
        ArrayList<Text> texts = generateTexts();

        countryCodeText.getChildren().clear();
        countryNameText.getChildren().clear();
        continentCodeText.getChildren().clear();
        continentNameText.getChildren().clear();
        languageText.getChildren().clear();
        currencyText.getChildren().clear();
        infoText.getChildren().clear();

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
        Text currency = new Text(country.getCurrency().getNameCurrency());

        infoTypeSearch.setItems(infoBean.getInfoTypes());
        infoTypeSearch.getSelectionModel().selectFirst();
        infoTypeSearch.valueProperty().addListener((ob, o, n) -> onSelectInfoType(n));

        Text info = new Text("");
        if(!infoCollection.isEmpty())
            info.setText(infoCollection.get(infoTypeSearch.getSelectionModel().getSelectedItem()));

        return new ArrayList<>(Arrays.asList(countryCode, countryName, continentCode, continentName, language, currency, info));
    }

    public void onSelectInfoType(InfoType infoType) {
        if(selectedInfoType != null && selectedInfoType.equals(infoType))
            return;

        if(infoType != null) {
            selectedInfoType = infoType;
            infoText.getChildren().set(0, new Text(infoCollection.get(selectedInfoType)));
        }
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
