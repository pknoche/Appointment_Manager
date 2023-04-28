package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods for reading data associated with users and for validating user login information
 */
public class UserDAO {
    /**
     * Username of currently logged in user.
     */
    private static String currentUser;
    /**
     * List of all users from the database.
     */
    private static final ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * Generates and executes a SQL command to look up all users in the database and puts them in a list.
     *
     * @return observable list of users
     */
    public static ObservableList<User> getAllUsers() {
            allUsers.clear();
            String sql = "SELECT User_ID, User_Name FROM client_schedule.users;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int User_ID = rs.getInt("User_ID");
                    String User_Name = rs.getString("User_Name");
                    User user = new User(User_ID, User_Name);
                    allUsers.add(user);
                }
            } catch (SQLException e) {
                DialogBox.generateErrorMessage("Error retrieving users from database.");
                System.out.println(e.getMessage());
            }
        return allUsers;
    }

    /**
     * Checks if login information entered in login form is valid.
     *
     * @param username username entered in form
     * @param password password entered in form
     * @return true if login information is valid
     */
    public static boolean loginIsValid(String username, String password) {
            String sql = "SELECT User_Name, Password FROM client_schedule.users WHERE User_Name = ? AND Password = ?;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) { // if there is an item in the ResultSet, then the username and password were valid
                    currentUser = username;
                    return true;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        return false;
    }

    /**
     * Gets the username of the currently logged-in user
     *
     * @return username of current user
     */
    public static String getCurrentUser() {
        return currentUser;
    }

    /**
     * Looks up a user associated with a specific user ID.
     *
     * @param userId user ID to look up
     * @return User associated with user ID
     */
    public static User get(int userId) {
        for(User u : allUsers) {
            if(u.getUser_ID() == userId) {
                return u;
            }
        }
        return null;
    }
}