package com.example.xyzmarket.controller;

import com.example.xyzmarket.common.Result;
import com.example.xyzmarket.dto.OrderDTO;
import com.example.xyzmarket.dto.ReviewDTO;
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
        Long buyerId = (Long)request.getAttribute("userId");
        Long orderId = orderService.createOrder(orderDTO, buyerId);
        return Result.success(orderId);
    }

    /**
     * 我的订单
     * 需要 JWT 认证，userId 从 token 获取
     */
    @GetMapping("/my")
    public Result<List<Order>> getMyOrders(HttpServletRequest request) {
        Long userId = (Long)request.getAttribute("userId");
        List<Order> orderList = orderService.getMyOrders(userId);
        return Result.success(orderList);
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
        Long userId = (Long)request.getAttribute("userId");
        orderService.updateOrderStatus(id, body.get("status"), userId);
        Map<String, Object> result = new HashMap<>();
        return Result.success(result);
    }

    /**
     * 提交订单评价
     * 需要 JWT 认证，仅买家可操作
     */
    @PostMapping("/{id}/review")
    public Result<Map<String, Object>> submitReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewDTO reviewDTO,
            HttpServletRequest request) {
        Long userId = (Long)request.getAttribute("userId");
        orderService.submitReview(id, reviewDTO.getRating(), reviewDTO.getReview(), userId);
        Map<String, Object> result = new HashMap<>();
        return Result.success(result);
    }
}
