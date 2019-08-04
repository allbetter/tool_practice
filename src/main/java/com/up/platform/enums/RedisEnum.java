package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum  RedisEnum {
    USER_ID("UserId"),
    ;

    private String message;

    RedisEnum(String message) {
        this.message = message;
    }
}