package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.security.JwtUtil;
import com.eqriesracingteam.garage.service.CarService;
import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class)
@ContextConfiguration(classes={GarageApplication.class})
@EnableConfigurationProperties
class CarControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private JwtUtil jwtUtil;
//
//    @MockBean
//    private CarService carService;
//
//
//
//    @Test
//    public void testEndpointGetAllCars() throws Exception {
//        Car car = new Car();
//        car.setLicensePlate("24-xz-zg");
//        List<Car> allCars = Arrays.asList(car);
//
//        given(carService.getAllCars()).willReturn(allCars);
//
//        mvc.perform(get("/api/garage/cars")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].licensePlate", is("24-xz-zg")));
//    }
}