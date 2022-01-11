package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.model.RegistrationPapers;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CarInputDto {
    //Attributes
    public long id;
    public String licensePlate;

    public Customer customer;
    public Appointment appointment;

    public Car toCar() {
        var car = new Car();

        car.setId(id);
        car.setLicensePlate(licensePlate);
        car.setOwner(customer);

        return car;

    }
}
