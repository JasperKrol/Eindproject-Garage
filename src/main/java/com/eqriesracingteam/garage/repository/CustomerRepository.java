package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Iterable<Customer> findAllByLastName(String lastname);
    Iterable<Customer> findAllByPostalCode(String postalCode);
    Optional<Customer> findCustomerByLastNameContainingIgnoreCase(String lastname);
//    Optional<Customer> findCustomerPostalCodeContainingIgnoreCase(String postalCode);

}
