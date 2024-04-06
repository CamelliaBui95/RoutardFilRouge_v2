package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Language;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguageDAO extends DAO<Language, Language>{

    @Override
    public ArrayList<Language> getAll() {
        ArrayList<Language> languages = new ArrayList<>();
        String request = "SELECT * FROM LANGUE";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(request);
            while (resultSet.next()) {
                String idLanguage = resultSet.getString("ISO_LANGUE");
                String nameLanguage = resultSet.getString("NOM_LANGUE");

                Language language = new Language(idLanguage,nameLanguage);
                languages.add(language);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
    }

    @Override
    public ArrayList<Language> getLike(Language searchObject) {
        return new ArrayList<>();
    }

    @Override
    public boolean update(Language object) {
        return false;
    }

    @Override
    public boolean post(Language object) {
        return false;
    }

    @Override
    public boolean delete(Language object) {
        return false;
    }
}
