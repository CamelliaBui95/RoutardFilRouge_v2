package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.SearchableComboBox;

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
    @FXML
    private SearchableComboBox<Continent> continentSearch;
    @FXML
    private SearchableComboBox<String> codeCountrySearch;
    private CountryBean countryBean;
    private ContinentBean continentBean;

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

    @FXML
    private SearchableComboBox<ClimateType> climateTypeSearch;
    private ClimateBean climateBean;

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

    private void setUpContinentSearchBox() {
        ObservableList<Continent> continentsObservableList = continentBean.getContinents();
        //continentsObservableList.add(0, new Continent("", "Continent (" + continentsObservableList.size() + ")"));
        continentSearch.setItems(continentsObservableList);
        continentSearch.getSelectionModel().selectFirst();

        continentSearch.valueProperty().addListener((ob, o, n) -> {
            countryBean.getCountriesByContinent((Continent) n);
            subdivisionBean.getSubdivisionsByContinent((Continent) n);
            cityBean.getCitiesByContinent((Continent) n);
        });
    }

    private void setUpCountryCodeSearchBox() {
        ObservableList<String> countryCodesObservableList = countryBean.getCountryCodes();
        countryCodesObservableList.add(0,"Country Code (" + countryCodesObservableList.size() + ")");
        codeCountrySearch.setItems(countryCodesObservableList);
        codeCountrySearch.getSelectionModel().selectFirst();

        codeCountrySearch.valueProperty().addListener((ob, o, n) -> {
            String value = "";
            if(codeCountrySearch.getSelectionModel().getSelectedIndex() != 0)
                value = n;

            countryBean.getCountriesByCountryCode(value);
            subdivisionBean.getSubdivisionsByCountryCode(value);
            cityBean.getCitiesByCountryCode(value);
        });
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
        SortedList<Country> sortedCountries = this.countryBean.getSortedCountries();
        sortedCountries.comparatorProperty().bind(countryTableView.comparatorProperty());
        countryTableView.setItems(sortedCountries);

        setUpContinentSearchBox();
        setUpCountryCodeSearchBox();
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
    public void setClimateBean(ClimateBean climateBean) {
        this.climateBean = climateBean;

        ObservableList<ClimateType> climateTypesObservableList = climateBean.getClimateTypes();
        climateTypesObservableList.add(0, new ClimateType("", "Climate (" + climateTypesObservableList.size() + ")"));
        climateTypeSearch.setItems(climateTypesObservableList);
        climateTypeSearch.getSelectionModel().selectFirst();
    }

    public void setContinentBean(ContinentBean continentBean) {
        this.continentBean = continentBean;
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}