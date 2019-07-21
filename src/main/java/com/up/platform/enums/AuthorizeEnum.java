package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum AuthorizeEnum {
    SUCCESS(0, "成功"),
    PASSWORD_ERROR(-2000, "用户密码错误"),
    AUTH_NO_COOKIE(-2001, "无权限，请先登录"),
    AUTH_NO_REDIS(-2002, "无权限，请先登录"),
    ;

    private Integer code;

    private String message;

    AuthorizeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
