package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {

}
