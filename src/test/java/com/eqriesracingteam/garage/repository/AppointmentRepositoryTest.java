package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.GarageApplication;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import com.eqriesracingteam.garage.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class AppointmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void findById() {
//
//        //Given
//        Appointment appointment = new Appointment();
//
//        appointment.setAppointmentStatus(AppointmentStatus.REPARATIE_GEPLAND);
//        appointment.setAppointmentDate(LocalDateTime.of(2022, 01, 01, 10, 10));
//        entityManager.persist(appointment);
//        entityManager.flush();
//
//        //When
//        Optional<Appointment> found = appointmentRepository.findById(2L);
//
//        //Then
//        long expected = 2L;
//        long actual = found.get().getId();
//
//        assertEquals(expected, actual);
//    }
}