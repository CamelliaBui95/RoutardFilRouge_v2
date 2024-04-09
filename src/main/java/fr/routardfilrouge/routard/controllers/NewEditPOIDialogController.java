package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.POI;
import fr.routardfilrouge.routard.metier.POIType;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.service.ContinentBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.POIBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;
import java.util.HashMap;

public class NewEditPOIDialogController {
    @FXML
    private Label idPOILabel;
    @FXML
    private TextField poiNameField;
    @FXML
    private TextField longitudeField;
    @FXML
    private TextField latitudeField;
    @FXML
    private SearchableComboBox<POIType> categorySearch;
    @FXML
    private SearchableComboBox<Subdivision> subdivisionSearch;
    @FXML
    private SearchableComboBox<Country> countrySearch;
    @FXML
    private Button okBtn;
    @FXML
    private ButtonBar btnBar;
    private boolean isNew;
    private POI poi;
    private POIBean poiBean;
    private CountryBean countryBean;
    private SubdivisionBean subdivisionBean;

    private Stage dialogStage;
    private MainApp mainApp;

    private ObservableList<Subdivision> subdivisions;
    private ObservableList<POIType> categories;
    private ObservableList<Country> countries;

    private Country selectedCountry;
    private Subdivision selectedSubdivision;

    public NewEditPOIDialogController() {
        subdivisions = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        countries = FXCollections.observableArrayList();

        selectedCountry = new Country();
        selectedSubdivision = new Subdivision();
    }

    @FXML
    public void handleAddCategory() {
        HashMap<String, String> element = mainApp.showNewElementDialog("New Category", true);
        if(element == null || element.isEmpty()) {
            System.out.println("Category insertion failed");
            return;
        }

        POIType category = new POIType(0, element.get("name"));
        poiBean.postCategory(category);
        categorySearch.getSelectionModel().selectLast();
    }
    @FXML
    public void handleCancelClick() {
        dialogStage.close();
    }
    @FXML
    public void handleOkClick() {
        if(!isDataValid())
            return;

        POIType category = categorySearch.getSelectionModel().getSelectedItem();
        Subdivision subdivision = subdivisionSearch.getSelectionModel().getSelectedItem();
        String name = poiNameField.getText();
        Float longitude = Float.parseFloat(longitudeField.getText());
        Float latitude = Float.parseFloat(latitudeField.getText());

        poi.setPOIName(name);
        poi.setLongitude(longitude);
        poi.setLatitude(latitude);
        poi.setSubdivision(subdivision);
        poi.setType(category);

        if(isNew)
            poiBean.postPOI(poi);

        dialogStage.close();
    }

    private void handleDeleteClick() {
        poiBean.deletePOI(poi);
        dialogStage.close();
    }

    private void mapDataToView() {
        if(!isNew) {
            idPOILabel.setText(Integer.toString(poi.getIdPOI()));
            setUpDeleteBtn();
        }

        poiNameField.setText(poi.getPOIName());
        longitudeField.setText(Float.toString(poi.getLongitude()));
        latitudeField.setText(Float.toString(poi.getLatitude()));

        setUpCategories();
        setUpSubdivisionSearch();
        setUpCountrySearch();
        setUpOkBtn();
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
        mapDataToView();
    }

    public void setPoi(POI poi) {
        this.poi = poi;
    }

    public void setPoiBean(POIBean poiBean) {
        this.poiBean = poiBean;
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setSubdivisionBean(SubdivisionBean subdivisionBean) {
        this.subdivisionBean = subdivisionBean;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private boolean isDataValid() {
        boolean isNameValid = poiNameField.getText() != null && !poiNameField.getText().isEmpty();
        boolean isLongitudeValid = longitudeField.getText() != null && isValidFloat(longitudeField.getText());
        boolean isLatitudeValid = latitudeField.getText() != null && isValidFloat(latitudeField.getText());
        boolean isCategoryValid = categorySearch.getSelectionModel().getSelectedIndex() > 0;
        boolean isSubdivisionValid = subdivisionSearch.getSelectionModel().getSelectedIndex() > 0;
        //boolean isCountryValid = countrySearch.getSelectionModel().getSelectedIndex() > 0;

        return isNameValid && isLongitudeValid && isLatitudeValid && isCategoryValid && isSubdivisionValid;
    }

    private boolean isValidFloat(String floatString) {
        try {
            Float.parseFloat(floatString);
            return true;
        } catch(Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    private void setUpCountrySearch() {
        countries.addAll(countryBean.getCountriesArr());
        countries.add(0, new Country());
        countrySearch.setItems(countries);
        countrySearch.getSelectionModel().select(poi.getSubdivision().getCountry());

        countrySearch.valueProperty().addListener((ob, o, n) -> {
            if(n == null || selectedCountry.equals(n))
                return;

            selectedCountry = n;

            if(selectedSubdivision.getIdSubdivision() == 0) {
                subdivisions.setAll(subdivisionBean.populateSubdivisionsByCountry(n));
                subdivisions.add(0, new Subdivision());
                subdivisionSearch.getSelectionModel().select(1);
            }

        });
    }

    private void setUpSubdivisionSearch() {
        subdivisions.addAll(subdivisionBean.getSubdivisionArr());
        subdivisions.add(0, new Subdivision());
        subdivisionSearch.setItems(subdivisions);
        subdivisionSearch.getSelectionModel().select(poi.getSubdivision());
        /*subdivisionSearch.valueProperty().addListener((ob, o ,n) -> {
            if(n == null || selectedSubdivision.equals(n))
                return;

            selectedSubdivision = n;

            countrySearch.getSelectionModel().select(selectedSubdivision.getCountry());
        });*/
    }
    private void setUpCategories() {
        categories.addAll(poiBean.getCategoriesArr());
        categories.add(0, new POIType());
        categorySearch.setItems(categories);
        categorySearch.getSelectionModel().select(poi.getType());
    }

    private void setUpDeleteBtn() {
        Button deleteBtn = new Button();
        deleteBtn.setText("Delete");
        deleteBtn.setOnAction(e -> handleDeleteClick());
        btnBar.getButtons().add(2, deleteBtn);
    }

    private void setUpOkBtn() {
        okBtn.setDisable(!isDataValid());

        poiNameField.textProperty().addListener((ob,o,n) -> okBtn.setDisable(!isDataValid()));
        longitudeField.textProperty().addListener((ob,o,n) -> okBtn.setDisable(!isDataValid()));
        latitudeField.textProperty().addListener((ob,o,n) -> okBtn.setDisable(!isDataValid()));
        subdivisionSearch.valueProperty().addListener((ob,o,n) -> okBtn.setDisable(!isDataValid()));
    }

}
