package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Speak;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SpeakDAO extends DAO<Speak, Country>{
    private HashMap<String, Country> countries;
    public SpeakDAO() {
        countries = new HashMap<>();
    }

    public void setCountries(ArrayList<Country> countries) {
        for (int i = 0; i<countries.size(); i++) {
            String codeCountry = countries.get(i).getIsoCode();
            this.countries.putIfAbsent(codeCountry, countries.get(i));
        }
    }

    @Override
    public ArrayList<Speak> getAll() {
        ArrayList<Speak> speaks = new ArrayList<>();
        String request = "SELECT * FROM PARLER";

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(request);
            while(resultSet.next()){
                String idLanguage = resultSet.getString("ISO_LANGUE");
                String codeCountry = resultSet.getString("CODE_ISO_3166_1");
                Country country = countries.get(codeCountry);
                Speak speak = new Speak(idLanguage, country);
                speaks.add(speak);
            }
            resultSet.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return speaks;
    }

    @Override
    public ArrayList<Speak> getLike(Country searchCountry) {
        ArrayList<Speak> speaks = new ArrayList<>();
        String request = "SELECT * FROM PARLER WHERE CODE_ISO_3166_1 = ?";
        try (PreparedStatement statement= connection.prepareStatement(request)) {
            statement.setString(1, searchCountry.getIsoCode());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String idLanguage = resultSet.getString("ISO_LANGUE");
                String codeCountry = resultSet.getString("CODE_ISO_3166_1");
                Country country = countries.get(codeCountry);
                Speak speak = new Speak(idLanguage, country);
                speaks.add(speak);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return speaks;
    }

    @Override
    public boolean update(Speak object) {
        return false;
    }

    @Override
    public boolean post(Speak object) {
        return false;
    }

    @Override
    public boolean delete(Speak object) {
        return false;
    }
}
