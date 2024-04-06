package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.Language;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class LanguageBean {
    private ArrayList<Language> languageArr;
    private ObservableList<Language> languages;

    public LanguageBean() {
        languages = FXCollections.observableArrayList();
        languageArr = DAOFactory.getLanguageDAO().getAll();

        languages.addAll(languageArr);

        languages.add(0,new Language("", "Language (" + languages.size() + ")"));
    }
}
