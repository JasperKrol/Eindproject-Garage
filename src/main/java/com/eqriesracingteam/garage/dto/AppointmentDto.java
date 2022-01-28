package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public static AppointmentDto fromAppointment(Appointment appointment) {

        var dto = new AppointmentDto();

        dto.id = appointment.getId();
        dto.appointmentDate = appointment.getAppointmentDate();
        dto.appointmentStatus = appointment.getAppointmentStatus();
        dto.carPickupDate = appointment.getCarPickupDate();
        dto.description = appointment.getDescription();


        // TODO: 28-1-2022 null execption eruit
        if (appointment.getCarForAppointment() != null){
            dto.car = appointment.getCarForAppointment();
        }
        if (appointment.getCustomer() != null){
            dto.customer = appointment.getCustomer();
        }

        return dto;
    }
}
