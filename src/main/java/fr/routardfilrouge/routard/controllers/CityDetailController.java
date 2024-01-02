package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.Month;
import fr.routardfilrouge.routard.metier.Weather;
import fr.routardfilrouge.routard.service.CityBean;
import fr.routardfilrouge.routard.service.ClimateBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CityDetailController {
    @FXML
    private Text idCityText;
    @FXML
    private Text cityNameText;
    @FXML
    private Text subNameText;
    @FXML
    private Text countryNameText;
    @FXML
    private Text longitudeText;
    @FXML
    private Text latitudeText;
    @FXML
    private Text climateTypeText;
    @FXML
    private TableView<Weather> weatherTableView;
    @FXML
    private TableColumn<Weather, String> monthCol;
    @FXML
    private TableColumn<Weather, String> tempCol;
    @FXML
    private TableColumn<Weather, String> humidityCol;
    @FXML
    private TableColumn<Weather, String> precipitationCol;
    private City city;
    private ArrayList<Weather> weatherArr;
    private ObservableList<Weather> weatherList;
    private MainApp mainApp;
    private CityBean cityBean;
    private ClimateBean climateBean;

    public CityDetailController() {
        weatherList = FXCollections.observableArrayList();
        weatherArr = new ArrayList<>();
    }

    @FXML
    private void initialize() {
        monthCol.setCellValueFactory(cell -> cell.getValue().getMonth().monthNameProperty());
        tempCol.setCellValueFactory(cell -> cell.getValue().avgTempProperty().asString());
        humidityCol.setCellValueFactory(cell -> cell.getValue().avgHumidityProperty().asString());
        precipitationCol.setCellValueFactory(cell -> cell.getValue().avgPrecipitationProperty().asString());
    }

    @FXML
    private void handleDeleteClick() {
        cityBean.deleteCity(city);
    }

    @FXML
    private void handleNewClick() {
        City newCity = new City();
        ArrayList<Weather> newWeatherList = climateBean.createNewWeatherList(newCity);
        boolean isPosted = mainApp.showNewEditCityDialog("New City", newCity, newWeatherList, true);

        if(isPosted) {
            city = newCity;
            mapDataToView();
        }
    }

    @FXML
    private void handleModifyClick() {
        ArrayList<Weather> weatherListToModify = weatherArr.isEmpty() ? climateBean.createNewWeatherList(city) : weatherArr;

        boolean isModified = mainApp.showNewEditCityDialog("Modify City", city, weatherListToModify, false);

        if(isModified)
            mapDataToView();
        else
            setUpWeatherTable();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCityBean(CityBean cityBean) {
        this.cityBean = cityBean;
    }

    public void setClimateBean(ClimateBean climateBean) {
        this.climateBean = climateBean;
    }

    public void setCity(City city) {
        this.city = city;
        mapDataToView();
    }

    private void mapDataToView() {
        setUpWeatherTable();

        idCityText.setText(Integer.toString(city.getIdCity()));
        cityNameText.setText(city.getCityName());
        subNameText.setText(city.getSubdivision().getSubdivisionName());
        countryNameText.setText(city.getSubdivision().getCountry().getName());
        longitudeText.setText(Float.toString(city.getLongitude()));
        latitudeText.setText(Float.toString(city.getLatitude()));
        climateTypeText.setText(city.getClimateType().getClimateName());
    }

    private void setUpWeatherTable() {
        weatherArr = climateBean.getWeather(city);
        weatherList.setAll(weatherArr);
        weatherTableView.setItems(weatherList);
        monthCol.setSortable(false);
    }
}
