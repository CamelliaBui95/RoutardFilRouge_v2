package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.Continent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentDAO extends DAO<Continent, Continent>{
    @Override
    public ArrayList<Continent> getAll() {
        ArrayList<Continent> continents = new ArrayList<>();
        String rq = "SELECT * FROM CONTINENT";
        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(rq);
            while(rs.next())
                continents.add(new Continent(rs.getString("CODE_CONTINENT"), rs.getString("NOM_CONTINENT")));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return continents;
    }

    @Override
    public ArrayList<Continent> getLike(Continent searchObject) {
        return null;
    }

    @Override
    public boolean update(Continent object) {
        return false;
    }

    @Override
    public boolean post(Continent continent) {
        String req = "{call ps_insertContinent(?,?)}";
        try(PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setString(1, continent.getContinentCode());
            stm.setString(2, continent.getName());
            stm.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Continent object) {
        return false;
    }
}
