package com.up.platform.controller;

import com.up.platform.dto.BooleanDTO;
import com.up.platform.dto.DepartmentDTO;
import com.up.platform.dto.IdDTO;
import com.up.platform.dto.ResultDTO;
import com.up.platform.entity.Department;
import com.up.platform.enums.ResultEnum;
import com.up.platform.exception.ToolException;
import com.up.platform.service.DepartmentService;
import com.up.platform.utils.BooleanDTOUtil;
import com.up.platform.utils.IdDTOUtil;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.DepartmentValidation;
import com.up.platform.validation.IdValidation;
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
@RequestMapping(value = "/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value="获取部门列表")
    @PostMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO list(@RequestBody ResultVO resultVO) {
        List<Department> departmentList = departmentService.findAllDepartment(resultVO.getPage());
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department department: departmentList) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            BeanUtils.copyProperties(department, departmentDTO);
            departmentDTOList.add(departmentDTO);
        }
        return ResultDTOUtil.success(departmentList);
    }

    @ApiOperation(value="添加部门")
    @PostMapping(path = "/add",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO add(@RequestBody @Valid DepartmentValidation departmentValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建部门失败，参数不正确，department={}", departmentValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentValidation, department);
        departmentService.addDepartment(department);
        IdDTO idDTO = IdDTOUtil.setId(department.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="编辑部门")
    @PostMapping(path = "/edit",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO edit(@RequestBody @Valid DepartmentValidation departmentValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("编辑部门失败，参数不正确，department={}", departmentValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentValidation, department);
        departmentService.editDepartment(department);
        IdDTO idDTO = IdDTOUtil.setId(department.getId());
        return ResultDTOUtil.success(idDTO);
    }

    // TODO 删除的department={}
    @ApiOperation(value="删除部门")
    @PostMapping(path = "/delete",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO delete(@RequestBody @Valid IdValidation idValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("删除部门失败，参数不正确，department={}", idValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Integer result = departmentService.deleteDepartment(idValidation.getId());
        Boolean status = (result == 1 ? true : false);
        BooleanDTO booleanDTO = BooleanDTOUtil.setStatus(status);
        return ResultDTOUtil.success(booleanDTO);
    }
}
