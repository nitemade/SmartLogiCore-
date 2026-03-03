package com.logistics.repository;

import lombok.Data;

@Data
public class Inventory {
    private Long id;
    private String productName;
    private String productCode;
    private Integer quantity;
    private Double price;
    private String description;
    private Long createdAt;
    private Long updatedAt;
}