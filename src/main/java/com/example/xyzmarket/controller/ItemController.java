package com.example.xyzmarket.controller;

import com.example.xyzmarket.common.Result;
import com.example.xyzmarket.dto.ItemDTO;
import com.example.xyzmarket.entity.Item;
import com.example.xyzmarket.service.ItemService;
import com.example.xyzmarket.vo.PageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 发布商品
     * 需要 JWT 认证，sellerId 从 token 获取
     */
    @PostMapping
    public Result<Long> publishItem(@Valid @RequestBody ItemDTO itemDTO, HttpServletRequest request) {
        // TODO: 实现发布商品接口
        // 提示：
        // 1. 从 request attribute 获取 userId（由拦截器设置）
        //    Long sellerId = (Long) request.getAttribute("userId");
        // 2. 调用 itemService.publishItem(itemDTO, sellerId)
        // 3. 返回 Result.success(itemId)

        return null;
    }

    /**
     * 获取商品列表（分页）
     * 无需认证
     */
    @GetMapping("/list")
    public Result<PageResult<Item>> getItemList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        // TODO: 实现商品列表查询接口
        // 提示：
        // 1. 调用 itemService.getItemList(page, size)
        // 2. 返回 Result.success(pageResult)
        // 注意：返回的是 PageResult 对象，包含 list 和 total 两个字段

        return null;
    }

    /**
     * 获取商品详情
     * 无需认证
     */
    @GetMapping("/{id}")
    public Result<Item> getItemById(@PathVariable Long id) {
        // TODO: 实现商品详情查询接口
        // 提示：
        // 1. 调用 itemService.getItemById(id)
        // 2. 返回 Result.success(item)

        return null;
    }

    /**
     * 我的发布
     * 需要 JWT 认证，sellerId 从 token 获取
     */
    @GetMapping("/my")
    public Result<List<Item>> getMyItems(HttpServletRequest request) {
        // TODO: 实现查询我的发布接口
        // 提示：
        // 1. 从 request attribute 获取 userId
        //    Long sellerId = (Long) request.getAttribute("userId");
        // 2. 调用 itemService.getMyItems(sellerId)
        // 3. 返回 Result.success(itemList)

        return null;
    }

    /**
     * 更新商品状态
     * 需要 JWT 认证，仅发布者可操作
     */
    @PutMapping("/{id}/status")
    public Result<Map<String, Object>> updateItemStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body,
            HttpServletRequest request) {
        // TODO: 实现更新商品状态接口
        // 提示：
        // 1. 从 request attribute 获取 userId
        //    Long userId = (Long) request.getAttribute("userId");
        // 2. 从 body 获取 status
        //    Integer status = body.get("status");
        // 3. 调用 itemService.updateItemStatus(id, status, userId)
        // 4. 返回 Result.success(new HashMap<>())

        return null;
    }

}
