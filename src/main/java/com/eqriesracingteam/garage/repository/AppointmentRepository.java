package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByAppointmentDate(LocalDateTime date);
}
