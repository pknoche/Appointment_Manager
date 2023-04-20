package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.database.CustomerDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private TableColumn<Appointment, Integer> appointmentContactCol;
    @FXML
    private TableColumn<Appointment, String> appointmentCustomerCol;
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
    private TableColumn<Appointment, String> appointmentUserCol;
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
        // populate table view for appointments table
        appointmentsTable.setItems(AppointmentDAO.getAll());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_IDAndName"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("FormattedStart"));
        appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("FormattedEnd"));
        appointmentCustomerCol.setCellValueFactory(new PropertyValueFactory<>("Customer_IDAndName"));
        appointmentUserCol.setCellValueFactory(new PropertyValueFactory<>("User_IDAndName"));

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
        Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if(appointment == null) {
            return;
        }
        GUI_Navigator.newStage("AddModifyAppointment", "Modify Appointment", appointment);
    }

    @FXML
    void onDeleteAppointmentButtonClick(ActionEvent event) {
        if(appointmentsTable.getSelectionModel().getSelectedItem() == null) {
            return;
        } else if(DialogBox.generateConfirmationMessage("Are you sure you would like to delete this appointment? " +
                "This action cannot be undone.")) {
                    if(AppointmentDAO.delete(appointmentsTable.getSelectionModel().getSelectedItem())) {
                        DialogBox.generateInformationMessage("Appointment successfully deleted.");
                    } else {
                        DialogBox.generateErrorMessage("Error deleting appointment.");
                    }
        }
    }

    @FXML
    void onCreateNewCustomerButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyCustomer", "Create Customer");
    }

    @FXML
    void onModifyCustomerButtonClick(ActionEvent event) {
        Customer customer = customersTable.getSelectionModel().getSelectedItem();
        if(customer == null) {
            return;
        }
        GUI_Navigator.newStage("AddModifyCustomer", "Modify Customer", customer);
    }

    @FXML
    void onDeleteCustomerButtonClick(ActionEvent event) {
        if(customersTable.getSelectionModel().getSelectedItem() == null) {
            return;
        } else if(DialogBox.generateConfirmationMessage("Are you sure you would like to delete this customer?" +
                "Any associated appointments will also be deleted. This action cannot be undone.")) {
            if(AppointmentDAO.delete(customersTable.getSelectionModel().getSelectedItem().getCustomer_ID())) {
                if(CustomerDAO.delete(customersTable.getSelectionModel().getSelectedItem())) {
                    DialogBox.generateInformationMessage("Successfully deleted customer and " +
                            "all associated appointments.");
                } else {
                    DialogBox.generateErrorMessage("Successfully deleted customer's associated appointments," +
                            "but encountered an error when attempting to delete customer.");
                }
            } else {
                DialogBox.generateErrorMessage("Error deleting customer and associated appointments.");
            }
        }
    }
}