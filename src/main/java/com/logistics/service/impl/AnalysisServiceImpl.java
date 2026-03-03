package com.logistics.service.impl;

import com.logistics.repository.Inventory;
import com.logistics.repository.Order;
import com.logistics.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getOrderAnalysis() {
        String sql = "SELECT * FROM `order`";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        Map<String, Object> analysis = new HashMap<>();
        
        int totalOrders = orders.size();
        int pendingOrders = 0;
        int processingOrders = 0;
        int completedOrders = 0;
        int cancelledOrders = 0;
        double totalAmount = 0;

        for (Order order : orders) {
            totalAmount += order.getTotalAmount();
            switch (order.getStatus()) {
                case 1: pendingOrders++;
                    break;
                case 2: processingOrders++;
                    break;
                case 3: completedOrders++;
                    break;
                case 4: cancelledOrders++;
                    break;
            }
        }

        analysis.put("totalOrders", totalOrders);
        analysis.put("pendingOrders", pendingOrders);
        analysis.put("processingOrders", processingOrders);
        analysis.put("completedOrders", completedOrders);
        analysis.put("cancelledOrders", cancelledOrders);
        analysis.put("totalAmount", totalAmount);

        return analysis;
    }

    @Override
    public Map<String, Object> getInventoryAnalysis() {
        String sql = "SELECT * FROM inventory";
        List<Inventory> inventories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inventory.class));
        Map<String, Object> analysis = new HashMap<>();

        int totalProducts = inventories.size();
        int lowStockProducts = 0;
        int totalQuantity = 0;
        double totalValue = 0;

        for (Inventory inventory : inventories) {
            totalQuantity += inventory.getQuantity();
            totalValue += inventory.getPrice() * inventory.getQuantity();
            if (inventory.getQuantity() < 10) { // 假设阈值为10
                lowStockProducts++;
            }
        }

        analysis.put("totalProducts", totalProducts);
        analysis.put("lowStockProducts", lowStockProducts);
        analysis.put("totalQuantity", totalQuantity);
        analysis.put("totalValue", totalValue);

        return analysis;
    }

    @Override
    public Map<String, Object> getSalesAnalysis() {
        String sql = "SELECT * FROM `order`";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        Map<String, Object> analysis = new HashMap<>();

        double totalSales = 0;
        int completedOrders = 0;

        for (Order order : orders) {
            if (order.getStatus() == 3) { // 已完成的订单
                totalSales += order.getTotalAmount();
                completedOrders++;
            }
        }

        analysis.put("totalSales", totalSales);
        analysis.put("completedOrders", completedOrders);
        if (completedOrders > 0) {
            analysis.put("averageOrderValue", totalSales / completedOrders);
        } else {
            analysis.put("averageOrderValue", 0);
        }

        return analysis;
    }

    @Override
    public Map<String, Object> generateReport() {
        Map<String, Object> report = new HashMap<>();
        report.put("orderAnalysis", getOrderAnalysis());
        report.put("inventoryAnalysis", getInventoryAnalysis());
        report.put("salesAnalysis", getSalesAnalysis());
        return report;
    }
}