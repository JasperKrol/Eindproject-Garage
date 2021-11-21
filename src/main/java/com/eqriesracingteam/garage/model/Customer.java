package com.eqriesracingteam.garage.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "klanten")
public class Customer {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name",nullable = false, length = 50)
    private String lastName;

    // TODO: 11-11-2021 uitzoeken naar relatie springsql
    //mapped by = eigenaar van de relatie, mapped over lijst van autos adhv de user id
    //user id is eigenaar en achter komt de lijst met autos
    @OneToMany(targetEntity = Car.class, mappedBy = "id")
    private List<Car> cars;

    //Constructor
    // TODO: 21-11-2021 Constructors toevoegen -leeg en alle

    //Getters and setters
    // TODO: 21-11-2021 getters en setters maken
}
