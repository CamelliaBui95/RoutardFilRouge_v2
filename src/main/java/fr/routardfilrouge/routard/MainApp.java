package fr.routardfilrouge.routard;

import fr.routardfilrouge.routard.controllers.*;
import fr.routardfilrouge.routard.dao.RoutardConnect;
import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainApp extends Application {
    private Stage primaryStage;
    private CountryBean countryBean;
    private SubdivisionBean subdivisionBean;
    private CityBean cityBean;
    private ClimateBean climateBean;
    private ContinentBean continentBean;
    private InfoBean infoBean;
    private POIBean poiBean;

    private LoginDialogController loginController;
    private HashMap<String, String> account;

    public MainApp() {
        account = new HashMap<>();

    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        boolean isOkClicked = showLoginDialog();

        if(isOkClicked) {
            account.put("username", loginController.getUsername());
            account.put("password", loginController.getPassword());
            RoutardConnect.setAccount(account);
            setUpBeans();
            initMainView(primaryStage);
        }

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

            controller.setMainApp(this);
            controller.setInfoBean(infoBean);
            controller.setCountryBean(countryBean);
            controller.setCountry(country);

            countryDetailPane.setCenter(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void showSubdivisionDetail(BorderPane borderPane, Subdivision subdivision) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SubdivisionDetail-View.fxml"));
            VBox box = loader.load();
            SubdivisionDetailController controller = loader.getController();

            controller.setSubdivision(subdivision);
            controller.setPoiBean(poiBean);
            controller.setSubdivisionBean(subdivisionBean);
            controller.setMainApp(this);

            borderPane.setCenter(box);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void showCityDetail(BorderPane borderPane, City city) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CityDetail-View.fxml"));
            VBox box = loader.load();
            CityDetailController controller = loader.getController();

            controller.setMainApp(this);
            controller.setCityBean(cityBean);
            controller.setClimateBean(climateBean);
            controller.setCity(city);

            borderPane.setCenter(box);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public boolean showNewCountryDialog(Country country, String title, boolean isNew) {
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
            controller.setCountryBean(countryBean);
            controller.setContinentBean(continentBean);
            controller.setInfoBean(infoBean);
            controller.setCountry(country);
            controller.setMainApp(this);
            controller.setNew(isNew);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public HashMap<String, String> showNewElementDialog(String title, boolean deactivateIdField) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewElementDialog-View.fxml"));
            AnchorPane pane = loader.load();
            NewElementDialogController controller = loader.getController();

            Stage dialogStage = new Stage();

            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(pane));

            controller.setDialogStage(dialogStage);
            controller.deactivateIdField(deactivateIdField);

            dialogStage.showAndWait();
            return controller.getElement();
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void showNewEditPOIDialog(String title, POI poi, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditPOIDialog-View.fxml"));
            AnchorPane pane = loader.load();
            NewEditPOIDialogController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(pane));

            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setPoiBean(poiBean);
            controller.setCountryBean(countryBean);
            controller.setSubdivisionBean(subdivisionBean);
            controller.setPoi(poi);
            controller.setNew(isNew);

            dialogStage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showNewEditSubdivisionDialog(String title, Subdivision subdivision, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditSubdivisionDialog-View.fxml"));
            AnchorPane pane = loader.load();
            NewEditSubdivisionController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(pane));

            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setSubdivision(subdivision);
            controller.setCountryBean(countryBean);
            controller.setSubdivisionBean(subdivisionBean);
            controller.setNew(isNew);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showNewEditCityDialog(String title, City city, ArrayList<Weather> weatherList, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditCityDialog-View.fxml"));
            AnchorPane pane = loader.load();
            NewEditCityDialogController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(pane));

            controller.setDialogStage(dialogStage);
            controller.setCity(city);
            controller.setWeatherList(weatherList);
            controller.setCountryBean(countryBean);
            controller.setSubdivisionBean(subdivisionBean);
            controller.setClimateBean(climateBean);
            controller.setCityBean(cityBean);
            controller.setNew(isNew);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showLoginDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginDialog-View.fxml"));
            AnchorPane pane = loader.load();
            LoginDialogController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("Login - Routard Manager");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(pane));

            controller.setDialogStage(dialogStage);
            this.loginController = controller;

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setUpBeans() {
        this.continentBean = new ContinentBean();
        this.countryBean = new CountryBean();
        this.infoBean = new InfoBean();
        this.subdivisionBean = new SubdivisionBean();
        this.cityBean = new CityBean();
        this.climateBean = new ClimateBean();
        this.poiBean = new POIBean();
    }
}