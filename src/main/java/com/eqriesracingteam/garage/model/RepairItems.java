package com.eqriesracingteam.garage.model;

import javax.persistence.*;

@Entity
@Table(name = "reparatie_items")
public class RepairItems {

    @EmbeddedId
    private RepairsItemsKey id;

    @ManyToOne
    @MapsId("repairId")
    @JoinColumn(name = "repair_id")
    private Repair repair;

    @ManyToOne
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_item_id")
    private Inventory inventoryItem;

    private int amount;

    public RepairsItemsKey getId() {
        return id;
    }

    public void setId(RepairsItemsKey id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
