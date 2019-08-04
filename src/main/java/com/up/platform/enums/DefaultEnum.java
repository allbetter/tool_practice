package com.up.platform.enums;

import lombok.Getter;

@Getter
public enum  DefaultEnum {
    REMARK(-1,""),
    SORT(0,"默认排序"),
    ;

    private Integer code;

    private String message;

    DefaultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
