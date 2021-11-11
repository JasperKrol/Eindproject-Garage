package com.eqriesracingteam.garage.model;

import jdk.jfr.DataAmount;

import javax.persistence.*;

@Entity
@Table(name = "autos")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false, length = 50)
    private String licensePlate;

    // TODO: 11-11-2021 veranderen in PDF
    @Column(nullable = false, length = 50)
    private String registrationPapers;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    //Constructors


    public Car(String licensePlate, String registrationPapers, Customer customer) {
        this.licensePlate = licensePlate;
        this.registrationPapers = registrationPapers;
        this.customer = customer;
    }

    public Car() {
    }

    //Getters and Setters

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
}
