package com.eqriesracingteam.garage.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepairsItemsKey implements Serializable {

    @Column(name = "repair_id")
    private long repairId;

    @Column(name = "inventory_id")
    private long inventoryId;

    // Getters and setters

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }


    // Constructor

    public RepairsItemsKey() {
    }

    public RepairsItemsKey(long repairId, long inventoryId) {
        this.repairId = repairId;
        this.inventoryId = inventoryId;
    }

    // Hash and equals

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RepairsItemsKey))
            return false;
        RepairsItemsKey that = (RepairsItemsKey) o;
        return getRepairId() == that.getRepairId() && getInventoryId() == that.getInventoryId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRepairId(), getInventoryId());
    }
}
