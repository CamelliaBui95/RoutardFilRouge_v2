package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppContainerController {
    private MainApp mainApp;
    private Stage appPrimaryStage;

    @FXML
    private MenuItem countrySubdivisionCityItem;

    @FXML
    private void handleClickCountrySubdivisionCityItem() {
        mainApp.showMainView();
    }
}
