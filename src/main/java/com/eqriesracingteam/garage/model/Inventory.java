package com.eqriesracingteam.garage.model;

import javax.persistence.*;

@Entity
@Table(name = "magazijn_onderdelen")
public class Inventory {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String itemDescription;
    private double price;
    private long stock;
    private long usedParts;

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public double getUsedParts() {
        return usedParts;
    }

    public void setUsedParts(long usedParts) {
        this.usedParts = usedParts;
    }
}
