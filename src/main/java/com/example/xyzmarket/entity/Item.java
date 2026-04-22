package com.example.xyzmarket.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体类
 */
@Data
public class Item {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private Long sellerId;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
