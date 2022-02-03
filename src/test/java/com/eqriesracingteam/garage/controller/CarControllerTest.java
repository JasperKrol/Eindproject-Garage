package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.dto.CarDto;
import com.eqriesracingteam.garage.dto.CarInputDto;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.security.JwtUtil;
import com.eqriesracingteam.garage.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class)
@ContextConfiguration(classes = {GarageApplication.class})
@RunWith(SpringRunner.class)
class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CarService carService;

    Car car;

    @BeforeEach
    public void setup() {
        car = new Car();
        car.setId(1L);
        car.setLicensePlate("24-xz-zg");

    }

    @Test
    public void testEndpointGetAllCars() throws Exception {
        List<Car> allCars = Arrays.asList(car);

        given(carService.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/garage/cars")
                .with(user("Jasper").roles("ADMIN"))
                .content(asJsonString(car)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddCar() throws Exception {

        Mockito
                .when(carService.addCar(any(Car.class)))
                .thenReturn(car);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(CarDto.fromCar(car)));
        mvc.perform(post("/api/garage/cars")
                        .with(user("Jasper").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CarDto.fromCar(car))))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}