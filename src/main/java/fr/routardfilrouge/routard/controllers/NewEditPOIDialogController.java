package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.POIType;
import fr.routardfilrouge.routard.metier.Subdivision;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
