package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inventory;

import java.math.BigDecimal;

public class InventoryDto {

    public long id;
    public String itemDescription;
    public BigDecimal price;
    public int stock;
    public int usedParts;

    public static InventoryDto fromInventory(Inventory inventory) {

        var dto = new InventoryDto();

        dto.id = inventory.getId();
        dto.itemDescription = inventory.getItemDescription();
        dto.price = inventory.getPrice();
        dto.stock = inventory.getStock();
        dto.usedParts = inventory.getUsedParts();

        return dto;
    }

}
