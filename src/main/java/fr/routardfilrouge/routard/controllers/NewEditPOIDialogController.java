package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.POI;
import fr.routardfilrouge.routard.metier.POIType;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.service.ContinentBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.POIBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;

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

    private boolean isNew;
    private POI poi;
    private POIBean poiBean;
    private CountryBean countryBean;
    private SubdivisionBean subdivisionBean;

    private Stage dialogStage;

    @FXML
    public void handleAddCategory() {

    }
    @FXML
    public void handleCancelClick() {
        dialogStage.close();
    }
    @FXML
    public void handleOkClick() {

    }

    private void mapDataToView() {
        if(!isNew)
            idPOILabel.setText(Integer.toString(poi.getIdPOI()));

        poiNameField.setText(poi.getPOIName());
        longitudeField.setText(Float.toString(poi.getLongitude()));
        latitudeField.setText(Float.toString(poi.getLatitude()));

        ArrayList<POIType> categories = poiBean.getCategoriesArr();
        categorySearch.setItems(FXCollections.observableArrayList(categories));
        categorySearch.getSelectionModel().select(poi.getType());

        ArrayList<Subdivision> subdivisions = subdivisionBean.getSubdivisionArr();
        subdivisionSearch.setItems(FXCollections.observableArrayList(subdivisions));
        subdivisionSearch.getSelectionModel().select(poi.getSubdivision());

        ArrayList<Country> countries = countryBean.getCountriesArr();
        countrySearch.setItems(FXCollections.observableArrayList(countries));
        countrySearch.getSelectionModel().select(poi.getSubdivision().getCountry());

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
}
