package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.dto.AuthenticationRequest;
import com.eqriesracingteam.garage.model.User;
import com.eqriesracingteam.garage.service.UserAuthenticateService;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes ={GarageApplication.class})
@EnableConfigurationProperties
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void shouldReturn200() throws Exception {
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk());
//    }
}