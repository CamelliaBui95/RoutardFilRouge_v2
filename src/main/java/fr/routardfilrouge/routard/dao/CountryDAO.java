package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryDAO extends DAO<Country, CountrySearch> {
    @Override
    public ArrayList<Country> getAll() {
        ArrayList<Country> countries = new ArrayList<>();
        String req = "SELECT * FROM VIEW_PAYS";
        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()) {
                Continent continent = new Continent(rs.getString("CODE_CONTINENT"), rs.getString("NOM_CONTINENT"));

                String countryCode = rs.getString("CODE_ISO_3166_1");
                String countryName = rs.getString("NOM_PAYS");

                Currency currency = new Currency(rs.getString("CODE_ISO_MONNAIE"), rs.getString("NOM_DEVISE"));

                Country country = new Country(countryCode, countryName, continent, currency);

                ArrayList<Language> languages = DAOFactory.getLanguageDAO().getLike(country);
                country.setLanguages(languages);

                countries.add(country);
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public ArrayList<Country> getLike(CountrySearch countrySearch) {
        ArrayList<Country> countries = new ArrayList<>();
        String rq = "{call ps_searchCountry(?,?,?,?,?,?)}";

        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, countrySearch.getCountryName());
            stm.setString(2, countrySearch.getCountryCode());
            stm.setString(3, countrySearch.getContinent().getContinentCode());
            stm.setString(4, countrySearch.getCurrency().getCurrenyCode());
            stm.setString(5, countrySearch.getClimateType().getClimateCode());
            stm.setString(6, countrySearch.getLanguage().getLanguageCode());

            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Continent continent = new Continent(rs.getString("CODE_CONTINENT"), rs.getString("NOM_CONTINENT"));

                String countryCode = rs.getString("CODE_PAYS");
                String countryName = rs.getString("NOM_PAYS");

                Currency currency = new Currency(rs.getString("CODE_ISO_MONNAIE"), rs.getString("NOM_DEVISE"));

                Country country = new Country(countryCode,countryName,continent, currency);

                ArrayList<Language> languages = DAOFactory.getLanguageDAO().getLike(country);
                country.setLanguages(languages);

                countries.add(country);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public boolean update(Country country) {
        String req = "{call ps_modifyCountry(?,?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, country.getIsoCode());
            stm.setString(2, country.getName());
            stm.setString(3, country.getContinent().getContinentCode());
            stm.setString(4, country.getCurrency().getCurrenyCode());

            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(Country country) {
        String rq = "{call ps_insertCountry(?,?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, country.getIsoCode());
            stm.setString(2, country.getName());
            stm.setString(3, country.getContinent().getContinentCode());
            stm.setString(4, country.getCurrency().getCurrenyCode());

            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Country country) {
        String rq = "DELETE FROM PAYS WHERE CODE_ISO_3166_1=?";
        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, country.getIsoCode());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
