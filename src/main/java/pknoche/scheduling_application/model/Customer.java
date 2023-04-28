package pknoche.scheduling_application.model;

import pknoche.scheduling_application.helper.TimeConversion;

import java.time.LocalDateTime;

/**
 * Object model for customers
 */
public class Customer {
    /**
     * ID of the customer.
     */
    private final int Customer_ID;
    /**
     * Name of the customer.
     */
    private final String Customer_Name;
    /**
     * Address of the customer.
     */
    private final String Address;
    /**
     * Postal code of the customer.
     */
    private final String Postal_Code;
    /**
     * Phone number of the customer.
     */
    private final String Phone;
    /**
     * Time/date that customer was created.
     */
    private final LocalDateTime Create_Date;
    /**
     * Username of the user who created the customer.
     */
    private final String Created_By;
    /**
     * Time/date of the last update to the customer's information.
     */
    private final LocalDateTime Last_Update;
    /**
     * Username of the user who last updated the customer.
     */
    private final String Last_Updated_By;
    /**
     * Division ID of the customer.
     */
    private final int Division_ID;

    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone,
                    LocalDateTime create_Date, String created_By, LocalDateTime last_Update, String last_Updated_By,
                    int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Division_ID = division_ID;
    }

    /**
     * @return customer ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * @return customer name
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     * @return customer address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @return customer postal code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * @return customer phone number
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @return customer create time/date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * @return formatted string of customer create time/date
     */
    public String getFormattedCreate_Date() {
        return TimeConversion.toFormattedString(getCreate_Date());
    }

    /**
     * @return customer created by username
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * @return customer last update time/date
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * @return formatted string of customer last update time/date
     */
    public String getFormattedLast_Update() {
        return TimeConversion.toFormattedString(getLast_Update());
    }

    /**
     * customer last updated by username
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * @return customer division ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * Displays customer as concatenation of customer ID and customer name
     * @return concatenation of customer ID and customer name
     */
    @Override
    public String toString() {
        return Customer_ID + " - " + Customer_Name;
    }
}
