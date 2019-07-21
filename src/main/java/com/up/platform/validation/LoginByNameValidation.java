package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginByNameValidation {

    @NotEmpty(message = "用户名必填")
    private String userName;

    @NotEmpty(message = "用户密码必填")
    private String userPassword;

}
