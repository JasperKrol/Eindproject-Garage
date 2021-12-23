package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;

import java.util.Date;

public class AppointmentDto {

    public long id;
    public Date appointmentDate;
    public AppointmentStatus appointmentStatus;
    public Date carPickupDate;

    //Customer en car toevoegen?
    //dto.customer = car.getOwner(); hieronder

    public static AppointmentDto fromAppointment(Appointment appointment) {
        var dto = new AppointmentDto();

        dto.id = appointment.getId();
        dto.appointmentDate = appointment.getAppointmentDate();
        dto.appointmentStatus = appointment.getAppointmentStatus();
        dto.carPickupDate = appointment.getCarPickupDate();

        return dto;
    }
}
