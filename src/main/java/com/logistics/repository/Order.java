package com.logistics.repository;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String orderNo;
    private Long userId;
    private String customerName;
    private String customerPhone;
    private String address;
    private Integer status; // 1: pending, 2: processing, 3: completed, 4: cancelled
    private Double totalAmount;
    private Long createdAt;
    private Long updatedAt;
}