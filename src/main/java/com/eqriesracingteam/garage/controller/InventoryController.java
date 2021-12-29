package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
