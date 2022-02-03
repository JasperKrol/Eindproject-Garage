package com.eqriesracingteam.garage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private Appointment appointment;

    @Test
    void getAppointment() {

        Appointment newAppointment = new Appointment();
        newAppointment.setId(1l);
        newAppointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expected = newAppointment.getAppointmentDate();
        assertEquals(expected, now);
    }
}