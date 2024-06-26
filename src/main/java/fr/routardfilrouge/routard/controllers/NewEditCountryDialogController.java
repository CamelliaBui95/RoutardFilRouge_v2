package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.ContinentBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.CurrencyBean;
import fr.routardfilrouge.routard.service.InfoBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private CurrencyBean currencyBean;
    private InfoBean infoBean;
    private boolean isNew = false;
    private boolean isOkClicked = false;
    private InfoType selectedInfoType;

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
        if(!isDataValid())
            return;

        country.setIsoCode(countryCodeField.getText());
        country.setName(countryNameField.getText());
        country.setContinent((Continent) continentSearch.getSelectionModel().getSelectedItem());
        country.setCurrency((Currency) currencySearch.getSelectionModel().getSelectedItem());

        boolean hasSucceeded;
        if(!isNew)
            hasSucceeded = countryBean.updateCountry(country);
        else
            hasSucceeded = countryBean.postCountry(country);

        if(hasSucceeded) {
            if(selectedInfoType != null) {
                infoCollection.put(selectedInfoType, infoTextarea.getText());
            }

            for(Map.Entry<InfoType, String> entry : infoCollection.entrySet()) {
                InfoType infoType = entry.getKey();

                String text = entry.getValue();

                if(text != null)
                    infoBean.postInfo(new Info(infoType.getIdType(), country, text));
            }

            infoBean.updateInfoCollectionForCountry(country);
        }

        isOkClicked = true;
        dialogStage.close();
    }
    @FXML
    private void handleAddNewInfoType() {
        HashMap<String, String> element = mainApp.showNewElementDialog("New Info Type", true);
        if(element == null || element.isEmpty()) {
            System.out.println("Info Type insertion failed");
            return;
        }
        InfoType infoType = new InfoType(0, element.get("name"));
        infoBean.postInfoType(infoType);

    }
    @FXML
    private void handleAddNewContinent() {
        HashMap<String, String> element = mainApp.showNewElementDialog("New Continent", false);
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
        HashMap<String, String> element  = mainApp.showNewElementDialog("New Currency", false);
        if(element == null || element.isEmpty()){
            System.out.println("Currency insertion failed");
            return;
        }
        Currency currency = new Currency(element.get("id"), element.get("name"));
        currencyBean.postCurrency(currency);
    }

    private void mapDataToView() {
        ObservableList<Continent> continentsList = continentBean.getContinents();
        ObservableList<Currency> currenciesList = currencyBean.getCurrencies();
        continentSearch.setItems(continentsList);
        continentSearch.getSelectionModel().selectFirst();
        currencySearch.setItems(currenciesList);
        currencySearch.getSelectionModel().selectFirst();
        infoTypeSearch.setItems(infoBean.getInfoTypes());
        infoTypeSearch.getSelectionModel().selectFirst();
        infoTypeSearch.valueProperty().addListener((ob, o, n) -> onSelectInfoType((InfoType) n));
        selectedInfoType = (InfoType) infoTypeSearch.getSelectionModel().getSelectedItem();


        if(!isNew) {
            countryCodeField.setText(country.getIsoCode());
            countryCodeField.setDisable(!isNew);

            countryNameField.setText(country.getName());

            int indexContinent = continentsList.indexOf(country.getContinent());
            int indexCurrency = currenciesList.indexOf(country.getCurrency());

            continentSearch.getSelectionModel().select(indexContinent);
            currencySearch.getSelectionModel().select(indexCurrency);
            String infoText = infoCollection.get((InfoType) infoTypeSearch.getSelectionModel().getSelectedItem());
            infoTextarea.setText(infoText);
        }

        setUpOkBtn();
    }

    private void onSelectInfoType(InfoType infoType) {
        if(selectedInfoType != null && selectedInfoType.equals(infoType))
            return;

        if(infoType != null) {
            if(selectedInfoType != null)
                infoCollection.put(selectedInfoType, infoTextarea.getText());

            selectedInfoType = infoType;
            infoTextarea.setText(infoCollection.get(selectedInfoType));
        }
    }

    private void setUpOkBtn() {
        okBtn.setDisable(!isDataValid());
        countryCodeField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        countryNameField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        continentSearch.valueProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        currencySearch.valueProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
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

    public void setCurrencyBean(CurrencyBean currencyBean){
        this.currencyBean = currencyBean;
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

    private boolean isDataValid() {
        boolean isCountryCodeValid = countryCodeField.getText() != null && !countryCodeField.getText().isEmpty();
        boolean isCountryNameValid = countryNameField.getText() != null && !countryNameField.getText().isEmpty();
        boolean isContinentSelected = continentSearch.getSelectionModel().getSelectedIndex() > 0;
        boolean isCurrencySelected = currencySearch.getSelectionModel().getSelectedIndex() > 0;
        return isCountryCodeValid && isCountryNameValid && isContinentSelected && isCurrencySelected;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }
}
