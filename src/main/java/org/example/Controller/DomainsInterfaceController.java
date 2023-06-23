package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Models.Domain;
import org.example.Models.PersonDto;

import java.time.LocalDate;
import java.util.ArrayList;

public class DomainsInterfaceController {
    @FXML
    public VBox rootVBox;
    @FXML
    private Label titleLabel;

    @FXML
    private TableView<Domain> tableView;

    @FXML
    private TableColumn<Domain, Integer> idColumn;

    @FXML
    private TableColumn<Domain, String> webNameColumn;

    @FXML
    private TableColumn<Domain, String> domainNameColumn;

    @FXML
    private TableColumn<Domain, String> ipColumn;

    @FXML
    private TableColumn<Domain, LocalDate> dateColumn;

    @FXML
    private TableColumn<Domain, String> countryRegisteredColumn;
    private String currentStyle = "/CSS/Style1.css";
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleStyleSwitchButtonAction(ActionEvent event) {
        String nextStyle = currentStyle.equals("/CSS/Style1.css") ? "/CSS/Style2.css" : "/CSS/Style1.css";
        rootVBox.getStylesheets().clear();
        rootVBox.getStylesheets().add(nextStyle);

        currentStyle = nextStyle;
    }

    @FXML
    private void handleWindowDrag(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            xOffset = event.getSceneX(); yOffset = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        }
    }

    public void showDomains(PersonDto person) {
        ObservableList<Domain> domains = FXCollections.observableList(new ArrayList<>(person.getDomains()));
        tableView.setItems(domains);
        titleLabel.setText("Домен клиента: " + person.getFirstNameLastName());


        tableView.getColumns().forEach(column -> {
            String title = column.getText();
            switch (title) {
                case "id":
                    column.setCellValueFactory(new PropertyValueFactory<>("id"));
                    break;
                case "webName":
                    column.setCellValueFactory(new PropertyValueFactory<>("webName"));
                    break;
                case "domainName":
                    column.setCellValueFactory(new PropertyValueFactory<>("domainName"));
                    break;
                case "ip":
                    column.setCellValueFactory(new PropertyValueFactory<>("ip"));
                    break;
                case "dateRegistered":
                    column.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));
                    break;
                case "countryRegistered":
                    column.setCellValueFactory(new PropertyValueFactory<>("countryRegistered"));
                    break;
                default:
                    break;
            }
        });
    }
}