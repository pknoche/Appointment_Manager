package pknoche.scheduling_application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddModifyAppointmentController {
    @FXML
    private TextField appointmentIdField;
    @FXML
    private ComboBox<?> contactCombo;
    @FXML
    private ComboBox<?> customerIdCombo;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField endTimeField;
    @FXML
    private ComboBox<?> locationCombo;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<?> typeCombo;
    @FXML
    private ComboBox<?> userIdCombo;
}
