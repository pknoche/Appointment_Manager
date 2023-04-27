package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static String currentUser;
    private static final ObservableList<User> allUsers = FXCollections.observableArrayList();

    public static ObservableList<User> getAllUsers() {
        try {
            allUsers.clear();
            String sql = "SELECT User_ID, User_Name FROM client_schedule.users;";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int User_ID = rs.getInt("User_ID");
                String User_Name = rs.getString("User_Name");
                User user = new User(User_ID, User_Name);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users");
        }
        return allUsers;
    }

    public static boolean loginIsValid(String username, String password) {
        try {
            String sql = "SELECT User_Name, Password FROM client_schedule.users WHERE User_Name = ? AND Password = ?;";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { // if there is an item in the ResultSet, then the username and password were valid
                currentUser = username;
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static User get(int userId) {
        for(User u : allUsers) {
            if(u.getUser_ID() == userId) {
                return u;
            }
        }
        return null;
    }
}