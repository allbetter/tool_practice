package com.up.platform.controller;

import com.up.platform.dto.BooleanDTO;
import com.up.platform.dto.IdDTO;
import com.up.platform.dto.ResultDTO;
import com.up.platform.entity.GroupInfo;
import com.up.platform.enums.ResultEnum;
import com.up.platform.exception.ToolException;
import com.up.platform.service.GroupInfoService;
import com.up.platform.utils.BooleanDTOUtil;
import com.up.platform.utils.IdDTOUtil;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.IdValidation;
import com.up.platform.validation.GroupInfoValidation;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/group")
public class GroupInfoController {

    @Autowired
    private GroupInfoService groupInfoService;

    @ApiOperation(value="添加分组")
    @PostMapping(path = "/add",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO add(@RequestBody @Valid GroupInfoValidation groupInfoValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建分组失败，参数不正确，groupInfo={}", groupInfoValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupInfoValidation, groupInfo);
        groupInfoService.addGroupInfo(groupInfo);
        IdDTO idDTO = IdDTOUtil.setId(groupInfo.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="编辑分组")
    @PostMapping(path = "/edit",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO edit(@RequestBody @Valid GroupInfoValidation groupInfoValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("编辑分组失败，参数不正确，groupInfo={}", groupInfoValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupInfoValidation, groupInfo);
        groupInfoService.editGroupInfo(groupInfo);
        IdDTO idDTO = IdDTOUtil.setId(groupInfo.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="删除分组")
    @PostMapping(path = "/delete",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO delete(@RequestBody @Valid IdValidation idValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("删除分组失败，参数不正确，groupInfo={}", idValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Integer result = groupInfoService.deleteGroupInfo(idValidation.getId());
        Boolean status = (result == 1 ? true : false);
        BooleanDTO booleanDTO = BooleanDTOUtil.setStatus(status);
        return ResultDTOUtil.success(booleanDTO);
    }
}
