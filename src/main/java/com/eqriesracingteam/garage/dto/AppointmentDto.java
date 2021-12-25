package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.util.Date;

public class AppointmentDto {

    public long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date appointmentDate;

    public AppointmentStatus appointmentStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
