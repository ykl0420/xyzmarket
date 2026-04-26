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
        // 思路：从 request 获取当前用户ID，调用 service 发布商品

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
        // 思路：调用 service 获取分页数据并返回

        return null;
    }

    /**
     * 搜索商品（分页）
     * 无需认证
     */
    @GetMapping("/search")
    public Result<PageResult<Item>> searchItems(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        // TODO: 实现商品搜索接口
        // 思路：调用 service 搜索商品并返回分页结果

        return null;
    }

    /**
     * 获取商品详情
     * 无需认证
     */
    @GetMapping("/{id}")
    public Result<Item> getItemById(@PathVariable Long id) {
        // TODO: 实现商品详情查询接口

        return null;
    }

    /**
     * 我的发布
     * 需要 JWT 认证，sellerId 从 token 获取
     */
    @GetMapping("/my")
    public Result<List<Item>> getMyItems(HttpServletRequest request) {
        // TODO: 实现查询我的发布接口
        // 思路：从 request 获取当前用户ID，查询该用户发布的商品

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
        // 思路：获取用户ID和新状态，调用 service 更新

        return null;
    }

}
