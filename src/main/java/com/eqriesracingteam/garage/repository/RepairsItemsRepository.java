package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface RepairsItemsRepository extends JpaRepository <RepairItems, RepairsItemsKey> {
    List<RepairItems> findAllByRepairId(Long repairId);
    List<RepairItems> findAllByInventoryItem_Id(Long inventoryId);
    List<RepairItems> countRepairItemsByInventoryItem_Price(BigDecimal inventoryItem_price);
}
