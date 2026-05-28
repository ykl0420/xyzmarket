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

        // 放行规则统一集中于拦截器开头
        // 无需认证的接口：登录、搜索商品、商品列表、商品详情
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/user/wxLogin") || uri.equals("/api/item/search")
                || uri.equals("/api/item/list") || uri.matches("/api/item/\\d+")) return true;

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"用户未登录\"}");
            return false;
        }

        String token = header.substring(7);
        if (!jwtUtil.validateToken(token)) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"token无效或已过期\"}");
            return false;
        }
        Long userId = jwtUtil.getUserIdFromToken(token);
        request.setAttribute("userId", userId);
        return true;
    }

}
