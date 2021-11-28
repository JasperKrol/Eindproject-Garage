package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarService {

    //Attributes
    @Autowired
    private CarRepository carRepository;

    //Constructor

    //Methods
    public Long addCar(Car car) {
        String licensePlate = car.getLicensePlate();
        List<Car> cars = (List<Car>) carRepository.findCarByLicensePlateIsContainingIgnoreCase(licensePlate);

        if (cars.size() >0) {
            throw new BadRequestException("Car with license plate already in system");
        }
        Car newCar = carRepository.save(car);
        return newCar.getId();
    }

    public Object getAllCustomers() {
        List<Car> carsList = new ArrayList<>();

        var cars = carRepository.findAll();

        for (Car car : cars) {
            carsList.add(car);
        }
        return cars;
    }
}
