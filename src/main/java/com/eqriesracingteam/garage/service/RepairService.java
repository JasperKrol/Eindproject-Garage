package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.dto.IdInputDto;
import com.eqriesracingteam.garage.exceptions.AppointmentException;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.*;
import com.eqriesracingteam.garage.repository.CarRepository;
import com.eqriesracingteam.garage.repository.InventoryRepository;
import com.eqriesracingteam.garage.repository.RepairRepository;
import com.eqriesracingteam.garage.repository.RepairsItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    private RepairRepository repairRepository;
    private CarRepository carRepository;
    private InventoryRepository inventoryRepository;
    private RepairsItemsRepository repairsItemsRepository;


    // Constructor

    @Autowired
    public RepairService(RepairRepository repairRepository, CarRepository carRepository, InventoryRepository inventoryRepository, RepairsItemsRepository repairsItemsRepository) {
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;
        this.inventoryRepository = inventoryRepository;
        this.repairsItemsRepository = repairsItemsRepository;
    }

    // Methods
    public Repair getOneAppointment(Long id) {
        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if (optionalRepair.isPresent()) {
            return optionalRepair.get();
        } else {
            throw new AppointmentException("Could not find repair with that id");
        }
    }

    // TODO: 6-1-2022 request param for date search option or car
    // TODO: 13-1-2022 postman request not werking 
    public List<Repair> getAllRepairAppointments() {
        return repairRepository.findAll();
    }

    public void adjustRepairAppointment(Long id, Repair repair) {

        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if (optionalRepair.isPresent()) {
            Repair existingRepair = optionalRepair.get();

            repair.setId(existingRepair.getId());
            repair.setRepairItems(existingRepair.getRepairItems());
            repair.setScheduledCar(existingRepair.getScheduledCar());
            repairRepository.save(repair);

        } else {
            throw new AppointmentException("Appointment with id not found");
        }
    }

    public void deleteRepairAppointment(Long id) {
        var existingAppointment = repairRepository.findById(id);

        if (existingAppointment.isPresent()) {
            repairRepository.deleteById(id);
        } else {
            throw new AppointmentException("Appointment not found");
        }
    }


    // TODO: 13-1-2022 after creating solution, check if needed
    //    public void addARepairItem(Long id, Long repairItemId) {
    //        Repair repair = getOneAppointment(id);
    //        Optional<Inventory> optionalInventory = inventoryRepository.findById(repairItemId);
    //
    //        if (optionalInventory.isPresent()) {
    //            Inventory inventoryItem = optionalInventory.get();
    //            if (inventoryItem.getStock() != 0) {
    //                RepairItems repairItems = new RepairItems();
    //
    //                repairItems.setRepair(repair);
    //                repairItems.setInventoryItem(inventoryItem);
    //                repairsItemsRepository.save(repairItems);
    //
    //                repair.getRepairItems().add(repairItems);
    //                repairRepository.save(repair);
    //                inventoryItem.setStock(-1);
    //                inventoryRepository.save(inventoryItem);
    //            }
    //        } else {
    //            throw new RecordNotFoundException("No item with id " + repairItemId + " found");
    //        }
    //    }

    public Repair createRepairAppointment(Repair repair, Long carId) {
        var date = repair.getRepairDateWorkshop();
        List<Repair> repairs = repairRepository.findAllByRepairDateWorkshop(date);
        var optionalCar = carRepository.findById(carId);


        if (repairs.isEmpty() && optionalCar.isPresent()) {

            repair.setAppointmentStatus(AppointmentStatus.REPARATIE_GEPLAND);

            var car = optionalCar.get();
            repair.setScheduledCar(car);

            Repair newRepair = repairRepository.save(repair);
            return newRepair;

        } else {
            throw new BadRequestException("Missing car information");
        }
    }
}
