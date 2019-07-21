package com.up.platform.exception;

import com.up.platform.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ToolAuthorizeException extends RuntimeException {

    private Integer code;

    public ToolAuthorizeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    // TODO
    public ToolAuthorizeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
