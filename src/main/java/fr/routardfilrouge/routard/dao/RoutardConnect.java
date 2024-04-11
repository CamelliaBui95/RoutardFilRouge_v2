package fr.routardfilrouge.routard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class RoutardConnect {
    private static Connection connection;

    private RoutardConnect() {

    }

    public static Connection getInstance() throws Exception {
        if(connection == null) {
            try {
                String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=ROUTARD;encrypt=false";
                connection = DriverManager.getConnection(url, "dev", "routard123");
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        return connection;
    }

}
