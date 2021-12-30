package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "afspraken")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appointment {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime appointmentDate;
    private AppointmentStatus appointmentStatus;
    private LocalDateTime carPickupDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car carForAppointment;

    @JsonIgnore
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return carForAppointment;
    }

    public void setCar(Car carForAppointment) {
        this.carForAppointment = carForAppointment;
    }
}
