package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.RepairItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RepairsItemsRepository extends JpaRepository <RepairItems, Long> {
    Collection<RepairItems> findAllByRepairId(long repairId);
}
