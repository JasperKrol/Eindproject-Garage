package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.CarDto;
import com.eqriesracingteam.garage.dto.CarInputDto;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CarController {

    //Attributes
    @Autowired
    private CarService carService;

    //Constructor
    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    //CRUD Requests
    //Post request
    @PostMapping(value = "/api/garage/cars")
    public CarDto addCar(@RequestBody CarInputDto dto){
        var car = carService.addCar(dto.toCar());
        return CarDto.fromCar(car);
    }

    //Get requests
    @GetMapping(value = "/api/garage/cars")
    public ResponseEntity<Object> getAllCars(@RequestParam(name = "licenseplate", defaultValue="") String licensePlate) {
        return ResponseEntity.ok(carService.getAllCars(licensePlate));
    }

    //Get one car
    @GetMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable("id") long id) {
        return ResponseEntity.ok(carService.getOneCar(id));
    }

    //Delete
    @DeleteMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    //Updates
    //Total update
    @PutMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") long id, @RequestBody Car car) {
        carService.updateCar(id, car);
        return ResponseEntity.noContent().build();
    }

    //Partial update
    @PatchMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> partialUpdateCar(@PathVariable("id") long id, @RequestBody Car car) {
        carService.partialUpdateCar(id, car);
        return ResponseEntity.noContent().build();
    }
}
