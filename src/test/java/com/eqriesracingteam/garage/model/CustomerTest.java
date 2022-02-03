package com.eqriesracingteam.garage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        this.customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");
    }

    @Test
    void testGetFullName() {
        String expectedName = "Einstein";
        String actualName = this.customer.getLastName();
        assertEquals(expectedName, actualName);
    }

}