package fr.routardfilrouge.routard.controllers;

import fr.routardfilrouge.routard.metier.*;
import fr.routardfilrouge.routard.service.ExigenceStatusBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.SearchableComboBox;

@Getter
@Setter
public class EditEntryReqDialogController {
    @FXML
    private Button okBtn;
    @FXML
    private Text countryNameText;
    @FXML
    private Text reqNameText;
    @FXML
    private Text reqTypeText;
    @FXML
    private SearchableComboBox<ExigenceStatus> exigenceSearchBox;
    @FXML
    private TextArea noteTextarea;

    private CountryEntryRequirement entryRequirement;

    private boolean isOkClicked;

    private Stage dialogStage;

    private ExigenceStatusBean exigenceStatusBean;

    @FXML
    private void handleClickCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleClickOk() {
        if(!isDataValid())
            return;

        ExigenceStatus exigenceStatus = exigenceSearchBox.getSelectionModel().getSelectedItem();
        String note = noteTextarea.getText();

        entryRequirement.setStatus(exigenceStatus);
        entryRequirement.setNote(note);

        isOkClicked = true;
        dialogStage.close();
    }
    @FXML
    private void handleClickDelete() {
        System.out.println("Delete Item");
    }


    public void mapDataToView() {
        countryNameText.setText(entryRequirement.getCountry().getName());
        noteTextarea.setText(entryRequirement.getNote());
        reqNameText.setText(entryRequirement.getEntryRequirement().getEntryReqName());
        reqTypeText.setText(entryRequirement.getEntryRequirement().getEntryReqType().getReqTypeName());

        setUpExigenceSearchBox();
        setUpOkBtn();
    }

    private void setUpExigenceSearchBox() {
        exigenceSearchBox.setItems(exigenceStatusBean.getObservableExigenceStatuses());
        exigenceSearchBox.getSelectionModel().select(entryRequirement.getStatus());
    }
    private boolean isDataValid() {
        ExigenceStatus selectedExigenceStatus = exigenceSearchBox.getSelectionModel().getSelectedItem();

        return selectedExigenceStatus != null && selectedExigenceStatus.getIdStatus() > 0;
    }

    private void setUpOkBtn() {
        okBtn.setDisable(!isDataValid());

        exigenceSearchBox.valueProperty().addListener((ob,o,n) -> okBtn.setDisable(!isDataValid()));
    }
}
