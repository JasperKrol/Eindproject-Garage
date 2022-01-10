package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.eqriesracingteam.garage.dto.RepairDto;
import com.eqriesracingteam.garage.dto.RepairInputDto;
import com.eqriesracingteam.garage.model.Appointment;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepairController {

    private RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    // CRUD Requests
    // Post request
    @PostMapping(value = "/api/garage/repairs")
    public RepairDto addRepairAppointment(@RequestBody RepairInputDto dto){
        var repairAppointment = repairService.createRepairAppointment(dto.toRepair(), dto.carId);
        return RepairDto.fromRepair(repairAppointment);
    }

    // Get one
    @GetMapping(value = "/api/garage/repairs/{id}")
    public RepairDto getOneRepairAppointment(@PathVariable("id") long id){
        var repairAppointment = repairService.getOneAppointment(id);
        return RepairDto.fromRepair(repairAppointment);
    }

    // Get all
    @GetMapping(value = "/api/garage/repairs")
    public List<RepairDto> getAllRepairAppointments(){
        var dtos = new ArrayList<RepairDto>();
        var repairAppointments = repairService.getAllRepairAppointments();

        for (Repair repair: repairAppointments) {
            dtos.add(RepairDto.fromRepair(repair));
        }
        return dtos;
    }

    // Adjust
    @PutMapping(value = "/api/garage/repairs/{id}")
    public RepairDto adjustRepairAppointment(@PathVariable("id") long id, @RequestBody Repair repair) {
        repairService.adjustRepairAppointment(id, repair);
        return RepairDto.fromRepair(repair);

    }

    // Delete appointment
    @DeleteMapping(value = "/api/garage/repairs/{id}")
    public void deleteRepairAppointment(@PathVariable("id") long id){
        repairService.deleteRepairAppointment(id);
    }


    // Patch
//    @PatchMapping(value = "/api/garage/repairs/{id}/repairItems" )
//    public ResponseEntity<?> addUsedInventoryItemsByRepair(@PathVariable("id") long id, @RequestBody long repairItemId) {
//        repairService.addARepairItem(id, repairItemId);
//        return ResponseEntity.ok().build();
//    }
}
