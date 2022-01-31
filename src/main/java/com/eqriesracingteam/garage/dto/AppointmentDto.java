package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public class AppointmentDto {

    public long id;

    public LocalDateTime appointmentDate;

    public AppointmentStatus appointmentStatus;

    public LocalDateTime carPickupDate;

    public String description;

    @JsonIgnoreProperties("registrationPapers")
    public Car car;

    public Customer customer;

    public Repair repair;

    public static AppointmentDto fromAppointment(Appointment appointment) {

        var dto = new AppointmentDto();

        dto.id = appointment.getId();
        dto.appointmentDate = appointment.getAppointmentDate();
        dto.appointmentStatus = appointment.getAppointmentStatus();
        dto.carPickupDate = appointment.getCarPickupDate();
        dto.description = appointment.getDescription();

        if (appointment.getCarForAppointment() != null) {
            dto.car = appointment.getCarForAppointment();
        }
        if (appointment.getCustomer() != null) {
            dto.customer = appointment.getCustomer();
        }

        dto.repair = appointment.getRepair();

        return dto;
    }
}
