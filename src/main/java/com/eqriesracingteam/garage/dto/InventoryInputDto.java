package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inventory;

import java.math.BigDecimal;

public class InventoryInputDto {

    public Long id;
    public String itemDescription;
    public BigDecimal price;
    public int stock;

    public Inventory toInventory() {
        var inventory = new Inventory();

        inventory.setId(id);
        inventory.setItemDescription(itemDescription);
        inventory.setPrice(price);
        inventory.setStock(stock);

        return inventory;
    }
}
