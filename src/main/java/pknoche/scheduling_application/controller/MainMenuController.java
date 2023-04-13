package pknoche.scheduling_application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.database.CustomerDAO;
import pknoche.scheduling_application.database.LoginDAO;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Customer;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private TableColumn<Appointment, Integer> appointmentContactCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIdCol;
    @FXML
    private TableColumn<Appointment, String> appointmentDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> appointmentEndCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableColumn<Appointment, String> appointmentLocationCol;
    @FXML
    private TableColumn<Appointment, String> appointmentStartCol;
    @FXML
    private TableColumn<Appointment, String> appointmentTitleCol;
    @FXML
    private TableColumn<Appointment, String> appointmentTypeCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentUserIdCol;
    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn<Customer, String> customerAddressCol;
    @FXML
    private TableColumn<Customer, String> customerCreateDateCol;
    @FXML
    private TableColumn<Customer, String> customerCreatedByCol;
    @FXML
    private TableColumn<Customer, Integer> customerDivisionIdCol;
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    @FXML
    private TableColumn<Customer, String> customerLastUpdateCol;
    @FXML
    private TableColumn<Customer, String> customerLastUpdatedByCol;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> customerPhoneCol;
    @FXML
    private TableColumn<Customer, String> customerPostalCodeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Appointment appointment = new Appointment(999, "test", "test", "test", "test",
                LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2),
                LocalDateTime.now(), LoginDAO.getCurrentUser(), LocalDateTime.now(),
                LoginDAO.getCurrentUser(), 1, 1, 1);
        AppointmentDAO.create(appointment);
        System.out.println(appointment.getFormattedStart());*/


        // populate table view for appointments table
        appointmentsTable.setItems(AppointmentDAO.getAll());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("FormattedStart"));
        appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("FormattedEnd"));
        appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));

        //populate table view for customers table
        customersTable.setItems(CustomerDAO.getAll());
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        customerDivisionIdCol.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        customerCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("FormattedCreate_Date"));
        customerCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
        customerLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("FormattedLast_Update"));
        customerLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
    }
    @FXML
    void onCreateNewAppointmentButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyAppointment", "Create Appointment");

    }
    @FXML
    void onModifyAppointmentButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyAppointment", "Modify Appointment");

    }
    @FXML
    void onDeleteAppointmentButtonClick(ActionEvent event) {

    }

    @FXML
    void onCreateNewCustomerButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyCustomer", "Create Customer");
    }

    @FXML
    void onModifyCustomerButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyCustomer", "Modify Customer");
    }
    @FXML
    void onDeleteCustomerButtonClick(ActionEvent event) {

    }
}
