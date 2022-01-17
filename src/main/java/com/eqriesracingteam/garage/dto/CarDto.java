package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class CarDto {
    //Attributes
    public long id;
    public String licensePlate;

    //    @JsonIgnoreProperties({"appointments", "cars"})
//    @JsonSerialize
    public Customer owner;

    @JsonSerialize
    public RegistrationPaper registrationPaper;

//        @JsonIgnoreProperties("owner")
//    @JsonSerialize
    public List<Inspection> inspection;

    //Constructor
    public static CarDto fromCar(Car car) {

        var dto = new CarDto();

        dto.id = car.getId();
        dto.licensePlate = car.getLicensePlate();
        dto.registrationPaper = car.getRegistrationPapers();
        dto.owner = car.getOwner();

        return dto;
    }
}
