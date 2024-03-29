package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods for reading country information from the database.
 */
public class CountryDAO {
    /**
     * List of all countries from the database.
     */
    private static final ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * Generates and executes a SQL query to get all countries from the database and puts them in a list.
     *
     * @return observable list of all countries
     */
    public static ObservableList<Country> getAll() {
            allCountries.clear();
            String sql = "SELECT Country_ID, Country FROM client_schedule.countries;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int Country_ID = rs.getInt("Country_ID");
                    String Country = rs.getString("Country");
                    Country country = new Country(Country_ID, Country);
                    allCountries.add(country);
                }
            } catch (SQLException e) {
                System.out.println("Error getting list of countries from database.");
            }
        return allCountries;
    }

    /**
     * Gets a specific country by ID.
     *
     * @param countryId country ID of country to be found
     * @return country associated with specified country if found
     */
    public static Country get(int countryId) {
        for (Country c : allCountries) {
            if (c.getCountry_ID() == countryId) {
                return c;
            }
        }
        return null;
    }
}