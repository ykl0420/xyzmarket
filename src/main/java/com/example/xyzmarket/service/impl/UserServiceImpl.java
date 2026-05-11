package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.config.WxConfig;
import com.example.xyzmarket.dto.WxLoginDTO;
import com.example.xyzmarket.entity.User;
import com.example.xyzmarket.mapper.UserMapper;
import com.example.xyzmarket.service.UserService;
import com.example.xyzmarket.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.json.JacksonJsonParser;

import java.time.LocalDateTime;
import java.util.Map;

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
        // 思路：通过 code 换取 openid，查询或创建用户，返回用户ID
        // 需要用到：httpClientUtil, wxConfig, userMapper

        String appid = wxConfig.getAppid();
        String secret = wxConfig.getSecret();
        String code = wxLoginDTO.getCode();

        String jsonString = httpClientUtil.code2Session(appid, secret, code);
        JacksonJsonParser parser = new JacksonJsonParser();
        Map<String, Object> map = parser.parseMap(jsonString);

        String openid = (String)map.get("openid");
        if (openid == null) throw new RuntimeException("微信登录失败");

        User user = userMapper.findByOpenid(openid);
        if (user == null) {
            User newUser = new User();
            newUser.setOpenid(openid);
            newUser.setNickname("<DEFAULT_NICKNAME>");
            newUser.setPhone(null);
            newUser.setAvatarUrl(null);
            newUser.setCreateTime(LocalDateTime.now());
            newUser.setUpdateTime(LocalDateTime.now());

            userMapper.insert(newUser);
            return newUser.getId();
        }
        return user.getId();
    }

    @Override
    public User getUserByOpenid(String openid) {
        // TODO: 根据 openid 查询用户

        return userMapper.findByOpenid(openid);
    }

}
