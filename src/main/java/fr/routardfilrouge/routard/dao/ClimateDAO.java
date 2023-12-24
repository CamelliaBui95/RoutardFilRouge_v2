package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.City;
import fr.routardfilrouge.routard.metier.ClimateType;
import fr.routardfilrouge.routard.metier.Month;
import fr.routardfilrouge.routard.metier.Weather;

import java.sql.PreparedStatement;
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

    public ArrayList<Weather> getWeather(City city) {
        ArrayList<Weather> weatherList = new ArrayList<>();
        String req = "SELECT * FROM View_TEMPERER WHERE ID_VILLE=?";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, city.getIdCity());
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Month month = new Month(rs.getInt("ID_MOIS"), rs.getString("NOM_MOIS"));
                float avgTemp = rs.getFloat("TEMPERATURE_MOYENNE");
                int avgHumidity = rs.getInt("HUMIDITE_MOYENNE");
                float avgPrecipitation = rs.getFloat("PRECIPITATION_MOYENNE");
                weatherList.add(new Weather(city, month, avgTemp, avgHumidity, avgPrecipitation));
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return weatherList;
    }

    @Override
    public boolean update(ClimateType object) {
        return false;
    }

    public boolean updateWeather(Weather weather) {
        String req = "{call ps_insertOrUpdateTemperer(?,?,?,?,?)}";

        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, weather.getCity().getIdCity());
            stm.setInt(2, weather.getMonth().getIdMonth());
            stm.setFloat(3, weather.getAvgTemp());
            stm.setInt(4, weather.getAvgHumidity());
            stm.setFloat(5, weather.getAvgPrecipitation());

            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(ClimateType object) {
        return false;
    }

    public boolean postWeather(Weather weather) {
        String req = "INSERT INTO TEMPERER (ID_MOIS, ID_VILLE, TEMPERATURE_MOYENNE, HUMIDITE_MOYENNE, PRECIPITATION_MOYENNE) VALUES (?,?,?,?,?)";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, weather.getMonth().getIdMonth());
            stm.setInt(2, weather.getCity().getIdCity());
            stm.setFloat(3, weather.getAvgTemp());
            stm.setInt(4, weather.getAvgHumidity());
            stm.setFloat(5, weather.getAvgPrecipitation());

            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ClimateType object) {
        return false;
    }
}
