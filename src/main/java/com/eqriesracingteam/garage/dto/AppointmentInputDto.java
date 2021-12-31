package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Date;

public class AppointmentInputDto {

    public long id;

    public LocalDateTime appointmentDate;

    public AppointmentStatus appointmentStatus;

    public LocalDateTime carPickupDate;

    public String description;

    public Car car;

    public Appointment toAppointment() {
        var appointment = new Appointment();

        appointment.setId(id);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDescription(description);
        appointment.setCarPickupDate(carPickupDate);

        return appointment;
    }
}
