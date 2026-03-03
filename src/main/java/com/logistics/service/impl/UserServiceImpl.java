package com.logistics.service.impl;

import com.logistics.repository.User;
import com.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date().getTime());
        user.setUpdatedAt(new Date().getTime());
        user.setStatus(1);
        String sql = "INSERT INTO user (username, password, email, phone, role, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getRole(), user.getStatus(), user.getCreatedAt(), user.getUpdatedAt());
        return user;
    }

    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        if (!users.isEmpty()) {
            User user = users.get(0);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User updateUser(User user) {
        user.setUpdatedAt(new Date().getTime());
        String sql = "UPDATE user SET username = ?, password = ?, email = ?, phone = ?, role = ?, status = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getRole(), user.getStatus(), user.getUpdatedAt(), user.getId());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}