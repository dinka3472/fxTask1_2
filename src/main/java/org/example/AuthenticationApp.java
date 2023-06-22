package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Config.AppConfig;
import org.example.view.PrimaryAuthenticationView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthenticationApp extends Application {
    private AppConfig appConfig;

    @Override
    public void start(Stage stage) {
        appConfig = new AppConfig();
        createDatabaseSchema();

        PrimaryAuthenticationView authenticationView = new PrimaryAuthenticationView();
        Scene scene = new Scene(authenticationView, 320, 240);
        stage.setTitle("Окно авторизации");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws Exception {
        clearDatabaseTables();
    }

    private void clearDatabaseTables() {
        try (Connection connection = DriverManager.getConnection(appConfig.getDbUrl(), appConfig.getDbUser(), appConfig.getDbPassword());
             Statement statement = connection.createStatement()) {
            executeScript(statement, "src/main/resources/after.sql");
            System.out.println("Скрипт успешно выполнен.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseSchema() {
        try (Connection connection = DriverManager.getConnection(appConfig.getDbUrl(), appConfig.getDbUser(), appConfig.getDbPassword());
             Statement statement = connection.createStatement()) {
             executeScript(statement, "src/main/resources/Scheme.sql");
             System.out.println("Скрипт успешно выполнен.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeScript(Statement statement, String scriptPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
            String line;
            StringBuilder scriptBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                scriptBuilder.append(line);
                scriptBuilder.append("\n");
            }
            String script = scriptBuilder.toString();
            String[] queries = script.split(";");

            for (String query : queries) {
                query = query.trim();
                if (!query.isEmpty()) {
                    statement.execute(query);
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}