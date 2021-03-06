package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.*;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.CarRepository;
import com.eqriesracingteam.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    //talks to Repository layer
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CarRepository carRepository, AppointmentRepository appointmentRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Customer addCustomer(Customer customer) {
        String postalCode = customer.getPostalCode();
        List<Customer> customers = (List<Customer>) customerRepository.findAllByPostalCode(postalCode);

        if (customers.size() > 0) {
            throw new BadRequestException("Customer already exists");
        } else {
            Customer newCustomer = customerRepository.save(customer);
            return newCustomer;
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getAllCustomersByLastName(String lastname) {
        return customerRepository.findAllByLastNameContainingIgnoreCase(lastname);
    }

    public Customer getCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public void deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }

    public void updateCustomer(long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            customer.setId(existingCustomer.getId());
            customer.setAppointments(existingCustomer.getAppointments());
            customer.setCars(existingCustomer.getCars());
            customer.setInvoices(existingCustomer.getInvoices());
            customerRepository.save(customer);
        } else {
            throw new RecordNotFoundException("Customer ID does not exists");
        }
    }

    public void partialUpdateCustomer(long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        //Add conditions
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = customerRepository.findById(id).orElse(null);

            if (customer.getFirstName() != null && !customer.getFirstName().isEmpty()) {
                existingCustomer.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null && !customer.getLastName().isEmpty()) {
                existingCustomer.setLastName(customer.getLastName());
            }
            if (customer.getPostalCode() != null && !customer.getPostalCode().isEmpty()) {
                existingCustomer.setPostalCode(customer.getPostalCode());
            }
            if (customer.getTelephoneNumber() != null && !customer.getTelephoneNumber().isEmpty()) {
                existingCustomer.setTelephoneNumber(customer.getTelephoneNumber());
            }
            if (customer.getAppointments() != null && !customer.getAppointments().isEmpty()) {
                existingCustomer.setAppointments(customer.getAppointments());
            }
            if (customer.getCars() != null && !customer.getCars().isEmpty()) {
                existingCustomer.setCars(customer.getCars());
            }
            customerRepository.save(existingCustomer);

        } else {
            throw new BadRequestException("ID does not exist");
        }
    }

    //Methods for getting and adding cars with relations
    public List<Car> getCustomerCars(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getCars();
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public void addCustomerCar(long id, Car car) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            List<Car> cars = customer.getCars();


            cars.add(car);
            car.setOwner(customer);
            carRepository.save(car);
            customerRepository.save(customer);
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public List<Appointment> getCustomerAppointments(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getAppointments();
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public void addAppointmentToCustomer(long id, Appointment appointment) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            List<Appointment> appointments = customer.getAppointments();

            appointment.setAppointmentStatus(AppointmentStatus.AFSPRAAK_GEPLAND);
            appointments.add(appointment);
            appointmentRepository.save(appointment);
            appointment.setCustomer(customer);
            customerRepository.save(customer);
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public List<Invoice> getCustomerInvoices(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getInvoices();
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }
}

