package com.example.xyzmarket.service.impl;

import com.example.xyzmarket.service.ImService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * IM 服务实现类
 */
@Service
public class ImServiceImpl implements ImService {

    @Value("${tencent.im.sdkAppId}")
    private Long sdkAppId;

    @Value("${tencent.im.secretKey}")
    private String secretKey;

    @Override
    public String generateUserSig(Long userId) {
        // TODO: 实现生成 UserSig
        // 提示：
        // 1. 使用腾讯云 IM SDK 的 TLSSigAPIv2 类
        // 2. 调用 genUserSig 方法生成签名
        //    TLSSigAPIv2 api = new TLSSigAPIv2(sdkAppId, secretKey);
        //    String userSig = api.genUserSig(String.valueOf(userId), 86400 * 180);
        // 3. 返回 userSig
        // 注意：需要在 pom.xml 中添加腾讯云 IM SDK 依赖

        return null;
    }

}
