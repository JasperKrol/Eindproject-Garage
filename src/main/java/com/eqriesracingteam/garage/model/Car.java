package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autos")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @OneToOne
    private RegistrationPapers registrationPapers;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer owner;

    @OneToMany(mappedBy = "carForAppointment")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "scheduledCar")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Inspection> inspections = new ArrayList<>();

    @OneToMany(mappedBy = "scheduledCar")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Repair> repairs = new ArrayList<>();

    // Constructors

    //Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public RegistrationPapers getRegistrationPapers() {
        return registrationPapers;
    }

    public void setRegistrationPapers(RegistrationPapers registrationPapers) {
        this.registrationPapers = registrationPapers;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }
}
