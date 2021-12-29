package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    // TODO: 29-12-2021 @RequestParam for description
    public Optional<Inventory> getOnePart(long id) {
        return inventoryRepository.findById(id);
    }

    // Handling of requests

}
