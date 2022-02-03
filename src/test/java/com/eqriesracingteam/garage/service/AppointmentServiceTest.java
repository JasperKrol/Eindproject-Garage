package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private CarRepository carRepository;

    @Mock
    Car car;

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
    void createAppointment(){

    }

}