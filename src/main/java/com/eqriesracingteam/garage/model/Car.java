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

    // TODO: 28-11-2021 kenteken koppelen in tabel?
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    // TODO: 21-11-2021 ENUM TOEVOEGEN 
    //    @Column
    //    private final enum appointmentStatus;

    //Constructors
    public Car() {
    }

    public Car(Long id, String licensePlate, String registrationPapers, Customer owner) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.registrationPapers = registrationPapers;
        this.owner = owner;
    }

    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
