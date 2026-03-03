package com.logistics.repository;

public interface UserMapper {
    void insert(User user);
    User selectByUsername(String username);
    User selectById(Long id);
    void updateById(User user);
    int deleteById(Long id);
}