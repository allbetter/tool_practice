package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DepartmentValidation {

    private Integer id;

    @NotEmpty(message = "部门名必填")
    private String departmentName;

}
