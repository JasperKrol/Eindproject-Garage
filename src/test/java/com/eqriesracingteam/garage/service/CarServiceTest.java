package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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

    @BeforeEach
    void setUp() {

    }

    @Test
    void addCar() {
    }

    @Test
    void getallbylicscenplate() {
    }

    @Test
    void getAllCars() {
        Car car1 = new Car(1,"aa-bb-cc", "document", null);
        Car car2 = new Car(2,"xx-xx-xx", "document", null);

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        when(carRepository.findAll()).thenReturn(cars);

        carService.getAllCars();

        verify(carRepository, times(1)).findAll();

        assertThat(cars.size()).isEqualTo(2);
//        Mockito.when(carRepository.findAll())
//                .thenReturn(cars);

//        String licensePlate = "xx--xx-xx";
//        String expected = "xx-xx-xx";

//        var found = carService.getAllCars();
//
//        assertEquals(found, cars);
    }

    @Test
    void getOneCar() {
    }

    @Test
    void deleteCar() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void partialUpdateCar() {
    }
}