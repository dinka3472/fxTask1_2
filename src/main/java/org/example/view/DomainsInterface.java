package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Models.Domain;
import org.example.Models.PersonDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DomainsInterface extends Stage {
    private TableView<Domain> table;

    public void showDomains(PersonDto person) {
        setTitle("Домены");
        initModality(Modality.APPLICATION_MODAL);

        table = new TableView<>();
        TableColumn<Domain, Integer> idColumn = new TableColumn<>("id");
        TableColumn<Domain, String> webNameColumn = new TableColumn<>("webName");
        TableColumn<Domain, String> domainNameColumn = new TableColumn<>("domainName");
        TableColumn<Domain, String> ipColumn = new TableColumn<>("ip");
        TableColumn<Domain,LocalDate> dateColumn = new TableColumn<>("dateRegistered");
        TableColumn<Domain, String> countryRegisteredColumn = new TableColumn<>("countryRegistered");

        table.getColumns().addAll(idColumn, webNameColumn, domainNameColumn, ipColumn, dateColumn, countryRegisteredColumn);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        webNameColumn.setCellValueFactory(new PropertyValueFactory<>("webName"));
        domainNameColumn.setCellValueFactory(new PropertyValueFactory<>("domainName"));
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistered"));
        countryRegisteredColumn.setCellValueFactory(new PropertyValueFactory<>("countryRegistered"));

        ObservableList<Domain> domains = FXCollections.observableList(new ArrayList<>(person.getDomains()));
        table.setItems(domains);

        table.setPrefSize(500, 300);
        table.getSortOrder().add(idColumn);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new Label("Домен клиента: " + person.getFirstNameLastName()), table);

        Scene scene = new Scene(vbox);
        setScene(scene);

    }
}
