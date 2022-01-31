package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairsItemsRepository extends JpaRepository<RepairItems, RepairsItemsKey> {
    List<RepairItems> findAllByRepairId(Long repairId);

    List<RepairItems> findAllByInventoryItem_Id(Long inventoryId);

    List<RepairItems> findRepairItemsByRepairId(Long repairId);
}
