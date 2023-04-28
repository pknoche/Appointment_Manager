package pknoche.scheduling_application.reports;

/**
 * Object model for storing data needed to generate the Customers By Division report.
 */
public class CustomersByDivision {
    /**
     * Name of the division.
     */
    private final String division;
    /**
     * Number of customers associated with the division.
     */
    int customerCount;

    public CustomersByDivision(String division, int customerCount) {
        this.division = division;
        this.customerCount = customerCount;
    }

    /**
     * @return division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return number of customers associated with division
     */
    public int getCustomerCount() {
        return customerCount;
    }
}
