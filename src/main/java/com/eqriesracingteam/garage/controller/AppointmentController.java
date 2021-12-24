package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AppointmentDto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get requests
    //  get all
    @GetMapping(value = "/api/garage/appointments")
    public List<AppointmentDto> getAppointments() {
        var dtos = new ArrayList<AppointmentDto>();

        var appointments = appointmentService.getAllAppointments();

        for (Appointment appointment : appointments) {
            dtos.add(AppointmentDto.fromAppointment(appointment));
        }

        return dtos;
    }
}
