package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.ClimateType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClimateDAO extends DAO<ClimateType, ClimateType>{
    @Override
    public ArrayList<ClimateType> getAll() {
        ArrayList<ClimateType> types = new ArrayList<>();
        String req = "SELECT * FROM TYPE_CLIMAT";
        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(req);

            while (rs.next())
                types.add(new ClimateType(rs.getString("CODE_CLIMAT"), rs.getString("NOM_TYPE_CLIMAT")));

            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return types;
    }

    @Override
    public ArrayList<ClimateType> getLike(ClimateType searchObject) {
        return null;
    }

    @Override
    public boolean update(ClimateType object) {
        return false;
    }

    @Override
    public boolean post(ClimateType object) {
        return false;
    }

    @Override
    public boolean delete(ClimateType object) {
        return false;
    }
}
