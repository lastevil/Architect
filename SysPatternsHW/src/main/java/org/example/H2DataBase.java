package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DataBase {
    private static Connection conn;
    private static Statement statement;

    private H2DataBase() {
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            statement = conn.createStatement();
            createTableOfUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static H2DataBase connect() {
        H2DataBase dataBase = new H2DataBase();
        return dataBase;
    }

    public Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        throw new IllegalStateException("Connection not exist!!!");
    }

    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createTableOfUsers() throws SQLException {
        statement.executeUpdate("" +
                "CREATE TABLE IF not exists users" +
                "(id integer not null primary key unique," +
                "login text not null unique," +
                "password text not null" +
                ");"
        );
    }
}
