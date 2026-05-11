package com.example.xyzmarket.mapper;

import com.example.xyzmarket.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * 用户 Mapper
 * 使用 MyBatis 注解方式
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户
     * TODO: 实现 SQL
     */
    @Insert("""
        INSERT INTO user(openid, nickname, avatar_url, phone, create_time, update_time)
        VALUES (#{openid}, #{nickname}, #{avatarUrl}, #{phone}, #{createTime}, #{updateTime})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User user);

    /**
     * 根据 openid 查询用户
     * TODO: 实现 SQL
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User findByOpenid(@Param("openid") String openid);

    /**
     * 根据ID查询用户
     * TODO: 实现 SQL
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Long id);

}
