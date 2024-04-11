package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.security.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO extends DAO<User, User> {
    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public ArrayList<User> getLike(User user) {
        return null;
    }

    public User getByLogin(User user) {
        User persistedUser = new User();
        String query = "SELECT LOGIN, PASSWORD, ROLE FROM UTILISATEUR WHERE LOGIN = ?";

        try(PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, user.getUsername());

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                persistedUser.setUsername(rs.getString("LOGIN"));
                persistedUser.setPassword(rs.getString("PASSWORD"));
                persistedUser.setRole(rs.getString("ROLE"));
            }

            rs.close();
            return persistedUser;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public boolean post(User object) {
        return false;
    }

    @Override
    public boolean delete(User object) {
        return false;
    }
}
