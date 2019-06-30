package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    PARAM_ERROR(-1, "参数错误"),
    AUTH_NO_COOKIE(-2001, "无权限，请先登录"),
    AUTH_NO_REDIS(-2002, "无权限，请先登录"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
