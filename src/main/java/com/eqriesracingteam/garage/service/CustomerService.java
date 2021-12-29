package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.CustomerDto;
import com.eqriesracingteam.garage.dto.CustomerInputDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import com.eqriesracingteam.garage.repository.CarRepository;
import com.eqriesracingteam.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    //talks to Repository layer
    private CustomerRepository customerRepository;
    private CarRepository carRepository;
    private AppointmentRepository appointmentRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CarRepository carRepository, AppointmentRepository appointmentRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.appointmentRepository = appointmentRepository;
    }

    // TODO: 25-11-2021 kijken of er een functie kan komen die checked op achternaam === postcode
    //@Pathvariable postalcode
    public Customer addCustomer(Customer customer) {
        String postalCode = customer.getPostalCode();
        List<Customer> customers = (List<Customer>) customerRepository.findAllByPostalCode(postalCode);

        if (customers.size() > 0) {
            throw new BadRequestException("Customer already exists");
        } else {
            return customerRepository.save(customer);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getAllCustomersByLastName(String lastname) {
        return customerRepository.findAllByLastNameContainingIgnoreCase(lastname);
    }


    // TODO: 25-11-2021 customer by lastname vinden
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
            }if (customer.getAppointments() != null && !customer.getAppointments().isEmpty()) {
                existingCustomer.setAppointments(customer.getAppointments());
            }if (customer.getCars() != null && !customer.getCars().isEmpty()) {
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

            carRepository.save(car);
            cars.add(car);
            car.setOwner(customer);
            customerRepository.save(customer);
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public List<Appointment> getCustomerAppointments(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            return customer.getAppointments();
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public void addAppointmentToCustomer(long id, Appointment appointment) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()){
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

    // TODO: 13-12-2021 needed for plan?

//        public void assingCarToCustomer(Long id, Long carId) {
//            var optionalCustomer = customerRepository.findById(id);
//            var optionalCar = carRepository.findById(carId);
//
//            if (optionalCustomer.isPresent() && optionalCar.isPresent()) {
//                var customer = optionalCustomer.get();
//                var car = optionalCar.get();
//
//                customer.setCars((List<Car>) car);
//                customerRepository.save(customer);
//            } else {
//                throw new RecordNotFoundException();
//            }
//        }
}
