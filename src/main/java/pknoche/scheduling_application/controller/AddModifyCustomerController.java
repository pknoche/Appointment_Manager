package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

import java.time.LocalDateTime;

/**
 * Controller for the AddModifyCustomer GUI. Contains logic for creating, modifying, deleting,
 * and validating customers.
 */
public class AddModifyCustomerController {
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

    /**
     * Sets values in Country ComboBox.
     */
    @FXML
    private void initialize() {
        countryCombo.setItems(CountryDAO.getAll());
    }

    /**
     * Sets values in ComboBox for division ID after selecting country.
     *
     * @param event country selected in country ComboBox
     */
    @FXML
    private void onCountrySelection(ActionEvent event) {
        if(countryCombo.getValue() == null) { // to prevent ActionEvent created by setItems in countryCombo list during initialize from trying to set divisionIdCombo with null value
            return;
        }
        divisionIdCombo.setItems(FirstLevelDivisionDAO.getByCountry(countryCombo.getValue()));
        divisionIdCombo.setPromptText("");
        divisionIdCombo.setDisable(false);
    }

    /**
     * Prompts user to confirm cancellation. If OK is clicked, closes form and discards data.
     *
     * @param event cancel button clicked
     */
    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        if(DialogBox.generateConfirmationMessage("Are you sure you would like to cancel? " +
                "The information entered will be discarded.")) { // if OK is clicked, then close window
            GUI_Navigator.closeStage(event);
        }
    }

    /**
     * Saves customer if validation passes. If validation does not pass, displays error messages indicating why.
     *
     * @param event save button clicked
     */
    @FXML
    private void onSaveButtonClick(ActionEvent event) {
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
        if(countryCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Country selection cannot be blank.");
        }
        String postalCode = postalCodeField.getText();
        if(postalCode.isBlank()) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Postal Code field cannot be blank.");
        }
        if(divisionIdCombo.getValue() == null) {
            dataInvalid = true;
            DialogBox.generateErrorMessage("Division ID selection cannot be blank.");
        } else {
            divisionId = divisionIdCombo.getValue().getDivision_ID();
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

    /**
     * Sets fields to proper values on AddModifyCustomer screen when modifying a customer.
     *
     * @param customer customer selected when modify button was clicked on main menu
     */
     public void modify(Customer customer) {
        customerIdField.setText(String.valueOf(customer.getCustomer_ID()));
        customerNameField.setText(customer.getCustomer_Name());
        addressField.setText(customer.getAddress());
        countryCombo.setValue(CountryDAO.get(FirstLevelDivisionDAO
                .getCountryId(customer.getDivision_ID())));
        onCountrySelection(new ActionEvent());
        postalCodeField.setText(customer.getPostal_Code());
        divisionIdCombo.setValue(FirstLevelDivisionDAO.get(customer.getDivision_ID()));
        phoneField.setText(customer.getPhone());
    }
}
