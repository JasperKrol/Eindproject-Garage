package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
