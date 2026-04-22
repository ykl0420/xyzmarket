package com.example.xyzmarket.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类（微信小程序版）
 */
@Data
public class User {

    private Long id;

    private String openid;

    private String nickname;

    private String avatarUrl;

    private String phone;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
