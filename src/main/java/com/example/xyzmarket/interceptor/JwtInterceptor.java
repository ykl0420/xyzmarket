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
        // 提示：
        // 1. 从请求头获取 token
        //    String token = request.getHeader("Authorization");
        // 2. 验证 token 是否有效
        //    if (token == null || !jwtUtil.validateToken(token)) {
        //        response.setStatus(401);
        //        return false;
        //    }
        // 3. 解析 userId 并存入 request attribute
        //    Long userId = jwtUtil.getUserIdFromToken(token);
        //    request.setAttribute("userId", userId);
        // 4. 返回 true 放行

        return true;
    }

}
