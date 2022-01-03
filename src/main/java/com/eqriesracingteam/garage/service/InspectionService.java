package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Iterable<Inspection> getInspections(LocalDateTime appointmentDate) {

        if (appointmentDate == null) {
            return inspectionRepository.findAll();
        } else {
            return inspectionRepository.findAllByAppointmentDate(appointmentDate);
        }

    }
}
