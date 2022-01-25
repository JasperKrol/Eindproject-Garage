package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RepairsItemsRepository extends JpaRepository <RepairItems, RepairsItemsKey> {
    Collection<RepairItems> findAllByRepairId(Long repairId);

    Collection<RepairItems> findAllByInventoryItem_Id(Long inventoryId);
}
