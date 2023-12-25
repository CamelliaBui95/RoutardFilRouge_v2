package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class CityDAO extends DAO<City, CitySearch>{
    private HashMap<Integer, Subdivision> subdivisions;

    public CityDAO() {
        this.subdivisions = new HashMap<>();
    }

    @Override
    public ArrayList<City> getAll() {
        ArrayList<City> cities = new ArrayList<>();
        String req = "SELECT * FROM View_Ville";
         try(Statement stm = connection.createStatement()) {
             ResultSet rs = stm.executeQuery(req);
             while(rs.next()) {
                 Subdivision subdivision = subdivisions.get(rs.getInt("ID_SUBDIVISION"));
                 int idCity = rs.getInt("ID_VILLE");
                 String cityName = rs.getString("NOM_VILLE");

                 City city = new City(idCity, cityName);

                 city.setSubdivision(subdivision);
                 ClimateType climate = new ClimateType();
                 if(rs.getString("CODE_CLIMAT") != null) {
                     climate.setClimateCode(rs.getString("CODE_CLIMAT"));
                     climate.setClimateName(rs.getString("NOM_TYPE_CLIMAT"));
                 }
                 city.setClimateType(climate);
                 city.setLongitude(rs.getFloat("LONGITUDE"));
                 city.setLatitude(rs.getFloat("LATITUDE"));

                 cities.add(city);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
        return cities;
    }

    @Override
    public ArrayList<City> getLike(CitySearch citySearch) {
        ArrayList<City> cities = new ArrayList<>();
        String req = "{call ps_searchVille(?,?,?,?,?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, citySearch.getCityName());
            stm.setInt(2, citySearch.getCityID());
            stm.setInt(3, citySearch.getSubdivision().getIdSubdivision());
            stm.setString(4, citySearch.getCountryCode());
            stm.setString(5, citySearch.getContinent().getContinentCode());
            stm.setString(6, citySearch.getClimateType().getClimateCode());
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Subdivision subdivision = subdivisions.get(rs.getInt("ID_SUBDIVISION"));
                int idCity = rs.getInt("ID_VILLE");
                String cityName = rs.getString("NOM_VILLE");

                City city = new City(idCity, cityName);

                city.setSubdivision(subdivision);
                ClimateType climate = new ClimateType();
                if(rs.getString("CODE_CLIMAT") != null) {
                    climate.setClimateCode(rs.getString("CODE_CLIMAT"));
                    climate.setClimateName(rs.getString("NOM_TYPE_CLIMAT"));
                }
                city.setClimateType(climate);
                city.setLongitude(rs.getFloat("LONGITUDE"));
                city.setLatitude(rs.getFloat("LATITUDE"));

                cities.add(city);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public boolean update(City city) {
        String req = "UPDATE VILLE\n" +
                        "\t\t\tSET NOM_VILLE = ?,\n" +
                        "\t\t\t\tLONGITUDE = ?,\n" +
                        "\t\t\t\tLATITUDE = ?,\n" +
                        "\t\t\t\tID_SUBDIVISION = ?,\n" +
                        "\t\t\t\tCODE_CLIMAT = ?\n" +
                    "\t\t\tWHERE ID_VILLE = ?";

        try(PreparedStatement stm = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, city.getCityName());
            stm.setFloat(2, city.getLongitude());
            stm.setFloat(3, city.getLatitude());
            stm.setInt(4, city.getSubdivision().getIdSubdivision());

            String climateCode = city.getClimateType().getClimateCode().isEmpty() ? null : city.getClimateType().getClimateCode();
            stm.setString(5, climateCode);
            stm.setInt(6, city.getIdCity());

            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(City city) {
        String req = "INSERT INTO VILLE(NOM_VILLE, LONGITUDE, LATITUDE, ID_SUBDIVISION, CODE_CLIMAT) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, city.getCityName());
            stm.setFloat(2, city.getLongitude());
            stm.setFloat(3, city.getLatitude());
            stm.setInt(4, city.getSubdivision().getIdSubdivision());

            String climateCode = city.getClimateType().getClimateCode().isEmpty() ? null : city.getClimateType().getClimateCode();
            stm.setString(5, climateCode);
            stm.executeUpdate();

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(City city) {
        String req = "DELETE FROM VILLE WHERE ID_VILLE = ?";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, city.getIdCity());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setSubdivisions(ArrayList<Subdivision> subdivisions) {
        for(int i = 0; i < subdivisions.size(); i++) {
            int idSub = subdivisions.get(i).getIdSubdivision();
            Subdivision subdivision = subdivisions.get(i);

            this.subdivisions.putIfAbsent(idSub, subdivision);
        }
    }
}
