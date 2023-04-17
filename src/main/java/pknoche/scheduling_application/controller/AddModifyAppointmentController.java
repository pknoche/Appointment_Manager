package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddModifyAppointmentController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        if(DialogBox.generateConfirmationMessage("Are you sure you would like to cancel? The information entered will be discarded.")) { // if OK is clicked, then close window
            GUI_Navigator.closeStage(event);
        }
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
        boolean newAppointment = appointmentIdField.getText().isEmpty(); // determine if appointment is being created or modified based on value of appointmentId field
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
        if(title.isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Title field cannot be blank.");
        }
        String description = descriptionField.getText();
        if(description.isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Description field cannot be blank.");
        }
        String location = locationField.getText();
        if(location.isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Location field cannot be blank.");
        }
        if(contactCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Contact selection cannot be blank.");
        } else {
            contactId = contactCombo.getSelectionModel().getSelectedItem().getContact_ID();
        }
        if(typeCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Type selection cannot be blank.");
        } else {
            type = typeCombo.getSelectionModel().getSelectedItem();
        }
        if(dateField.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Date selection cannot be blank.");
        }
        if(startTimeCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Start selection cannot be blank.");
        } else if(dateField.getValue() != null) {
            start = LocalDateTime.of(dateField.getValue(), startTimeCombo.getValue());
        } else {
            start = LocalDateTime.of(LocalDate.now(), startTimeCombo.getValue());
        }
        if(endTimeCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("End selection cannot be blank.");
        } else if (dateField.getValue() != null) {
            end = LocalDateTime.of(dateField.getValue(), endTimeCombo.getValue());
        } else {
            end = LocalDateTime.of(LocalDate.now(), endTimeCombo.getValue());
        }
        if(start != null && end != null) {
            if(end.isBefore(start)) {
                dataInvalid = true;
                DialogBox.generateErrorMessage("End time must be after start time.");
            }
        }
        if(customerCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Customer selection cannot be blank.");
        } else {
            customerId = customerCombo.getSelectionModel().getSelectedItem().getCustomer_ID();
        }
        if(userCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("User selection cannot be blank.");
        } else {
            userId = userCombo.getSelectionModel().getSelectedItem().getUser_ID();
        }


        if(dataInvalid) {
            return;
        }

        // If data passed validation checks, create new Appointment object and send to database.
        Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end,
                LocalDateTime.now(), UserDAO.getCurrentUser(), LocalDateTime.now(), UserDAO.getCurrentUser(),
                customerId, userId, contactId, null, null, null); // null arguments not needed for database insertion
        if(newAppointment) {
            saveSuccess = AppointmentDAO.create(appointment);
        } else {
            saveSuccess = AppointmentDAO.update(appointment);
        }
        if(saveSuccess) {
            GUI_Navigator.closeStage(event);
            DialogBox.generateInformationMessage("Appointment saved.");
        }
    }

    public void modify(Appointment appointment) {
        appointmentIdField.setText(String.valueOf(appointment.getAppointment_ID()));
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        contactCombo.setValue(ContactDAO.get(appointment.getContact_ID()));
        typeCombo.getSelectionModel().select(appointment.getType());
        dateField.setValue(appointment.getStart().toLocalDate());
        startTimeCombo.setValue(appointment.getStart().toLocalTime());
        endTimeCombo.setValue(appointment.getEnd().toLocalTime());
        customerCombo.setValue(CustomerDAO.get(appointment.getCustomer_ID()));
        userCombo.setValue(UserDAO.get(appointment.getUser_ID()));
    }
}
