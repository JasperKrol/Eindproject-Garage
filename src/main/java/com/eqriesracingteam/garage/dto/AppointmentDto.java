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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime appointmentDate;

    public AppointmentStatus appointmentStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime carPickupDate;

    public String description;

    @JsonIgnoreProperties("appointments")
    public Car car;

    @JsonIgnoreProperties({"appointments", "cars"})
    public Customer customer;

    public static AppointmentDto fromAppointment(Appointment appointment) {

        var dto = new AppointmentDto();

        dto.id = appointment.getId();
        dto.appointmentDate = appointment.getAppointmentDate();
        dto.appointmentStatus = appointment.getAppointmentStatus();
        dto.carPickupDate = appointment.getCarPickupDate();
        dto.description = appointment.getDescription();
        dto.car = appointment.getCarForAppointment();
        dto.customer = appointment.getCustomer();

        return dto;
    }
}
