package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.dto.OrderDTO;
import com.example.xyzmarket.entity.Item;
import com.example.xyzmarket.entity.Order;
import com.example.xyzmarket.mapper.OrderMapper;
import com.example.xyzmarket.service.ItemService;
import com.example.xyzmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemService itemService;

    @Override
    public Long createOrder(OrderDTO orderDTO, Long buyerId) {
        // TODO: 实现创建订单逻辑
        // 思路：根据商品ID获取卖家ID，创建订单对象并设置初始状态，保存到数据库

        Item item = itemService.getItemById(orderDTO.getItemId());
        if (item == null) throw new RuntimeException("商品不存在");
        if (item.getStatus() != 0) throw new RuntimeException("商品已售出或下架");
        if (item.getSellerId().equals(buyerId)) throw new RuntimeException("不能购买自己的商品");

        Order order = new Order();
        order.setItemId(orderDTO.getItemId());
        order.setBuyerId(buyerId);
        order.setSellerId(item.getSellerId());
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        orderMapper.insert(order);

        return order.getId();
    }

    @Override
    public List<Order> getMyOrders(Long userId) {
        // TODO: 实现查询我的订单逻辑
        // 思路：查询作为买家和卖家的所有订单，合并后返回

        List<Order> orderList = new ArrayList<>();
        orderList.addAll(orderMapper.findByBuyerId(userId));
        orderList.addAll(orderMapper.findBySellerId(userId));

        return orderList;
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Integer status, Long userId) {
        // TODO: 实现更新订单状态逻辑
        // 思路：先查询订单，校验权限（买家或卖家），再更新状态

        Order order = orderMapper.findById(orderId);
        if (order == null) throw new RuntimeException("订单不存在");
        if (!userId.equals(order.getBuyerId()) && !userId.equals(order.getSellerId())) throw new RuntimeException("无权操作此订单");

        orderMapper.updateStatus(orderId, status, LocalDateTime.now());
        return true;
    }

}
