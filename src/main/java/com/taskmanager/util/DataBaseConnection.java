package com.taskmanager.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/task_manager";
        private static final String USER = "postgres";
        private static final String PASSWORD = "1998";

        public static Connection getConnection() throws SQLException {
            try {
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver PostgreSQL não encontrado", e);
            }
        }
}
