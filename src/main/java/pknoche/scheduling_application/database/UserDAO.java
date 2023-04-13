package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDAO {
    public static ObservableList<User> getAllUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT User_ID, User_Name FROM client_schedule.users";
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
}
