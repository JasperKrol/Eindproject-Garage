package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.eqriesracingteam.garage.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    // Attributes
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    // Requests

    // Get one
    @GetMapping(value = "/api/garage/inventory/{id}")
    public ResponseEntity<Object> getOnePart(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(inventoryService.getOnePart(id));
    }
}
