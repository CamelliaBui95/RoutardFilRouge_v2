package fr.routardfilrouge.routard;

import fr.routardfilrouge.routard.controllers.MainViewController;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private CountryBean countryBean;
    private SubdivisionBean subdivisionBean;

    public MainApp() {
        this.countryBean = new CountryBean();
        this.subdivisionBean = new SubdivisionBean();
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        initMainView(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

    private void initMainView(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Main-View.fxml"));
            BorderPane pane = (BorderPane) fxmlLoader.load();
            MainViewController controller = fxmlLoader.getController();
            controller.setCountryBean(countryBean);
            controller.setSubdivisionBean(subdivisionBean);
            controller.setMainApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setTitle("Project Manager - Routard");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}