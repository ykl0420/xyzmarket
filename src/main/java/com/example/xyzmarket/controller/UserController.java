package com.example.xyzmarket.controller;

import com.example.xyzmarket.common.Result;
import com.example.xyzmarket.dto.WxLoginDTO;
import com.example.xyzmarket.service.UserService;
import com.example.xyzmarket.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 微信登录
     */
    @PostMapping("/wxLogin")
    public Result<Map<String, Object>> wxLogin(@Valid @RequestBody WxLoginDTO wxLoginDTO) {
        // TODO: 实现微信登录接口
        // 思路：调用 service 获取用户ID，生成 JWT token，封装返回

        Long userId = userService.wxLogin(wxLoginDTO);
        String jwtToken = jwtUtil.generateToken(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        data.put("token", jwtToken);
        return Result.success(data);
    }

}
