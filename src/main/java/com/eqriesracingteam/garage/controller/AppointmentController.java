package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AppointmentDto;

import com.eqriesracingteam.garage.dto.AppointmentInputDto;
import com.eqriesracingteam.garage.dto.CarInputDto;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    // TODO: 29-12-2021 data param @RequestParam(name = "date", defaultValue="") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date
    @GetMapping(value = "/api/garage/appointments")
    public List<AppointmentDto> getAppointments(@RequestParam (name = "status", required = false)AppointmentStatus status) {
        var dtos = new ArrayList<AppointmentDto>();

        var appointments = appointmentService.getAllAppointments(status);

        for (Appointment appointment : appointments) {
            dtos.add(AppointmentDto.fromAppointment(appointment));
        }

        return dtos;
    }

    // Get one
    @GetMapping(value = "/api/garage/appointments/{id}")
    public AppointmentDto getOneAppointment(@PathVariable("id") long id) {
        var appointment = appointmentService.getAppointment(id);
        return AppointmentDto.fromAppointment(appointment);
    }

    // Add appointment
    @PostMapping(value = "/api/garage/appointments")
    public AppointmentDto createAppointment(@RequestBody AppointmentInputDto dto) {
        var appointment = appointmentService.createAppointment(dto.toAppointment());
        return AppointmentDto.fromAppointment(appointment);
    }

    // Adjust appointment
    @PutMapping(value = "/api/garage/appointments/{id}")
    public AppointmentDto adjustAppointment(@PathVariable("id") long id, @RequestBody Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return AppointmentDto.fromAppointment(appointment);
    }

    // Delete appointment
    @DeleteMapping(value = "/api/garage/appointments/{id}")
    public void deleteAppointment(@PathVariable("id") long id) {
        appointmentService.deleteAppointment(id);
    }

    // Assign car to appointment
    @PutMapping(value = "/api/garage/appointments/{id}/car")
    public AppointmentDto assignCarToAppointment(@PathVariable("id") long id, @RequestBody CarInputDto input) {
        return AppointmentDto.fromAppointment(appointmentService.assignCarToAppointment(id, input.id));
    }
}
