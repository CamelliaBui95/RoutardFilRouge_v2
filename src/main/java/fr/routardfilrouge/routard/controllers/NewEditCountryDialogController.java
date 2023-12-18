package fr.routardfilrouge.routard.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class NewEditCountryDialogController {
    private Stage dialogStage;
    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
