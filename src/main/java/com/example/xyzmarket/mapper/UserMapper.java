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
    int insert(User user);

    /**
     * 根据 openid 查询用户
     * TODO: 实现 SQL
     */
    User findByOpenid(@Param("openid") String openid);

    /**
     * 根据ID查询用户
     * TODO: 实现 SQL
     */
    User findById(@Param("id") Long id);

}
