package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inventory;

public class InventoryDto {

    public long itemId;
    public String itemDescription;
    public double price;
    public double stock;

    public static InventoryDto fromInventory(Inventory inventory) {

        var dto = new InventoryDto();

        return dto;
    }

}
