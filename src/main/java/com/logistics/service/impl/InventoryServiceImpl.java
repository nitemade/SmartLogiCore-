package com.logistics.service.impl;

import com.logistics.repository.Inventory;
import com.logistics.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Inventory addInventory(Inventory inventory) {
        inventory.setCreatedAt(new Date().getTime());
        inventory.setUpdatedAt(new Date().getTime());
        String sql = "INSERT INTO inventory (product_name, product_code, quantity, price, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, inventory.getProductName(), inventory.getProductCode(), inventory.getQuantity(), inventory.getPrice(), inventory.getDescription(), inventory.getCreatedAt(), inventory.getUpdatedAt());
        return inventory;
    }

    @Override
    public Inventory getInventoryById(Long id) {
        String sql = "SELECT * FROM inventory WHERE id = ?";
        List<Inventory> inventories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inventory.class), id);
        return inventories.isEmpty() ? null : inventories.get(0);
    }

    @Override
    public List<Inventory> getAllInventory() {
        String sql = "SELECT * FROM inventory";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inventory.class));
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        inventory.setUpdatedAt(new Date().getTime());
        String sql = "UPDATE inventory SET product_name = ?, product_code = ?, quantity = ?, price = ?, description = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, inventory.getProductName(), inventory.getProductCode(), inventory.getQuantity(), inventory.getPrice(), inventory.getDescription(), inventory.getUpdatedAt(), inventory.getId());
        return inventory;
    }

    @Override
    public boolean deleteInventory(Long id) {
        String sql = "DELETE FROM inventory WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public List<Inventory> getLowStockInventory(Integer threshold) {
        String sql = "SELECT * FROM inventory WHERE quantity < ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inventory.class), threshold);
    }
}