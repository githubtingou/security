package com.ting.security.common;

import lombok.Getter;

/**
 * 返参枚举值
 *
 * @author ting
 * @version 1.0
 * @date 2021/9/30
 */
@Getter
public enum ResultEnum {
    SUCCESS(true, "200", "处理成功"),
    SERVER_ERROR("500", "服务处理异常");

    private boolean success;
    private String code;
    private String message;

    ResultEnum(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
