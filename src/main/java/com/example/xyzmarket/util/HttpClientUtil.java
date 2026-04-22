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
        // TODO: 实现调用微信 API
        // 接口地址：https://api.weixin.qq.com/sns/jscode2session
        // 参数：appid, secret, js_code, grant_type=authorization_code
        // 返回：{"openid":"xxx","session_key":"xxx"}
        return null;
    }

}
