package com.example.xyzmarket.util;

import org.springframework.stereotype.Component;

/**
 * HTTP 客户端工具类
 * 用于调用微信 API
 */
@Component
public class HttpClientUtil {

    /**
     * 调用微信 code2session 接口
     *
     * @param appid 小程序 appid
     * @param secret 小程序 secret
     * @param code 微信登录 code
     * @return 包含 openid 的 JSON 字符串
     */
    public String code2Session(String appid, String secret, String code) {
        // TODO: 实现调用微信 code2session 接口
        // 思路：构造请求 URL，发送 HTTP GET 请求，返回 JSON 响应
        return null;
    }

}
