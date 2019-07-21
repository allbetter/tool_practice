package com.up.platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DBManageDTO {
    private Integer id;

    @JsonProperty("name")
    private String dbName;

    @JsonProperty("host")
    private String dbHost;

    @JsonProperty("port")
    private Integer dbPort;

    @JsonProperty("user")
    private String dbUsername;

    @JsonProperty("password")
    private String dbPassword;

    @JsonProperty("schema")
    private String dbSchemaName;

    private Integer departmentId;

    private Integer userId;
}
