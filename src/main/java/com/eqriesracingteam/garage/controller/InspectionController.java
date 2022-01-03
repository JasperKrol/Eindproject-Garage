package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class InspectionController {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Iterable<Inspection> getInspections(LocalDateTime appointmentDate) {

        if(appointmentDate == null) {
            return inspectionRepository.findAll();
        } else {
            return inspectionRepository.findAllByAppointmentDate(appointmentDate);
        }

    }
}
