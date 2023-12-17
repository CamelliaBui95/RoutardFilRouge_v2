package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.SubType;
import fr.routardfilrouge.routard.metier.Subdivision;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class SubdivisionDAO extends DAO<Subdivision, Subdivision>{
    private HashMap<String, Country> countries;

    public SubdivisionDAO() {
        this.countries = new HashMap<>();
        ArrayList<Country> countriesArr = DAOFactory.getCountryDAO().getAll();
        for(int i = 0; i < countriesArr.size(); i++) {
            String countryCode = countriesArr.get(i).getIsoCode();
            Country country = countriesArr.get(i);

            countries.putIfAbsent(countryCode, country);
        }
    }

    @Override
    public ArrayList<Subdivision> getAll() {
        ArrayList<Subdivision> subdivisions = new ArrayList<>();
        String req = "{call ps_searchSubdivision}";
        try(CallableStatement stm = connection.prepareCall(req)) {
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Country country = getCountry(rs.getString("CODE_PAYS"));
                SubType type = new SubType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                int idSub = rs.getInt("ID_SUBDIVISION");
                String codeSub = rs.getString("CODE_SUB");
                String nameSub = rs.getString("NOM_SUBDIVISION");
                Subdivision subdivision = new Subdivision(idSub, nameSub, country);
                subdivision.setSubdivisionCode(codeSub);
                subdivision.setSubType(type);

                subdivisions.add(subdivision);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return subdivisions;
    }

    @Override
    public boolean update(Subdivision object) {
        return false;
    }

    @Override
    public boolean post(Subdivision object) {
        return false;
    }

    @Override
    public boolean delete(Subdivision object) {
        return false;
    }

    private Country getCountry(String countryCode) {
        if(countries.isEmpty())
            return null;

        return countries.get(countryCode);
    }

}
