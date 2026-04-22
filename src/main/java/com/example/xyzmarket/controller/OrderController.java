package com.example.xyzmarket.controller;

import com.example.xyzmarket.common.Result;
import com.example.xyzmarket.dto.OrderDTO;
import com.example.xyzmarket.entity.Order;
import com.example.xyzmarket.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * 需要 JWT 认证，buyerId 从 token 获取
     */
    @PostMapping
    public Result<Long> createOrder(@Valid @RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        // TODO: 实现创建订单接口
        // 提示：
        // 1. 从 request attribute 获取 userId
        //    Long buyerId = (Long) request.getAttribute("userId");
        // 2. 调用 orderService.createOrder(orderDTO, buyerId)
        // 3. 返回 Result.success(orderId)

        return null;
    }

    /**
     * 我的订单
     * 需要 JWT 认证，userId 从 token 获取
     */
    @GetMapping("/my")
    public Result<List<Order>> getMyOrders(HttpServletRequest request) {
        // TODO: 实现查询我的订单接口
        // 提示：
        // 1. 从 request attribute 获取 userId
        //    Long userId = (Long) request.getAttribute("userId");
        // 2. 调用 orderService.getMyOrders(userId)
        // 3. 返回 Result.success(orderList)

        return null;
    }

    /**
     * 更新订单状态
     * 需要 JWT 认证，仅买家或卖家可操作
     */
    @PutMapping("/{id}/status")
    public Result<Map<String, Object>> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body,
            HttpServletRequest request) {
        // TODO: 实现更新订单状态接口
        // 提示：
        // 1. 从 request attribute 获取 userId
        //    Long userId = (Long) request.getAttribute("userId");
        // 2. 从 body 获取 status
        //    Integer status = body.get("status");
        // 3. 调用 orderService.updateOrderStatus(id, status, userId)
        // 4. 返回 Result.success(new HashMap<>())

        return null;
    }

}
