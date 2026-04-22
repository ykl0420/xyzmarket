package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.config.WxConfig;
import com.example.xyzmarket.dto.WxLoginDTO;
import com.example.xyzmarket.entity.User;
import com.example.xyzmarket.mapper.UserMapper;
import com.example.xyzmarket.service.UserService;
import com.example.xyzmarket.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private WxConfig wxConfig;

    @Override
    public Long wxLogin(WxLoginDTO wxLoginDTO) {
        // TODO: 实现微信登录逻辑
        // 提示：
        // 1. 调用微信 API 获取 openid
        //    String result = httpClientUtil.code2Session(
        //        wxConfig.getAppid(), wxConfig.getSecret(), wxLoginDTO.getCode());
        // 2. 解析 JSON 获取 openid
        // 3. 根据 openid 查询用户
        //    User user = userMapper.findByOpenid(openid);
        // 4. 如果用户不存在，创建新用户
        //    if (user == null) {
        //        user = new User();
        //        user.setOpenid(openid);
        //        userMapper.insert(user);
        //    }
        // 5. 返回 user.getId()

        return null;
    }

    @Override
    public User getUserByOpenid(String openid) {
        // TODO: 根据 openid 查询用户
        // 提示：直接调用 userMapper.findByOpenid(openid)

        return null;
    }

}
