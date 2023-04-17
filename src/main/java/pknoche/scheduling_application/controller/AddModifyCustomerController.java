package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.model.FirstLevelDivision;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyCustomerController implements Initializable {
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
    @FXML
    private ComboBox<?> countryCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        if(DialogBox.generateConfirmationMessage("Are you sure you would like to cancel? The information entered will be discarded.")) { // if OK is clicked, then close window
            GUI_Navigator.closeStage(event);
        }
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {

    }
}
