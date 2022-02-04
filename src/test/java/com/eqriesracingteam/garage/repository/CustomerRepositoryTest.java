package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
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

//    @Test
//    void testFindCustomerByPostalCode() {
//
//        // given
//        Customer customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");
//        entityManager.persist(customer);
//        entityManager.flush();
//
//        //when
//        Customer found = customerRepository.findAllByPostalCode("1111aa");
//
//        //then
//        String expected = "1111aa" ;
//        String actual = found.getPostalCode() ;
//
//        assertEquals(expected, actual);
//    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheCustomer() {
        Customer customer = new Customer("Albert", "Einstein", "1111aa", "0612312132");
        customerRepository.save(customer);
        customerRepository.deleteById(customer.getId());
        Optional optional = customerRepository.findById(customer.getId());
        assertEquals(Optional.empty(), optional);
    }

}