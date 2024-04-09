package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EntryRequirementDAO extends DAO<CountryEntryRequirement, Country>{
    @Override
    public ArrayList<CountryEntryRequirement> getAll() {
        return null;
    }

    @Override
    public ArrayList<CountryEntryRequirement> getLike(Country country) {
        String rq = "SELECT * \n" +
                    "FROM VIEW_PAYS_FORMALITE_ENTREE\n" +
                    "WHERE CODE_ISO_3166_1 = ?";
        ArrayList<CountryEntryRequirement> entryReqs = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(rq)) {
            pstm.setString(1, country.getIsoCode());

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                ExigenceStatus status = new ExigenceStatus(rs.getInt("ID_STATUT"), rs.getString("LIBELLE"));
                EntryReqType docType = new EntryReqType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                EntryRequirement entryReq = new EntryRequirement(rs.getInt("ID_FORMALITE"), rs.getString("NOM_FORMALITE"), docType);

                CountryEntryRequirement requiredEntry = new CountryEntryRequirement(country, entryReq, rs.getString("NOTES"), status);

                entryReqs.add(requiredEntry);
            }
            rs.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return entryReqs;
    }

    public ArrayList<CountryEntryRequirement> getAdministrativeReqs(Country country) {
        String query = "SELECT *\n" +
                        "FROM VIEW_PAYS_FORMALITE_ENTREE\n" +
                        "WHERE CODE_ISO_3166_1 = ? AND NOM_TYPE != 'Vaccines/Treatments';";
        ArrayList<CountryEntryRequirement> entryReqs = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, country.getIsoCode());

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                ExigenceStatus status = new ExigenceStatus(rs.getInt("ID_STATUT"), rs.getString("LIBELLE"));
                EntryReqType entryReqType = new EntryReqType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                EntryRequirement entryReq = new EntryRequirement(rs.getInt("ID_FORMALITE"), rs.getString("NOM_FORMALITE"), entryReqType);

                CountryEntryRequirement countryEntryReq = new CountryEntryRequirement(country, entryReq, rs.getString("NOTES"), status);

                entryReqs.add(countryEntryReq);
            }
            rs.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return entryReqs;
    }

    public ArrayList<CountryEntryRequirement> getMedicalReqs(Country country) {
        String query = "SELECT *\n" +
                "FROM VIEW_PAYS_FORMALITE_ENTREE\n" +
                "WHERE CODE_ISO_3166_1 = ? AND NOM_TYPE = 'Vaccines/Treatments';";
        ArrayList<CountryEntryRequirement> entryReqs = new ArrayList<>();

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, country.getIsoCode());

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                ExigenceStatus status = new ExigenceStatus(rs.getInt("ID_STATUT"), rs.getString("LIBELLE"));
                EntryReqType entryReqType = new EntryReqType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                EntryRequirement entryReq = new EntryRequirement(rs.getInt("ID_FORMALITE"), rs.getString("NOM_FORMALITE"), entryReqType);

                CountryEntryRequirement countryEntryReq = new CountryEntryRequirement(country, entryReq, rs.getString("NOTES"), status);

                entryReqs.add(countryEntryReq);
            }
            rs.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return entryReqs;
    }

    @Override
    public boolean update(CountryEntryRequirement countryEntryReq) {
        String query = "{call ps_updateFormaliteEntree(?,?,?,?)}";

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, countryEntryReq.getCountry().getIsoCode());
            pstm.setInt(2, countryEntryReq.getEntryRequirement().getIdEntryReq());
            pstm.setString(3, countryEntryReq.getNote());
            pstm.setInt(4, countryEntryReq.getStatus().getIdStatus());

            pstm.executeUpdate();

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(CountryEntryRequirement countryEntryReq) {
        String query = "{call ps_insertAdminReq(?,?,?,?)}";

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, countryEntryReq.getCountry().getIsoCode());
            pstm.setInt(2, countryEntryReq.getEntryRequirement().getIdEntryReq());
            pstm.setString(3, countryEntryReq.getNote());
            pstm.setInt(4, countryEntryReq.getStatus().getIdStatus());

            pstm.executeUpdate();

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(CountryEntryRequirement object) {
        return false;
    }
}
