package com.eqriesracingteam.garage.model;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private LocalDateTime inspectionDate;

    private String findings;

    private BigDecimal estimatedCosts;

    private InspectionStatus inspectionStatus;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car scheduledCar;

    // Getters and setters

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

    public Car getScheduledCar() {
        return scheduledCar;
    }

    public void setScheduledCar(Car scheduledCar) {
        this.scheduledCar = scheduledCar;
    }

}
