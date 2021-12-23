package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentRepositoryTest {

    @MockBean
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
//       Appointment appointment = new Appointment();
//
//       appointment.setId(1);
//       appointment.setAppointmentDate(today);
//       appointment.setAppointmentStatus(AppointmentStatus.BETAALD);
//       appointment.setCarPickUpDate(null);
//
//        Customer customer = new Customer();
//        customer.setFirstName("jasper");
//        customer.setLastName("krol");
//        customer.setPostalCode("2343KJ");
//
//
//        appointment.setCustomer(customer);
//        Appointment savedAppointment = appointmentRepository.save(appointment);
    }
}