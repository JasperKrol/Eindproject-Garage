package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "afspraken")
public class Appointment {

    // Attributes

    @Id
    @GeneratedValue
    private long id;

    @JsonFormat(pattern = "mm-dd-yyyy")
    private Date appointmentDate;

    private AppointmentStatus appointmentStatus;

    @JsonFormat(pattern = "mm-dd-yyyy")
    private Date carPickUpDate;

    //one to one of many to one 1 afsprak heeft betrekking op 1 persoon, maar kan meerdere afspraken hebben
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // TODO: 23-12-2021 invoice relatie in appointment maken + getters and setters
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "invoice_nr", referencedColumnName = "invoice_nr")
//    private Invoice invoiceNumber;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

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

    public Date getCarPickUpDate() {
        return carPickUpDate;
    }

    public void setCarPickUpDate(Date carPickUpDate) {
        this.carPickUpDate = carPickUpDate;
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
