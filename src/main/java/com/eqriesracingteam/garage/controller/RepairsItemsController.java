package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.dto.RepairItemsDto;
import com.eqriesracingteam.garage.dto.RepairItemsInputDto;
import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import com.eqriesracingteam.garage.service.RepairsItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/garage")
public class RepairsItemsController {

    @Autowired
    private RepairsItemsService repairsItemsService;

    @GetMapping(value = "/repairs_items")
    public ResponseEntity<Object> getAllRepairsWithItems() {
        List<RepairItems> repairItems = repairsItemsService.getAllRepairsWithItems();
        return ResponseEntity.ok(repairItems);
    }

    //bij een repair aanmaken frontend id ophalen
    @PostMapping(value = "/repairs_items")
    public ResponseEntity<Object> createRepairWithItems(@RequestBody RepairItemsInputDto dto) {

        RepairsItemsKey ID = repairsItemsService.addRepairsItems(dto.repairId, dto.repairId, dto.amount);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(ID);
    }

    @DeleteMapping(value = "/repairs_items/{repair_id}/{inventoryId}")
    public ResponseEntity<Object> deleteById(@PathVariable("repair_id") long repairId, @PathVariable("inventoryId") long inventoryId) {
        repairsItemsService.deleteById(repairId, inventoryId);
        return new ResponseEntity<>("Repair with items deleted", HttpStatus.OK);
    }

}
