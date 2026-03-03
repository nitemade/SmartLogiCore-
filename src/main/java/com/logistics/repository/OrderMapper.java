package com.logistics.repository;

import java.util.List;

public interface OrderMapper {
    void insert(Order order);
    Order selectById(Long id);
    List<Order> selectByUserId(Long userId);
    List<Order> selectAll();
    void updateById(Order order);
    int deleteById(Long id);
}