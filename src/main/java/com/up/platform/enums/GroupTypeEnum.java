package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum GroupTypeEnum {
    DATABASE(0,"数据库"),
    ;

    private Integer code;

    private String message;

    GroupTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
