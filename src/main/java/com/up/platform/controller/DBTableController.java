package com.up.platform.controller;

import com.up.platform.dto.ResultDTO;
import com.up.platform.enums.ResultEnum;
import com.up.platform.exception.ToolException;
import com.up.platform.service.DBTableService;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.IdValidation;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/table")
public class DBTableController {
    
    @Autowired
    private DBTableService dbTableService;

    @ApiOperation(value="同步数据库表结构信息")
    @PostMapping(path = "/syncDB",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO syncDB(@RequestBody @Valid IdValidation idValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建表失败，参数不正确，Id={}", idValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        dbTableService.syncTable(idValidation.getId());

        return ResultDTOUtil.success("");
    }
}
