package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.Customer;

public class CustomerInputDto {
    //Attributen
    public long id;
    public String firstName;
    public String lastName;
    public String postalCode;
    public Appointment appointment;
    public String telephoneNumber;


    public Customer toCustomer() {
        var customer = new Customer();

        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPostalCode(postalCode);
        customer.setTelephoneNumber(telephoneNumber);
        return customer;
    }
}
