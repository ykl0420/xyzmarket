package com.example.xyzmarket.interceptor;

import com.example.xyzmarket.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT 拦截器
 * 用于验证请求中的 token
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO: 实现 token 验证逻辑
        // 思路：从请求头获取 token，验证有效性，解析 userId 并存入 request attribute

        return true;
    }

}
