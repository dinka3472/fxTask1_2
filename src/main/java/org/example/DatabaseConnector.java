package org.example;

import org.example.Config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static AppConfig appConfig;

    static {
        appConfig = new AppConfig();
    }

    public static Connection getConnection() throws SQLException {
        String dbUrl = appConfig.getDbUrl();
        String dbUser = appConfig.getDbUser();
        String dbPassword = appConfig.getDbPassword();
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
