package com.example.xyzmarket.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 微信登录 DTO
 */
@Data
public class WxLoginDTO {

    @NotBlank(message = "code 不能为空")
    private String code;

}
