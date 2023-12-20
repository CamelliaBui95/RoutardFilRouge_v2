package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.CountrySearch;
import fr.routardfilrouge.routard.metier.InfoType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class CountryDAO extends DAO<Country, CountrySearch> {

    private HashMap<String, Continent> continents;

    public CountryDAO() {
        continents = new HashMap<>();
    }

    @Override
    public ArrayList<Country> getAll() {
        ArrayList<Country> countries = new ArrayList<>();
        String req = "{call ps_searchCountry}";
        try(CallableStatement stm = connection.prepareCall(req)) {
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Continent continent = this.continents.get(rs.getString("CODE_CONTINENT"));

                String countryCode = rs.getString("CODE_PAYS");
                String countryName = rs.getString("NOM_PAYS");
                Country country = new Country(countryCode,countryName,continent);

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
        String rq = "{call ps_searchCountry(?,?,?)}";

        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, countrySearch.getCountryName());
            stm.setString(2, countrySearch.getCountryCode());
            stm.setString(3, countrySearch.getContinent().getContinentCode());

            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Continent continent = this.continents.get(rs.getString("CODE_CONTINENT"));

                String countryCode = rs.getString("CODE_PAYS");
                String countryName = rs.getString("NOM_PAYS");
                Country country = new Country(countryCode,countryName,continent);

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
        String req = "{call ps_modifyCountry(?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, country.getIsoCode());
            stm.setString(2, country.getName());
            stm.setString(3, country.getContinent().getContinentCode());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(Country country) {
        String rq = "{call ps_insertCountry(?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, country.getIsoCode());
            stm.setString(2, country.getName());
            stm.setString(3, country.getContinent().getContinentCode());
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

    public void setContinents(ArrayList<Continent> continents) {
        for(int i = 0; i < continents.size(); i++) {
            String key = continents.get(i).getContinentCode();
            Continent value = continents.get(i);

            this.continents.putIfAbsent(key, value);
        }

    }
}
