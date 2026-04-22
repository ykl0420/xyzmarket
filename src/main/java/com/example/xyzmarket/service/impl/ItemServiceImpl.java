package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.dto.ItemDTO;
import com.example.xyzmarket.entity.Item;
import com.example.xyzmarket.mapper.ItemMapper;
import com.example.xyzmarket.service.ItemService;
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
        // 提示：
        // 1. 创建 Item 对象
        //    Item item = new Item();
        // 2. 复制属性
        //    BeanUtils.copyProperties(itemDTO, item);
        // 3. 设置发布者和初始状态
        //    item.setSellerId(sellerId);
        //    item.setStatus(0);  // 0-在售
        // 4. 调用 itemMapper.insert(item) 保存商品
        // 5. 返回 item.getId()

        return null;
    }

    @Override
    public List<Item> getItemList(Integer page, Integer size) {
        // TODO: 实现商品列表查询（分页）
        // 提示：
        // 1. 计算偏移量：int offset = (page - 1) * size;
        // 2. 查询商品列表：List<Item> items = itemMapper.findList(offset, size);
        // 3. 返回 items

        return null;
    }

    @Override
    public Item getItemById(Long id) {
        // TODO: 根据ID查询商品详情
        // 提示：直接调用 itemMapper.findById(id)

        return null;
    }

    @Override
    public List<Item> getMyItems(Long sellerId) {
        // TODO: 查询我的发布列表
        // 提示：直接调用 itemMapper.findBySellerId(sellerId)

        return null;
    }

    @Override
    public void updateItemStatus(Long id, Integer status, Long userId) {
        // TODO: 更新商品状态
        // 提示：
        // 1. 根据ID查询商品
        //    Item item = itemMapper.findById(id);
        // 2. 校验权限：只有发布者可以修改
        //    if (!item.getSellerId().equals(userId)) throw new RuntimeException("无权操作");
        // 3. 调用 itemMapper.updateStatus(id, status)

    }

}
