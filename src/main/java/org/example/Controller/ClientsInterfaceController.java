package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.Models.PersonDto;
import org.example.Service.PersonService;

import java.io.IOException;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsInterfaceController implements Initializable {
    @FXML
    private TableView<PersonDto> tableView;
    @FXML
    private TableColumn<PersonDto, Integer> idColumn;
    @FXML
    private TableColumn<PersonDto, String> jobColumn;
    @FXML
    private TableColumn<PersonDto, String> nameColumn;
    @FXML
    private TableColumn<PersonDto, String> phoneColumn;
    @FXML
    private TableColumn<PersonDto, String> emailColumn;
    @FXML
    private TableColumn<PersonDto, Integer> countDomainColumn;
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PersonService personService = new PersonService();
        Set<PersonDto> persons = personService.getAllPersons();
        ObservableList<PersonDto> obsPersons = FXCollections.observableArrayList(persons);

        tableView.setItems(obsPersons);

        tableView.getColumns().forEach(column -> {
            String title = column.getText();
            switch (title) {
                case "id":
                    column.setCellValueFactory(new PropertyValueFactory<>("id"));
                    break;
                case "jobTitle":
                    column.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
                    break;
                case "firstNameLastName":
                    column.setCellValueFactory(new PropertyValueFactory<>("firstNameLastName"));
                    break;
                case "phone":
                    column.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    break;
                case "email":
                    column.setCellValueFactory(new PropertyValueFactory<>("email"));
                    break;
                case "countDomain":
                    column.setCellValueFactory(new PropertyValueFactory<>("countDomain"));
                    break;
                default:
                    break;
            }
        });
    }

    @FXML
    private void setDoubleClickHandler(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            PersonDto selectedPerson = tableView.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                openDomainsInterface(selectedPerson);
            }
        }
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleWindowDrag(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        }
    }

    private void openDomainsInterface(PersonDto selectedPerson) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/DomainsInterface.fxml"));
            Parent root = fxmlLoader.load();
            DomainsInterfaceController controller = fxmlLoader.getController();

            controller.showDomains(selectedPerson);
            Stage domainsStage = new Stage();

            domainsStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);

            domainsStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);

            domainsStage.setScene(scene);
            domainsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
