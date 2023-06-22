package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Models.Domain;
import org.example.Models.PersonDto;

import java.time.LocalDate;
import java.util.ArrayList;

public class DomainsInterfaceController {
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