package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.InventoryException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
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

    public void deleteItemFromInventory(long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Item with id not found");
        }
    }

    public void updateInventoryItem(long id, Inventory inventoryItem) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);

        if (optionalInventory.isPresent()) {
            Inventory storedItem = optionalInventory.get();

            inventoryItem.setId(storedItem.getId());
            inventoryRepository.save(inventoryItem);
        }
    }

    public void partialUpdateItem(long id, Inventory inventory) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);

        if (optionalInventory.isPresent()) {
            Inventory storedItem = inventoryRepository.findById(id).orElse(null);
            if (inventory.getPrice() != null) {
                storedItem.setPrice(inventory.getPrice());
            }
        }
    }
}
