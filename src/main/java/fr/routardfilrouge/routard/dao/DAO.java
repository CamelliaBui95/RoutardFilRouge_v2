package fr.routardfilrouge.routard.dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T, TSearch> {
    protected Connection connection;

    public DAO() {
        this.connection = RoutardConnect.getInstance();
    }
    public abstract ArrayList<T> getAll();
}
