package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logistics.repository.Inventory;
import com.logistics.repository.InventoryMapper;
import com.logistics.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public Inventory addInventory(Inventory inventory) {
        inventory.setCreatedAt(new Date().getTime());
        inventory.setUpdatedAt(new Date().getTime());
        inventoryMapper.insert(inventory);
        return inventory;
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryMapper.selectById(id);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryMapper.selectList(null);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        inventory.setUpdatedAt(new Date().getTime());
        inventoryMapper.updateById(inventory);
        return inventory;
    }

    @Override
    public boolean deleteInventory(Long id) {
        return inventoryMapper.deleteById(id) > 0;
    }

    @Override
    public List<Inventory> getLowStockInventory(Integer threshold) {
        QueryWrapper<Inventory> wrapper = new QueryWrapper<>();
        wrapper.lt("quantity", threshold);
        return inventoryMapper.selectList(wrapper);
    }
}