package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdministrativeRequirementDAO extends DAO<AdministrativeRequirement, Country>{
    @Override
    public ArrayList<AdministrativeRequirement> getAll() {
        return null;
    }

    @Override
    public ArrayList<AdministrativeRequirement> getLike(Country country) {
        String rq = "SELECT * \n" +
                    "FROM VIEW_ENTRY_REQ\n" +
                    "WHERE CODE_ISO_3166_1 = ?";
        ArrayList<AdministrativeRequirement> entryReqs = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(rq)) {
            pstm.setString(1, country.getIsoCode());

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                ExigenceStatus status = new ExigenceStatus(rs.getInt("ID_STATUT"), rs.getString("LIBELLE"));
                AdministrativeDocType docType = new AdministrativeDocType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                AdministrativeDocument entryDoc = new AdministrativeDocument(rs.getInt("ID_DOCUMENT"), rs.getString("NOM_DOCUMENT"), docType);

                AdministrativeRequirement requiredEntry = new AdministrativeRequirement(entryDoc, rs.getString("NOTES"), status);

                entryReqs.add(requiredEntry);
            }
            rs.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return entryReqs;
    }

    @Override
    public boolean update(AdministrativeRequirement object) {
        return false;
    }

    @Override
    public boolean post(AdministrativeRequirement object) {
        return false;
    }

    @Override
    public boolean delete(AdministrativeRequirement object) {
        return false;
    }
}
