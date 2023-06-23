package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.Config.AppConfig;

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
    public void start(Stage stage) throws IOException {
        appConfig = new AppConfig();
        createDatabaseSchema();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/PrimaryAuthenticationView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Окно авторизации");

        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/CSS/Style1.css");

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