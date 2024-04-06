package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SubdivisionDAO extends DAO<Subdivision, SubdivisionSearch>{

    @Override
    public ArrayList<Subdivision> getAll() {
        ArrayList<Subdivision> subdivisions = new ArrayList<>();
        String req = "{call ps_searchSubdivision}";
        try(CallableStatement stm = connection.prepareCall(req)) {
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Country country = new Country(rs.getString("CODE_ISO_3166_1"), rs.getString("NOM_PAYS"), new Continent(rs.getString("CODE_CONTINENT"), rs.getString("NOM_CONTINENT")), null);

                SubType type = new SubType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE_SUBDIVISION"));
                int idSub = rs.getInt("ID_SUBDIVISION");
                String codeSub = rs.getString("CODE_ISO_3166_2");
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
    public ArrayList<Subdivision> getLike(SubdivisionSearch subdivisionSearch) {
        ArrayList<Subdivision> subdivisions = new ArrayList<>();
        String rq = "{call ps_searchSubdivision(?,?,?,?,?,?)}";

        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, subdivisionSearch.getNameSubdivision());
            stm.setInt(2, subdivisionSearch.getIdSubdivision());
            stm.setString(3, subdivisionSearch.getCodeSubdivision());
            stm.setInt(4, subdivisionSearch.getSubType().getIdType());
            stm.setString(5, subdivisionSearch.getCodeCountry());
            stm.setString(6, subdivisionSearch.getContinent().getContinentCode());

            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Country country = new Country(rs.getString("CODE_ISO_3166_1"), rs.getString("NOM_PAYS"), new Continent(rs.getString("CODE_CONTINENT"), rs.getString("NOM_CONTINENT")), null);
                SubType type = new SubType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE_SUBDIVISION"));
                int idSub = rs.getInt("ID_SUBDIVISION");
                String codeSub = rs.getString("CODE_ISO_3166_2");
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

    public ArrayList<SubType> getTypes() {
        ArrayList<SubType> types = new ArrayList<>();
        String req = "SELECT * FROM TYPE_SUBDIVISION";

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm .executeQuery(req);
            while(rs.next())
                types.add(new SubType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE_SUBDIVISION")));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return types;
    }

    @Override
    public boolean update(Subdivision subdivision) {
        String req = "UPDATE SUBDIVISION\n" +
                "\t\t\tSET NOM_SUBDIVISION = ?,\n" +
                "\t\t\t\tCODE_ISO_3166_2 = ?,\n" +
                "\t\t\t\tCODE_ISO_3166_1 = ?,\n" +
                "\t\t\t\tID_TYPE = ?\n" +
                "\t\t\tWHERE ID_SUBDIVISION = ?";

        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, subdivision.getSubdivisionName());
            stm.setString(2, subdivision.getSubdivisionCode());
            stm.setString(3, subdivision.getCountry().getIsoCode());
            stm.setInt(4, subdivision.getSubType().getIdType());
            stm.setInt(5, subdivision.getIdSubdivision());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(Subdivision subdivision) {
        String req = "INSERT INTO SUBDIVISION (NOM_SUBDIVISION, CODE_ISO_3166_2, ID_TYPE, CODE_ISO_3166_1) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, subdivision.getSubdivisionName());
            stm.setString(2, subdivision.getSubdivisionCode());
            stm.setInt(3, subdivision.getSubType().getIdType());
            stm.setString(4, subdivision.getCountry().getIsoCode());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean postType(SubType type) {
        String req = "INSERT INTO TYPE_SUBDIVISION (NOM_TYPE_SUBDIVISION) VALUES (?)";

        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, type.getTypeName());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Subdivision subdivision) {
        String req = "DELETE FROM SUBDIVISION WHERE ID_SUBDIVISION=?";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, subdivision.getIdSubdivision());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
