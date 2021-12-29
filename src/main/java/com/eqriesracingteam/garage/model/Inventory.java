package com.eqriesracingteam.garage.model;

import javax.persistence.*;

@Entity
@Table(name = "inventory_list")
public class Inventory {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    private String itemDescription;
    private double price;
    private double stock;

    // Getters and setters

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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

    public void setStock(double stock) {
        this.stock = stock;
    }
}
