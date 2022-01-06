package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.eqriesracingteam.garage.dto.RepairDto;
import com.eqriesracingteam.garage.dto.RepairInputDto;
import com.eqriesracingteam.garage.model.Repair;
import com.eqriesracingteam.garage.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
