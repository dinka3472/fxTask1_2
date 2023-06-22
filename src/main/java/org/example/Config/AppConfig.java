package org.example.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String CONFIG_FILE = "application.properties";
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USER_KEY = "db.user";
    private static final String DB_PASSWORD_KEY = "db.password";
    private static String driverClassName = "db.driver";

    private Properties properties;

    public AppConfig() {
        loadConfig();
    }

    private void loadConfig() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDbUrl() {
        return properties.getProperty(DB_URL_KEY);
    }

    public String getDbUser() {
        return properties.getProperty(DB_USER_KEY);
    }

    public String getDbPassword() {
        return properties.getProperty(DB_PASSWORD_KEY);
    }
    public String getDriverClassName() {
        return properties.getProperty(driverClassName);
    }
}