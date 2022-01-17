package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.model.InspectionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InspectionDto {
    public long id;
    public LocalDateTime inspectionDate;
    public String findings;
    public BigDecimal estimatedCosts;
    public InspectionStatus inspectionStatus;

    // TODO: 5-1-2022 for now just this hard ignore
    @JsonIgnoreProperties("registrationPapers")
    public Car scheduledCar;

    public static InspectionDto fromInspection(Inspection inspection){
        var dto = new InspectionDto();

        dto.id = inspection.getId();
        dto.inspectionDate = inspection.getInspectionDate();
        dto.findings = inspection.getFindings();
        dto.estimatedCosts = inspection.getEstimatedCosts();
        dto.inspectionStatus = inspection.getInspectionStatus();
        dto.scheduledCar = inspection.getScheduledCar();

        return dto;
    }
}
