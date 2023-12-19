package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Info;
import fr.routardfilrouge.routard.metier.InfoType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoDAO extends DAO<Info, Info>{
    private HashMap<String, Country> countries;

    public InfoDAO() {
        countries = new HashMap<>();
    }

    @Override
    public ArrayList<Info> getAll() {
        ArrayList<Info> infos = new ArrayList<>();
        String req = "SELECT * FROM INFORMER";

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()) {
                int idType = rs.getInt("ID_TYPE_INFO");
                Country country = countries.get(rs.getString("CODE_ISO_3166_1"));
                String infoText = rs.getString("INFO");
                Info info = new Info(idType, country, infoText);
                infos.add(info);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return infos;
    }

    @Override
    public ArrayList<Info> getLike(Info searchObject) {
        return null;
    }

    @Override
    public boolean update(Info object) {
        return false;
    }

    @Override
    public boolean post(Info object) {
        return false;
    }

    @Override
    public boolean delete(Info object) {
        return false;
    }

    public ArrayList<InfoType> getAllType() {
        ArrayList<InfoType> infoTypes = new ArrayList<>();
        String req = "SELECT * FROM TYPE_INFO";
        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()) {
                int idType = rs.getInt("ID_TYPE_INFO");
                String typeName = rs.getString("NOM_TYPE_INFO");
                InfoType infoType = new InfoType(idType, typeName);
                infoTypes.add(infoType);
            }
            rs.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return infoTypes;
    }

    public void setCountries(ArrayList<Country> countries) {
        for(int i = 0; i < countries.size(); i++) {
            String countryCode = countries.get(i).getIsoCode();
            this.countries.putIfAbsent(countryCode, countries.get(i));
        }
    }
}
