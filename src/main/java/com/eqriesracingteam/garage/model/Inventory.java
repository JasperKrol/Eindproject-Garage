package com.eqriesracingteam.garage.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "auto_onderdelen")
public class Inventory {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String itemDescription;
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public long getUsedParts() {
        return usedParts;
    }

    public void setUsedParts(long usedParts) {
        this.usedParts = usedParts;
    }
}
