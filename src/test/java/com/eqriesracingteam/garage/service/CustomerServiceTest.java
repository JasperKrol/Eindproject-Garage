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

@SpringBootTest()
@ContextConfiguration(classes= {GarageApplication.class})
class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Before
    public void setUp(){
        Customer customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");

        Mockito
                .when(customerRepository.findByLastName(customer.getLastName()))
                .thenReturn(customer);

    }

    @Test
    public void testAddCustomer(){

        Customer customer = new Customer();
        customer.setFirstName("Puck");
        customer.setLastName("Pet");
        customer.setPostalCode("1234AA");
        customer.setId(20L);

        Mockito
                .when(customerRepository.save(customer))
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

        Mockito
                .when(customerRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        assertThat(customerService.getCustomer(1L)).isEqualTo(customer);

    }

    @Test
    void testDeleteCustomerByID() {
//
//        int customerId = 1;
//
//        customerService.deleteCustomer(customerId);
//
//        verify(customerRepository, times(1)).deleteById(eq(customerId));


    }

    @Test
    void testCreateCustomer() {

    }
}