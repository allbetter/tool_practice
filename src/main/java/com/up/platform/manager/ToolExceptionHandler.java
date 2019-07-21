package com.up.platform.manager;


import com.up.platform.dto.ResultDTO;
import com.up.platform.exception.ToolException;
import com.up.platform.exception.ToolAuthorizeException;
import com.up.platform.utils.ResultDTOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ToolExceptionHandler {

    @ExceptionHandler(value = ToolException.class)
    @ResponseBody
    public ResultDTO handlerToolException(ToolException e) {
        return ResultDTOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ToolAuthorizeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultDTO handlerAuthException(ToolAuthorizeException e) {
        return ResultDTOUtil.error(e.getCode(), e.getMessage());
    }

}
