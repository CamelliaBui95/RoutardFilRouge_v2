package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.MainApp;
import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.AdministrativeServicesBean;
import fr.routardfilrouge.routard.service.ExigenceStatusBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.SearchableComboBox;

@Getter
@Setter
public class NewEditAdminReqDialogController {

    @FXML
    private ButtonBar btnBar;
    @FXML
    private Text countryNameText;
    @FXML
    private SearchableComboBox<AdministrativeDocument> documentSearchBox;
    @FXML
    private Text docTypeText;
    @FXML
    private SearchableComboBox<ExigenceStatus> exigenceSearchBox;
    @FXML
    private TextArea noteTextarea;

    private Country selectedCountry;
    private AdministrativeRequirement adminReq;
    
    private boolean isNew;

    private boolean isOkClicked;

    private Stage dialogStage;

    private AdministrativeServicesBean adminServicesBean;

    private ExigenceStatusBean exigenceStatusBean;

    @FXML
    private void handleClickCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleClickOk() {
        dialogStage.close();
    }
    
    public void setNew(boolean isNew) {
        this.isNew = isNew;

        String prompt = "Edit - ";
        if(this.isNew)
            prompt = "New - ";

        dialogStage.setTitle(prompt + "Administrative Requirement");
        mapDataToView();
    }

    private void mapDataToView() {
        if(!isNew)
            setUpDeleteBtn();

        countryNameText.setText(selectedCountry.getName());
        noteTextarea.setText(adminReq.getNote());
        docTypeText.setText(adminReq.getAdministrativeDocument().getDocumentType().getTypeName());

        setUpDocumentSearchBox();
        setUpExigenceSearchBox();
    }

    private void setUpDocumentSearchBox() {
        documentSearchBox.setItems(adminServicesBean.getObservableAdminDocs());
        documentSearchBox.getSelectionModel().select(adminReq.getAdministrativeDocument());

        documentSearchBox.valueProperty().addListener((ob, o, n) -> {
            if(n == null || adminReq.getAdministrativeDocument().equals(n))
                return;

            adminReq.setAdministrativeDocument(n);

            docTypeText.setText(n.getDocumentType().getTypeName());
        });
    }

    private void setUpExigenceSearchBox() {
        exigenceSearchBox.setItems(exigenceStatusBean.getObservableExigenceStatuses());
        exigenceSearchBox.getSelectionModel().select(adminReq.getStatus());

        exigenceSearchBox.valueProperty().addListener((ob, o, n) -> {
            if(n == null || adminReq.getStatus().equals(n))
                return;

            adminReq.setStatus(n);
        });
    }

    private void setUpDeleteBtn() {
        Button deleteBtn = new Button();
        deleteBtn.setText("Delete");
        deleteBtn.setOnAction(e -> handleDeleteClick());
        btnBar.getButtons().add(2, deleteBtn);
    }

    private void handleDeleteClick() {
        System.out.println("Delete Item");
    }
}
