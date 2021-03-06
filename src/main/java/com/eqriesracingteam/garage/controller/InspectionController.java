package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.CarInputDto;
import com.eqriesracingteam.garage.dto.InspectionDto;
import com.eqriesracingteam.garage.dto.InspectionInputDto;
import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class InspectionController {

    private InspectionService inspectionService;

    @Autowired
    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    // Requests
    // Get all
    @GetMapping(value = "/api/garage/inspections")
    public ResponseEntity<Object> getInspections(@RequestParam(value = "date", required = false) LocalDateTime inspectionDate, @RequestParam(value = "licensePlate", required = false) String licensePlate) {
        return ResponseEntity.ok(inspectionService.getInspections(inspectionDate, licensePlate));
    }

    // Get one
    @GetMapping("/api/garage/inspections/{id}")
    public ResponseEntity<Object> getInspection(@PathVariable("id") long id) {
        return ResponseEntity.ok(inspectionService.getInspection(id));
    }

    // Post
    @PostMapping(value = "/api/garage/inspections")
    public InspectionDto createInspection(@RequestBody InspectionInputDto dto) {
        var inspection = inspectionService.createInspection(dto.toInspection(), dto.appointmentId);
        return InspectionDto.fromInspection(inspection);
    }

    // Update
    @PutMapping(value = "/api/garage/inspections/{id}")
    public InspectionDto updateInspection(@PathVariable("id") long id, @RequestBody Inspection inspection) {
        inspectionService.updateInspection(id, inspection);
        return InspectionDto.fromInspection(inspection);
    }

    // Delete
    @DeleteMapping(value = "/api/garage/inspections/{id}")
    public void deleteInspection(@PathVariable("id") long id) {
        inspectionService.deleteInspection(id);
    }

    //Assign
    // Put
    @PutMapping(value = "/api/garage/inspections/{id}/car")
    public InspectionDto assignCarToInspection(@PathVariable("id") long id, @RequestBody CarInputDto inputDto) {
        return InspectionDto.fromInspection(inspectionService.assignCarToInspection(id, inputDto.id));
    }
}
