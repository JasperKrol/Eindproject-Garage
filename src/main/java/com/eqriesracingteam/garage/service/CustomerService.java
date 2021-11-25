package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    //talks to Repository layer
    @Autowired
    private CustomerRepository customerRepository;

    public Long addCustomer(Customer customer) {
        String postalCode = customer.getPostalCode();
        List<Customer> customers = (List<Customer>) customerRepository.findAllByPostalCode(postalCode);

        if (customers.size() > 0 && customer.getLastName().equalsIgnoreCase(customer.getLastName())) {
            throw new BadRequestException("Customer allready excists");
        }
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getId();
    }

    public Iterable<Customer> getAllCustomers(String lastName) {
        if (lastName.isEmpty()) {
            return customerRepository.findAll();
        } else {
            return customerRepository.findAllByLastName(lastName);
        }
    }

    // TODO: 25-11-2021 customer by lastname vinden 
    public Customer getCustomer(String lastName) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByLastNameContainingIgnoreCase(lastName);
        //        Optional<Customer> optionalCustomer = customerRepository.findCustomerByLastNameContainingIgnoreCase(lastName);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }

    public void updateCustomer(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            customer.setId(existingCustomer.getId());
            customerRepository.save(customer);
        }
        else {
            throw new RecordNotFoundException("Custormer ID does not exists");
        }
    }

    // TODO: 25-11-2021 werkt nog niet helemaal goed
    public void partialUpdateCustomer(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        //Add conditions
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = customerRepository.findById(id).orElse(null);

            if (customer.getFirstName() != null && !customer.getFirstName().isEmpty()) {
                existingCustomer.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null && !customer.getLastName().isEmpty()){
                existingCustomer.setLastName(customer.getLastName());
            }
            if (customer.getPostalCode() != null && !customer.getPostalCode().isEmpty()){
                existingCustomer.setPostalCode(customer.getPostalCode());

                //save new partial update
                customerRepository.save(existingCustomer);
            } else
                throw new RecordNotFoundException("Customer not found");
        }
    }
}
