package com.example.xyzmarket.service;

import com.example.xyzmarket.dto.WxLoginDTO;
import com.example.xyzmarket.entity.User;

public interface UserService {

    /**
     * 微信登录
     * @param wxLoginDTO 包含微信 code
     * @return 登录成功的用户ID
     */
    Long wxLogin(WxLoginDTO wxLoginDTO);

    /**
     * 根据 openid 查询用户
     * @param openid 微信 openid
     * @return 用户信息
     */
    User getUserByOpenid(String openid);

}
