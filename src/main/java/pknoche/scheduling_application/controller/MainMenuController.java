package pknoche.scheduling_application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.model.Appointment;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, Integer> contactCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, Timestamp> endCol;
    @FXML
    private TableColumn<Appointment, Integer> locationCol;
    @FXML
    private TableColumn<Appointment, Timestamp> startCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Integer> userIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList(appointmentDAO.getAll());
        appointmentsTable.setItems(allAppointments);
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
    }
}
