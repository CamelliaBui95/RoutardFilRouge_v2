package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.CityBean;
import fr.routardfilrouge.routard.service.ClimateBean;
import fr.routardfilrouge.routard.service.CountryBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import fr.routardfilrouge.routard.utils.DataValidator;
import fr.routardfilrouge.routard.utils.EditingCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.SearchableComboBox;

public class NewEditCityDialogController {
    @FXML
    private Label idCityLabel;
    @FXML
    private TextField cityNameField;
    @FXML
    private TextField longitudeField;
    @FXML
    private TextField latitudeField;
    @FXML
    private SearchableComboBox<Subdivision> subdivisionSearchBox;
    @FXML
    private SearchableComboBox<Country> countrySearchBox;
    @FXML
    private SearchableComboBox<ClimateType> climateSearchBox;
    @FXML
    private TableView<Weather> weatherTable;
    @FXML
    private TableColumn<Weather, String> monthCol;
    @FXML
    private TableColumn<Weather, String> tempCol;
    @FXML
    private TableColumn<Weather, String> humidityCol;
    @FXML
    private TableColumn<Weather, String> precipitationCol;
    @FXML
    private Button okBtn;
    private boolean isOkClicked;

    private Stage dialogStage;
    private ClimateBean climateBean;
    private CountryBean countryBean;
    private SubdivisionBean subdivisionBean;
    private CityBean cityBean;
    private City city;
    private ObservableList<Weather> weatherList;
    private ObservableList<Subdivision> subdivisions;
    private ObservableList<Country> countries;
    private ObservableList<ClimateType> climateTypes;
    private boolean isNew;

    public NewEditCityDialogController() {
        weatherList = FXCollections.observableArrayList();
        subdivisions = FXCollections.observableArrayList();
        countries = FXCollections.observableArrayList();
        climateTypes = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        setUpWeatherCols();
    }

    @FXML
    private void handleCancelClick() {
        dialogStage.close();
    }

    @FXML
    private void handleOkClick() {
        if(!isDataValid())
            return;

        mapDataToModel();

        boolean hasSucceeded;
        if(isNew)
            hasSucceeded = cityBean.postCity(city);
        else
            hasSucceeded = cityBean.updateCity(city);

        boolean isCurrentPosted = true;
        if(hasSucceeded) {
            if(isNew)
                city.setIdCity(cityBean.getCitiesArr().getLast().getIdCity());

            int index = 0;
            while(isCurrentPosted && index < weatherList.size())
                isCurrentPosted = climateBean.postWeather(weatherList.get(index++));
        }

        isOkClicked = hasSucceeded && isCurrentPosted;
        dialogStage.close();
    }

    private boolean isDataValid() {
        boolean isNameValid = cityNameField.getText() != null && !cityNameField.getText().isEmpty();
        boolean isSubdivisionValid = subdivisionSearchBox.getSelectionModel().getSelectedIndex() > 0;
        boolean isLongitudeValid = longitudeField.getText() != null && DataValidator.isValidFloat(longitudeField.getText());
        boolean isLatitudeValid = latitudeField.getText() != null && DataValidator.isValidFloat(latitudeField.getText());

        return isNameValid && isSubdivisionValid && isLongitudeValid && isLatitudeValid;
    }

    private void mapDataToView() {
        if(!isNew)
            idCityLabel.setText(Integer.toString(city.getIdCity()));

        cityNameField.setText(city.getCityName());
        longitudeField.setText(Float.toString(city.getLongitude()));
        latitudeField.setText(Float.toString(city.getLatitude()));

        setUpSubdivisionSearch();
        setUpCountrySearch();
        setUpClimateSearch();
        setUpWeatherTable();
        setUpOkBtn();
    }

    private void mapDataToModel() {
        int idCity = isNew ? 0 : Integer.parseInt(idCityLabel.getText());
        city.setIdCity(idCity);
        city.setCityName(cityNameField.getText());
        city.setLongitude(Float.parseFloat(longitudeField.getText()));
        city.setLatitude(Float.parseFloat(latitudeField.getText()));
        city.setSubdivision(subdivisionSearchBox.getSelectionModel().getSelectedItem());
        city.setClimateType(climateSearchBox.getSelectionModel().getSelectedItem());
    }

    private void setUpOkBtn() {
        okBtn.setDisable(!isDataValid());

        cityNameField.textProperty().addListener((ob, o, n) -> okBtn.setDisable(!isDataValid()));
        subdivisionSearchBox.valueProperty().addListener((ob , o, n) -> okBtn.setDisable(!isDataValid()));
        longitudeField.textProperty().addListener((ob, o,n) -> okBtn.setDisable(!isDataValid()));
        latitudeField.textProperty().addListener((ob, o,n) -> okBtn.setDisable(!isDataValid()));
    }

    private void setUpSubdivisionSearch() {
        subdivisions.addAll(subdivisionBean.getSubdivisionArr());
        subdivisions.add(0, new Subdivision());

        subdivisionSearchBox.setItems(subdivisions);
        subdivisionSearchBox.getSelectionModel().select(city.getSubdivision());
    }

    private void setUpCountrySearch() {
        countries.addAll(countryBean.getCountriesArr());
        countries.add(0, new Country());

        countrySearchBox.setItems(countries);
        countrySearchBox.getSelectionModel().select(city.getSubdivision().getCountry());
    }

    private void setUpClimateSearch() {
        climateTypes.addAll(climateBean.getClimateTypeArr());
        climateTypes.add(0, new ClimateType());

        climateSearchBox.setItems(climateTypes);
        climateSearchBox.getSelectionModel().select(city.getClimateType());
    }

    private void setUpWeatherTable() {
        weatherTable.setItems(weatherList);
        monthCol.setSortable(false);
    }

    private void setUpWeatherCols() {
        weatherTable.setEditable(true);
        monthCol.setCellValueFactory(cell -> cell.getValue().getMonth().monthNameProperty());
        monthCol.setEditable(false);

        tempCol.setCellValueFactory(cell -> cell.getValue().avgTempProperty().asString());
        tempCol.setCellFactory(cell -> {
            EditingCell editingCell = new EditingCell();
            editingCell.setValidator(DataValidator::isValidFloat);
            return editingCell;
        });
        tempCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Weather, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Weather, String> e) {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAvgTemp(Float.parseFloat(e.getNewValue()));
            }
        });


        humidityCol.setCellValueFactory(cell -> cell.getValue().avgHumidityProperty().asString());
        humidityCol.setCellFactory(cell -> {
            EditingCell editingCell = new EditingCell();
            editingCell.setValidator(DataValidator::isValidInteger);
            return editingCell;
        });
        humidityCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Weather, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Weather, String> e) {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAvgHumidity(Integer.parseInt(e.getNewValue()));
            }
        });

        precipitationCol.setCellValueFactory(cell -> cell.getValue().avgPrecipitationProperty().asString());
        precipitationCol.setCellFactory(cell -> {
            EditingCell editingCell = new EditingCell();
            editingCell.setValidator(DataValidator::isValidFloat);
            return editingCell;
        });
        precipitationCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Weather, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Weather, String> e) {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAvgPrecipitation(Float.parseFloat(e.getNewValue()));
            }
        });
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setClimateBean(ClimateBean climateBean) {
        this.climateBean = climateBean;
    }

    public void setCountryBean(CountryBean countryBean) {
        this.countryBean = countryBean;
    }

    public void setSubdivisionBean(SubdivisionBean subdivisionBean) {
        this.subdivisionBean = subdivisionBean;
    }

    public void setCityBean(CityBean cityBean) {
        this.cityBean = cityBean;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setWeatherList(ObservableList<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
        mapDataToView();
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }
}


