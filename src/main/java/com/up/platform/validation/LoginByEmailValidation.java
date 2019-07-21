package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginByEmailValidation {

    @NotEmpty(message = "用户邮箱必填")
    private String userEmail;

    @NotEmpty(message = "用户密码必填")
    private String userPassword;

}
