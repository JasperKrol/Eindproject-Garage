package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "afspraken")
public class Appointment {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date appointmentDate;
    private AppointmentStatus appointmentStatus;
    private Date carPickupDate;

    @OneToOne
    @JsonIgnore
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("appointments")
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Date getCarPickupDate() {
        return carPickupDate;
    }

    public void setCarPickupDate(Date carPickupDate) {
        this.carPickupDate = carPickupDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
