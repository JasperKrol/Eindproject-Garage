package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import com.eqriesracingteam.garage.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    // Requests

    // Get all
    @GetMapping(value = "/api/garage/inspections")
    public ResponseEntity<Object> getInspections(@RequestParam(value = "date", required = false) LocalDateTime inspectionDate) {
        return ResponseEntity.ok(inspectionService.getInspections(inspectionDate));
    }

    // Get one
    @GetMapping("/api/garage/inspections/{id}")
    public ResponseEntity<Object> getInspection(@PathVariable("id") long id){
        return ResponseEntity.ok(inspectionService.getInspection(id));
    }
}
