package com.eqriesracingteam.garage.model;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "inspecties")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO: 4-1-2022 relation with appointment date 
    private LocalDateTime inspectionDate;
    // TODO: 4-1-2022 appointmentstatus setting

    private String findings;

    private BigDecimal estimatedCosts;

    private InspectionStatus inspectionStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public BigDecimal getEstimatedCosts() {
        return estimatedCosts;
    }

    public void setEstimatedCosts(BigDecimal estimatedCosts) {
        this.estimatedCosts = estimatedCosts;
    }

    public InspectionStatus getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(InspectionStatus inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }
}
