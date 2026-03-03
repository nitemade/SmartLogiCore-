package com.logistics.service;

import com.logistics.repository.Inventory;
import java.util.List;

public interface InventoryService {
    Inventory addInventory(Inventory inventory);
    Inventory getInventoryById(Long id);
    List<Inventory> getAllInventory();
    Inventory updateInventory(Inventory inventory);
    boolean deleteInventory(Long id);
    List<Inventory> getLowStockInventory(Integer threshold);
}