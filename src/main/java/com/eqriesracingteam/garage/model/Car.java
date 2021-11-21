package com.eqriesracingteam.garage.model;


import javax.persistence.*;

@Entity
@Table(name = "autos")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    // TODO: 11-11-2021 veranderen in PDF
    @Column(name = "registration_papers")
    private String registrationPapers;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // TODO: 21-11-2021 ENUM TOEVOEGEN 
    //    @Column
    //    private final enum appointmentStatus;

    //Constructors
    // TODO: 21-11-2021 1 lege en een volledige met alle toevoegen

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
