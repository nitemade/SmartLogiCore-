package com.logistics.repository;

import java.util.List;

public interface InventoryMapper {
    void insert(Inventory inventory);
    Inventory selectById(Long id);
    List<Inventory> selectAll();
    List<Inventory> selectLowStock(int threshold);
    void updateById(Inventory inventory);
    int deleteById(Long id);
}