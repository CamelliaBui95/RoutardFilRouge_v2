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
    public boolean post(POI object) {
        return false;
    }

    @Override
    public boolean delete(POI object) {
        return false;
    }
}
