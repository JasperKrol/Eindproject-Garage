package com.eqriesracingteam.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "auto_onderdelen")
public class Inventory {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemDescription;
    private BigDecimal price;
    private int stock;
    private int usedParts;

    // many to many wit ass. class
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "repair")
    @JsonIgnore
    private List<RepairItems> repairItems;

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

    public List<RepairItems> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(List<RepairItems> repairs) {
        this.repairItems = repairs;
    }

    //    public void addRepair(RepairsItems repair) {
    //        this.repairs.add(repair);
    //    }
    //
    //    public void removeRepair(Repair repair) {
    //        this.repairs.remove(repair);
    //    }
}
