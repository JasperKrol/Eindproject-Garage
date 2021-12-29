package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inventory;

public class InventoryInputDto {

    public long itemId;
    public String itemDescription;
    public double price;
    public double stock;

    public Inventory toInventory(){
        var inventory = new Inventory();

        return inventory;
    }
}
