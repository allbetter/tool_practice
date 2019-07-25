package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    STATUS_TRUE(0, "存在"),
    STATUS_FALSE(1, "不存在"),
    ;

    private Integer code;

    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
