package com.up.platform.exception;

import com.up.platform.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ToolException extends RuntimeException {

    private Integer code;

    public ToolException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public ToolException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
