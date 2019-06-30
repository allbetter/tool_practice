package com.up.platform.exception;

import com.up.platform.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ToolerAuthorizeException extends RuntimeException {

    private Integer code;

    public ToolerAuthorizeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    // TODO
    public ToolerAuthorizeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
