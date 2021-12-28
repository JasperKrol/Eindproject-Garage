package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "autos")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "license_plate")
    private String licensePlate;

    // TODO: 11-11-2021 veranderen in PDF
    @Column(name = "registration_papers")
    private String registrationPapers;

    //Create relationship in sql/database
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer owner;

    //Constructors
    public Car() {
    }

    public Car(long id, String licensePlate, String registrationPapers, Customer owner) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.registrationPapers = registrationPapers;
        this.owner = owner;
    }

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

    public String getRegistrationPapers() {
        return registrationPapers;
    }

    public void setRegistrationPapers(String registrationPapers) {
        this.registrationPapers = registrationPapers;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
