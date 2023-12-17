package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CountryDAO extends DAO<Country, Country> {
    @Override
    public ArrayList<Country> getAll() {
        ArrayList<Country> countries = new ArrayList<>();
        String req = "{call ps_searchCountry}";
        try(CallableStatement stm = connection.prepareCall(req)) {
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                String continentCode = rs.getString("CODE_CONTINENT");
                String continentName = rs.getString("NOM_CONTINENT");
                Continent continent = new Continent(continentCode, continentName);

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
}
