package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.Service.UserService;

import java.io.IOException;

public class PrimaryAuthenticationController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label resultLabel;

    private final UserService userService;

    public PrimaryAuthenticationController() {
        userService = new UserService();
    }

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean authenticated = userService.authenticate(username, password);

        if (authenticated) {
            resultLabel.setText("Успешная авторизация");
            resultLabel.setTextFill(Color.GREEN);

            openClientsInterface();
        } else {
            resultLabel.setText("Неверное имя пользователя или пароль\nПожалуйста, повторите ввод.");
            resultLabel.setTextFill(Color.RED);
        }
    }

    private void openClientsInterface() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ClientsInterface.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage clientsStage = new Stage();

            clientsStage.setScene(scene);
            clientsStage.setTitle("Клиенты");
            clientsStage.show();

            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
