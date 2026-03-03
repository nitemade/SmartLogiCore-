package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logistics.repository.Order;
import com.logistics.repository.OrderMapper;
import com.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order createOrder(Order order) {
        order.setOrderNo("ORDER" + System.currentTimeMillis());
        order.setStatus(1);
        order.setCreatedAt(new Date().getTime());
        order.setUpdatedAt(new Date().getTime());
        orderMapper.insert(order);
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return orderMapper.selectList(wrapper);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectList(null);
    }

    @Override
    public Order updateOrder(Order order) {
        order.setUpdatedAt(new Date().getTime());
        orderMapper.updateById(order);
        return order;
    }

    @Override
    public boolean deleteOrder(Long id) {
        return orderMapper.deleteById(id) > 0;
    }
}