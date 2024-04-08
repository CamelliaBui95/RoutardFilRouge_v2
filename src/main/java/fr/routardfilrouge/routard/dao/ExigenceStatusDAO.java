package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.ExigenceStatus;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExigenceStatusDAO extends DAO<ExigenceStatus, ExigenceStatus>{
    @Override
    public ArrayList<ExigenceStatus> getAll() {
        String query = "SELECT * FROM EXIGENCE_STATUT";

        ArrayList<ExigenceStatus> allStatuses = new ArrayList<>();

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while(rs.next()) {
                ExigenceStatus status = new ExigenceStatus(rs.getInt("ID_STATUT"), rs.getString("LIBELLE"));
                allStatuses.add(status);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStatuses;
    }

    @Override
    public ArrayList<ExigenceStatus> getLike(ExigenceStatus searchObject) {
        return null;
    }

    @Override
    public boolean update(ExigenceStatus object) {
        return false;
    }

    @Override
    public boolean post(ExigenceStatus object) {
        return false;
    }

    @Override
    public boolean delete(ExigenceStatus object) {
        return false;
    }
}
