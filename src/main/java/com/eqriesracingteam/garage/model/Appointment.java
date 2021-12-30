package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.*;

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
    //    @Size(min = 1)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @JsonIgnore
    private Car carForAppointment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    // TODO: 28-12-2021 invoice link?
    ////    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    ////    @JoinColumn(name = "invoice_nr", referencedColumnName = "invoice_nr")
    ////    private Invoice invoiceNumber;
    //
    //

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

//    public Car getCar() {
//        return carForAppointment;
//    }
//
//    public void setCar(Car carForAppointment) {
//        this.carForAppointment = carForAppointment;
//    }
}
