package pknoche.scheduling_application.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseLogin {

    public static boolean isLoginValid(String username, String password) {
        try {
            String sql = "SELECT * FROM client_schedule.users WHERE User_Name = \"" + username + "\"" +" AND Password = \"" + password + "\"";
            
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { // if there is an item in the ResultSet, then the username and password were valid
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
