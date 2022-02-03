package com.eqriesracingteam.garage.controller;


import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.security.JwtUtil;
import com.eqriesracingteam.garage.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.eqriesracingteam.garage.controller.CarControllerTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes={GarageApplication.class})
@EnableConfigurationProperties
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomerService customerService;

    Customer customer;
    @BeforeEach
    public void setup() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLastName("krol");
        customer.setFirstName("jasper");

    }

    @Test
    public void testEndpointCustomers() throws Exception {

        List<Customer> allCustomers = Arrays.asList(customer);

        given(customerService.getAllCustomers()).willReturn(allCustomers);

        mvc.perform(get("/api/garage/customers")
                        .with(user("Jasper").roles("ADMIN"))
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}