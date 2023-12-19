package fr.routardfilrouge.routard.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class NewElementDialogController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private Button element_okBtn;
    private boolean isOkClicked;
    private HashMap<String, String> element;

    public NewElementDialogController() {
        element = new HashMap<>();
    }

    @FXML
    private void initialize() {
        element_okBtn.setDisable(!isDataValid());

        idField.textProperty().addListener((ob, o, n) -> element_okBtn.setDisable(!isDataValid()));
        nameField.textProperty().addListener((ob,n,o) -> element_okBtn.setDisable(!isDataValid()));
    }
    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }
    @FXML
    private void handleOkClick() {
        if(!isDataValid())
            return;

        element.putIfAbsent("id", idField.getText());
        element.putIfAbsent("name", nameField.getText());

        isOkClicked = true;
        dialogStage.close();
    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public HashMap<String, String> getElement() {
        return element;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    private boolean isDataValid() {
        boolean isIdValid = idField.getText() != null && !idField.getText().isEmpty();
        boolean isNameValid = nameField.getText() != null && !nameField.getText().isEmpty();
        return isIdValid && isNameValid;
    }
}
