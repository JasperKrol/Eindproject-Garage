package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.IdInputDto;
import com.eqriesracingteam.garage.dto.RepairDto;
import com.eqriesracingteam.garage.dto.RepairInputDto;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.service.RepairService;
import com.eqriesracingteam.garage.service.RepairsItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepairController {

    private RepairService repairService;
    private RepairsItemsService repairsItemsService;

    @Autowired
    public RepairController(RepairService repairService, RepairsItemsService repairsItemsService) {
        this.repairService = repairService;
        this.repairsItemsService = repairsItemsService;
    }

    // CRUD Requests
    // Post request
    // als je twee services moet aanspreken doe je dat in de controller
    // TODO: 12-1-2022 repair controller verantwoordelijk maken voor het de inventory items
//    @PostMapping(value = "/api/garage/repairs")
//    public void addRepairAppointment(@RequestBody RepairInputDto dto) {
////        int amount = repairItems.getAmount();
//
//        var repairId = repairService.createRepairAppointment(dto.repairDateWorkshop, dto.inventoryItemIdList, dto.carId);
//
//        for (Long inventoryItemId : dto.inventoryItemIdList) {
//            repairsItemsService.addRepairsItems(repairId, inventoryItemId);
//        }
//    }

    // TODO: 13-1-2022 old way if for back up

        @PostMapping(value = "/api/garage/repairs")
        public RepairDto addRepairAppointment(@RequestBody RepairInputDto dto){
            var repairAppointment = repairService.createRepairAppointment(dto.toRepair(), dto.carId);
            return RepairDto.fromRepair(repairAppointment);
        }

    // Get one
    @GetMapping(value = "/api/garage/repairs/{id}")
    public RepairDto getOneRepairAppointment(@PathVariable("id") Long id) {
        var repairAppointment = repairService.getOneAppointment(id);
        return RepairDto.fromRepair(repairAppointment);
    }

    // Get all
    @GetMapping(value = "/api/garage/repairs")
    public List<RepairDto> getAllRepairAppointments() {
        var dtos = new ArrayList<RepairDto>();
        var repairAppointments = repairService.getAllRepairAppointments();

        for (Repair repair : repairAppointments) {
            dtos.add(RepairDto.fromRepair(repair));
        }
        return dtos;
    }

    // Adjust
    @PutMapping(value = "/api/garage/repairs/{id}")
    public RepairDto adjustRepairAppointment(@PathVariable("id") Long id, @RequestBody Repair repair) {
        repairService.adjustRepairAppointment(id, repair);
        return RepairDto.fromRepair(repair);

    }

    // Delete appointment
    @DeleteMapping(value = "/api/garage/repairs/{id}")
    public void deleteRepairAppointment(@PathVariable("id") Long id) {
        repairService.deleteRepairAppointment(id);
    }


    // Patch
    //    @PatchMapping(value = "/api/garage/repairs/{id}/repairItems")
    //    public ResponseEntity<?> addUsedInventoryItemsByRepair(@PathVariable("id") Long id, @RequestBody Long repairItemId) {
    //        repairService.addARepairItem(id, repairItemId);
    //        return ResponseEntity.ok().build();
    //    }

}
