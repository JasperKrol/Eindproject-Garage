package com.eqriesracingteam.garage.controller;

import com.eqriesracingteam.garage.model.RepairItems;
import com.eqriesracingteam.garage.model.RepairsItemsKey;
import com.eqriesracingteam.garage.service.RepairsItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class RepairsItemsController {

    @Autowired
    private RepairsItemsService repairsItemsService;

    @GetMapping(value = "/repairs_items")
    public ResponseEntity<Object> getAllRepairsWithItems() {
        List<RepairItems> repairItems = repairsItemsService.getAllRepairsWithItems();
        return ResponseEntity.ok(repairItems);
    }

    @GetMapping(value = "/repairs_items/{id}")
    public ResponseEntity<Object> getRepairWithItems(@PathVariable("id") long id) {
        return ResponseEntity.ok(repairsItemsService.getRepairWithItems(id));
    }

    @PostMapping(value = "/repairs_items")
    public ResponseEntity<Object> createRepairWithItems(@RequestBody RepairItems repairItems) {
        RepairsItemsKey newId = repairsItemsService.save(repairItems);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/repairs_items/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        repairsItemsService.deleteById(id);
        return new ResponseEntity<>("Repair with items deleted", HttpStatus.OK);
    }
}
