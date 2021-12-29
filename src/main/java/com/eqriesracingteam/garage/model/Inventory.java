package com.eqriesracingteam.garage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_list")
public class Inventory {

    @Id
    long id;
    }
