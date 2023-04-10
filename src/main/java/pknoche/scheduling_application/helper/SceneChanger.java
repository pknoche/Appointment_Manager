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

public class SceneChanger {
    /**
     * Associates menu name with resource path of FXML document pertaining to menu.
     * @param menuName menu name to be located
     * @return resource path of specified menu's FXML document
     */
    public static String resourceLocator(String menuName) {
        Map<String, String> resourcePath = new HashMap<String, String>();
        resourcePath.put("LoginMenu", "/pknoche/scheduling_application/GUI/LoginMenu.fxml");
        resourcePath.put("MainMenu", "/pknoche/scheduling_application/GUI/MainMenu.fxml");
        return resourcePath.get(menuName);
    }

    /**
     * Gets stage and sets scene for specified menu to be navigated to.
     * @param menuName name of menu to be loaded
     * @param actionEvent button pressed
     * @return stage so that it can be used by the calling method to set stage title to appropriate value
     * @throws IOException
     */
    public static Stage menuNavigator(String menuName, ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(SceneChanger.class.getResource(resourceLocator(menuName)));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
