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


    private RepairsItemsRepository repairsItemsRepository;
    private RepairRepository repairRepository;
    private InventoryRepository inventoryRepository;

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

    public Collection<RepairItems> getJobPartsByPartId(Long inventoryId){
        return repairsItemsRepository.findAllByInventoryItem_Id(inventoryId);
    }

    // Method to be able to get all inventoryItems in repair list
    // amount in parameter en daarna setAmount in repairsItemAmount
    // TODO: 22-1-2022 add check if stock is 0, bad request
    public RepairsItemsKey addRepairsItems(Long repairId, Long inventoryId, int amount) {
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
        inventoryItem.setStock(inventoryItem.getStock() - amount);
        inventoryItem.setUsedParts(inventoryItem.getUsedParts() + amount);

        repairsItemsRepository.save(repairItems);
        return id;
    }


//    public RepairItems getRepairWithItems(Long id) {
//        if (repairsItemsRepository.existsById(id)) {
//            return repairsItemsRepository.findById(id).get();
//        } else {
//            throw new RecordNotFoundException("No Repair with id " + id);
//        }
//    }

    public RepairsItemsKey save(RepairItems repairItems) {
        RepairItems saved = repairsItemsRepository.save(repairItems);
        return saved.getId();
    }

//    public void deleteById(long repair_id, Long inventoryId) {
//        if (repairsItemsRepository.existsById(repair_id) && repairsItemsRepository.existsById(inventoryId)) {
//            repairsItemsRepository.deleteById(repair_id);
//            repairsItemsRepository.deleteById(inventoryId);
//        } else {
//            throw new RecordNotFoundException("No Repair with repair_id " + repair_id + " or inventory id with inventory id " + inventoryId);
//        }
//    }
}
