package pknoche.scheduling_application.model;

/**
 * Object model for countries.
 */
public class Country {
    /**
     * ID of the country.
     */
    private final int Country_ID;
    /**
     * Name of the country.
     */
    private final String Country;

    public Country(int country_ID, String country) {
        Country_ID = country_ID;
        Country = country;
    }

    /**
     * @return ID of country
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * @return name of country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Displays Country as concatenation of country ID and country name.
     *
     * @return concatenation of country ID and country name
     */
    @Override
    public String toString() {
        return Country_ID + " - " + Country;
    }
}


