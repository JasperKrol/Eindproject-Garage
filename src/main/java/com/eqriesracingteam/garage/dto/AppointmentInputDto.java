package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;

import java.util.Date;

public class AppointmentInputDto {

    public long id;
    public Date appointmentDate;
    public AppointmentStatus appointmentStatus;
    public Date carPickupDate;

    public Appointment toAppointment() {
        var appointment = new Appointment();

        appointment.setId(id);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentStatus(appointmentStatus);
        appointment.setCarPickupDate(carPickupDate);

        return appointment;
    }
}
