package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Continent;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.InfoType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CountryDAO extends DAO<Country, Country> {
    private ArrayList<InfoType> infoTypes;

    public CountryDAO() {
        infoTypes = new ArrayList<>();
    }

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

                /*This will be displaced*/
                int infoTypeIndex = rs.getInt("ID_TYPE_INFO") - 1;
                if(infoTypeIndex >= 0) {
                    InfoType infoType = infoTypes.get(infoTypeIndex);
                    String info = rs.getString("INFO");

                    country.addInfo(infoType, info);
                }

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
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setInfoTypes(ArrayList<InfoType> infoTypes) {
        this.infoTypes = infoTypes;
    }
}
