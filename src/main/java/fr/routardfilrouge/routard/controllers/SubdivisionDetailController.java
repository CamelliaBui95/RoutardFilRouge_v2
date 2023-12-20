package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.metier.CountrySearch;
import fr.routardfilrouge.routard.metier.POI;
import fr.routardfilrouge.routard.metier.Subdivision;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

public class SubdivisionDetailController {
    @FXML
    private TextField subSearchField;
    @FXML
    private SearchableComboBox<POI> poiTypeSearch;
    @FXML
    private TableView<POI> poiTableView;
    @FXML
    private TableColumn<POI, String> idPOICol;
    @FXML
    private TableColumn<POI, String> poiNameCol;
    @FXML
    private Text idSubText;
    @FXML
    private Text subCodeText;
    @FXML
    private Text subNameText;
    @FXML
    private Text subTypeText;
    @FXML
    private Text countryText;

    private Subdivision subdivision;

    @FXML
    public void handleAddNewPOI() {

    }
    @FXML
    public void handleDeleteClick() {

    }
    @FXML
    public void handleNewClick() {

    }
    @FXML
    public void handleModifyClick() {

    }

    private void mapDataToView() {
        idSubText.setText(Integer.toString(subdivision.getIdSubdivision()));
        subCodeText.setText(subdivision.getSubdivisionCode());
        subNameText.setText(subdivision.getSubdivisionName());
        subTypeText.setText(subdivision.getSubType().getTypeName());
        countryText.setText(subdivision.getCountry().getName());
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
        mapDataToView();
    }
}
