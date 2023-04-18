package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.database.CountryDAO;
import pknoche.scheduling_application.database.CustomerDAO;
import pknoche.scheduling_application.database.FirstLevelDivisionDAO;
import pknoche.scheduling_application.database.UserDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.model.Country;
import pknoche.scheduling_application.model.Customer;
import pknoche.scheduling_application.model.FirstLevelDivision;
import pknoche.scheduling_application.model.User;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddModifyCustomerController implements Initializable {
    @FXML
    private TextField addressField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private ComboBox<FirstLevelDivision> divisionIdCombo;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private ComboBox<Country> countryCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(CountryDAO.getAll());
    }

    @FXML
    void onCountrySelection(ActionEvent event) {
        if(countryCombo.getSelectionModel().isEmpty()) { // to prevent ActionEvent created by setItems in countryCombo list during initialize from trying to set divisionIdCombo with null value
            return;
        }
        divisionIdCombo.setItems(FirstLevelDivisionDAO.getByCountry(countryCombo.getSelectionModel().getSelectedItem()));
        divisionIdCombo.setPromptText("");
        divisionIdCombo.setDisable(false);
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        if(DialogBox.generateConfirmationMessage("Are you sure you would like to cancel? " +
                "The information entered will be discarded.")) { // if OK is clicked, then close window
            GUI_Navigator.closeStage(event);
        }
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
        boolean newCustomer = customerIdField.getText().isEmpty(); // determine if appointment is being created or modified based on value of appointmentId field
        boolean saveSuccess;
        boolean dataInvalid = false;
        int customerId;

        // declare and initialize variable to placeholder value before validating data
        int divisionId = -1;

        // If new contact is being created, set contactId to placeholder value so that object can be created.
        // In this case, database will automatically assign contactId upon insertion.
        // If contact is being modified, contactId set to value from contact being modified.
        if(newCustomer) {
            customerId = -1;
        } else {
            customerId = Integer.parseInt(customerIdField.getText());
        }

        // Validate data to ensure no fields are left blank
        String customerName = customerNameField.getText();
        if(customerName.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Customer Name field cannot be blank.");
        }
        String address = addressField.getText();
        if(address.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Address field cannot be blank.");
        }
        if(countryCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Country selection cannot be blank.");
        }
        String postalCode = postalCodeField.getText();
        if(postalCode.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Postal Code field cannot be blank.");
        }
        if(divisionIdCombo.getSelectionModel().isEmpty()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Division ID field cannot be blank.");
        } else {
            divisionId = divisionIdCombo.getSelectionModel().getSelectedItem().getDivision_ID();
        }
        String phone = phoneField.getText();
        if(phone.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Phone field cannot be blank.");
        }

        if(dataInvalid) {
            return;
        }

        // If data passed validation checks, create new Customer object and send to database.
        Customer customer = new Customer(customerId, customerName, address, postalCode, phone, LocalDateTime.now(),
                UserDAO.getCurrentUser(), LocalDateTime.now(), UserDAO.getCurrentUser(), divisionId);
        if(newCustomer) {
            saveSuccess = CustomerDAO.create(customer);
        } else {
            saveSuccess = CustomerDAO.update(customer);
        }
        if(saveSuccess) {
            GUI_Navigator.closeStage(event);
            DialogBox.generateInformationMessage("Customer saved.");
        } else {
            DialogBox.generateErrorMessage("Error saving customer.");
        }
    }

    public void modify(Customer customer) {
        customerIdField.setText(String.valueOf(customer.getCustomer_ID()));
        customerNameField.setText(customer.getCustomer_Name());
        addressField.setText(customer.getAddress());
        countryCombo.getSelectionModel().select(CountryDAO.get(FirstLevelDivisionDAO
                .getCountryId(customer.getDivision_ID())));
        onCountrySelection(new ActionEvent());
        postalCodeField.setText(customer.getPostal_Code());
        divisionIdCombo.getSelectionModel().select(FirstLevelDivisionDAO.get(customer.getDivision_ID()));
        phoneField.setText(customer.getPhone());
    }
}
