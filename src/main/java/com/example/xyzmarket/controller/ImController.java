package com.example.xyzmarket.controller;

import com.example.xyzmarket.common.Result;
import com.example.xyzmarket.service.ImService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * IM 相关接口
 */
@RestController
@RequestMapping("/api/im")
public class ImController {

    @Autowired
    private ImService imService;

    /**
     * 获取腾讯云 IM UserSig
     * 需要 JWT 认证，userId 从 token 获取
     */
    @GetMapping("/getUserSig")
    public Result<Map<String, String>> getUserSig(HttpServletRequest request) {
        // TODO: 实现获取 UserSig 接口
        // 提示：
        // 1. 从 request attribute 获取 userId
        //    Long userId = (Long) request.getAttribute("userId");
        // 2. 调用 imService.generateUserSig(userId)
        //    String userSig = imService.generateUserSig(userId);
        // 3. 封装返回数据
        //    Map<String, String> data = new HashMap<>();
        //    data.put("userId", String.valueOf(userId));
        //    data.put("userSig", userSig);
        // 4. 返回 Result.success(data)

        return null;
    }

}
