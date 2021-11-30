package com.eqriesracingteam.garage.controller;

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

    //CRUD Requests
    //Post request
    @PostMapping(value = "/api/garage/cars")
    public ResponseEntity<Object> addCar(@RequestBody Car car){
        long newId = carService.addCar(car);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    //Get requests
    //Get all cars
    @GetMapping(value = "/api/garage/cars")
    public ResponseEntity<Object> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }
    //Get one car
    @GetMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable("id") Long id){
        return ResponseEntity.ok(carService.getOneCar(id));
    }

    //Delete
    @DeleteMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    //Updates
    //Total update
    @PutMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") Long id,@RequestBody Car car){
        carService.updateCar(id, car);
        return ResponseEntity.noContent().build();
    }
    //Partial update
    @PatchMapping(value = "/api/garage/cars/{id}")
    public ResponseEntity<Object> partialUpdateCar(@PathVariable("id") Long id, @RequestBody Car car){
        carService.partialUpdateCar(id, car);
        return ResponseEntity.noContent().build();
    }
}
