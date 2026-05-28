package com.example.xyzmarket.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

    // @Value("${jwt.secret}") 注入进来是一个 String，但jjwt(Java jwt库)接收的是 SecretKey，需要把 String 转成 SecretKey：
    private SecretKey getKey() {
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }



    /**
     * 生成 JWT token
     *
     * @param userId 用户ID
     * @return JWT token 字符串
     */
    public String generateToken(Long userId) {
        // TODO: 实现 JWT token 生成
        // 思路：使用 JWT 库创建 token，包含 userId 和过期时间
        Date now = new Date();
        Date expDate = new Date(now.getTime() + expiration); // 过期时间
        // 利用builder链式调用往jwt中填入信息
        // jwt格式为header.payload.signature，jjwt自动生成header
        // payload的标准字段为sub（用户身份标识）+iat+exp
        return Jwts.builder()
                .subject(userId.toString())    // payload: userId(toString)
                .issuedAt(now)                 // payload: issueTime（签发时间）
                .expiration(expDate)           // payload: expirationTime（过期时间）
                .signWith(getKey())                 // signature
                .compact();                    // 组装完毕，输出完整字符串
    }

    /**
     * 从 token 中解析用户ID
     *
     * @param token JWT token
     * @return 用户ID
     */
    public Long getUserIdFromToken(String token) {
        // TODO: 实现从 token 解析 userId
        String subject = Jwts.parser()
                .verifyWith(getKey())                // 用同样的 key 验证
                .build()
                .parseSignedClaims(token)       // 解析此token
                .getPayload()                   // 取出payload字段
                .getSubject();                  // 取出subject（其中保存userId）
        // subject形式的userId（String）转成Long
        return Long.valueOf(subject);
    }

    /**
     * 验证 token 是否有效
     *
     * @param token JWT token
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        // TODO: 实现 token 验证
        try {
            Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
