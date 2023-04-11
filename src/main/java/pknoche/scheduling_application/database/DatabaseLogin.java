package pknoche.scheduling_application.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseLogin {
    private static String currentUser;

    public static boolean isLoginValid(String username, String password) {
        try {
            String sql = "SELECT * FROM client_schedule.users WHERE User_Name = ? AND Password = ?";
            
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println(ps);
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
}
