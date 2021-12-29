package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class CarDto {
    //Attributes
    public long id;
    public String licensePlate;
    public String registrationPapers;
    public Customer customer;


    //Constructor
    public static CarDto fromCar(Car car) {
        var dto = new CarDto();

        dto.id = car.getId();
        dto.licensePlate = car.getLicensePlate();
        dto.registrationPapers = car.getRegistrationPapers();
        dto.customer = car.getOwner();


        return dto;
    }
}
