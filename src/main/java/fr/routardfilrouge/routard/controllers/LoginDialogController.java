package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.dao.RoutardConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class LoginDialogController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button okBtn;
    @FXML
    private Label errorMessage;
    private String username;
    private String password;
    private HashMap<String, String> account;
    private Stage dialogStage;
    private boolean isOkClicked;

    public LoginDialogController() {
        username = "";
        password = "";
        account = new HashMap<>();
        isOkClicked = false;
    }

    @FXML
    private void initialize() {
        okBtn.setDisable(!isDataValid());

        usernameField.textProperty().addListener((ob, o, n) -> {
            if(!errorMessage.getText().isEmpty())
                errorMessage.setText("");
            okBtn.setDisable(!isDataValid());
        });
        passwordField.textProperty().addListener((ob, o, n) -> {
            if(!errorMessage.getText().isEmpty())
                errorMessage.setText("");
            okBtn.setDisable(!isDataValid());
        });
    }

    @FXML
    private void handleOkClick() {
        if(!isDataValid())
            return;

        username = usernameField.getText();
        password = passwordField.getText();
        account.put("username", username);
        account.put("password", password);

        try {
            RoutardConnect.setAccount(account);
            RoutardConnect.getInstance();
            isOkClicked = true;
            dialogStage.close();
        } catch(Exception e) {
            errorMessage.setText("Access to database is denied.");
        }
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

    public HashMap<String, String> getAccount() {
        return account;
    }
}
