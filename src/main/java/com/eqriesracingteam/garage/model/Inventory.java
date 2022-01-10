package com.eqriesracingteam.garage.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "auto_onderdelen")
public class Inventory {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String itemDescription;
    private BigDecimal price;
    private int stock;
    private int usedParts;

    // TODO: 5-1-2022 many to many relation
    @OneToMany(mappedBy = "inventoryItem")
    private List<RepairsItems> repairs;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void takeItemFromStock(int amountFromStock) {
        this.stock -= amountFromStock;
    }

    public void reStockItem(int amountToStock) {
        this.stock += amountToStock;
    }

    public int getUsedParts() {
        return usedParts;
    }

    public void setUsedParts(int usedParts) {
        this.usedParts = usedParts;
    }

    public List<RepairsItems> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<RepairsItems> repairs) {
        this.repairs = repairs;
    }

//    public void addRepair(RepairsItems repair) {
//        this.repairs.add(repair);
//    }
//
//    public void removeRepair(Repair repair) {
//        this.repairs.remove(repair);
//    }
}
