package fr.routardfilrouge.routard.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class Language {
    private StringProperty languageCode;
    private StringProperty languageName;

    public Language(String languageCode, String languageName) {
        this.languageCode = new SimpleStringProperty(languageCode);
        this.languageName = new SimpleStringProperty(languageName);
    }

    public Language() {
        this.languageCode = new SimpleStringProperty("");
        this.languageName = new SimpleStringProperty("");
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode.set(languageCode);
    }

    public String getLanguageCode() {
        return this.languageCode.get();
    }

    public void setLanguageName(String languageName) {
        this.languageName.set(languageName);
    }

    public String getLanguageName() {
        return this.languageName.get();
    }

    @Override
    public String toString() {
        return languageName.get();
    }
}
