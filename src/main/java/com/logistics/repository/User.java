package com.logistics.repository;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer role; // 1: admin, 2: user
    private Integer status; // 1: active, 0: inactive
    private Long createdAt;
    private Long updatedAt;
}