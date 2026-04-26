package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.dto.ItemDTO;
import com.example.xyzmarket.entity.Item;
import com.example.xyzmarket.mapper.ItemMapper;
import com.example.xyzmarket.service.ItemService;
import com.example.xyzmarket.vo.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Long publishItem(ItemDTO itemDTO, Long sellerId) {
        // TODO: 实现商品发布逻辑
        // 思路：将 DTO 转为实体，设置发布者和初始状态，保存到数据库
        // 提示：可以使用 BeanUtils.copyProperties() 复制属性

        return null;
    }

    @Override
    public PageResult<Item> getItemList(Integer page, Integer size) {
        // TODO: 实现商品列表查询（分页）
        // 思路：计算偏移量，查询商品列表和总数，封装成 PageResult 返回

        return null;
    }

    @Override
    public Item getItemById(Long id) {
        // TODO: 根据ID查询商品详情

        return null;
    }

    @Override
    public List<Item> getMyItems(Long sellerId) {
        // TODO: 查询我的发布列表

        return null;
    }

    @Override
    public void updateItemStatus(Long id, Integer status, Long userId) {
        // TODO: 更新商品状态
        // 思路：先查询商品，校验权限（只有发布者可以修改），再更新状态

    }

    @Override
    public PageResult<Item> searchItems(String keyword, Integer page, Integer size) {
        // TODO: 实现商品搜索（分页）
        // 思路：计算偏移量，调用 mapper 搜索商品和统计总数，封装成 PageResult 返回

        return null;
    }

}
