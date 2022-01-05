package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.CarDto;
import com.eqriesracingteam.garage.dto.CarInputDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class CarController {

    //Attributes
    @Autowired
    private CarService carService;

    //Constructor
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //CRUD Requests
    //Post request
    @PostMapping(value = "/api/garage/cars")
    public CarDto addCar(@RequestBody CarInputDto dto) {
        var car = carService.addCar(dto.toCar());
        return CarDto.fromCar(car);
    }

    //Get
    // - Find all, with option on license plate
    @GetMapping(value = "/api/garage/cars")
    public List<CarDto> getAllCars(@RequestParam(name = "licenseplate", defaultValue = "") String licensePlate) {
        var dtos = new ArrayList<CarDto>();

        List<Car> carList;

        if (licensePlate != null) {
            carList = carService.getAllCarsByLicensePlate(licensePlate);
        } else if (licensePlate == null) {
            carList = carService.getAllCars();
        } else {
            throw new BadRequestException("Car with license plate not found");
        }

        for (Car car : carList) {
            dtos.add(CarDto.fromCar(car));
        }

        return dtos;
    }

    //Get one car
    @GetMapping(value = "/api/garage/cars/{id}")
    public CarDto getOneCar(@PathVariable("id") long id) {
        var car = carService.getOneCar(id);
        return CarDto.fromCar(car);
    }

    @GetMapping(value = "/api/garage/cars/{id}/inspections")
    public Collection<Inspection> getCarInspection(@PathVariable("id") long id) {
        return carService.getCarInspection(id);
    }

    //Delete
    @DeleteMapping(value = "/api/garage/cars/{id}")
    public void deleteCar(@PathVariable("id") long id) {
        carService.deleteCar(id);
    }

    //Updates
    //Total update
    @PutMapping(value = "/api/garage/cars/{id}")
    public CarDto updateCar(@PathVariable("id") long id, @RequestBody Car car) {
        carService.updateCar(id, car);
        return CarDto.fromCar(car);
    }

    //Partial update
    @PatchMapping(value = "/api/garage/cars/{id}")
    public CarDto partialUpdateCar(@PathVariable("id") long id, @RequestBody Car car) {
        carService.partialUpdateCar(id, car);
        return CarDto.fromCar(car);
    }
}
