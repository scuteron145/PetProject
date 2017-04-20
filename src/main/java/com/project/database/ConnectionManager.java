package com.project.database;

import java.sql.*;

public class ConnectionManager {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:/database/users.s3db";

    public Connection getConnection() throws SQLException {
        Connection result = null;
        System.out.println("Connecting to database...");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        result = DriverManager.getConnection(DB_URL);
        return result;
    }
}