package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.VisaExemptedCountry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VisaExemptionDAO extends DAO<VisaExemptedCountry, Country>{
    @Override
    public ArrayList<VisaExemptedCountry> getAll() {
        return null;
    }

    @Override
    public ArrayList<VisaExemptedCountry> getLike(Country country) {
        String query = "SELECT CODE_PAYS_EXEMPTE,\n" +
                "\t\tNOM_PAYS,\n" +
                "\t\tDUREE_MIN,\n" +
                "\t\tDUREE_MAX,\n" +
                "\t\tDATE_APPLIQUEE_DEBUT,\n" +
                "\t\tDATE_APPLIQUEE_FIN\n" +
                "FROM VISA_EXEMPTION V\n" +
                "JOIN PAYS P ON V.CODE_PAYS_EXEMPTE = P.CODE_ISO_3166_1\n" +
                "WHERE V.CODE_PAYS = ?";

        ArrayList<VisaExemptedCountry> exemptedCountries = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, country.getIsoCode());

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                Country exemptedCountry = new Country();
                exemptedCountry.setIsoCode(rs.getString(1));
                exemptedCountry.setName(rs.getString(2));

                VisaExemptedCountry visaExemptedCountry = new VisaExemptedCountry(exemptedCountry);
                visaExemptedCountry.setMinDuration(rs.getInt(3));
                visaExemptedCountry.setMaxDuration(rs.getInt(4));

                exemptedCountries.add(visaExemptedCountry);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exemptedCountries;
    }

    @Override
    public boolean update(VisaExemptedCountry object) {
        return false;
    }

    @Override
    public boolean post(VisaExemptedCountry object) {
        return false;
    }

    @Override
    public boolean delete(VisaExemptedCountry object) {
        return false;
    }
}
