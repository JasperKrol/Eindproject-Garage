package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.IdInputDto;
import com.eqriesracingteam.garage.dto.RepairDto;
import com.eqriesracingteam.garage.dto.RepairInputDto;
import com.eqriesracingteam.garage.model.Repair;
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
    @PostMapping(value = "/api/garage/repairs")
    public RepairDto addRepairAppointment(@RequestBody RepairInputDto dto) {
        var repairAppointment = repairService.createRepairAppointment(dto.toRepair(), dto.appointmentId);
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

    // Assign appointment to repair
    @PutMapping(value = "/api/garage/repairs/{id}/appointment")
    public void assignAppointmentToRepair(@PathVariable("id") long repairId, @RequestBody IdInputDto inputDto) {
        repairService.assignAppointment(repairId, inputDto.id);
    }
}
