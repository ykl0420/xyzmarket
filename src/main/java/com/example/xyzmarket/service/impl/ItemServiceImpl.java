package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.common.ErrorCode;
import com.example.xyzmarket.dto.ItemDTO;
import com.example.xyzmarket.entity.Item;
import com.example.xyzmarket.exception.BusinessException;
import com.example.xyzmarket.mapper.ItemMapper;
import com.example.xyzmarket.service.ItemService;
import com.example.xyzmarket.vo.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Long publishItem(ItemDTO itemDTO, Long sellerId) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        item.setSellerId(sellerId);
        LocalDateTime now = LocalDateTime.now();
        item.setCreateTime(now);
        item.setUpdateTime(now);
        itemMapper.insert(item);
        return item.getId();
    }

    @Override
    public PageResult<Item> getItemList(Integer page, Integer size) {
        List<Item> list = itemMapper.findList((page - 1) * size, size);
        Long count = itemMapper.countItems();
        return new PageResult<>(list, count);
    }

    @Override
    public Item getItemById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> getMyItems(Long sellerId) {
        return itemMapper.findBySellerId(sellerId);
    }

    @Override
    public void updateItemStatus(Long id, Integer status, Long userId) {
        Item item = itemMapper.findById(id);
        if(item == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND, "商品不存在");
        }
        if(!item.getSellerId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "无权修改该商品");
        }
        itemMapper.updateStatus(id, status);
    }

    @Override
    public PageResult<Item> searchItems(String keyword, Integer page, Integer size) {

        keyword = "%" + keyword + "%"; // 实现 SQL 模糊匹配

        List<Item> list = itemMapper.searchItems(keyword, (page - 1) * size, size);
        Long count = itemMapper.countSearchResults(keyword);
        return new PageResult<>(list, count);
    }

}
