package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "afspraken")
public class Appointment {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime appointmentDate;

    private AppointmentStatus appointmentStatus;

    private LocalDateTime carPickupDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car carForAppointment;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(mappedBy = "appointment")
    @JoinColumn(name = "repair_id", referencedColumnName = "id")
    private Repair repair;

    @OneToOne(mappedBy = "appointment",fetch = FetchType.EAGER)
    @JsonIgnore
    private Inspection inspection;

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public LocalDateTime getCarPickupDate() {
        return carPickupDate;
    }

    public void setCarPickupDate(LocalDateTime carPickupDate) {
        this.carPickupDate = carPickupDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCarForAppointment() {
        return carForAppointment;
    }

    public void setCarForAppointment(Car carForAppointment) {
        this.carForAppointment = carForAppointment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }
}
