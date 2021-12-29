package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.lang.Nullable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentDto {

    public long id;

    //    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date appointmentDate;

    public AppointmentStatus appointmentStatus;

    //    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date carPickupDate;

    @JsonIgnoreProperties("appointments")
    public CustomerDto customer;

    public CarDto car;

    // TODO: 28-12-2021 Car toevoegen

    public static AppointmentDto fromAppointment(Appointment appointment) {
        if (appointment == null)
            return null;
        var dto = new AppointmentDto();

        dto.id = appointment.getId();
        dto.appointmentDate = appointment.getAppointmentDate();
        dto.appointmentStatus = appointment.getAppointmentStatus();
        dto.carPickupDate = appointment.getCarPickupDate();

        dto.customer = CustomerDto.fromCustomer(appointment.getCustomer());
        return dto;
    }
}
