package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.EntryReqType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EntryReqTypeDAO extends DAO<EntryReqType, EntryReqType> {
    @Override
    public ArrayList<EntryReqType> getAll() {
        return null;
    }

    @Override
    public ArrayList<EntryReqType> getLike(EntryReqType searchObject) {
        return null;
    }

    public ArrayList<EntryReqType> getAdministrativeTypes() {
        String query = "SELECT * \n" +
                "FROM TYPE_FORMALITE\n" +
                "WHERE NOM_TYPE != 'Vaccines/Treatments'";
        ArrayList<EntryReqType> administrativeTypes = new ArrayList<>();

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while(rs.next()) {
                EntryReqType entryReqType = new EntryReqType(rs.getInt(1), rs.getString(2));
                administrativeTypes.add(entryReqType);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return administrativeTypes;
    }

    public ArrayList<EntryReqType> getMedicalTypes() {
        String query = "SELECT * \n" +
                "FROM TYPE_FORMALITE\n" +
                "WHERE NOM_TYPE = 'Vaccines/Treatments'";
        ArrayList<EntryReqType> medicalTypes = new ArrayList<>();

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while(rs.next()) {
                EntryReqType entryReqType = new EntryReqType(rs.getInt(1), rs.getString(2));
                medicalTypes.add(entryReqType);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return medicalTypes;
    }

    @Override
    public boolean update(EntryReqType object) {
        return false;
    }

    @Override
    public boolean post(EntryReqType object) {
        return false;
    }

    @Override
    public boolean delete(EntryReqType object) {
        return false;
    }
}
