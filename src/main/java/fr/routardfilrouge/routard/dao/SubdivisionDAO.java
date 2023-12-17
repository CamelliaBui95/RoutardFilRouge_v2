package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.SubType;
import fr.routardfilrouge.routard.metier.Subdivision;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubdivisionDAO extends DAO<Subdivision, Subdivision>{
    private ArrayList<Country> countries;
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

    private Country getCountry(String countryCode) {
        if(countries == null || countries.isEmpty())
            return null;

        Country country = countries.stream().iterator().next();
        while(!country.getIsoCode().equals(countryCode) && countries.stream().iterator().hasNext()) {
            country = countries.stream().iterator().next();
        }
        return country;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
