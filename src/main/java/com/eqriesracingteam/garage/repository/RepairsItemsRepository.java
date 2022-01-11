package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.RepairItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairsItemsRepository extends JpaRepository <RepairItems, Long> {
}
