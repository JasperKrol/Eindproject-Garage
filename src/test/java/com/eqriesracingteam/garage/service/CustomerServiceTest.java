package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.repository.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest()
@ContextConfiguration(classes= {GarageApplication.class})
class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Test
    public void findCustomer(){
        Customer customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");

        when(customerRepository.findByLastName(customer.getLastName()))
                .thenReturn(customer);

    }

    @Test
    public void testAddCustomer(){

        customer = new Customer();
        customer.setFirstName("Puck");
        customer.setLastName("Pet");
        customer.setPostalCode("1234AA");
        customer.setId(20L);

        when(customerRepository.save(customer))
                .thenReturn(customer);

        Customer expect = customer;
        Customer found = customerService.addCustomer(customer);

        assertEquals(expect, found);
    }

    @Test
    public void testGetCustomerById(){

        Customer customer = new Customer();
        customer.setFirstName("Puck");
        customer.setLastName("Pet");
        customer.setPostalCode("1234AA");
        customer.setId(1L);

        when(customerRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        assertThat(customerService.getCustomer(1L)).isEqualTo(customer);

    }

    @Test
    void testGetCustomerByLastNameNotFound() {
        String name = "Koos";

        // Setup our mock repository
        Mockito
                .doReturn(null).when(customerRepository)
                .findByLastName(name);

        // Execute the service call
        List<Customer> found = customerService.getAllCustomersByLastName(name);


        // Assert the response
        assertNotEquals(found, "[]");
    }


}