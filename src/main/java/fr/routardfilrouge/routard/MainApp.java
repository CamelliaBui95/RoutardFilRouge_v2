package fr.routardfilrouge.routard;

import fr.routardfilrouge.routard.controllers.CountryDetailController;
import fr.routardfilrouge.routard.controllers.MainViewController;
import fr.routardfilrouge.routard.controllers.NewCountryDialogController;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.service.CityBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private CountryBean countryBean;
    private SubdivisionBean subdivisionBean;
    private CityBean cityBean;

    public MainApp() {
        this.countryBean = new CountryBean();
        this.subdivisionBean = new SubdivisionBean();
        this.cityBean = new CityBean();
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
            controller.setCityBean(cityBean);
            controller.setMainApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setTitle("Project Manager - Routard");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showCountryDetail(BorderPane countryDetailPane, Country country) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CountryDetail-View.fxml"));
            AnchorPane pane = loader.load();
            CountryDetailController controller = loader.getController();
            controller.setCountryBean(countryBean);
            controller.setMainApp(this);
            controller.setCountry(country);

            countryDetailPane.setCenter(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showNewCountryDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewCountryDialog-View.fxml"));
            AnchorPane dialogPane = loader.load();
            NewCountryDialogController controller = loader.getController();

            Stage dialogStage = new Stage();

            dialogStage.setTitle("New Country");
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            dialogStage.setScene(new Scene(dialogPane));

            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}