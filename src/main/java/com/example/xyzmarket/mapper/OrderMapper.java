package com.example.xyzmarket.mapper;

import com.example.xyzmarket.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单 Mapper
 * 使用 MyBatis 注解方式
 */
@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     * TODO: 实现 SQL
     */
    int insert(Order order);

    /**
     * 根据买家ID查询订单列表
     * TODO: 实现 SQL
     */
    List<Order> findByBuyerId(@Param("buyerId") Long buyerId);

    /**
     * 根据卖家ID查询订单列表
     * TODO: 实现 SQL
     */
    List<Order> findBySellerId(@Param("sellerId") Long sellerId);

    /**
     * 根据ID查询订单
     * TODO: 实现 SQL
     */
    Order findById(@Param("id") Long id);

    /**
     * 更新订单状态
     * TODO: 实现 SQL
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

}
