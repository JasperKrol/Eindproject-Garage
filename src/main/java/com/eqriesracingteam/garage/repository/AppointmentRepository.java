package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByAppointmentDate(LocalDateTime date);
    List<Appointment> findAllByAppointmentStatus(AppointmentStatus status);
}
