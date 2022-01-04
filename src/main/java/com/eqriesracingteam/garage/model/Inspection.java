package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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
}
