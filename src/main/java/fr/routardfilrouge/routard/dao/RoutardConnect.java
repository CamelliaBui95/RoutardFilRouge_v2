package fr.routardfilrouge.routard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class RoutardConnect {
    private static Connection connection;
    private static String username;
    private static String password;

    private RoutardConnect() {

    }

    public static Connection getInstance() throws Exception {
        if(connection == null) {
            try {
                String url = "jdbc:sqlserver://127.0.0.1:1555;databaseName=ROUTARD;encrypt=false";
                connection = DriverManager.getConnection(url, username, password);
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        return connection;
    }

    public static void setAccount(HashMap<String, String> account) {
        username = account.get("username");
        password = account.get("password");
    }
}
