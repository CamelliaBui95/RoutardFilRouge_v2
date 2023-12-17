package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.Country;
import fr.routardfilrouge.routard.metier.Subdivision;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class CityDAO extends DAO<City, City>{
    private HashMap<Integer, Subdivision> subdivisions;

    public CityDAO() {
        this.subdivisions = new HashMap<>();
        ArrayList<Subdivision> subdivisionArr = DAOFactory.getSubdivisionDAO().getAll();

        for(int i = 0; i < subdivisionArr.size(); i++) {
            int idSub = subdivisionArr.get(i).getIdSubdivision();
            Subdivision subdivision = subdivisionArr.get(i);

            subdivisions.putIfAbsent(idSub, subdivision);
        }
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
                 cities.add(city);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
        return cities;
    }

    @Override
    public boolean update(City object) {
        return false;
    }

    @Override
    public boolean post(City object) {
        return false;
    }

    @Override
    public boolean delete(City object) {
        return false;
    }
}
