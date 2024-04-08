package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguageDAO extends DAO<Language, Country>{

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
    public ArrayList<Language> getLike(Country country) {
        ArrayList<Language> languages = new ArrayList<>();
        String query = "SELECT L.ISO_LANGUE,\n" +
                "\t\tNOM_LANGUE\n" +
                "FROM PARLER P\n" +
                "JOIN LANGUE L ON P.ISO_LANGUE = L.ISO_LANGUE\n" +
                "WHERE CODE_ISO_3166_1 = ?";

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, country.getIsoCode());

            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                Language language = new Language(rs.getString("ISO_LANGUE"), rs.getString("NOM_LANGUE"));
                languages.add(language);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
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
