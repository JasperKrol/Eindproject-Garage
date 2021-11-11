package com.eqriesracingteam.garage.model;

import org.springframework.core.SpringVersion;

import javax.persistence.*;

@Entity
@Table(name = "klanten")
public class Customer {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String LastName;

    //Constructor

    public Customer( String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
    }

    public Customer() {
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
