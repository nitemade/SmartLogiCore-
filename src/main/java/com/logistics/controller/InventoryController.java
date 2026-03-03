package com.logistics.controller;

import com.logistics.repository.Inventory;
import com.logistics.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @GetMapping("/info/{id}")
    public Inventory getInventoryInfo(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/all")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PutMapping("/update")
    public Inventory updateInventory(@RequestBody Inventory inventory) {
        return inventoryService.updateInventory(inventory);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteInventory(@PathVariable Long id) {
        return inventoryService.deleteInventory(id);
    }

    @GetMapping("/low-stock/{threshold}")
    public List<Inventory> getLowStockInventory(@PathVariable Integer threshold) {
        return inventoryService.getLowStockInventory(threshold);
    }
}