package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryDAO {
    public static ObservableList<Country> getAll() {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country FROM client_schedule.countries";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Country_ID = rs.getInt("Country_ID");
                String Country = rs.getString("Country");
                Country country = new Country(Country_ID, Country);
                allCountries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Error getting list of countries.");
        }
        return allCountries;
    }
}
