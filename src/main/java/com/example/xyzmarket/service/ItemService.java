package com.example.xyzmarket.service;

import com.example.xyzmarket.dto.ItemDTO;
import com.example.xyzmarket.entity.Item;

import java.util.List;

public interface ItemService {

    /**
     * 发布商品
     * @param itemDTO 商品信息
     * @param sellerId 发布者ID（从 token 获取）
     * @return 商品ID
     */
    Long publishItem(ItemDTO itemDTO, Long sellerId);

    /**
     * 获取商品列表（分页）
     * @param page 页码
     * @param size 每页数量
     * @return 商品列表
     */
    List<Item> getItemList(Integer page, Integer size);

    /**
     * 获取商品详情
     * @param id 商品ID
     * @return 商品信息
     */
    Item getItemById(Long id);

    /**
     * 我的发布
     * @param sellerId 发布者ID（从 token 获取）
     * @return 商品列表
     */
    List<Item> getMyItems(Long sellerId);

    /**
     * 更新商品状态
     * @param id 商品ID
     * @param status 状态
     * @param userId 操作用户ID（从 token 获取，用于权限校验）
     */
    void updateItemStatus(Long id, Integer status, Long userId);

}
