package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Customer;

public class CustomerInputDto {
    //Attributen
    public String firstName;
    public String lastName;
    public String postalCode;

    public Customer toCustomer() {
        var customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPostalCode(postalCode);
        return customer;
    }
}
