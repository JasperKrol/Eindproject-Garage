package com.eqriesracingteam.garage.dto;

import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;

public class RepairItemsDto {

    private RepairsItemsKey id;
    private long repairId;
    private Inventory inventoryItem;
    private int amount;

    public static RepairItemsDto fromRepairItems(RepairItems repairItems){
        var dto = new RepairItemsDto();

        dto.id = repairItems.getId();
//        dto.repairId = repairItems.getRepair();
//        dto.inventoryItem = repairItems.getInventoryItem();
        dto.amount = repairItems.getAmount();

        return dto;
    }
}
