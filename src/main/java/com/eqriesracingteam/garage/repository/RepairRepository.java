package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> findAllByAppointmentDate(LocalDateTime date);

}
