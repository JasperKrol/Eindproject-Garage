package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {GarageApplication.class})
@EnableConfigurationProperties
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindByLastName() {
        // given
        Customer customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");
        entityManager.persist(customer);
        entityManager.flush();

        // when
        Customer found = customerRepository.findByLastName("Einstein");

        // then
        String expected = "Einstein";
        String actual = found.getLastName();

        assertEquals(expected, actual);
    }

    @Test
    void testFindById() {

        // given
        Customer customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");
        entityManager.persist(customer);
        entityManager.flush();


        //when
        Optional<Customer> found = customerRepository.findById(3L);

        //then
        long expected = 3L ;
        long actual = found.get().getId() ;

        assertEquals(expected, actual);
    }

}