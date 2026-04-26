package com.example.xyzmarket.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT 工具类
 * 用于生成和解析 JWT token
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成 JWT token
     *
     * @param userId 用户ID
     * @return JWT token 字符串
     */
    public String generateToken(Long userId) {
        // TODO: 实现 JWT token 生成
        // 思路：使用 JWT 库创建 token，包含 userId 和过期时间
        return null;
    }

    /**
     * 从 token 中解析用户ID
     *
     * @param token JWT token
     * @return 用户ID
     */
    public Long getUserIdFromToken(String token) {
        // TODO: 实现从 token 解析 userId
        return null;
    }

    /**
     * 验证 token 是否有效
     *
     * @param token JWT token
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        // TODO: 实现 token 验证
        return false;
    }

}
