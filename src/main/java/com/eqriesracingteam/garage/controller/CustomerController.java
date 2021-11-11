package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    //Attribute
    private List<Customer> customers = new ArrayList<>();

    @Autowired
    private CustomerRepository customerRepository;

    //Constructor
//    public CustomerController () {
//        Customer klant1 = new Customer("jasper", "krol");
//        Customer klant2 = new Customer("Willeke", "Vossen");
//    }

    //CRUD Requests

    //Post
    @PostMapping(value = "/klanten")
    public ResponseEntity<Object> postCustomers(@RequestBody Customer customer){
        int newId = customers.size() -1;
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();
        customers.add(customer);
        return ResponseEntity.created(location).build();
    }

    //Get
        // - get all
    @GetMapping(value = "/klanten")
    public ResponseEntity<Object> getCustomers() {
        // in de ok()komt de body te staan
        return ResponseEntity.ok(customerRepository.findAll());
    }
        // - get by ID
    @GetMapping(value = "/klanten/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("id") int id){
        return ResponseEntity.ok(customerRepository.findById(id));
    }
}
