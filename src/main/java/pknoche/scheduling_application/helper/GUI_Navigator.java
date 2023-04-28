package pknoche.scheduling_application.helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pknoche.scheduling_application.controller.AddModifyAppointmentController;
import pknoche.scheduling_application.controller.AddModifyCustomerController;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Contains classes for navigating between UI elements.
 */
public class GUI_Navigator {
    /**
     * Associates the menu name with the resource path of the FXML document pertaining to the menu.
     *
     * @param menuName menu name to be located
     * @return resource path of specified menu's FXML document
     */
    public static String resourceLocator(String menuName) {
        Map<String, String> resourcePath = new HashMap<>();
        resourcePath.put("LoginMenu", "/pknoche/scheduling_application/GUI/LoginMenu.fxml");
        resourcePath.put("MainMenu", "/pknoche/scheduling_application/GUI/MainMenu.fxml");
        resourcePath.put("AddModifyAppointment", "/pknoche/scheduling_application/GUI/AddModifyAppointment.fxml");
        resourcePath.put("AddModifyCustomer", "/pknoche/scheduling_application/GUI/AddModifyCustomer.fxml");
        return resourcePath.get(menuName);
    }

    /**
     * Gets stage and sets scene for specified menu to be navigated to.
     *
     * @param menuName name of menu to be loaded
     * @param stageName name to set stage to
     * @param event button pressed
     */
    public static void replaceScene(String menuName, String stageName, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(GUI_Navigator.class.getResource(resourceLocator(menuName))));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and displays a new stage containing the specified menu.
     *
     * @param menuName menu name to be loaded
     * @param stageName name to set stage to
     */
    public static void newStage(String menuName, String stageName) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(GUI_Navigator.class.getResource(resourceLocator(menuName))));
            Scene scene = new Scene(root);
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and displays a new stage and passes data to the associated controller.
     *
     * @param menuName menu name to be loaded
     * @param stageName name to set stage to
     * @param object object to be passed to the controller
     */
    public static void newStage(String menuName, String stageName, Object object) {
        try {
            FXMLLoader loader = new FXMLLoader(GUI_Navigator.class.getResource(resourceLocator(menuName)));
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            if(menuName.equals("AddModifyAppointment")) {
                AddModifyAppointmentController controller = loader.getController();
                controller.modify((Appointment) object);
            } else if(menuName.equals("AddModifyCustomer")) {
                AddModifyCustomerController controller = loader.getController();
                controller.modify((Customer) object);
            }
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the specified stage.
     *
     * @param event button pressed
     */
    public static void closeStage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}