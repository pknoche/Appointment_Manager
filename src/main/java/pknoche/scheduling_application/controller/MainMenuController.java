package pknoche.scheduling_application.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.database.ContactDAO;
import pknoche.scheduling_application.database.CustomerDAO;
import pknoche.scheduling_application.database.reports.AppointmentsByMonthDAO;
import pknoche.scheduling_application.database.reports.CustomersByDivisionDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.helper.TimeConversion;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Contact;
import pknoche.scheduling_application.model.Customer;
import pknoche.scheduling_application.reports.AppointmentsByMonth;
import pknoche.scheduling_application.reports.ContactSchedule;
import pknoche.scheduling_application.reports.CustomersByDivision;

import java.time.LocalDateTime;

/**
 * Controller for the MainMenu GUI. Contains logic for populating tableviews, handling button clicks, and deleting
 * appointments and customers.
 */
public class MainMenuController {
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
    @FXML
    private TableView<AppointmentsByMonth> appointmentsByMonthTable;
    @FXML
    private TableView<Appointment> contactScheduleTable;
    @FXML
    private ComboBox<Contact> reportsContactsCombo;
    @FXML
    private TableView<CustomersByDivision> customersByDivisionTable;
    @FXML
    private TableColumn<Appointment, String> scheduleReportCustomerCol;
    @FXML
    private TableColumn<Appointment, String> scheduleReportDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> scheduleReportEndCol;
    @FXML
    private TableColumn<Appointment, Integer> scheduleReportIdCol;
    @FXML
    private TableColumn<Appointment, String> scheduleReportStartCol;
    @FXML
    private TableColumn<Appointment, String> scheduleReportTitleCol;
    @FXML
    private TableColumn<Appointment, String> scheduleReportTypeCol;
    @FXML
    private TableColumn<CustomersByDivision, Integer> divisionReportCustomerCol;
    @FXML
    private TableColumn<CustomersByDivision, String> divisionReportDivisionCol;
    @FXML
    private ComboBox<String> reportsYearCombo;
    @FXML
    private TableColumn<AppointmentsByMonth, Integer> appointmentsReportDebriefingCol;
    @FXML
    private TableColumn<AppointmentsByMonth, String> appointmentsReportMonthCol;
    @FXML
    private TableColumn<AppointmentsByMonth, Integer> appointmentsReportNewClientCol;
    @FXML
    private TableColumn<AppointmentsByMonth, Integer> appointmentsReportPlanningSessionCol;
    @FXML
    private TableColumn<AppointmentsByMonth, Integer> appointmentsReportStatusUpdateCol;


    /**
     * Sets TableViews for appointments, customers, and reports. Sets up event listener for allAppointments list
     * that is used to call a refresh method for the Appointment Types report. This method requires the value
     * of the year selected in the GUI as an argument, so it is called directly from the controller. Other reports
     * do not require arguments when refreshing and are therefore handled outside the controller.
     * <p>
     * This method includes a lambda expression which uses the ListChangeListener functional interface to implement logic to automatically
     * refresh the data in the report when an appointment is added, modified, or deleted.
     */
    @FXML
    private void initialize() {
        // populate table view for appointments table
        ObservableList<Appointment> allAppointments = AppointmentDAO.getAll();
        allAppointments.addListener((ListChangeListener<Appointment>) change -> {
            while(change.next()) {
                if (change.getList().isEmpty()) {
                    // If statement used to only execute the report refresh once when the list is updated.
                    // This works since the appointments list is cleared when an appointment is added,
                    // modified, or deleted before being rebuilt from the database.
                    AppointmentsByMonthDAO.refresh(Integer.parseInt(reportsYearCombo.getValue()));
                }
            }
        });
        appointmentsTable.setItems(allAppointments);
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

        // Appointment Types report
        for(int i = 0; i < 3; i ++) { // set year combo box list to value of last year, current year, and next year
            int lastYear = LocalDateTime.now().getYear() - 1;
            reportsYearCombo.getItems().add(String.valueOf(lastYear + i));
        }
        reportsYearCombo.getSelectionModel().select(1); // select current year as default
        appointmentsByMonthTable.setItems(AppointmentsByMonthDAO.getAll(LocalDateTime.now().getYear()));
        appointmentsReportMonthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        appointmentsReportNewClientCol.setCellValueFactory(new PropertyValueFactory<>("NewClient"));
        appointmentsReportPlanningSessionCol.setCellValueFactory(new PropertyValueFactory<>("PlanningSession"));
        appointmentsReportStatusUpdateCol.setCellValueFactory(new PropertyValueFactory<>("StatusUpdate"));
        appointmentsReportDebriefingCol.setCellValueFactory(new PropertyValueFactory<>("Debriefing"));

        // Contact Schedule report
        reportsContactsCombo.setItems(ContactDAO.getAll());
        scheduleReportIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        scheduleReportTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        scheduleReportTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        scheduleReportDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        scheduleReportStartCol.setCellValueFactory(new PropertyValueFactory<>("FormattedStart"));
        scheduleReportEndCol.setCellValueFactory(new PropertyValueFactory<>("FormattedEnd"));
        scheduleReportCustomerCol.setCellValueFactory(new PropertyValueFactory<>("Customer_IDAndName"));

        // Customers by Division report
        customersByDivisionTable.setItems(CustomersByDivisionDAO.getAll());
        divisionReportDivisionCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
        divisionReportCustomerCol.setCellValueFactory(new PropertyValueFactory<>("CustomerCount"));
    }

    /**
     * Sets appointments TableView to display all appointments regardless of start date.
     *
     * @param event All radio button clicked
     */
    @FXML
    private void onAllButtonClick(ActionEvent event) {
        appointmentsTable.setItems(AppointmentDAO.getAll());
    }

    /**
     * Sets appointments TableView to display appointments with a start date in the current week, starting on Sunday.
     * <p>
     * This method uses a lambda expression which uses the Predicate functional interface to set logic for filtering
     * the list of appointments to only include appointments with a start date in the current week.
     *
     * @param event Current Week radio button clicked
     */
    @FXML
    private void onCurrentWeekButtonClick(ActionEvent event) {
        FilteredList<Appointment> filteredAppointments = AppointmentDAO.getAll().filtered(appointment ->
                (appointment.getStart().toLocalDate().isEqual(TimeConversion.firstDayThisWeek()) ||
                        appointment.getStart().toLocalDate().isAfter(TimeConversion.firstDayThisWeek())) &&
                        appointment.getStart().toLocalDate().isBefore(TimeConversion.firstDayNextWeek()));
        appointmentsTable.setItems(filteredAppointments);
    }

    /**
     * Sets appointments TableView to display appointments with a start date in the current month, starting on the 1st.
     * <p>
     * This method uses a lambda expression which uses the Predicate functional interface to set logic for filtering
     * the list of appointments to only include appointments with a start date in the current month.
     *
     * @param event Current Month radio button clicked
     */
    @FXML
    private void onCurrentMonthButtonClick(ActionEvent event) {
        FilteredList<Appointment> filteredAppointments = AppointmentDAO.getAll().filtered(appointment ->
                (appointment.getStart().toLocalDate().isEqual(TimeConversion.firstDayThisMonth()) ||
                        appointment.getStart().toLocalDate().isAfter(TimeConversion.firstDayThisMonth())) &&
                        appointment.getStart().toLocalDate().isBefore(TimeConversion.firstDayNextMonth()));
        appointmentsTable.setItems(filteredAppointments);
    }

    /**
     * Opens the AddModifyAppointment GUI with blank fields for creating a new appointment.
     *
     * @param event Create New Appointment button clicked
     */
    @FXML
    private void onCreateNewAppointmentButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyAppointment", "Create Appointment");
    }

    /**
     * Opens the AddModifyAppointment GUI with fields pre-filled based on the values from the appointment selected.
     *
     * @param event Modify Appointment button clicked
     */
    @FXML
    private void onModifyAppointmentButtonClick(ActionEvent event) {
        Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if(appointment == null) {
            return;
        }
        GUI_Navigator.newStage("AddModifyAppointment", "Modify Appointment", appointment);
    }

    /**
     * Prompts the user for confirmation to delete the selected appointment. If OK is clicked, the appointment is
     * deleted and a confirmation message is displayed.
     *
     * @param event Delete Appointment button clicked
     */
    @FXML
    private void onDeleteAppointmentButtonClick(ActionEvent event) {
        if(appointmentsTable.getSelectionModel().getSelectedItem() != null) {
            if (DialogBox.generateConfirmationMessage("Are you sure you would like to delete this appointment? " +
                    "This action cannot be undone.")) {
                int appointmentId = appointmentsTable.getSelectionModel().getSelectedItem().getAppointment_ID();
                String appointmentType = appointmentsTable.getSelectionModel().getSelectedItem().getType();
                if (AppointmentDAO.delete(appointmentsTable.getSelectionModel().getSelectedItem())) {
                    DialogBox.generateInformationMessage(appointmentType + " appointment (Appointment ID #" +
                            appointmentId + ") deleted.");
                } else {
                    DialogBox.generateErrorMessage("Error deleting appointment.");
                }
            }
        }
    }

    /**
     * Opens the AddModifyCustomer GUI with blank fields for creating a new customer.
     *
     * @param event Create New Customer button clicked
     */
    @FXML
    private void onCreateNewCustomerButtonClick(ActionEvent event) {
        GUI_Navigator.newStage("AddModifyCustomer", "Create Customer");
    }

    /**
     * Opens the AddModifyCustomer GUI with fields pre-filled based on the values from the customer selected.
     *
     * @param event Modify Customer button clicked
     */
    @FXML
    private void onModifyCustomerButtonClick(ActionEvent event) {
        Customer customer = customersTable.getSelectionModel().getSelectedItem();
        if(customer == null) {
            return;
        }
        GUI_Navigator.newStage("AddModifyCustomer", "Modify Customer", customer);
    }

    /**
     * Prompts the user for confirmation to delete the selected customer. If OK is clicked, the customer is
     * deleted along with any appointments associated with that customer and a confirmation message is displayed.
     *
     * @param event Delete Customer button clicked
     */
    @FXML
    private void onDeleteCustomerButtonClick(ActionEvent event) {
        if(customersTable.getSelectionModel().getSelectedItem() != null) {
            if (DialogBox.generateConfirmationMessage("Are you sure you would like to delete this customer?" +
                    "Any associated appointments will also be deleted. This action cannot be undone.")) {
                if (AppointmentDAO.delete(customersTable.getSelectionModel().getSelectedItem().getCustomer_ID())) {
                    if (CustomerDAO.delete(customersTable.getSelectionModel().getSelectedItem())) {
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

    /**
     * Refreshes data in the table based on the value of the year selected.
     *
     * @param event year selected from ComboBox
     */
    @FXML
    private void onReportsYearSelection(ActionEvent event) {
        AppointmentsByMonthDAO.refresh(Integer.parseInt(reportsYearCombo.getValue()));
    }

    /**
     * Refreshes data in the table based on the contact selected.
     *
     * @param event contact selected from ComboBox
     */
    @FXML
    private void onReportsContactSelection(ActionEvent event) {
        contactScheduleTable.setItems(ContactSchedule.getContactSchedule(reportsContactsCombo.getValue()));
    }
}