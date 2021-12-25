package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AppointmentInputDto {

    public long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date appointmentDate;

    public AppointmentStatus appointmentStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date carPickupDate;

    public Appointment toAppointment() {
        var appointment = new Appointment();

        appointment.setId(id);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setCarPickupDate(carPickupDate);

        return appointment;
    }
}
