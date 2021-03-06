package com.eqriesracingteam.garage.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "kentekenbewijzen")
public class RegistrationPaper {

    @Id
    @GeneratedValue
    Long id;

    String name;
    String type;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    byte[] data;


    @OneToOne(mappedBy = "registrationPaper")
    Car car;

    // Constructor

    public RegistrationPaper() {
    }

    public RegistrationPaper(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

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

}
