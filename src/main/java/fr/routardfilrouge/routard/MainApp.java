package fr.routardfilrouge.routard;

import fr.routardfilrouge.routard.controllers.CountryDetailController;
import fr.routardfilrouge.routard.controllers.MainViewController;
import fr.routardfilrouge.routard.controllers.NewEditCountryDialogController;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.service.*;
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
    private ClimateBean climateBean;
    private ContinentBean continentBean;

    public MainApp() {
        this.continentBean = new ContinentBean();

        this.countryBean = new CountryBean();
        this.countryBean.setContinents(continentBean.getContinentsArr());

        this.subdivisionBean = new SubdivisionBean();
        this.cityBean = new CityBean();
        this.climateBean = new ClimateBean();
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
            controller.setContinentBean(continentBean);
            controller.setCountryBean(countryBean);
            controller.setSubdivisionBean(subdivisionBean);
            controller.setCityBean(cityBean);
            controller.setClimateBean(climateBean);
            controller.setMainApp(this);

            Scene scene = new Scene(pane);
            scene.getStylesheets().add(getClass().getResource("stylesheets/styles.css").toExternalForm());

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

    public void showNewCountryDialog(Country country, String title, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditCountryDialog-View.fxml"));
            AnchorPane dialogPane = loader.load();
            NewEditCountryDialogController controller = loader.getController();

            Stage dialogStage = new Stage();

            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            dialogStage.setScene(new Scene(dialogPane));

            controller.setDialogStage(dialogStage);
            controller.setCountry(country);
            controller.setCountryBean(countryBean);
            controller.setContinentBean(continentBean);
            controller.setNew(isNew);
            dialogStage.showAndWait();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}