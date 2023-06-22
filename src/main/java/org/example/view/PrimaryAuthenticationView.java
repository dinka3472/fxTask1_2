package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.Service.UserService;


public class PrimaryAuthenticationView extends GridPane {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label resultLabel;

    public PrimaryAuthenticationView() {
        initUI();
        handleLoginButtonAction();
    }

    private void initUI() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        resultLabel = new Label();

        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        add(new Label("Username:"), 0, 0);
        add(usernameField, 1, 0);
        add(new Label("Password:"), 0, 1);
        add(passwordField, 1, 1);
        add(loginButton, 1, 2);
        add(resultLabel, 0, 3, 2, 1);

    }
    private void handleLoginButtonAction() {
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            UserService userService = new UserService();
            boolean authenticated = userService.authenticate(username, password);

            if (authenticated) {
                resultLabel.setText("Успешная авторизация");
                resultLabel.setTextFill(Color.GREEN);

                Stage clientsStage = new Stage();
                ClientsInterface clientsInterface = new ClientsInterface();

                Stage currentStage = (Stage) getScene().getWindow();
                currentStage.close();

                clientsStage.setScene(new Scene(clientsInterface));
                clientsStage.setTitle("Клиенты");
                clientsStage.show();
            } else {
                resultLabel.setText("Неверное имя пользователя или пароль\nПожалуйста, повторите ввод.");
                resultLabel.setTextFill(Color.RED);
            }
        });
    }
}
