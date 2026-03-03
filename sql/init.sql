-- 创建数据库
CREATE DATABASE IF NOT EXISTS logistics DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE logistics;

-- 创建用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    role INT NOT NULL DEFAULT 2, -- 1: admin, 2: user
    status INT NOT NULL DEFAULT 1, -- 1: active, 0: inactive
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建订单表
CREATE TABLE IF NOT EXISTS `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_no VARCHAR(50) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    status INT NOT NULL DEFAULT 1, -- 1: pending, 2: processing, 3: completed, 4: cancelled
    total_amount DOUBLE NOT NULL DEFAULT 0,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建库存表
CREATE TABLE IF NOT EXISTS inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_code VARCHAR(50) NOT NULL UNIQUE,
    quantity INT NOT NULL DEFAULT 0,
    price DOUBLE NOT NULL DEFAULT 0,
    description TEXT,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认管理员用户
INSERT INTO user (username, password, email, phone, role, status, created_at, updated_at)
VALUES ('admin', '$2a$10$eW35F6v5O1m7w5e7u8i9o0p1q2r3s4t5u6v7w8x9y0z', 'admin@example.com', '13800138000', 1, 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000)
ON DUPLICATE KEY UPDATE updated_at = UNIX_TIMESTAMP() * 1000;

-- 插入测试数据
-- 插入库存数据
INSERT INTO inventory (product_name, product_code, quantity, price, description, created_at, updated_at)
VALUES 
('iPhone 15', 'IP15', 50, 6999, '苹果手机', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('MacBook Pro', 'MBP', 20, 12999, '苹果笔记本电脑', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('AirPods Pro', 'APP', 100, 1999, '苹果无线耳机', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000)
ON DUPLICATE KEY UPDATE quantity = VALUES(quantity), price = VALUES(price), updated_at = UNIX_TIMESTAMP() * 1000;