package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.CountrySearch;
import fr.routardfilrouge.routard.metier.POI;
import fr.routardfilrouge.routard.metier.POIType;
import fr.routardfilrouge.routard.metier.Subdivision;
import fr.routardfilrouge.routard.service.POIBean;
import fr.routardfilrouge.routard.service.SubdivisionBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;

public class SubdivisionDetailController {
    @FXML
    private TextField poiSearchField;
    @FXML
    private SearchableComboBox<POIType> poiTypeSearch;
    @FXML
    private TableView<POI> poiTableView;
    @FXML
    private TableColumn<POI, String> idPOICol;
    @FXML
    private TableColumn<POI, String> poiNameCol;
    @FXML
    private TableColumn<POI, String> poiCategoryCol;
    @FXML
    private Text idSubText;
    @FXML
    private Text subCodeText;
    @FXML
    private Text subNameText;
    @FXML
    private Text subTypeText;
    @FXML
    private Text countryText;

    private Subdivision subdivision;
    private POIBean poiBean;
    private SubdivisionBean subdivisionBean;
    private MainApp mainApp;

    @FXML
    private void initialize() {
        setUpPOIView();
    }

    @FXML
    public void handleAddNewPOI() {
        mainApp.showNewEditPOIDialog("New POI", new POI(), true);
    }

    @FXML
    public void handleDeleteClick() {
        subdivisionBean.deleteSub(subdivision);
    }
    @FXML
    public void handleNewClick() {
        boolean isOk = mainApp.showNewEditSubdivisionDialog("New Subdivision", new Subdivision(), true);
        if(isOk) {
            subdivision = subdivisionBean.getSortedSubdivisions().getLast();
            mapDataToView();
        }
    }
    @FXML
    public void handleModifyClick() {
        boolean isOk = mainApp.showNewEditSubdivisionDialog("Modify Subdivision", subdivision, false);
        if(isOk)
            mapDataToView();

    }

    private void mapDataToView() {
        idSubText.setText(Integer.toString(subdivision.getIdSubdivision()));
        subCodeText.setText(subdivision.getSubdivisionCode());
        subNameText.setText(subdivision.getSubdivisionName());
        subTypeText.setText(subdivision.getSubType().getTypeName());
        countryText.setText(subdivision.getCountry().getName());
    }

    private void setUpPOIView() {
        idPOICol.setCellValueFactory(cell -> cell.getValue().idPOIProperty().asString());
        poiNameCol.setCellValueFactory(cell -> cell.getValue().POINameProperty());
        poiCategoryCol.setCellValueFactory(cell -> cell.getValue().getType().typeNameProperty());

        poiSearchField.textProperty().addListener((ob, o, n) -> poiBean.filterPOIbyName(n));
        poiTableView.setOnMouseClicked(event -> {
            POI selectedPOI = poiTableView.getSelectionModel().getSelectedItem();
            if(selectedPOI != null)
                mainApp.showNewEditPOIDialog("Modify POI", selectedPOI, false);
        });
    }

    private void setUpPOICategorySearch() {
        ArrayList<POIType> categoriesArr = poiBean.getCategoriesArr();
        ObservableList<POIType> categories = FXCollections.observableArrayList(categoriesArr);
        categories.add(0,new POIType(0, "Category (" + categories.size() + ")"));
        poiTypeSearch.setItems(categories);
        poiTypeSearch.getSelectionModel().selectFirst();

        poiTypeSearch.valueProperty().addListener((ob, o, n) -> poiBean.getPOIsByCategory(n));
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
        mapDataToView();
    }

    public void setPoiBean(POIBean poiBean) {
        this.poiBean = poiBean;

        if(subdivision != null) {
            this.poiBean.getPOIsBySubdivision(subdivision);
            SortedList<POI> sortedPOIS = this.poiBean.getSortedPOIs();
            poiTableView.setItems(sortedPOIS);
            sortedPOIS.comparatorProperty().bind(poiTableView.comparatorProperty());

            setUpPOICategorySearch();
        }
    }

    public void setSubdivisionBean(SubdivisionBean subdivisionBean) {
        this.subdivisionBean = subdivisionBean;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
