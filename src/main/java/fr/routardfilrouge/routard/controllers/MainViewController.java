package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.service.CityBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainViewController {
    @FXML
    private BorderPane detailPane;
    @FXML
    private TableView<Country> countryTableView;
    @FXML
    private TableColumn<Country, String> countryCodeCol;
    @FXML
    private TableColumn<Country, String> countryNameCol;
    @FXML
    private TableColumn<Country, String> continentNameCol;
    @FXML
    private TextField countrySearchField;
    private CountryBean countryBean;

    @FXML
    private TableView<Subdivision> subdivisionTableView;
    @FXML
    private TableColumn<Subdivision, String> idSubdivisionCol;
    @FXML
    private TableColumn<Subdivision, String> subdivisionNameCol;
    @FXML
    private TableColumn<Subdivision, String> subdivisionCodeCol;
    @FXML
    private TextField subdivisionSearchField;
    private SubdivisionBean subdivisionBean;

    @FXML
    private TableView<City> cityTableView;
    @FXML
    private TableColumn<City, String> idCityCol;
    @FXML
    private TableColumn<City, String> cityNameCol;
    @FXML
    private TextField citySearchField;
    private CityBean cityBean;

    private MainApp mainApp;

    @FXML
    private void initialize() {
        setUpCountryView();
        setUpSubdivisionView();
        setUpCityView();
    }

    private void setUpCountryView() {
        countryCodeCol.setCellValueFactory(cell -> cell.getValue().isoCodeProperty());
        countryNameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        continentNameCol.setCellValueFactory(cell -> cell.getValue().getContinent().nameProperty());

        countrySearchField.textProperty().addListener((ob, o, n) -> {
            String subdivisionSearchStr = subdivisionSearchField.textProperty().getValue();
            String citySearchStr = citySearchField.textProperty().getValue();

            this.countryBean.filterCountry(n);
            this.subdivisionBean.filterSubdivisions(subdivisionSearchStr, n);
            this.cityBean.filterCity(citySearchStr, subdivisionSearchStr, n);
        });

        countryTableView.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> {
            if(n != null)
                mainApp.showCountryDetail(detailPane, n);
        });
    }

    private void setUpSubdivisionView() {
        idSubdivisionCol.setCellValueFactory(cell -> cell.getValue().idSubdivisionProperty().asString());
        subdivisionNameCol.setCellValueFactory(cell -> cell.getValue().subdivisionNameProperty());
        subdivisionCodeCol.setCellValueFactory(cell -> cell.getValue().subdivisionCodeProperty());

        subdivisionSearchField.textProperty().addListener((ob, o, n) -> {
            String countrySearchStr = countrySearchField.textProperty().getValue();
            String citySearchStr = citySearchField.textProperty().getValue();
            this.subdivisionBean.filterSubdivisions(n, countrySearchStr);
            this.cityBean.filterCity(citySearchStr, n, countrySearchStr);
        });
    }

    private void setUpCityView() {
        idCityCol.setCellValueFactory(cell -> cell.getValue().idCityProperty().asString());
        cityNameCol.setCellValueFactory(cell -> cell.getValue().cityNameProperty());

        citySearchField.textProperty().addListener((ob, o, n) -> {
            String subdivisionSearchStr = subdivisionSearchField.textProperty().getValue();
            String countrySearchStr = countrySearchField.textProperty().getValue();
            this.cityBean.filterCity(n, subdivisionSearchStr, countrySearchStr);
        });
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
        SortedList<Country> sortedCountries = this.countryBean.getSortedCountries();
        sortedCountries.comparatorProperty().bind(countryTableView.comparatorProperty());
        countryTableView.setItems(sortedCountries);
    }

    public void setSubdivisionBean(SubdivisionBean subdivisionBean) {
        this.subdivisionBean = subdivisionBean;
        SortedList<Subdivision> sortedSubdivisions = this.subdivisionBean.getSortedSubdivisions();
        sortedSubdivisions.comparatorProperty().bind(subdivisionTableView.comparatorProperty());
        subdivisionTableView.setItems(sortedSubdivisions);
    }

    public void setCityBean(CityBean cityBean) {
        this.cityBean = cityBean;
        SortedList<City> sortedCities = this.cityBean.getSortedCities();
        sortedCities.comparatorProperty().bind(cityTableView.comparatorProperty());
        cityTableView.setItems(sortedCities);
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}