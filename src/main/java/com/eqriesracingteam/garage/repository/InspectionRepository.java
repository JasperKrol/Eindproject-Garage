package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    Iterable<Inspection> findAllByAppointmentDate(LocalDateTime appointmentDate);

}
