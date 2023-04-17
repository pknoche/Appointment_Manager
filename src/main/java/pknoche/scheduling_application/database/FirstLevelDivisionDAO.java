package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.Country;
import pknoche.scheduling_application.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionDAO {
    public static ObservableList<FirstLevelDivision> getByCountry(Country country) {
        ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division_ID, Division, Country_ID from client_schedule.first_level_divisions " +
                    "WHERE Country_ID = ?";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, country.getCountry_ID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country_ID = rs.getInt("Country_ID");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(Division_ID, Division, Country_ID);
                firstLevelDivisions.add(firstLevelDivision);
            }
        } catch (SQLException e) {
            DialogBox.generateErrorMessage("Error retrieving First_Level_Division");
            System.out.println(e.getMessage());
        }
        return firstLevelDivisions;
    }
}