package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;


public class CustomerDto {
    //Attributen
    public long id;
    public String firstName;
    public String lastName;
    public String postalCode;

    //    @JsonIgnoreProperties("appointments")
//    @JsonSerialize
    public List<Car> cars;

    //    @JsonIgnoreProperties("customer")
//    @JsonSerialize
    public List<Appointment> appointments;

    public static CustomerDto fromCustomer(Customer customer) {
        var dto = new CustomerDto();

        dto.id = customer.getId();
        dto.firstName = customer.getFirstName();
        dto.lastName = customer.getLastName();
        dto.postalCode = customer.getPostalCode();
        dto.appointments = customer.getAppointments();
        dto.cars = customer.getCars();

        // TODO: 30-12-2021 if appointmentstatus is !afgerond customer.get) :: ""
        //        dto.appointments = customer.getAppointments();

        return dto;
    }
}
