package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerDto {
    //Attributen
    public long id;
    public String firstName;
    public String lastName;
    public String postalCode;
    public List<CarDto> cars;
    public Appointment appointment;

    public static CustomerDto fromCustomer(Customer customer) {
        var dto = new CustomerDto();
        List<CarDto> carList = new ArrayList<>();

        dto.id = customer.getId();
        dto.firstName = customer.getFirstName();
        dto.lastName = customer.getLastName();
        dto.postalCode = customer.getPostalCode();
        for (Car car : customer.getCars()) {
            carList.add(CarDto.fromCar(car));
        }
        dto.cars = carList;
        dto.appointment = customer.getAppointment();
        return dto;
    }
}
