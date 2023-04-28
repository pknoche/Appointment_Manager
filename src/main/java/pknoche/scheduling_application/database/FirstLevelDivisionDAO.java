package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.Country;
import pknoche.scheduling_application.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods for reading and modifying data associated with first level divisions in the database.
 */
public class FirstLevelDivisionDAO {
    /**
     * List of first level divisions associated with a specific country in the database.
     */
    private static final ObservableList<FirstLevelDivision> divisionsByCountry = FXCollections.observableArrayList();

    /**
     * Generates and executes a SQL command to read first level divisions from the database associated with
     * a specific country.
     *
     * @param country country to look up first level divisions for
     * @return observable list of first level divisions associated with specified country
     */
    public static ObservableList<FirstLevelDivision> getByCountry(Country country) {
            divisionsByCountry.clear();
            String sql = """
                    SELECT
                        Division_ID, Division, Country_ID
                    FROM
                        client_schedule.first_level_divisions
                    WHERE
                        Country_ID = ?;""";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setInt(1, country.getCountry_ID());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int Division_ID = rs.getInt("Division_ID");
                    String Division = rs.getString("Division");
                    int Country_ID = rs.getInt("Country_ID");
                    FirstLevelDivision firstLevelDivision = new FirstLevelDivision(Division_ID, Division, Country_ID);
                    divisionsByCountry.add(firstLevelDivision);
                }
            } catch (SQLException e) {
                DialogBox.generateErrorMessage("Error retrieving list of first level divisions from database.");
                System.out.println(e.getMessage());
            }
        return divisionsByCountry;
    }

    /**
     * Looks up a country ID given a division ID.
     * @param divisionId division ID to look up associated country with
     * @return country ID if a country corresponding to division ID is found; -1 if no match found
     */
    public static int getCountryId(int divisionId) {
            String sql = "SELECT Country_ID FROM client_schedule.first_level_divisions WHERE Division_ID = ?;";
            try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                ps.setInt(1, divisionId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("Country_ID");
                }
            } catch (SQLException e) {
                DialogBox.generateErrorMessage("Error retrieving country from database.");
                System.out.println(e.getMessage());
            }
        return -1;
    }

    /**
     * Looks up a first level division associated with a division ID
     *
     * @param divisionId division ID to look up
     * @return first level division associated with division ID
     */
    public static FirstLevelDivision get(int divisionId) {
        for(FirstLevelDivision f : divisionsByCountry) {
            if(f.getDivision_ID() == divisionId) {
                return f;
            }
        }
        return null;
    }
}