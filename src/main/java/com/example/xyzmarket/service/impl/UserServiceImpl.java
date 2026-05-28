package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.common.ErrorCode;
import com.example.xyzmarket.config.WxConfig;
import com.example.xyzmarket.dto.WxLoginDTO;
import com.example.xyzmarket.entity.User;
import com.example.xyzmarket.exception.BusinessException;
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
        String appid = wxConfig.getAppid();
        String secret = wxConfig.getSecret();
        String code = wxLoginDTO.getCode();
        String nickname = wxLoginDTO.getNickname();
        String avatarUrl = wxLoginDTO.getAvatarUrl();

        String jsonString = httpClientUtil.code2Session(appid, secret, code);
        JacksonJsonParser parser = new JacksonJsonParser();
        Map<String, Object> map = parser.parseMap(jsonString);

        String openid = (String)map.get("openid");
        if (openid == null) throw new BusinessException(ErrorCode.SERVER_ERROR, "微信登录失败");

        User user = userMapper.findByOpenid(openid);
        if (user == null) {
            User newUser = new User();
            newUser.setOpenid(openid);
            newUser.setNickname(nickname);
            newUser.setAvatarUrl(avatarUrl);
            newUser.setCreateTime(LocalDateTime.now());
            newUser.setUpdateTime(LocalDateTime.now());

            userMapper.insert(newUser);
            return newUser.getId();
        }
        return user.getId();
    }

    @Override
    public User getUserByOpenid(String openid) {
        return userMapper.findByOpenid(openid);
    }

}
