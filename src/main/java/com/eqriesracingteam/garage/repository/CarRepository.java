package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarByLicensePlateIsContainingIgnoreCase(String licensePlate);

    List<Car> findAllByLicensePlateContainingIgnoreCase(String licensePlate);
}
