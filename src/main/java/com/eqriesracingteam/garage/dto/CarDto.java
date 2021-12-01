package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;

public class CarDto {
    //Attributes
    public String licensePlate;
    public String registrationPapers;
    public Customer customer;

    //Constructor
    public static CarDto fromCar(Car car) {
        var dto = new CarDto();

        dto.licensePlate = car.getLicensePlate();
        dto.registrationPapers = car.getRegistrationPapers();
        dto.customer = car.getOwner();

        return dto;
    }
}
