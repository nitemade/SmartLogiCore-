package com.logistics.service.impl;

import com.logistics.repository.Order;
import com.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Order createOrder(Order order) {
        order.setStatus(1); // 1: 待处理
        order.setOrderNo("ORDER" + System.currentTimeMillis());
        order.setCreatedAt(new Date().getTime());
        order.setUpdatedAt(new Date().getTime());
        String sql = "INSERT INTO `order` (user_id, order_no, total_amount, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, order.getUserId(), order.getOrderNo(), order.getTotalAmount(), order.getStatus(), order.getCreatedAt(), order.getUpdatedAt());
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        String sql = "SELECT * FROM `order` WHERE id = ?";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), id);
        return orders.isEmpty() ? null : orders.get(0);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        String sql = "SELECT * FROM `order` WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), userId);
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM `order`";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public Order updateOrder(Order order) {
        order.setUpdatedAt(new Date().getTime());
        String sql = "UPDATE `order` SET user_id = ?, order_no = ?, total_amount = ?, status = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, order.getUserId(), order.getOrderNo(), order.getTotalAmount(), order.getStatus(), order.getUpdatedAt(), order.getId());
        return order;
    }

    @Override
    public boolean deleteOrder(Long id) {
        String sql = "DELETE FROM `order` WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}