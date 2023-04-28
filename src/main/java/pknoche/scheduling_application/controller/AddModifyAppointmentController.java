package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.database.ContactDAO;
import pknoche.scheduling_application.database.CustomerDAO;
import pknoche.scheduling_application.database.UserDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.helper.TimeConversion;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Contact;
import pknoche.scheduling_application.model.Customer;
import pknoche.scheduling_application.model.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Controller for the AddModifyAppointment GUI. Contains logic for creating, modifying, deleting,
 * and validating appointments.
 */
public class AddModifyAppointmentController {
    @FXML
    private TextField appointmentIdField;
    @FXML
    private ComboBox<Contact> contactCombo;
    @FXML
    private ComboBox<Customer> customerCombo;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox<LocalTime> endTimeCombo;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox<LocalTime> startTimeCombo;
    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private ComboBox<User> userCombo;

    /**
     * Sets values of ComboBoxes before launching AddModifyAppointment GUI.
     */
    @FXML
    private void initialize() {
        contactCombo.setItems(ContactDAO.getAll());
        customerCombo.setItems(CustomerDAO.getAll());
        typeCombo.setItems(Appointment.getAppointmentTypes());
        userCombo.setItems(UserDAO.getAllUsers());

        // populate start and end time combo boxes in 15 minute increments during business hours
        LocalTime start = TimeConversion.LocalOpenTime(); // opening time in user's local time zone
        LocalTime end = TimeConversion.LocalCloseTime(); // closing time in user's local time zone
        while(start.isBefore(end)) {
            startTimeCombo.getItems().add(start);
            int timeInterval = 15;
            endTimeCombo.getItems().add(start.plusMinutes(timeInterval));
            start = start.plusMinutes(timeInterval);
        }
    }

    /**
     * Prompts user to confirm cancellation. If OK is clicked, closes form and discards data.
     *
     * @param event cancel button clicked
     */
    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        if(DialogBox.generateConfirmationMessage("Are you sure you would like to cancel? The information entered will be discarded.")) { // if OK is clicked, then close window
            GUI_Navigator.closeStage(event);
        }
    }

    /**
     * Saves appointment if validation passes. If validation does not pass, displays error messages indicating why.
     *
     * @param event save button clicked
     */
    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        boolean newAppointment = appointmentIdField.getText().isBlank(); // determine if appointment is being created or modified based on value of appointmentId field
        boolean saveSuccess;
        boolean dataInvalid = false;
        int appointmentId;

        // declare and initialize variables to placeholder values before validating data
        String type = null;
        LocalDateTime start = null;
        LocalDateTime end = null;
        int customerId = -1;
        int userId = -1;
        int contactId = -1;

        // If new appointment is being created, set appointmentId to placeholder value so that object can be created.
        // In this case, database will automatically assign appointmentId upon insertion.
        // If appointment is being modified, appointmentId set to value from appointment being modified.
        if(newAppointment) {
            appointmentId = -1;
        } else {
            appointmentId = Integer.parseInt(appointmentIdField.getText());
        }

        // Validate data to ensure no fields are left blank and end time is after start time
        String title = titleField.getText();
        if(title.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Title field cannot be blank.");
        }
        String description = descriptionField.getText();
        if(description.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Description field cannot be blank.");
        }
        String location = locationField.getText();
        if(location.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Location field cannot be blank.");
        }
        if(contactCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Contact selection cannot be blank.");
        } else {
            contactId = contactCombo.getValue().getContact_ID();
        }
        if(typeCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Type selection cannot be blank.");
        } else {
            type = typeCombo.getValue();
        }
        if(dateField.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Date selection cannot be blank.");
        }
        if(startTimeCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Start selection cannot be blank.");
        } else if(dateField.getValue() != null) {
            start = LocalDateTime.of(dateField.getValue(), startTimeCombo.getValue());
            //if(start.isBefore(LocalDateTime.now())) {TODO - uncomment - commented out for testing purposes
            //    dataInvalid = true;
            //    DialogBox.generateErrorMessage("Appointment start time cannot be in the past.");
            //}
        }
        if(endTimeCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("End selection cannot be blank.");
        } else if (dateField.getValue() != null) {
            end = LocalDateTime.of(dateField.getValue(), endTimeCombo.getValue());
        }
        if(start != null && end != null) {
            if(end.isBefore(start)) {
                dataInvalid = true;
                DialogBox.generateErrorMessage("End time must be after start time.");
            }
        }
        if(customerCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Customer selection cannot be blank.");
        } else {
            customerId = customerCombo.getValue().getCustomer_ID();
        }
        if(userCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("User selection cannot be blank.");
        } else {
            userId = userCombo.getValue().getUser_ID();
        }

        if(dataInvalid) {
            return;
        }

        // If data passed validation checks, create new Appointment object, check for overlap, and send to database if no overlap.
        Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end,
                LocalDateTime.now(), UserDAO.getCurrentUser(), LocalDateTime.now(), UserDAO.getCurrentUser(),
                customerId, userId, contactId, null, null, null); // null arguments not needed for database insertion

        if(AppointmentDAO.appointmentOverlaps(appointment)) {
            DialogBox.generateErrorMessage("Appointment overlaps with an existing appointment " +
                    "already scheduled with the selected customer.");
            return;
        }

        if(newAppointment) {
            saveSuccess = AppointmentDAO.create(appointment);
        } else {
            saveSuccess = AppointmentDAO.update(appointment);
        }
        if(saveSuccess) {
            GUI_Navigator.closeStage(event);
            DialogBox.generateInformationMessage("Appointment saved.");
        } else {
            DialogBox.generateErrorMessage("Error saving appointment.");
        }
    }

    /**
     * Sets fields to proper values on AddModifyAppointment screen when modifying an appointment.
     *
     * @param appointment appointment selected when modify button was clicked on main menu
     */
    public void modify(Appointment appointment) {
        appointmentIdField.setText(String.valueOf(appointment.getAppointment_ID()));
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        contactCombo.setValue(ContactDAO.get(appointment.getContact_ID()));
        typeCombo.setValue(appointment.getType());
        dateField.setValue(appointment.getStart().toLocalDate());
        startTimeCombo.setValue(appointment.getStart().toLocalTime());
        endTimeCombo.setValue(appointment.getEnd().toLocalTime());
        customerCombo.setValue(CustomerDAO.get(appointment.getCustomer_ID()));
        userCombo.setValue(UserDAO.get(appointment.getUser_ID()));
    }
}