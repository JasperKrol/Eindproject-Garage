package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.CustomerDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
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
    public List<CustomerDto> getCustomers(@RequestParam(name = "lastname", defaultValue = "") String lastName) {
        var dtos = new ArrayList<CustomerDto>();

        List<Customer> customers;

        if (lastName != null) {
            customers = customerService.getAllCustomersByLastName(lastName);
        } else if (lastName == null) {
            customers = customerService.getAllCustomers();
        } else {
            throw new BadRequestException();
        }

        for (Customer customer : customers) {
            dtos.add(CustomerDto.fromCustomer(customer));
        }

        return dtos;
    }

    // - Get one customer
    @GetMapping(value = "/api/garage/customers/{id}")
    public CustomerDto getCustomer(@PathVariable("id") long id) {
        var customer = customerService.getCustomer(id);
        return CustomerDto.fromCustomer(customer);
    }

    @DeleteMapping(value = "/api/garage/customers/{id}")
    public void deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
    }

    //Update
    @PutMapping(value = "/api/garage/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return CustomerDto.fromCustomer(customer);
    }

    //Partial update customer
    @PatchMapping(value = "/api/garage/customers/{id}")
    public ResponseEntity<Object> partialUpdateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        customerService.partialUpdateCustomer(id, customer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/api/garage/customers/{id}/cars")
    public ResponseEntity<Object> getCustomerCars(@PathVariable long id) {
        return ResponseEntity.ok(customerService.getCustomerCars(id));
    }

    @PostMapping(value = "/api/garage/customers/{id}/cars")
    public ResponseEntity<Object> addCustomerCar(@PathVariable long id, @RequestBody Car car) {
        customerService.addCustomerCar(id, car);
        return ResponseEntity.ok().build();
    }
}
