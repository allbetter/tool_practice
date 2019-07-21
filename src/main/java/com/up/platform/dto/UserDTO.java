package com.up.platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    @JsonProperty("name")
    private String userName;

    @JsonProperty("email")
    private String userEmail;

    @JsonProperty("password")
    private String userPassword;

    private Integer departmentId;
}
