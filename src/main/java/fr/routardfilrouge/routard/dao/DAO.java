package fr.routardfilrouge.routard.dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T, TSearch> {
    protected Connection connection;

    public DAO() {
        try {
            this.connection = RoutardConnect.getInstance();
        } catch(Exception e) {
            //e.printStackTrace();
        }
    }
    public abstract ArrayList<T> getAll();

    public abstract ArrayList<T> getLike(TSearch searchObject);
    public abstract boolean update(T object);
    public abstract boolean post(T object);
    public abstract boolean delete(T object);
}
