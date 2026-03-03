package com.logistics.repository;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("order")
public class Order {
    @TableId(type = IdType.AUTO)
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