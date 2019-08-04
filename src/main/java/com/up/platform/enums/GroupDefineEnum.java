package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum GroupDefineEnum {
    DEFINE(0,"自定义"),
    UNDEFINE(1,"未分组"),
    ;

    private Integer code;

    private String message;

    GroupDefineEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
