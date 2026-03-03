package com.logistics.service;

import com.logistics.repository.User;

public interface UserService {
    User register(User user);
    User login(String username, String password);
    User getUserById(Long id);
    User updateUser(User user);
    boolean deleteUser(Long id);
}