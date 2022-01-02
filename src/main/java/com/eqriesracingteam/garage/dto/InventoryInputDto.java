package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inventory;

import java.math.BigDecimal;

public class InventoryInputDto {

    public long itemId;
    public String itemDescription;
    public BigDecimal price;
    public long stock;
    private long usedParts;

    public Inventory toInventory(){
        var inventory = new Inventory();

        inventory.setId(itemId);
        inventory.setItemDescription(itemDescription);
        inventory.setPrice(price);
        inventory.setStock(stock);

        return inventory;
    }
}
