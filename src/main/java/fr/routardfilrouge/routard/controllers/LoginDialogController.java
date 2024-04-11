package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.dao.RoutardConnect;
import fr.routardfilrouge.routard.dao.UserDAO;
import fr.routardfilrouge.routard.security.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class LoginDialogController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label errorMessage;
    private String username;
    private String password;

    private MainApp mainApp;
    private Stage dialogStage;

    public LoginDialogController() {
        username = "";
        password = "";
    }

    @FXML
    private void initialize() {
        loginBtn.setDisable(!isDataValid());

        usernameField.textProperty().addListener((ob, o, n) -> {
            if(!errorMessage.getText().isEmpty())
                errorMessage.setText("");
            loginBtn.setDisable(!isDataValid());
        });
        passwordField.textProperty().addListener((ob, o, n) -> {
            if(!errorMessage.getText().isEmpty())
                errorMessage.setText("");
            loginBtn.setDisable(!isDataValid());
        });
    }

    @FXML
    private void handleClickLogin() {
        if(!isDataValid())
            return;

        username = usernameField.getText();
        password = passwordField.getText();

        User user = new User(username, password);
        boolean isConnected = mainApp.loginAndConnect(user);

        if(!isConnected)
            errorMessage.setText("Access to application is denied.");
        else mainApp.showMainView();

    }

    private boolean isDataValid() {
        boolean isUsernameValid = usernameField.getText() != null && !usernameField.getText().isEmpty();
        boolean isPasswordValid = passwordField.getText() != null && !passwordField.getText().isEmpty();
        return isUsernameValid && isPasswordValid;
    }

}
