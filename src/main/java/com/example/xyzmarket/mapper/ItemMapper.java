package com.example.xyzmarket.mapper;

import com.example.xyzmarket.entity.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品 Mapper
 * 使用 MyBatis 注解方式
 */
@Mapper
public interface ItemMapper {

    /**
     * 插入商品
     * TODO: 实现 SQL
     * 提示：
     * 1. 使用 @Insert 注解
     * 2. INSERT INTO item (title, description, price, image_url, seller_id, status) VALUES (...)
     * 3. 使用 @Options(useGeneratedKeys = true, keyProperty = "id") 获取自增ID
     */
    // TODO: 在这里添加 @Insert 注解和 SQL
    // TODO: 在这里添加 @Options 注解
    int insert(Item item);

    /**
     * 根据ID查询商品
     * TODO: 实现 SQL
     */
    Item findById(@Param("id") Long id);

    /**
     * 查询商品列表（分页）
     * TODO: 实现 SQL
     */
    List<Item> findList(@Param("offset") int offset, @Param("size") int size);

    /**
     * 根据发布者ID查询商品列表
     * TODO: 实现 SQL
     */
    List<Item> findBySellerId(@Param("sellerId") Long sellerId);

    /**
     * 更新商品状态
     * TODO: 实现 SQL
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 统计在售商品总数
     * TODO: 实现 SQL
     * 提示：SELECT COUNT(*) FROM item WHERE status = 1
     */
    long countAvailable();

}
