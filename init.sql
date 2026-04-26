-- 创建数据库
CREATE DATABASE IF NOT EXISTS xyz_market DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE xyz_market;

-- 用户表（微信小程序版：使用 openid 替代 username/password）
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    openid VARCHAR(64) NOT NULL UNIQUE COMMENT '微信 openid',
    nickname VARCHAR(50) COMMENT '昵称',
    avatar_url VARCHAR(255) COMMENT '头像URL',
    phone VARCHAR(20) COMMENT '联系电话',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_openid (openid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 商品表
CREATE TABLE item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    title VARCHAR(100) NOT NULL COMMENT '商品标题',
    description TEXT COMMENT '商品描述',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    image_url VARCHAR(255) COMMENT '图片URL',
    seller_id BIGINT NOT NULL COMMENT '发布者ID',
    status INT DEFAULT 0 COMMENT '状态：0在售 1已售',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_seller_id (seller_id),
    INDEX idx_status (status),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 订单表（新增）
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    item_id BIGINT NOT NULL COMMENT '商品ID',
    buyer_id BIGINT NOT NULL COMMENT '买家ID',
    seller_id BIGINT NOT NULL COMMENT '卖家ID',
    status INT DEFAULT 0 COMMENT '状态：0待联系 1已完成 2已取消',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_buyer (buyer_id),
    INDEX idx_seller (seller_id),
    INDEX idx_item (item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
