package com.up.platform.controller;


import com.up.platform.dto.BooleanDTO;
import com.up.platform.dto.IdDTO;
import com.up.platform.dto.UserDTO;
import com.up.platform.dto.ResultDTO;
import com.up.platform.entity.User;
import com.up.platform.enums.ResultEnum;
import com.up.platform.exception.ToolException;
import com.up.platform.service.UserService;
import com.up.platform.utils.BooleanDTOUtil;
import com.up.platform.utils.IdDTOUtil;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.DeleteByIdValidation;
import com.up.platform.validation.UserValidation;
import com.up.platform.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @ApiOperation(value = "获取用户列表")
    @PostMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO list(@RequestBody ResultVO resultVO) {
        List<User> userList = userService.findAllUser(resultVO.getPage());
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userList) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTOList.add(userDTO);
        }
        return ResultDTOUtil.success(userList);
    }

    @ApiOperation(value="添加用户")
    @PostMapping(path = "/add",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO add(@RequestBody @Valid UserValidation userValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建用户失败，参数不正确，user={}", userValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(userValidation, user);
        userService.addUser(user);
        IdDTO idDTO = IdDTOUtil.setId(user.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="编辑用户")
    @PostMapping(path = "/edit",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO edit(@RequestBody @Valid UserValidation userValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("编辑用户失败，参数不正确，user={}", userValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(userValidation, user);
        userService.editUser(user);
        IdDTO idDTO = IdDTOUtil.setId(user.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="删除用户")
    @PostMapping(path = "/delete",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO delete(@RequestBody @Valid DeleteByIdValidation deleteByIdValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("删除用户失败，参数不正确，user={}", deleteByIdValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Integer result = userService.deleteUser(deleteByIdValidation.getId());
        Boolean status = (result == 1 ? true : false);
        BooleanDTO booleanDTO = BooleanDTOUtil.setStatus(status);
        return ResultDTOUtil.success(booleanDTO);
    }
    
    
}
