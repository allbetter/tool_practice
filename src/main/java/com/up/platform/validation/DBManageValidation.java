package com.up.platform.validation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DBManageValidation {

    private Integer id;

    @NotEmpty(message = "数据库自定义名必填")
    private String dbName;

    @NotEmpty(message = "数据库地址必填")
    private String dbHost;

    @NotNull(message = "数据库端口号必填")
    private Integer dbPort;

    @NotEmpty(message = "数据库用户名必填")
    private String dbUsername;

    @NotEmpty(message = "数据库密码必填")
    private String dbPassword;

    @NotEmpty(message = "数据库库名必填")
    private String dbSchemaName;

    @NotBlank
    private Integer departmentId;

    private Integer userId;
}
