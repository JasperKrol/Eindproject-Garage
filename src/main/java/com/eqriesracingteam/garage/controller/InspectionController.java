package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.Inspection;
import com.eqriesracingteam.garage.repository.InspectionRepository;
import com.eqriesracingteam.garage.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @GetMapping(value = "/inspections")
    public ResponseEntity<Object> getInspections(@RequestParam(value = "date", required = false) LocalDateTime inspectionDate) {
        return ResponseEntity.ok(inspectionService.getInspections(inspectionDate));


    }
}
