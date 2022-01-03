package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public List<Inspection> getInspections(LocalDateTime inspectionDate) {

        if (inspectionDate == null) {
            return inspectionRepository.findAll();
        } else {
            return inspectionRepository.findAllByInspectionDate(inspectionDate);
        }

    }
}
