package pknoche.scheduling_application.model;

public class Country {
    private final int Country_ID;
    private final String Country;

    public Country(int country_ID, String country) {
        Country_ID = country_ID;
        Country = country;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public String getCountry() {
        return Country;
    }

    @Override
    public String toString() {
        return Country_ID + " - " + Country;
    }
}


