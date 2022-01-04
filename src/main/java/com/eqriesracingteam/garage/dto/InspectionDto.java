package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.model.InspectionStatus;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InspectionDto {
    public long id;
    public LocalDateTime inspectionDate;
    public String findings;
    public BigDecimal estimatedCosts;
    public InspectionStatus inspectionStatus;

    public static InspectionDto fromInspection(Inspection inspection){
        var dto = new InspectionDto();

        dto.id = inspection.getId();
        dto.inspectionDate = inspection.getInspectionDate();
        dto.findings = inspection.getFindings();
        dto.estimatedCosts = inspection.getEstimatedCosts();
        dto.inspectionStatus = inspection.getInspectionStatus();

        return dto;
    }
}
