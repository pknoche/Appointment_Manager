package pknoche.scheduling_application.model;

public class FirstLevelDivision {
    private final int Division_ID;
    private final String Division;
    private final int Country_ID;

    public FirstLevelDivision(int division_ID, String division, int country_ID) {
        Division_ID = division_ID;
        Division = division;
        Country_ID = country_ID;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public String getDivision() {
        return Division;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    @Override
    public String toString() {
        return Division_ID + "  -  " + Division;
    }
}
