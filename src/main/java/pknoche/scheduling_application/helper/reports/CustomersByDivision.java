package pknoche.scheduling_application.helper.reports;

public class CustomersByDivision {
    private String division;
    int customerCount;

    public CustomersByDivision(String division, int customerCount) {
        this.division = division;
        this.customerCount = customerCount;
    }

    public String getDivision() {
        return division;
    }

    public int getCustomerCount() {
        return customerCount;
    }
}
