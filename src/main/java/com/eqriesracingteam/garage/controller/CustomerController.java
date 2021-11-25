package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


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
    public ResponseEntity<Object> getCustomers(@RequestParam(name = "lastname", defaultValue = "") String lastName) {
        // in de ok()komt de body te staan
        return ResponseEntity.ok(customerService.getAllCustomers(lastName));
    }

    // TODO: 21-11-2021 perhaps on postalCode query string?
    // - Find by last name
    @GetMapping(value = "/api/garage/customers/{lastname}")
    public ResponseEntity<Object> getCustomer(@RequestParam(name = "lastname", defaultValue = "") @PathVariable("lastname") String lastName) {
        return ResponseEntity.ok(customerService.getCustomer(lastName));
    }

    @DeleteMapping(value = "/api/garage/customers/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    //Update
    @PutMapping(value = "/api/garage/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.noContent().build();
    }

    //Partial update customer
    @PatchMapping(value = "/api/garage/customers/{id}")
    public ResponseEntity<Object> partialUpdateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customerService.partialUpdateCustomer(id, customer);
        return ResponseEntity.noContent().build();
    }
}
