package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


//@WebMvcTest
//@ContextConfiguration(classes={GarageApplication.class})
//@EnableConfigurationProperties
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    Car car;

    @Test
    void getAllCars() {
        Car car1 = new Car();
        car1.setLicensePlate("aa-bb-cc");
        Car car2 = new Car();
        car2.setLicensePlate("dd-ee-ff");

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        when(carRepository.findAll()).thenReturn(cars);

        carService.getAllCars();

        verify(carRepository, times(1)).findAll();

        assertThat(cars.size()).isEqualTo(2);
        Mockito.when(carRepository.findAll()).thenReturn(cars);



        var found = carService.getAllCars();

        assertEquals(found, cars);
    }

    @Test
    void testGetCustomerByLicensePlateNotFound() {
        String licensePlate = "24-xz-ww";

        // Setup our mock repository
        Mockito
                .doReturn(null).when(carRepository)
                .findAllByLicensePlateContainingIgnoreCase(licensePlate);

        // Execute the service call
        List<Car> found  = carService.getAllCarsByLicensePlate(licensePlate);

        // Assert the response
        assertNull(found, "Widget should not be found");
    }

    @Test
    public void testGetCarByLicensePlate() {

        car = new Car();
        car.setLicensePlate("24-xz-zg");
        List<Car> carList = new ArrayList<>();
        carList.add(car);

        when(carRepository.findCarByLicensePlateIsContainingIgnoreCase(car.getLicensePlate()))
                .thenReturn(car);

        String licensePlate = "24-xz-zg";
        String expected =  "24-xz-zg";

        Car found = carRepository.findCarByLicensePlateIsContainingIgnoreCase(licensePlate);

        assertEquals(expected, found.getLicensePlate());

    }
}