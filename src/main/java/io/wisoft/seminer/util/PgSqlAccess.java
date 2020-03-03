package io.wisoft.seminer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSqlAccess {
    private static Connection conn = null;

    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection setConnection() {
        String url = "";
        String username = "";
        String password = "";

        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
