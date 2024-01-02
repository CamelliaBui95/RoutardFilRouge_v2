package fr.routardfilrouge.routard.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginDialogController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button okBtn;
    private String username;
    private String password;
    private Stage dialogStage;

    public LoginDialogController() {
        username = "";
        password = "";
        isOkClicked = false;
    }

    @FXML
    private void initialize() {
        okBtn.setDisable(!isDataValid());

        usernameField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        passwordField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
    }

    private boolean isOkClicked;

    @FXML
    private void handleOkClick() {
        if(!isDataValid())
            return;

        username = usernameField.getText();
        password = passwordField.getText();
        isOkClicked = true;
        dialogStage.close();
    }
    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }

    private boolean isDataValid() {
        boolean isUsernameValid = usernameField.getText() != null && !usernameField.getText().isEmpty();
        boolean isPasswordValid = passwordField.getText() != null && !passwordField.getText().isEmpty();
        return isUsernameValid && isPasswordValid;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
