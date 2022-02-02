package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import com.eqriesracingteam.garage.repository.InventoryRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import com.eqriesracingteam.garage.repository.RepairsItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RepairsItemsService {


    private final RepairsItemsRepository repairsItemsRepository;
    private final RepairRepository repairRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public RepairsItemsService(RepairsItemsRepository repairsItemsRepository, RepairRepository repairRepository, InventoryRepository inventoryRepository) {
        this.repairsItemsRepository = repairsItemsRepository;
        this.repairRepository = repairRepository;
        this.inventoryRepository = inventoryRepository;
    }

    // normal methods for CRUD requests

    public List<RepairItems> getAllRepairsWithItems() {
        return repairsItemsRepository.findAll();
    }

    public Collection<RepairItems> getJobPartsByPartId(Long inventoryId) {
        return repairsItemsRepository.findAllByInventoryItem_Id(inventoryId);
    }

    // TODO: 22-1-2022 add check if stock is 0, bad request
    public RepairsItemsKey addRepairsItems(Long repairId, Long inventoryId,int amount) {
        var repairItems = new RepairItems();
        if (!repairRepository.existsById(repairId)) {
            throw new RecordNotFoundException();
        }
        Repair repair = repairRepository.findById(repairId).orElse(null);

        if (!inventoryRepository.existsById(inventoryId)) {
            throw new RecordNotFoundException();
        }
        Inventory inventoryItem = inventoryRepository.findById(inventoryId).orElse(null);

        repairItems.setRepair(repair);
        repairItems.setInventoryItem(inventoryItem);
        RepairsItemsKey id = new RepairsItemsKey(repairId, inventoryId);
        repairItems.setId(id);
        repairItems.setAmount(amount);

        // TODO: 26-1-2022 check if needed for now
        inventoryItem.setStock(inventoryItem.getStock() - amount);
        inventoryItem.setUsedParts(inventoryItem.getUsedParts() + amount);

        repairsItemsRepository.save(repairItems);
        return repairItems.getId();
    }

    public RepairsItemsKey save(RepairItems repairItems) {
        RepairItems saved = repairsItemsRepository.save(repairItems);
        return saved.getId();
    }

    public void deleteById(long repairId, Long inventoryId) {
        RepairsItemsKey ID = new RepairsItemsKey(repairId, inventoryId);
        if (repairsItemsRepository.existsById(ID)) {
            repairsItemsRepository.deleteById(ID);
        } else {
            throw new RecordNotFoundException("ID's not found");
        }
    }
}
