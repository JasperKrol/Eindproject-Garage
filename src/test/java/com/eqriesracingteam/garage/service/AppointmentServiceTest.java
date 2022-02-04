package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    Appointment appointment;

    @Test
    void AppointmentStatusCheck() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAppointmentStatus(AppointmentStatus.REPARATIE_UITGEVOERD);

        // Act
        boolean statusOk = appointmentService.appointmentByStatus(appointment);

        // Assert
        assertTrue(statusOk);

    }

    @Test
    void createAppointment() {

        Appointment appointment = new Appointment();
        appointment.setDescription("test");
        appointment.setAppointmentStatus(AppointmentStatus.NIET_UITVOEREN);
        LocalDateTime date = LocalDateTime.of(2020, Month.APRIL, 12, 22, 10);
        appointment.setAppointmentDate(date);
        appointment.setId(22L);


        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment found = appointmentService.createAppointment(appointment);
        Appointment expect = appointment;

        assertEquals(expect, found);
    }

    @Test
    void getAppointmentByID() {

        Appointment appointment = new Appointment();
        appointment.setDescription("test");
        appointment.setId(22L);
        long id = appointment.getId();

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));

        Appointment expected = appointment;
        Appointment found = appointmentService.getAppointment(id);
        assertEquals(expected, found);
    }
}
