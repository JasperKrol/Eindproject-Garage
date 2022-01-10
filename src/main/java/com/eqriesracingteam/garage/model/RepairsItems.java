package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "repairs_items")
public class RepairsItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonManagedReference
    private Inventory inventoryItem;

    @ManyToOne
    private Repair repair;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inventory getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(Inventory inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
