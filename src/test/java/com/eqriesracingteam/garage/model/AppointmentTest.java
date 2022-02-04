package com.eqriesracingteam.garage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private Appointment appointment;

    @Test
    void getAppointment() {

        Appointment newAppointment = new Appointment();
        newAppointment.setId(1l);
        newAppointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);

        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL,12,22,10);
        newAppointment.setAppointmentDate(date);

        LocalDateTime expected = LocalDateTime.of(2020, Month.APRIL,12,22,10);
        assertEquals(expected, newAppointment.getAppointmentDate());
    }
}