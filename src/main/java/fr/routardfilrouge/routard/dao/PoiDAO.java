package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.POI;
import fr.routardfilrouge.routard.metier.POISearch;
import fr.routardfilrouge.routard.metier.POIType;
import fr.routardfilrouge.routard.metier.Subdivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PoiDAO extends DAO<POI, POISearch>{
    @Override
    public ArrayList<POI> getAll() {
        return null;
    }

    @Override
    public ArrayList<POI> getLike(POISearch poiSearch) {
        ArrayList<POI> pois = new ArrayList<>();
        String req = "{call ps_searchPOI(?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, poiSearch.getPoiName());
            stm.setInt(2, poiSearch.getType().getIdType());
            stm.setInt(3, poiSearch.getSubdivision().getIdSubdivision());

            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                int idPOI = rs.getInt("ID_POI");
                String poiName = rs.getString("NOM_POI");

                //float longitude = rs.getFloat("LONGITUDE");
                //float latitude = rs.getFloat("LATITUDE");

                POIType type = new POIType(rs.getInt("ID_CATEGORIE"), rs.getString("NOM_CATEGORIE"));
                Subdivision subdivision = poiSearch.getSubdivision();

                POI poi = new POI(idPOI, poiName, subdivision, type);
                pois.add(poi);
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return pois;
    }

    public ArrayList<POIType> getAllCategories() {
        ArrayList<POIType> categories = new ArrayList<>();
        String req = "SELECT * FROM CATEGORIE";

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(req);
            while (rs.next())
                categories.add(new POIType(rs.getInt("ID_CATEGORIE"), rs.getString("NOM_CATEGORIE")));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean update(POI object) {
        return false;
    }

    @Override
    public boolean post(POI poi) {
        String req = "{call ps_insertPOI(?,?,?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, poi.getPOIName());
            stm.setFloat(2, poi.getLongitude());
            stm.setFloat(3, poi.getLatitude());
            stm.setInt(4, poi.getSubdivision().getIdSubdivision());
            stm.setInt(5, poi.getType().getIdType());
            stm.executeUpdate();
           return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean postCategory(POIType category) {
        String req = "INSERT INTO CATEGORIE (NOM_CATEGORIE) VALUES (?)";
        try (PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, category.getTypeName());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(POI poi) {
        String req = "DELETE FROM POINT_INTERET WHERE ID_POINT_INTERET=?";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, poi.getIdPOI());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
