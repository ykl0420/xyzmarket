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
    @Insert("""
        INSERT INTO orders (item_id, buyer_id, seller_id, status, create_time, update_time)
        VALUES (#{itemId}, #{buyerId}, #{sellerId}, #{status}, #{createTime}, #{updateTime})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);

    /**
     * 根据买家ID查询订单列表
     * TODO: 实现 SQL
     */
    @Select("SELECT * FROM orders WHERE buyer_id = #{buyerId}")
    List<Order> findByBuyerId(@Param("buyerId") Long buyerId);

    /**
     * 根据卖家ID查询订单列表
     * TODO: 实现 SQL
     */
    @Select("SELECT * FROM orders WHERE seller_id = #{sellerId}")
    List<Order> findBySellerId(@Param("sellerId") Long sellerId);

    /**
     * 根据ID查询订单
     * TODO: 实现 SQL
     */
    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order findById(@Param("id") Long id);

    /**
     * 更新订单状态
     * TODO: 实现 SQL
     */
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

}
