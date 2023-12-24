package fr.routardfilrouge.routard.utils;

import fr.routardfilrouge.routard.metier.Weather;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class EditingCell extends TableCell<Weather, String> {
    private final String defaultStyle = "-fx-border-color: #2a78f5; -fx-focus-color: #2a78f5";
    private final String onErrorStyle = "-fx-border-color: #de2702; -fx-focus-color: #de2702";
    private TextField textField;

    private Callback<String, Boolean> validator;

    public EditingCell() {

    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(item);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);

        textField.textProperty().addListener((ob,o,n) -> {
            if(!validator.call(n))
                textField.setStyle(onErrorStyle);
            else textField.setStyle(defaultStyle);
        });
        textField.setOnAction((e) -> {
            if(validator.call(textField.getText()))
                commitEdit(textField.getText());
        });
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                if(validator.call(textField.getText()))
                    commitEdit(textField.getText());
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

    public void setValidator(Callback<String, Boolean> validator) {
        this.validator = validator;
    }
}
