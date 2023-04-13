package pknoche.scheduling_application.helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class GUI_Navigator {
    /**
     * Associates menu name with resource path of FXML document pertaining to menu.
     * @param menuName menu name to be located
     * @return resource path of specified menu's FXML document
     */
    public static String resourceLocator(String menuName) {
        Map<String, String> resourcePath = new HashMap<String, String>();
        resourcePath.put("LoginMenu", "/pknoche/scheduling_application/GUI/LoginMenu.fxml");
        resourcePath.put("MainMenu", "/pknoche/scheduling_application/GUI/MainMenu.fxml");
        resourcePath.put("AddModifyAppointment", "/pknoche/scheduling_application/GUI/AddModifyAppointment.fxml");
        resourcePath.put("AddModifyCustomer", "/pknoche/scheduling_application/GUI/AddModifyCustomer.fxml");
        return resourcePath.get(menuName);
    }

    /**
     * Gets stage and sets scene for specified menu to be navigated to.
     * @param menuName name of menu to be loaded
     * @param stageName name to set stage to
     * @param actionEvent button pressed
     */
    public static void replaceScene(String menuName, String stageName, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(GUI_Navigator.class.getResource(resourceLocator(menuName)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newStage(String menuName, String stageName) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(GUI_Navigator.class.getResource(resourceLocator(menuName)));
            Scene scene = new Scene(root);
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeStage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
