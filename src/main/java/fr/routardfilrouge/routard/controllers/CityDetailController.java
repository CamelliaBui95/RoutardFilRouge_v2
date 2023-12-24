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
    private ObservableList<Weather> weatherList;
    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private MainApp mainApp;
    private CityBean cityBean;
    private ClimateBean climateBean;

    public CityDetailController() {
        weatherList = FXCollections.observableArrayList();
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

    }

    @FXML
    private void handleNewClick() {
        City newCity = new City();
        ObservableList<Weather> newWeatherList = createNewWeatherList(newCity);
        mainApp.showNewEditCityDialog("New City", newCity, newWeatherList, true);
    }

    @FXML
    private void handleModifyClick() {
        if(weatherList.isEmpty())
            weatherList = createNewWeatherList(city);
        mainApp.showNewEditCityDialog("Modify City", city, weatherList, false);
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
        weatherList.setAll(climateBean.getWeather(city));
        weatherTableView.setItems(weatherList);
        monthCol.setSortable(false);

        idCityText.setText(Integer.toString(city.getIdCity()));
        cityNameText.setText(city.getCityName());
        subNameText.setText(city.getSubdivision().getSubdivisionName());
        countryNameText.setText(city.getSubdivision().getCountry().getName());
        longitudeText.setText(Float.toString(0));
        latitudeText.setText(Float.toString(0));
        climateTypeText.setText(city.getClimateType().getClimateName());
    }

    private ObservableList<Weather> createNewWeatherList(City city) {
        ObservableList<Weather> newWeatherList = FXCollections.observableArrayList();
        for(int i = 0 ; i < MONTHS.length; i++) {
            Month month = new Month(i + 1, MONTHS[i]);
            Weather weather = new Weather();
            weather.setMonth(month);
            weather.setCity(city);
            newWeatherList.add(weather);
        }

        return newWeatherList;
    }
}
