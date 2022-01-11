package com.eqriesracingteam.garage.model;

import javax.persistence.*;

@Entity
public class RegistrationPapers {

    @Id
    @GeneratedValue
    Long id;

    private String name;
    private String type;

    @Lob
    byte[] data;

    @OneToOne(mappedBy = "registrationPapers")
    Car car;


    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
