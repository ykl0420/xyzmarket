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
        // 提示：
        // 1. 调用 userService.wxLogin() 获取 userId
        //    Long userId = userService.wxLogin(wxLoginDTO);
        // 2. 生成 JWT token
        //    String token = jwtUtil.generateToken(userId);
        // 3. 封装返回数据
        //    Map<String, Object> data = new HashMap<>();
        //    data.put("userId", userId);
        //    data.put("token", token);
        // 4. 返回 Result.success(data)

        return null;
    }

}
