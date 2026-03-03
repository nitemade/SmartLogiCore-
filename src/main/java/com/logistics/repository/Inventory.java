package com.logistics.repository;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("inventory")
public class Inventory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String productName;
    private String productCode;
    private Integer quantity;
    private Double price;
    private String description;
    private Long createdAt;
    private Long updatedAt;
}