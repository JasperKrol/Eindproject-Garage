package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;

public class CarInputDto {
    //Attributes
    public long id;
    public String licensePlate;
    public String registrationPapers;
    public Customer customer;

    public Car toCar() {
        var car = new Car();

        car.setId(id);
        car.setLicensePlate(licensePlate);
        car.setRegistrationPapers(registrationPapers);
        car.setOwner(customer);
        return car;

    }
}
