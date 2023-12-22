package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.SubType;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.metier.SubdivisionSearch;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;
import java.util.HashMap;

public class NewEditSubdivisionController {
    @FXML
    private Label idSubLabel;
    @FXML
    private TextField subCodeField;
    @FXML
    private TextField subNameField;
    @FXML
    private SearchableComboBox<Country> countrySearch;
    @FXML
    private SearchableComboBox<SubType> subTypeSearch;
    @FXML
    private Button okBtn;
    private Stage dialogStage;
    private MainApp mainApp;
    private SubdivisionBean subdivisionBean;
    private CountryBean countryBean;
    private Subdivision subdivision;
    private boolean isNew;
    private boolean isOkClicked;

    ObservableList<Country> countries;
    ObservableList<SubType> types;

    public NewEditSubdivisionController() {
        countries = FXCollections.observableArrayList();
        types = FXCollections.observableArrayList();
    }

    @FXML
    private void handleAddNewSubType() {
        HashMap<String, String> element = mainApp.showNewElementDialog("New Subdivision Type", true);
        if(element == null || element.isEmpty()) {
            System.out.println("Subdivision Type insertion failed");
            return;
        }
        SubType subType = new SubType(types.size(), element.get("name"));
        boolean isPosted = subdivisionBean.postType(subType);

        if(isPosted) {
            types.setAll(subdivisionBean.getTypesArr());
            types.add(0, new SubType());
            subTypeSearch.getSelectionModel().select(subType);
        }
    }

    @FXML
    private void handleOkClick() {
        if(!isDataValid())
            return;

        subdivision.setSubdivisionName(subNameField.getText());
        subdivision.setSubdivisionCode(subCodeField.getText());
        subdivision.setCountry(countrySearch.getSelectionModel().getSelectedItem());
        subdivision.setSubType(subTypeSearch.getSelectionModel().getSelectedItem());

        boolean hasSucceeded;
        if(!isNew)
            hasSucceeded = subdivisionBean.updateSub(subdivision);
        else
            hasSucceeded = subdivisionBean.postSub(subdivision);

        dialogStage.close();
        isOkClicked = hasSucceeded;
    }

    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSubdivisionBean(SubdivisionBean subdivisionBean) {
        this.subdivisionBean = subdivisionBean;
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
        mapDataToView();
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    private void mapDataToView() {
        if(!isNew)
            idSubLabel.setText(Integer.toString(subdivision.getIdSubdivision()));

        subCodeField.setText(subdivision.getSubdivisionCode());
        subNameField.setText(subdivision.getSubdivisionName());

        setUpSearchBoxes();

        setUpOkBtn();
    }

    private boolean isDataValid() {
        boolean isNameValid = subNameField.getText() != null && !subNameField.getText().isEmpty();
        boolean isCodeValid = subCodeField.getText() != null && !subCodeField.getText().isEmpty() && subCodeField.getText().length() <= 3;
        boolean isCountryValid = countrySearch.getSelectionModel().getSelectedIndex() > 0;
        boolean isTypeValid = subTypeSearch.getSelectionModel().getSelectedIndex() > 0;

        return isNameValid && isCodeValid && isCountryValid && isTypeValid;
    }

    private void setUpOkBtn() {
        okBtn.setDisable(!isDataValid());

        subNameField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        subCodeField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        countrySearch.valueProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        subTypeSearch.valueProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
    }

    private void setUpSearchBoxes() {
        countries.addAll(countryBean.getCountriesArr());
        countries.add(0, new Country());
        countrySearch.setItems(countries);
        countrySearch.getSelectionModel().select(subdivision.getCountry());

        types.addAll(subdivisionBean.getTypesArr());
        types.add(0, new SubType());
        subTypeSearch.setItems(types);
        subTypeSearch.getSelectionModel().select(subdivision.getSubType());
    }
}
