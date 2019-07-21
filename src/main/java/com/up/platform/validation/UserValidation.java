package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserValidation {

    private Integer id;

    @NotEmpty(message = "用户名必填")
    private String userName;

    @NotEmpty(message = "用户邮箱必填")
    private String userEmail;

    @NotEmpty(message = "用户密码必填")
    private String userPassword;

    // TODO "message": "HV000030: No validator could be found for constraint 'javax.validation.constraints.NotEmpty' validating type 'java.lang.Integer'.
    private Integer departmentId;
}
