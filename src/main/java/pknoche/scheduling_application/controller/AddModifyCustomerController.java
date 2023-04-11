package pknoche.scheduling_application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddModifyCustomerController {
    @FXML
    private TextField addressField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private ComboBox<?> divisionIdCombo;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<?> postalCodeCombo;
}
