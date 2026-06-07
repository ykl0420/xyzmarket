package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.common.ErrorCode;
import com.example.xyzmarket.dto.OrderDTO;
import com.example.xyzmarket.entity.Item;
import com.example.xyzmarket.entity.Order;
import com.example.xyzmarket.exception.BusinessException;
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
        Item item = itemService.getItemById(orderDTO.getItemId());
        if (item == null) throw new BusinessException(ErrorCode.NOT_FOUND, "商品不存在");
        if (item.getStatus() != 0) throw new BusinessException(ErrorCode.FORBIDDEN, "商品已售出或下架");
        if (item.getSellerId().equals(buyerId)) throw new BusinessException(ErrorCode.FORBIDDEN, "不能购买自己的商品");

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
        List<Order> orderList = new ArrayList<>();
        orderList.addAll(orderMapper.findByBuyerId(userId));
        orderList.addAll(orderMapper.findBySellerId(userId));

        return orderList;
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Integer status, Long userId) {
        Order order = orderMapper.findById(orderId);
        if (order == null) throw new BusinessException(ErrorCode.NOT_FOUND, "订单不存在");
        if (!userId.equals(order.getBuyerId()) && !userId.equals(order.getSellerId())) throw new BusinessException(ErrorCode.FORBIDDEN, "无权操作此订单");

        orderMapper.updateStatus(orderId, status, LocalDateTime.now());
        return true;
    }

    @Override
    public void submitReview(Long orderId, Integer rating, String review, Long userId) {
        Order order = orderMapper.findById(orderId);
        if (order == null) throw new BusinessException(ErrorCode.NOT_FOUND, "订单不存在");
        if (!userId.equals(order.getBuyerId())) throw new BusinessException(ErrorCode.FORBIDDEN, "仅买家可以评价订单");
        if (order.getStatus() != 1) throw new BusinessException(ErrorCode.FORBIDDEN, "订单未完成，无法评价");
        if (order.getRating() != null) throw new BusinessException(ErrorCode.FORBIDDEN, "该订单已评价");

        orderMapper.submitReview(orderId, rating, review, LocalDateTime.now());
    }
}
