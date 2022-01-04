package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.InspectionInputDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.InspectionException;
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

        if (inspectionOptional.isPresent()) {
            return inspectionOptional.get();
        } else {
            throw new RecordNotFoundException("Inspection not found");
        }
    }

    public Inspection createInspection(Inspection inspection) {
        var inspectionDate = inspection.getInspectionDate();
        List<Inspection> inspections = inspectionRepository.findAllByInspectionDate(inspectionDate);

        inspection.setInspectionStatus(InspectionStatus.INSPECTIE_GEPLAND);

        if (inspections.size() > 0) {
            throw new InspectionException("No more space on this date/time combination");
        } else {
            return inspectionRepository.save(inspection);
        }
    }

    public Inspection updateInspection(long id, Inspection inspection) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if (optionalInspection.isPresent()) {
            Inspection plannedInspection = optionalInspection.get();

            plannedInspection.setId(plannedInspection.getId());
            plannedInspection.setInspectionDate(inspection.getInspectionDate());
            plannedInspection.setInspectionStatus(inspection.getInspectionStatus());
            plannedInspection.setFindings(inspection.getFindings());
            plannedInspection.setEstimatedCosts(inspection.getEstimatedCosts());

            return inspectionRepository.save(plannedInspection);
        } else {
            throw new InspectionException("Inspection could not be saved or found");
        }
    }

    public void deleteInspection(long id) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if (!optionalInspection.isPresent()){
            throw new InspectionException("Inspection with id not found");
        } else {
           inspectionRepository.deleteById(id);
        }
    }
}
