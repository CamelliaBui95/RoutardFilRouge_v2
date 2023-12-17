package fr.routardfilrouge.routard.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewCountryDialogController {
    private Stage dialogStage;
    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
