package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Language;

import java.util.ArrayList;
import java.util.HashMap;

public class LanguageDAO extends DAO<Language, Country>{
    private HashMap<String, Country> countries;

    public LanguageDAO() {
        countries = new HashMap<>();
    }

    @Override
    public ArrayList<Language> getAll() {
        return null;
    }

    @Override
    public ArrayList<Language> getLike(Country searchObject) {
        return null;
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
