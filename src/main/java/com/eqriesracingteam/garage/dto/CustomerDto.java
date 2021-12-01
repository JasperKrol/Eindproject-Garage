package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Customer;

public class CustomerDto {
    //Attributen
    public String firstName;
    public String lastName;
    public String postalCode;

    public static CustomerDto toCustomer(Customer customer) {
        var dto = new CustomerDto();

        dto.firstName = customer.getFirstName();
        dto.lastName = customer.getLastName();
        dto.postalCode = customer.getPostalCode();
        return dto;
    }
}
