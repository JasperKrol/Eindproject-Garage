package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.InventoryException;
import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // TODO: 29-12-2021 @RequestParam for description
    public Optional<Inventory> getOnePart(long id) {
        return inventoryRepository.findById(id);
    }

    public List<Inventory> getAllItemsByDescription(String description) {
        return inventoryRepository.findAllByItemDescriptionContainingIgnoreCase(description);
    }

    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    public Inventory createNewInventoryItem(Inventory inventoryItem) {
        var description = inventoryItem.getItemDescription();
        List<Inventory> inventoryList = (List<Inventory>) inventoryRepository.findAllByItemDescriptionContainingIgnoreCase(description);

        if (inventoryList.size() > 0) {
            throw new InventoryException("Inventory item already exists");
        } else {
            return inventoryRepository.save(inventoryItem);
        }
    }

    // Handling of requests

}
