package pknoche.scheduling_application.model;

/**
 * Object model for first level divisions.
 */
public class FirstLevelDivision {
    /**
     * ID of the division.
     */
    private final int Division_ID;
    /**
     * Name of the division.
     */
    private final String Division;
    /**
     * ID of the country associated with the division.
     */
    private final int Country_ID;

    public FirstLevelDivision(int division_ID, String division, int country_ID) {
        Division_ID = division_ID;
        Division = division;
        Country_ID = country_ID;
    }

    /**
     * @return ID of the division
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * @return name of the division
     */
    public String getDivision() {
        return Division;
    }

    /**
     * @return ID of the country
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * Displays the first level division as a concatenation of the division ID and the division name.
     * @return concatenation of division ID and division name
     */
    @Override
    public String toString() {
        return Division_ID + "  -  " + Division;
    }
}
