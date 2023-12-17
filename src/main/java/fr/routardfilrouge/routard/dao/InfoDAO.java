package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.InfoType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InfoDAO extends DAO<InfoType, InfoType>{
    @Override
    public ArrayList<InfoType> getAll() {
        return null;
    }

    @Override
    public boolean update(InfoType object) {
        return false;
    }

    @Override
    public boolean post(InfoType object) {
        return false;
    }

    @Override
    public boolean delete(InfoType object) {
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
}
