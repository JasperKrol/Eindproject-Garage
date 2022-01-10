package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.RepairsItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import com.eqriesracingteam.garage.repository.RepairsItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairsItemsService {

    @Autowired
    private RepairsItemsRepository repairsItemsRepository;

    public List<RepairsItems> getAllRepairsWithItems() {
        return repairsItemsRepository.findAll();
    }

    public RepairsItems getRepairWithItems(long id) {
        if (repairsItemsRepository.existsById(id)) {
            return repairsItemsRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException("No Repair with id " + id);
        }
    }

    public RepairsItemsKey save(RepairsItems repairItems) {
        RepairsItems saved = repairsItemsRepository.save(repairItems);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (repairsItemsRepository.existsById(id)) {
            repairsItemsRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("No Repair with id " + id);
        }
    }

}
