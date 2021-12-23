package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
