package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.model.InspectionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InspectionInputDto {
    public long id;
    public LocalDateTime inspectionDate;
    public String findings;
    public BigDecimal estimatedCosts;
    public InspectionStatus inspectionStatus;
    public long appointmentId;

    public Inspection toInspection(){
        var inspection = new Inspection();

        inspection.setId(id);
        inspection.setInspectionDate(inspectionDate);
//        inspection.setFindings(findings);
//        inspection.setEstimatedCosts(estimatedCosts);

        return inspection;
    }
}
