package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.repository.CustomerRepository;
import com.eqriesracingteam.garage.service.CustomerService;
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
    //Connect to service layer
    @Autowired
    private CustomerService customerService;

    //Constructor
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //CRUD Requests

    //Post
    @PostMapping(value = "/api/garage/customers")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        long newId = customerService.addCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    //Get
    // - Find all, with option on lastnames
    // TODO: 21-11-2021 perhaps on lastname query string?
    @GetMapping(value = "/api/garage/customers")
    public ResponseEntity<Object> getCustomers(@RequestParam(name = "lastName", defaultValue = "") String lastName) {
        // in de ok()komt de body te staan
        return ResponseEntity.ok(customerService.getAllCustomers(lastName));
    }

    // - get by ID
//    @GetMapping(value = "/api/garage/customers/{id}")
//    public ResponseEntity<Object> getCustomer(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(customerService.getCustomer(id));
//    }
}
