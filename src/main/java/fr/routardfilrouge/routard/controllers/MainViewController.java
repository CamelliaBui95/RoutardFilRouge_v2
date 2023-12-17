package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainViewController {
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

    private MainApp mainApp;

    @FXML
    private void initialize() {
        countryCodeCol.setCellValueFactory(cell -> cell.getValue().isoCodeProperty());
        countryNameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        continentNameCol.setCellValueFactory(cell -> cell.getValue().getContinent().nameProperty());

        countrySearchField.textProperty().addListener((ob, o, n) -> this.countryBean.filterCountry(n));

        idSubdivisionCol.setCellValueFactory(cell -> cell.getValue().idSubdivisionProperty().asString());
        subdivisionNameCol.setCellValueFactory(cell -> cell.getValue().subdivisionNameProperty());
        subdivisionCodeCol.setCellValueFactory(cell -> cell.getValue().subdivisionCodeProperty());

        subdivisionSearchField.textProperty().addListener((ob, o, n) -> this.subdivisionBean.filterSubdivisions(n));
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
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}