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

import java.util.List;

@Service
public class RepairsItemsService {


    private RepairsItemsRepository repairsItemsRepository;
    private RepairRepository repairRepository;
    private InventoryRepository inventoryRepository;

    @Autowired
    public RepairsItemsService(RepairsItemsRepository repairsItemsRepository, RepairRepository repairRepository, InventoryRepository inventoryRepository) {
        this.repairsItemsRepository = repairsItemsRepository;
        this.repairRepository = repairRepository;
        this.inventoryRepository = inventoryRepository;
    }

    // Method to be able to get all inventoryitems in repair list
    public RepairsItemsKey addRepairsItems(long repairId, long inventoryId) {
        var repairItems = new RepairItems();
        if (!repairRepository.existsById(repairId)) { throw new RecordNotFoundException(); }
        Repair repair = repairRepository.findById(repairId).orElse(null);
        if (!inventoryRepository.existsById(inventoryId)) { throw new RecordNotFoundException(); }
        Inventory inventoryItem = inventoryRepository.findById(inventoryId).orElse(null);
        repairItems.setRepair(repair);
        repairItems.setInventoryItem(inventoryItem);
        RepairsItemsKey id = new RepairsItemsKey(repairId, inventoryId);
        repairItems.setId(id);
        repairsItemsRepository.save(repairItems);
        return id;
    }

    // normal methods for CRUD requests

    public List<RepairItems> getAllRepairsWithItems() {
        return repairsItemsRepository.findAll();
    }

    public RepairItems getRepairWithItems(long id) {
        if (repairsItemsRepository.existsById(id)) {
            return repairsItemsRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException("No Repair with id " + id);
        }
    }

    public RepairsItemsKey save(RepairItems repairItems) {
        RepairItems saved = repairsItemsRepository.save(repairItems);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (repairsItemsRepository.existsById(id)) {
            repairsItemsRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Repair with id " + id);
        }
    }

}
