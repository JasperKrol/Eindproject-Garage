package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.InspectionInputDto;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.model.InspectionStatus;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Inspection getInspection(long id) {
        Optional<Inspection> inspectionOptional = inspectionRepository.findById(id);

        if (inspectionOptional.isPresent()){
            return inspectionOptional.get();
        } else {
            throw new RecordNotFoundException("Inspection not found");
        }
    }

    public Inspection createInspection(Inspection inspection) {

        inspection.setInspectionStatus(InspectionStatus.INSPECTIE_GEPLAND);
        return inspectionRepository.save(inspection);
    }
}
