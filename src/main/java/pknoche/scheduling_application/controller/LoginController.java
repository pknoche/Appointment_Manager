package pknoche.scheduling_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pknoche.scheduling_application.database.UserDAO;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.helper.GUI_Navigator;
import pknoche.scheduling_application.helper.TimeConversion;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(UserDAO.loginIsValid(username, password)) {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("logs/login_history.txt", true)));
                printWriter.println(TimeConversion.toUTC(LocalDateTime.now()));
                printWriter.println("Username: " + username);
                printWriter.println("Login Successful");
                printWriter.println();
                printWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            GUI_Navigator.replaceScene("MainMenu", "Scheduling Application", event);
        }
        else {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("logs/login_history.txt", true)));
                printWriter.println(TimeConversion.toUTC(LocalDateTime.now()));
                printWriter.println("Username: " + username);
                printWriter.println("Login Failed");
                printWriter.println();
                printWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            DialogBox.generateErrorMessage("Invalid Username/Password. Please try again.");
        }
    }
}