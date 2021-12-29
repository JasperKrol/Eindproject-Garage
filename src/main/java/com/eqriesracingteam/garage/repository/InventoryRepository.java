package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long> {
}
