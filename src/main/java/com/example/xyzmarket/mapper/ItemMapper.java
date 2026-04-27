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
     */
    @Insert("""
        INSERT INTO item (title,description,price,image_url,seller_id,status,create_time,update_time)
        VALUES (#{title}, #{description}, #{price}, #{imageUrl}, #{sellerId}, #{status}, #{createTime}, #{updateTime})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Item item);

    /**
     * 根据ID查询商品
     */
    @Select("SELECT * FROM item WHERE id = #{id}")
    Item findById(@Param("id") Long id);

    /**
     * 查询在售商品列表（分页）
     */
    @Select("SELECT * FROM item WHERE status = 0 LIMIT #{size} OFFSET #{offset}")
    List<Item> findList(@Param("offset") int offset, @Param("size") int size);

    /**
     * 统计在售商品总数
     */
    @Select("SELECT COUNT(*) FROM item WHERE status = 0")
    long countItems();

    /**
     * 搜索在售商品（分页）
     * keyword需要在service层处理，例如添加通配符
     */
    @Select("SELECT * FROM item WHERE status = 0 AND (title LIKE #{keyword} OR description LIKE #{keyword}) LIMIT #{size} OFFSET #{offset}")
    List<Item> searchItems(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    /**
     * 统计在售商品符合搜索结果的总数
     * keyword需要在service层处理，例如添加通配符
     */
    @Select("SELECT COUNT(*) FROM item WHERE status = 0 AND (title LIKE #{keyword} OR description LIKE #{keyword})")
    long countSearchResults(@Param("keyword") String keyword);

    /**
     * 根据发布者ID查询商品列表
     */
    @Select("SELECT * FROM item WHERE seller_id = #{sellerId}")
    List<Item> findBySellerId(@Param("sellerId") Long sellerId);

    /**
     * 更新商品状态
     */
    @Update("UPDATE item SET status=#{status} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

}
