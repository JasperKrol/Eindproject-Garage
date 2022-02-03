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
import static org.junit.jupiter.api.Assertions.assertEquals;
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

//    @Test
//    void testDeleteCarByID() {
//
//        Car car1 = new Car();
//        car1.setId(1L);
//
//        Long carId = 1L;
//
//        carService.deleteCar(carId);
//
//        verify(carRepository, times(1)).deleteById(eq(carId));
//
//
//    }
}