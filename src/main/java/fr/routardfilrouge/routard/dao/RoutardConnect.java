package fr.routardfilrouge.routard.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class RoutardConnect {
    private static Connection connection;

    private RoutardConnect() {

    }

    public static Connection getInstance() {
        if(connection == null) {
            try {
                String url = "jdbc:sqlserver://127.0.0.1:1555;databaseName=ROUTARD;encrypt=false";
                String user = "dev";
                String password = "routard123";
                connection = DriverManager.getConnection(url, user, password);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}
