package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {

}
