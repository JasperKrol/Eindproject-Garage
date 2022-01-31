package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.AppointmentDto;
import com.eqriesracingteam.garage.dto.InventoryDto;
import com.eqriesracingteam.garage.dto.InventoryInputDto;
import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.model.Inventory;
import com.eqriesracingteam.garage.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {

    // Attributes
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Requests

    // Get one
    @GetMapping(value = "/api/garage/inventory/{id}")
    public ResponseEntity<Object> getOnePart(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(inventoryService.getOnePart(id));
    }

    // Get all
    @GetMapping(value = "/api/garage/inventory")
    public List<InventoryDto> getAllInventoryItems(@RequestParam(name = "description", defaultValue = "") String description) {
        var dtos = new ArrayList<InventoryDto>();

        List<Inventory> inventoryList;

        if (description != null) {
            inventoryList = inventoryService.getAllItemsByDescription(description);
        } else if (description == null) {
            inventoryList = inventoryService.getAllInventoryItems();
        } else {
            throw new BadRequestException("Items not found");
        }

        for (Inventory inventoryItem : inventoryList) {
            dtos.add(InventoryDto.fromInventory(inventoryItem));
        }
        return dtos;
    }

    @PostMapping(value = "/api/garage/inventory")
    public InventoryDto addInventoryItem(@RequestBody InventoryInputDto dto) {
        var inventoryItem = inventoryService.createNewInventoryItem(dto.toInventory());
        return InventoryDto.fromInventory(inventoryItem);
    }

    @DeleteMapping(value = "/api/garage/inventory/{id}")
    public void deleteItem(@PathVariable("id") long id) {
        inventoryService.deleteItemFromInventory(id);
    }

    @PutMapping(value = "/api/garage/inventory/{id}")
    public InventoryDto updateInventoryItem(@PathVariable("id") long id, @RequestBody Inventory inventory) {
        inventoryService.updateInventoryItem(id, inventory);
        return InventoryDto.fromInventory(inventory);
    }
}
