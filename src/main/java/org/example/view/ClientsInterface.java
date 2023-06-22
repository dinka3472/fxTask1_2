package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.example.Models.PersonDto;
import org.example.Service.PersonService;

import java.util.Set;

public class ClientsInterface extends VBox {
    private TableView<PersonDto> tableView;
    private PersonService personService;

    public ClientsInterface() {
        initUI();
        loadTableData();
        setDoubleClickHandler();
    }

    private void initUI() {
        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.CENTER);

        tableView = new TableView<>();

        TableColumn<PersonDto, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PersonDto, String> jobColumn = new TableColumn<>("jobTitle");
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));

        TableColumn<PersonDto, String> nameColumn = new TableColumn<>("firstNameLastName");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameLastName"));

        TableColumn<PersonDto, String> phoneColumn = new TableColumn<>("phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<PersonDto, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<PersonDto, Integer> countDomainColumn = new TableColumn<>("countDomain");
        countDomainColumn.setCellValueFactory(new PropertyValueFactory<>("countDomain"));

        ObservableList<PersonDto> data = FXCollections.observableArrayList();

        tableView.setItems(data);
        tableView.getColumns().addAll(idColumn, nameColumn, jobColumn, phoneColumn, emailColumn, countDomainColumn);
        tableView.setPrefSize(700, 500);
        tableView.getSortOrder().add(idColumn);
        getChildren().add(tableView);
    }

    private void loadTableData() {
       personService = new PersonService();
        Set<PersonDto> persons = personService.getAllPersons();
        tableView.getItems().addAll(persons);
    }

    private void setDoubleClickHandler() {

        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                PersonDto selectedPerson = tableView.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                    DomainsInterface domainsInterface = new DomainsInterface();
                    domainsInterface.showDomains(selectedPerson);
                    domainsInterface.show();
                }
            }
        });
    }
}
