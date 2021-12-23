package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@ContextConfiguration(classes={GarageApplication.class})
@EnableConfigurationProperties
class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
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
    void getAllCars() {
    }

    @Test
    void getAllCarsByLicensePlate() {
        car = new Car(1,"aa-bb-cc", "document", null);
        car = new Car(2,"xx-xx-xx", "document", null);

        List<Car> cars = new ArrayList<>();
        Mockito.when(carRepository.findAll());

        String licensePlate = "xx--xx-xx";
        String expected = "xx-xx-xx";

        assertEquals(expected, cars);
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