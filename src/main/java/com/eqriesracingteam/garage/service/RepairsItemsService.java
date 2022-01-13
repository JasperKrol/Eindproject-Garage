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

        // instantiate repairsItems
        var repairsItems = new RepairItems();
        // check if repair id is available
        if (!repairsItemsRepository.existsById(repairId)) {
            throw new RecordNotFoundException("Repair with id " + repairId + " not found");
        }
        // get if so get and put in variable
        Repair repair = repairRepository.findById(repairId).orElse(null);
        // same for inventory item
        if (!inventoryRepository.existsById(inventoryId)){
            throw new RecordNotFoundException("Inventory item with id " + inventoryId + " not found");
        }
        Inventory inventoryItem = inventoryRepository.findById(inventoryId).orElse(null);

        //add the repair item and inventory item to repairsItems object
        repairsItems.setRepair(repair);
        repairsItems.setInventoryItem(inventoryItem);

        // create the key for both ids and put them in key variable
        RepairsItemsKey id = new RepairsItemsKey(repairId, inventoryId);
        repairsItems.setId(id);
        repairsItemsRepository.save(repairsItems);
        return id;
    }

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
