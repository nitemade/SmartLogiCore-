package com.logistics.service;

import com.logistics.repository.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getAllOrders();
    Order updateOrder(Order order);
    boolean deleteOrder(Long id);
}