package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findAllByItemDescriptionContainingIgnoreCase(String description);
}
